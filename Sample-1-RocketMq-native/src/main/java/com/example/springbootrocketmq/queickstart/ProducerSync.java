package com.example.springbootrocketmq.queickstart;

import com.example.springbootrocketmq.contants.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Date;

public class ProducerSync {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("test_quick_producer_name");

        producer.setNamesrvAddr(Const.NAMESRV_ADDR);

        producer.start();

        Message messageA = new Message();
        messageA.setTopic("nativeApi-topic");
        messageA.setTags("nativeApi-tagA");
        messageA.setKeys("nativeApi-key");

        messageA.setBody((new Date() + "Hello RocketMQ").getBytes());

        SendResult sr = producer.send(messageA);

        System.out.println(new Date() + "消息发出结果： " + sr);

        producer.shutdown();

    }
}
