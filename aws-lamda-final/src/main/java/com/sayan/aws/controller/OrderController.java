package com.sayan.aws.controller;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.sayan.aws.domain.Order;
import com.sayan.aws.repository.OrderDao;

@RestController
public class OrderController {

	@Autowired
	private OrderDao orderDao;
	
	@Bean
	public Supplier<List<Order>> orders(){
		return ()->orderDao.buildOrders();
	}
	
	/*
	 * @Bean public Function<String,List<Order>> findOrderByName(){ return
	 * (input)->orderDao.buildOrders().stream()
	 * .filter((order)->order.getName().equals(input)).collect(Collectors.toList());
	 * }
	 */
	
	@Bean
	public Function<APIGatewayProxyRequestEvent,List<Order>> findOrderByName(){
		return (requestEvent)->orderDao.buildOrders().stream()
				.filter((order)->order.getName().equals(requestEvent.getQueryStringParameters().get("orderName")))
				.collect(Collectors.toList());
	}
}
