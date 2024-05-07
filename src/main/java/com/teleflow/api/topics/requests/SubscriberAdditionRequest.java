package com.teleflow.api.topics.requests;

import com.teleflow.common.contracts.IRequest;
import java.util.List;
import lombok.Data;

@Data
public class SubscriberAdditionRequest implements IRequest {

    private List<String> subscribers;
}
