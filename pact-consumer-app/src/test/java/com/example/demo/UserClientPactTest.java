package com.example.demo;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.demo.models.User;
import com.example.demo.services.UserClient;


import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;

import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "UserService",port ="1234",pactVersion = PactSpecVersion.V3)
public class UserClientPactTest {
	
	
	@Pact(consumer = "UserApp")
	public RequestResponsePact createPact(PactDslWithProvider builder) {
		return builder
				.given("user with ID 1 exists")
				.uponReceiving("a request for user 1")
				.path("/user/1")
				.method("GET")
				.willRespondWith()
				.status(200)
				.headers(Map.of("Content-Type","application/json"))
				.body("{\"id\": 1, \"name\": \"Alice\", \"email\": \"alice@example.com\"}")
                .toPact();
	}
	
	


	
	@Test
    void testGetUser() {
        UserClient client = new UserClient();
        User user = client.getUser(1, "http://localhost:1234");
        assertEquals(1, user.getId());
        assertEquals("Alice", user.getName());
        assertEquals("alice@example.com", user.getEmail());
    }

}
