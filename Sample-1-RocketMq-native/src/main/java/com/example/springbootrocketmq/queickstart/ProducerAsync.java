package com.example.springbootrocketmq.queickstart;

import com.example.springbootrocketmq.contants.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Date;

public class ProducerAsync {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("test_quick_producer_nameA");

        producer.setNamesrvAddr(Const.NAMESRV_ADDR);

        producer.start();

        producer.setRetryTimesWhenSendAsyncFailed(0);

        Message message = new Message();
        message.setTopic("nativeApi-topic");
        message.setTags("nativeApi-tag");
        message.setKeys("nativeApi-key");

        message.setBody((new Date() + " async message ").getBytes());

        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(new Date() + "消息发出结果： " + sendResult);

            }

            @Override
            public void onException(Throwable e) {
                System.out.println(new Date() + "消息发送失败: " + e.getMessage());
            }
        });
//        producer.send(message);
        Thread.sleep(5000);
        producer.shutdown();

    }
}
