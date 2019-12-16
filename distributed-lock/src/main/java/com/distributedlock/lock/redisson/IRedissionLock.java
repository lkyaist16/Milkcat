package com.distributedlock.lock.redisson;

import java.util.concurrent.TimeUnit;

/**
 * redisson分布式锁
 * 注意：
 * 1. 可自定义锁过期时间、获取锁间隔时间、获取锁超时时间、获取锁重试次数
 * 2. 如未设置以上参数，则使用默认值
 */
public interface IRedissionLock {

    boolean tryLock(String key);

    boolean tryLock(String key, long waitTime);

    boolean tryLock(String key, long waitTime, long sleepTime);

    boolean tryLock(String key, long waitTime, long sleepTime, TimeUnit timeUnit);

    void unLock(String key);
}
