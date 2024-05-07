package com.teleflow.api.inboundparse;

import java.io.IOException;

import com.google.gson.Gson;

import com.teleflow.api.inboundparse.responses.ValidateMxRecordResponse;
import com.teleflow.api.inboundparse.responses.ValidateMxRecordResponseData;
import com.teleflow.common.base.TeleflowConfig;
import com.teleflow.common.rest.TeleflowNetworkException;
import com.teleflow.common.rest.RestHandler;
import junit.framework.TestCase;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class InboundParseHandlerTest extends TestCase {

    private InboundParseHandler inboundParseHandler;

    private MockWebServer mockWebServer;

    @Override
    protected void setUp() {
    	mockWebServer = new MockWebServer();
        TeleflowConfig teleflowConfig = new TeleflowConfig("1234");
        teleflowConfig.setBaseUrl(mockWebServer.url("").toString());
        RestHandler restHandler = new RestHandler(teleflowConfig);
        inboundParseHandler = new InboundParseHandler(restHandler);
    }

    public void test_validateMxRecordSetupForInboundParse() throws IOException, TeleflowNetworkException, InterruptedException {
        ValidateMxRecordResponse validateMxRecordResponse = new ValidateMxRecordResponse();
        ValidateMxRecordResponseData responseData = new ValidateMxRecordResponseData();
        responseData.setMxRecordConfigured(true);
        validateMxRecordResponse.setData(responseData);

        Gson gson = new Gson();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(validateMxRecordResponse)));

        ValidateMxRecordResponse response = inboundParseHandler.validateMxRecordSetupForInboundParse();
        RecordedRequest request = mockWebServer.takeRequest();
        
        assertEquals("/inbound-parse/mx/status", request.getPath());
        assertEquals("GET", request.getMethod());
        assertNotNull(response);
        assertEquals(gson.toJson(validateMxRecordResponse), gson.toJson(response));
    }
}