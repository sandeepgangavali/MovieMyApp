package com.moviecruiser.userprofile.queue;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.moviecruiser.userprofile.model.Register;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${moviecruiser.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${moviecruiser.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(Register registerData) {
		rabbitTemplate.convertAndSend(exchange, routingkey, registerData);
		System.out.println("Sent msg = " + registerData);
	    
	}

}
