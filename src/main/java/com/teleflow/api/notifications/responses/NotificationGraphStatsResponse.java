package com.teleflow.api.notifications.responses;

import com.teleflow.api.notifications.pojos.NotificationGraphStats;
import lombok.Data;

import java.util.List;

@Data
public class NotificationGraphStatsResponse {
    private List<NotificationGraphStats> data;
}