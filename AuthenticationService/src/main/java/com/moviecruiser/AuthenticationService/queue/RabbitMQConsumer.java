package com.moviecruiser.AuthenticationService.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.moviecruiser.AuthenticationService.models.AuthenticationRequest;
import com.moviecruiser.AuthenticationService.models.Register;
import com.moviecruiser.AuthenticationService.services.MyUserDetailsService;

@Component
public class RabbitMQConsumer {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	

	@RabbitListener(queues = "${moviecruiser.rabbitmq.queue}")
	public void onMessage(Register register) {
		System.out.println("Entered ListnerMessage");
		System.out.println(register.toString());
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		  authenticationRequest.setId(register.getId()); //
		  authenticationRequest.setUsername(register.getUsername()); //
		  authenticationRequest.setPassword(register.getPassword()); //
		  AuthenticationRequest saveduser =myUserDetailsService.registerUser(authenticationRequest);
		        
	}

	
	

}
