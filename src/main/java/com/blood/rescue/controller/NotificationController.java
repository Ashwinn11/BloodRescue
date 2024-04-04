package com.blood.rescue.controller;

import com.blood.rescue.service.NotificationService;

public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
