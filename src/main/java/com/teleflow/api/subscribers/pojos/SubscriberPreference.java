package com.teleflow.api.subscribers.pojos;

import com.teleflow.api.common.Template;
import lombok.Data;

@Data
public class SubscriberPreference {
    private Template template;
    private Preference preference;
}