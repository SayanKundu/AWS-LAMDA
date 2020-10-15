package com.sayan.aws.handler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

public class OrderHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent,Object>
{

}
