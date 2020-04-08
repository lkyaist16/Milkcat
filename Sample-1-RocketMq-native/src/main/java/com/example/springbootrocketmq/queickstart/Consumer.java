package com.example.springbootrocketmq.queickstart;

import com.example.springbootrocketmq.contants.Const;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.Date;
import java.util.List;

public class Consumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_quick_producer_name");

        consumer.setNamesrvAddr(Const.NAMESRV_ADDR);

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        consumer.subscribe("nativeApi-topic", "nativeApi-tagA");

//        consumer.subscribe("nativeApi-topic", "*");

//        consumer.subscribe("nativeApi-topic", "nativeApi-tagA||nativeApi-tagB");

//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                MessageExt me = list.get(0);
//                try {
//                    String topic = me.getTopic();
//                    String tags = me.getTags();
//                    String keys = me.getKeys();
//
//                    String msgBody = new String(me.getBody(), RemotingHelper.DEFAULT_CHARSET);
//                    System.out.println(new Date() + "topic: " + topic + ", tags: " + tags + ", keys: " + keys + ", msg: " + msgBody);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    int reconsumerTimes = me.getReconsumeTimes();
//                    System.out.println("reconsumerTimes: " + reconsumerTimes);
//                    if(reconsumerTimes == 3) {
//                        //    记录日志。。。
//                        //    做补偿处理
//                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                    }
//                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });

        consumer.registerMessageListener(new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                MessageExt me = msgs.get(0);
                try {
                    String topic = me.getTopic();
                    String tags = me.getTags();
                    String keys = me.getKeys();

                    String msgBody = new String(me.getBody(), RemotingHelper.DEFAULT_CHARSET);
                    System.out.println(new Date() + "topic: " + topic + ", tags: " + tags + ", keys: " + keys + ", msg: " + msgBody);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.println("consumer start... ");
    }

}
