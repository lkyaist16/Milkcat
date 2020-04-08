package com.example.springcloudstream.outbound;

import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class SenderService {

    public interface MySource {
        @Output("output1")
        MessageChannel outPut1();

        @Output("output2")
        MessageChannel outPut2();

        @Output("output4")
        MessageChannel outPut4();

        @Output("output5")
        MessageChannel outPut5();

    }

    @Autowired
    private MySource mySource;

    public void send(String msg) {
        mySource.outPut1().send(MessageBuilder.withPayload(msg).build());
    }

    public <T> void sendObject(T msg, String tag) {
        Message message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_TAGS, tag)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        mySource.outPut2().send(message);
    }

    //"1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"
    public <T> void sendDelayMsg(T msg, String tag, String delayTimeLevel) {
        Message message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, delayTimeLevel)
                .setHeader(MessageConst.PROPERTY_TAGS, tag)
                .setHeader(MessageConst.PROPERTY_KEYS, "test-delay-key")
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        mySource.outPut2().send(message);
    }

    public <T> void sendTransactionalMsg(T msg, int flag) {
        MessageBuilder builder = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        builder.setHeader("flag", String.valueOf(flag));
        builder.setHeader(RocketMQHeaders.TAGS, "binder");
        Message message = builder.build();
        mySource.outPut4().send(message);
    }
}
