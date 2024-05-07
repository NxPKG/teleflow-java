package com.teleflow.api.subscribers.responses;

import com.teleflow.api.subscribers.pojos.SubscriberPreference;
import lombok.Data;

@Data
public class SingleSubscriberPrefResponse {
    private SubscriberPreference data;
}