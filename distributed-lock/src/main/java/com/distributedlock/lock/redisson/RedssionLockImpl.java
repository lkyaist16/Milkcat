package com.distributedlock.lock.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedssionLockImpl implements IRedissionLock{

    @Autowired
    private RedissonClient redissonClient;

    private static final long waitTime = 50000L;

    private static final long sleepTime = 10000L;

    private static TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    @Override
    public boolean tryLock(String key) {
        return this.tryLock(key, waitTime, sleepTime, timeUnit);
    }

    @Override
    public boolean tryLock(String key, long waitTime) {
        return this.tryLock(key, waitTime, sleepTime, timeUnit);
    }

    @Override
    public boolean tryLock(String key, long waitTime, long sleepTime) {
        return this.tryLock(key, waitTime, sleepTime, timeUnit);
    }

    @Override
    public boolean tryLock(String key, long waitTime, long sleepTime, TimeUnit timeUnit) {
        RLock rLock = this.getRLock(key);
        try {
            return rLock.tryLock(waitTime, sleepTime, timeUnit);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void unLock(String key) {
        this.getRLock(key).unlock();
    }

    private RLock getRLock(String objectName) {
        RLock rLock = redissonClient.getLock(objectName);
        return rLock;
    }

}
