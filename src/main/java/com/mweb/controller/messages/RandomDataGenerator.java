///***********************************************************************
// *
// * @file         RandomDataGenerator.java
// *
// * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
// * @author       JetQin 
// * @create-time  2014年10月3日
// *
// *
// ***********************************************************************/
//package com.mweb.controller.messages;
//
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.messaging.core.MessageSendingOperations;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * @author jet
// *
// */
//@Component
//public class RandomDataGenerator implements
//    ApplicationListener<BrokerAvailabilityEvent> {
//
//    private final MessageSendingOperations<String> messagingTemplate;
//
//    @Autowired
//    public RandomDataGenerator(
//        final MessageSendingOperations<String> messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @Override
//    public void onApplicationEvent(final BrokerAvailabilityEvent event) {
//    }
//
//    @MessageMapping("/random")
//    @Scheduled(fixedDelay = 1000)
//    public void sendDataUpdates() {
//
//    	System.out.println("[send message]");
//        this.messagingTemplate.convertAndSend(
//            "/topic/data", new Random().nextInt(100));
//
//    }
//}
