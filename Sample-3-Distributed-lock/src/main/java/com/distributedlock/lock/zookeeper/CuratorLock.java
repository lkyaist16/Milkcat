package com.distributedlock.lock.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Description:
 * 可重入、分布式锁
 * Curator客户端支持多种重试策略，多次重试之后还不行的话才会删除临时节点
 * <p>
 * Version: 1.0.1
 * Update: 2017/5/25
 * Created by lkyi on 2019/8/1.
 */
@Component
public class CuratorLock {

    /**
     * zookeeper服务
     */
    @Value("${zookeeper.hosts}")
    private String zookeeperConnectionString;

    /**
     * 重试之间等待的初始时间，单位毫秒
     */
    @Value("${zookeeper.baseSleepTime}")
    private Integer baseSleepTimeMs;

    /**
     * 最多重试次数
     */
    @Value("${zookeeper.maxRetries}")
    private Integer maxRetries;

    /**
     * 锁根路径
     */
    @Value("${zookeeper.lockPath}")
    private String lockPath;

    /**
     * 等待获取锁最长时间，单位毫秒
     */
    @Value("${zookeeper.lockWaitTime}")
    private Long lockWaitTime;


    private static CuratorFramework client = null;

    @Bean
    public CuratorFramework initCuratorFramework() {
        if (client == null || !Objects.equals(client.getState(), CuratorFrameworkState.STARTED)) {
            client = CuratorFrameworkFactory
                    .builder()
                    .retryPolicy(new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries))
                    .connectString(zookeeperConnectionString)
                    .build();
            client.start();
        }
        return client;
    }

    public String getLockPath() {
        return lockPath;
    }


    public Long getLockWaitTime() {
        return lockWaitTime;
    }

}