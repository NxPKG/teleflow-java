package com.teleflow.api.subscribers.requests;

import com.teleflow.api.subscribers.pojos.Mark;
import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class MarkSubscriberFeedAsRequest implements IRequest {
    private Object messageId;
    private Mark mark;
}