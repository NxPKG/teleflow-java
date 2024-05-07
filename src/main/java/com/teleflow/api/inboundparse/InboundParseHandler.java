package com.teleflow.api.inboundparse;

import java.io.IOException;

import com.teleflow.api.inboundparse.responses.ValidateMxRecordResponse;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import retrofit2.Response;

public class InboundParseHandler {

    private final RestHandler restHandler;

    private final InboundParseApi inboundParseApi;

    public InboundParseHandler(RestHandler restHandler) {
		this.restHandler = restHandler;
		this.inboundParseApi = restHandler.buildRetrofit().create(InboundParseApi.class);
	}

	public ValidateMxRecordResponse validateMxRecordSetupForInboundParse() throws IOException, TeleflowNetworkException {
		Response<ValidateMxRecordResponse> response = inboundParseApi.validateMxRecordSetupForInboundParse().execute();
        return restHandler.extractResponse(response);
    }
}