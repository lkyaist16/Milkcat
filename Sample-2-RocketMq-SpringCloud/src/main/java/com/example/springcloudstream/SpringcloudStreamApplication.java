package com.example.springcloudstream;

import com.example.springcloudstream.inbound.ReceiveService;
import com.example.springcloudstream.outbound.SenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Date;

@SpringBootApplication
@EnableBinding({SenderService.MySource.class, ReceiveService.MySink.class})
public class SpringcloudStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudStreamApplication.class, args);
    }
//    @Bean
//    public CustomerRunner customerRunner2() {
//        return new CustomerRunner("produceMsg");
//    }
//    @Bean
//    public CustomerRunner customerRunner1() {
//        return new CustomerRunner("pullMsg");
//    }

    public static class CustomerRunner implements CommandLineRunner {

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        private final String bindingName;

        public CustomerRunner(String bindingName) {
            this.bindingName = bindingName;
        }

        @Autowired
        private ReceiveService.MySink mySink;

        @Autowired
        private SenderService senderService;

        @Override
        public void run(String... args) throws Exception {
            if(bindingName.equals("pullMsg")) {
                while (true) {
                    Thread.sleep(2_000);
                    boolean result = mySink.input5().poll(message -> {
                        String payload = (String) message.getPayload();
                        logger.info("pull msg: " + payload);
                    }, new ParameterizedTypeReference<String>() {
                    });

                    if(result) {
                        logger.info("Processed a message");
                    } else {
                        logger.info("Nothing to do ");
                    }
                }

            }

            if(bindingName.equals("produceMsg")) {
                for (int i = 0; i < 10; i++) {
                    logger.info("send msg: index-" + i);
                    senderService.send("index-" + i + " sendTime: " + new Date());
                    Thread.sleep(1000);
                }
            }

        }
    }
}
