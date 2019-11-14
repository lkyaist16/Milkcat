package com.example.springcloudstream;

import com.example.springcloudstream.inbound.ReceiveService;
import com.example.springcloudstream.outbound.SendMsg;
import com.example.springcloudstream.outbound.SenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test1")
public class SpringcloudStreamApplicationTests {

    @Autowired
    SenderService senderService;

    @Autowired
    ReceiveService.MySink mySink;

    @Test
    public void contextLoads() throws Exception {

    }

    @Test
    public void sendStringMsg() {

        senderService.send(new Date() + "String msg from output1");
    }

    @Test
    public void sendObjectMsg() {

        SendMsg sendMsg = new SendMsg();
        sendMsg.setContent(new Date() + "Object msg from output2");
        sendMsg.setMsgId(1);
        senderService.sendObject(sendMsg, "Tag-output2");
    }

    @Test
    public void sendDelayMsg() {

        senderService.sendDelayMsg(new Date().toString() + "Delay msg from output2", "Tag-output2","2");

    }

    @Test
    public void pullMsg() throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            senderService.send(new Date() + "msg-" + i);
            Thread.sleep(1000);
        }

        while (true) {
            mySink.input5().poll(message -> {
                String payload = (String) message.getPayload();
                System.out.println("pull msg: " + payload);
            }, new ParameterizedTypeReference<String>() {
            });
            Thread.sleep(1000);
        }
    }

    @Test
    public void sendTransactionMsg() {

          senderService.sendTransactionalMsg("transactional-msg1", 1);

          senderService.sendTransactionalMsg("transactional-msg2", 2);

          senderService.sendTransactionalMsg("transactional-msg3", 3);

    }

}
