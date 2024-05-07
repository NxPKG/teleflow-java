package com.teleflow.api.notifications.responses;

import com.teleflow.api.notifications.pojos.NotificationStats;
import lombok.Data;

@Data
public class NotificationStatsResponse {
    private NotificationStats data;
}