package com.example.springcloudstream.inbound;

import com.example.springcloudstream.outbound.SendMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReceiveService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MySink mySink;

    public interface MySink {
        @Input("input1")
        SubscribableChannel input1();

        @Input("input2")
        SubscribableChannel input2();

        @Input("input3")
        SubscribableChannel input3();

        @Input("input4")
        SubscribableChannel input4();

        @Input("input5")
        PollableMessageSource input5();
    }

    @StreamListener("input1")
    public void receiveInput1(String receiveMsg) {
        logger.info("input1 receive: " + receiveMsg);
    }

    @StreamListener("input2")
    public void receiveInput2(String msg) {
        logger.info("input2 receive: " + msg.toString());
    }

    @StreamListener("input3")
    public void receiveInput3(@Payload SendMsg msg) {
        logger.info("input3 receive: " + msg.toString());
    }

    @StreamListener("input4")
    public void receiveInput3(String msg) {
        logger.info("input4 receive: " + msg);
    }

}
