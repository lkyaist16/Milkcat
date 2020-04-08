package com.distributedlock.lock.redis;

/**
 * redis分布式锁
 * 注意：
 * 1. 可自定义锁过期时间、获取锁间隔时间、获取锁超时时间、获取锁重试次数
 * 2. 如未设置以上参数，则使用默认值
 */
public interface IRedisLock {

    boolean lock(String key);

    boolean lock(String key, long expire);

    boolean lock(String key, long expire, long sleepTime);

    boolean lock(String key, long expire, long sleepTime, long timeOut);

    boolean lock(String key, long expire, long timeOut, long sleepTime, Integer retries);

    boolean setLock(String key, long expire, long sleepTime, long timeOut, Integer retries);

    boolean reLease(String key);
}
