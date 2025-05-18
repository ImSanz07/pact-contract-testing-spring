package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.User;

@Service
public class UserClient {
	private final RestTemplate restTemplate = new RestTemplate();
	public User getUser(int id,String baseUrl) {
		return restTemplate.getForObject(baseUrl+"/user/"+id, User.class);
	}

}
