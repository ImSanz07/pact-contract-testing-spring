package com.example.demo.contracttests;

import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.CancelMissionCommand;
import com.example.demo.CancelMissionCommandRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit5.MessageTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;

@ExtendWith({MockitoExtension.class,SpringExtension.class })
@Provider("provider-service")
@PactFolder("D:\\Documents\\Coding\\CONTRACT TESTING\\Pact_Spring\\pact-provider-app\\src\\test\\pacts")

public class CancelMissionMessageContractTest_2 {

	@Mock
	private CancelMissionCommandRequestService cancelMissionCommandRequestService;

//	@InjectMocks
//	private CancelMissionMessageProvider messageProvider;
//	
	// This is the method Pact will call to get the message during verification
	@PactVerifyProvider("cancel mission command")
	public String provideCancelMissionCommandMessage() throws JsonProcessingException {
		
		CancelMissionCommand response =  cancelMissionCommandRequestService.getCancelMissionCommand();
		ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(response);
	}
	

	@BeforeEach
    void setup(PactVerificationContext context) {
		System.setProperty("pact.verifier.publishResults", "true");

		context.setTarget(new MessageTestTarget());
    	when(cancelMissionCommandRequestService.getCancelMissionCommand()).thenReturn(
    			new CancelMissionCommand(
    	                "abc123",
    	                Instant.parse("2025-05-25T12:00:00Z"),
    	                "user-42"
    	            )
    		);
    }
	
	@TestTemplate
	@ExtendWith(PactVerificationSpringProvider.class) 
    void pactVerificationTest(PactVerificationContext context) {
        context.verifyInteraction();
    }
	
	@State("mission with ID exists") 
	void testMissionId() {
		
	}
	

}
