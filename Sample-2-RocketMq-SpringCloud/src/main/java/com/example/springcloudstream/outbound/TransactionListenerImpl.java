//package com.example.springcloudstream.outbound;
//
//import org.apache.rocketmq.client.producer.LocalTransactionState;
//import org.apache.rocketmq.common.message.MessageConst;
//import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
//import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
//import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.messaging.Message;
//
//import java.util.concurrent.ConcurrentHashMap;
//
//@RocketMQTransactionListener(txProducerGroup = "myTxProducerGroup", corePoolSize = 5, maximumPoolSize = 10)
//public class TransactionListenerImpl implements RocketMQLocalTransactionListener {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();
//
//    @Override
//    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
//
//        Object flag = msg.getHeaders().get("flag");
//
//        if("1".equals(flag)) {
//            Integer status = 1;
//            localTrans.put(msg.getHeaders().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID).toString(), status);
//        }
//
//        else if("2".equals(flag)) {
//            Integer status = 2;
//            localTrans.put(msg.getHeaders().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID).toString(), status);
//        }
//
//        else if("3".equals(flag)) {
//            Integer status = 3;
//            localTrans.put(msg.getHeaders().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID).toString(), status);
//        }
//
//        return RocketMQLocalTransactionState.UNKNOWN;
//    }
//
//    @Override
//    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
//        Integer status = localTrans.get(msg.getHeaders().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID).toString());
//
//        if(status != null) {
//            switch (status) {
//                case 1:
//                    logger.info(msg.getHeaders().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID).toString() + " excuter: " + new String((byte[]) msg.getPayload()) + " unknown");
//                    return RocketMQLocalTransactionState.UNKNOWN;
//                case 2:
//                    logger.info(msg.getHeaders().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID).toString() + " excuter: " + new String((byte[]) msg.getPayload()) + " commit");
//                    return RocketMQLocalTransactionState.COMMIT;
//                case 3:
//                    logger.info(msg.getHeaders().get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID).toString() + " excuter: " + new String((byte[]) msg.getPayload()) + " rollback");
//                    return RocketMQLocalTransactionState.ROLLBACK;
//            }
//        }
//
//        return RocketMQLocalTransactionState.COMMIT;
//    }
//}
