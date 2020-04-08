package com.example.springbootrocketmq.queickstart;

import com.example.springbootrocketmq.contants.Const;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Date;

public class ProducerTransaction {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        TransactionMQProducer producer = new TransactionMQProducer("myTxProducerGroup");

        TransactionListenerImpl transactionListener = new TransactionListenerImpl();

        producer.setNamesrvAddr(Const.NAMESRV_ADDR);

        producer.setTransactionListener(transactionListener);

        producer.start();

        Message messageA = new Message();
        messageA.setTopic("TransactionTopic");
//        messageA.setTags("nativeApi-tagA");
//        messageA.setKeys("nativeApi-key");

        messageA.setBody((new Date() + "Hello RocketMQ").getBytes());

        SendResult sr = producer.sendMessageInTransaction(messageA, null);

        System.out.println(new Date() + "消息发出结果： " + sr);

        producer.shutdown();

    }



}
