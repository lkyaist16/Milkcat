package com.distributedlock.lock.redis;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Description:
 * 不可重入、分布式锁
 * <p>
 * Version: 1.0.1
 * Update: 2017/5/25
 * Created by lkyi on 2019/8/1.
 */
@Service
public class RedisLockImpl implements IRedisLock {

    /**
     * KEY前缀
     */
    private static final String ROOT_KEY = "REDIS_LOCK_";

    /**
     * 上锁成功后返回值
     */
    private static final String LOCK_SUCCESS = "OK";

    /**
     * SetNX方法中NX的含义
     */
    private static final String SET_IF_NOT_EXIST = "NX";

    /**
     * SetNX方法中PX的含义
     */
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 默认锁过期时间
     */
    private static final long expiryMillis = 20000L;

    /**
     * 默认获取锁的超时时间
     */
    private static final long timeOutMillis = 10000L;

    /**
     * 默认获取锁的间隔时间
     */
    private long sleepMillis = 500L;

    /**
     * 默认获取锁的尝试次数
     */
    private static final Integer retriyCount = Integer.MAX_VALUE;

    /**
     * 线程变量
     */
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * lua脚本：key相等时判断value值是否相等，相等的话则删除
     */
    private static final String LUA_UNLOCK_SCRIPT = "if redis.call(\"get\",KEYS[1]) == ARGV[1] " +
            "then " +
            "return redis.call(\"del\",KEYS[1]) " +
            "else " +
            "return 0 " +
            "end";


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public RedisLockImpl() {
    }


    @Override
    public boolean lock(String key) {
        return setLock(key, expiryMillis, sleepMillis, timeOutMillis, retriyCount);
    }

    @Override
    public boolean lock(String key, long expire) {
        return setLock(key, expire, sleepMillis, timeOutMillis, retriyCount);
    }

    @Override
    public boolean lock(String key, long expire, long sleepTime) {
        return setLock(key, expire, sleepTime, timeOutMillis, retriyCount);
    }

    @Override
    public boolean lock(String key, long expire, long sleepTime, long timeOut) {
        return setLock(key, expire, sleepTime, timeOut, retriyCount);
    }

    @Override
    public boolean lock(String key, long expire, long timeOut, long sleepTime, Integer retries) {
        return setLock(key, expire, sleepTime, timeOut, retries);
    }

    /**
     * 使用Jedis客户端执行原子指令
     *
     * @param key
     * @param value
     * @param expiry
     * @return
     */
    public boolean setNX(String key, String value, long expiry) {
        String result = stringRedisTemplate.execute((RedisCallback<String>) connection -> {
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            return commands.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expiry);
        });

        return LOCK_SUCCESS.equals(result);
    }

    /**
     * 获取锁
     *
     * @param key       锁名称
     * @param expire    过期时间
     * @param sleepTime 重试等待时间
     * @param timeOut   最长重试时间
     * @param retries   重试次数
     * @return
     */
    @Override
    public boolean setLock(String key, long expire, long sleepTime, long timeOut, Integer retries) {
        if ("".equals(key)) {
            return false;
        }
        key = ROOT_KEY + key;
        String value = UUID.randomUUID().toString();
        try {
            long startTime = System.currentTimeMillis();
            while ((!this.setNX(key, value, expire) && retries-- > 0)) {
                Thread.sleep(sleepTime);
                if ((System.currentTimeMillis() - startTime) > timeOut) {
                    return false;
                }
            }
            threadLocal.set(value);


            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 释放锁
     *
     * @param key
     * @return
     */
    @Override
    public boolean reLease(String key) {
        if ("".equals(key)) {
            return false;
        }
        List<String> keys = new ArrayList<>(1);
        List<String> args = new ArrayList<>(1);

        key = ROOT_KEY + key;
        keys.add(key);
        args.add(threadLocal.get());
        threadLocal.remove();

        try {
            return delate(keys, args);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 调用Lua脚本删除key
     *
     * @param keys
     * @param args
     * @return
     */
    public boolean delate(List<String> keys, List<String> args) {
        Object result = stringRedisTemplate.execute((RedisCallback<Object>) connection -> {
            Object nativeConnection = connection.getNativeConnection();

            if (nativeConnection instanceof Jedis) {
                return ((Jedis) nativeConnection).eval(LUA_UNLOCK_SCRIPT, keys, args);
            } else if (nativeConnection instanceof JedisCluster) {
                return ((JedisCluster) nativeConnection).eval(LUA_UNLOCK_SCRIPT, keys, args);
            }
            return 0L;
        });

        return result != null && Long.parseLong(result.toString()) > 0;
    }
}
