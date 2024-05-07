package com.teleflow.api.notifications.responses;

import com.teleflow.api.notifications.pojos.Notification;
import lombok.Data;

import java.util.List;

@Data
public class NotificationsResponse {
    private Long page;
    private Long totalCount;
    private Long pageSize;
    private List<Notification> data;
}