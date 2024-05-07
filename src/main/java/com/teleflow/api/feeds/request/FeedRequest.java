package com.teleflow.api.feeds.request;

import com.teleflow.common.contracts.IRequest;
import lombok.Data;

@Data
public class FeedRequest implements IRequest {
    private String name;

}
