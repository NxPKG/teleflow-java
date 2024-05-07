package com.teleflow.api.executivedetails.responses;

import com.teleflow.api.notifications.pojos.ExecutionDetails;
import lombok.Data;

import java.util.List;

@Data
public class ExecutiveDetailsResponse {
    private List<ExecutionDetails> data;
}