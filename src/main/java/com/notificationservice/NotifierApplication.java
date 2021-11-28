package com.notificationservice;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.time.LocalTime;

public class NotifierApplication {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(Config.class);

        LocalTime sendTime = LocalTime.of(19, 30, 00);

        NotificationSender notificationSender = (NotificationSender)
                applicationContext.getBean("");

//        while (true) {
//            if (LocalTime.now().equals(sendTime)) {
//                sendNotification();
//            }
//        }
        notificationSender.sendNotification();
    }
}