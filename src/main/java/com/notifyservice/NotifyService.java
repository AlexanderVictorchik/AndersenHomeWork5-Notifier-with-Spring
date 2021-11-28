package com.notifyservice;

public class NotifyService {

    public static void sendNotify(Long chatID, String text) {
        TaskNotifyService taskNotifyService =
                new TaskNotifyServiceService()
                        .getPort(com.notifyservice.TaskNotifyService.class);
        taskNotifyService.sendNotification(chatID, text);
    }
}
