# **分布式锁(Distributed Lock)**

[TOC]

### **说明：**

解决分布式应用中出现的并发问题

### **目前实现方式：**

| 实现方式  |                     性质                      |                 原理                  |                             优点                             |                             缺陷                             |
| :-------: | :-------------------------------------------: | :-----------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|   redis   |              不可重入、分布式锁               |   基于setnx(set if not exists) 指令   |                    redis基于内存，轻量级                     |  方法处理超过设置的超时时间时，锁会被释放；其他请求可获取锁  |
| redisson  |       redisson封装的可重入分布式锁RLock       | 与redis分布式锁原理差异不大（待补充） | 在redis上封装了一层netty，提高了效率，并且改善了简单封装的redis lock的缺陷 |                         -（待补充）                          |
| zookeeper | curator封装的可重入分布式锁 InterProcessMutex |         基于zookeeper临时节点         | 较安全、可靠、实现简单、方法处理超时也不会释放锁，解决了redis lock的缺陷 | 重量级，需要维护zookeeper服务器；业务处理过程中，如果与zookeeper服务器连接会话超时或断开，临时节点会被删除，锁会被释放（出现概率较低） |

### **使用方法（基于Springboot项目）：**

#### **redis lock**

##### 接入要求：

1. 在springboot项目中添加redis相关配置（可参考config文件夹中的RedisConfiguration以及resources下配置文件）
2. 将相关实现类和接口引入（lock-redis文件夹）

##### exmple：

```java
@Component
public class demo {
@Autowired
private IRedisLock redisLock;
public static void main(String[] args){    
      try {
          if (!redisLock.lock(key)) {
              //get lock error
          }
          // do your business

      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          try {
              if (redisLock.reLease(key)) {
                  
              }
          } catch (Exception e) {
              //release lock error
          }
      }
   }
}
```

#### **zookeeper lock**

##### 接入要求：

1. 在springboot项目中添加zookeeper相关配置（可参考resources下配置文件）
2. 将相关实现类引入（lock-zookeeper文件夹）

##### exmple：

```java
public class demo {

   public static void main(String[] args) {
   // init lock
   InterProcessMutex lock = new InterProcessMutex(client, curatorLock.getLockPath());
   try {  
          // 在"/root/test"下创建临时序列节点，申请越早的编号越小；尝试获取锁，将临时序列节点排序，判断自己是不是首位，是则获取锁
          if (!lock.acquire(100, TimeUnit.SECONDS)) {
              // get lock timeout
          } 
          // do your business...
          
          } catch (Exception e) {
              e.printStackTrace();
          }
           finally {
             // release lock 
            try{
               lock.release();              
              } catch (Exception e) {
                // release lock error
               
           }
   }

}
```

#### redisson Rlock

##### 接入要求：

1. 在springboot项目中添加redis相关配置（可参考config文件夹中的RedisConfiguration以及resources下配置文件）

2. 可选配置参考资料：

[https://github.com/redisson/redisson/wiki/%E7%9B%AE%E5%BD%95](https://github.com/redisson/redisson/wiki/目录)

##### exmple: 

```java
@Component
public class demo {
@Autowired
private IRedissionLock redssionLock;
public static void main(String[] args){    
      try {
          if (!redssionLock.lock(key)) {
              //get lock error
          }
          // do your business

      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          try {
              redssionLock.unLock(key);
          } catch (Exception e) {
              //release lock error
          }
      }
   }
}
```