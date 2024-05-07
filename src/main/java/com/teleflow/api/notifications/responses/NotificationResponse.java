package com.teleflow.api.notifications.responses;

import com.teleflow.api.notifications.pojos.Notification;
import lombok.Data;

@Data
public class NotificationResponse {
    private Notification data;
}