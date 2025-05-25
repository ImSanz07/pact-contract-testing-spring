package com.example.demo.contracttests;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.CancelMissionCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit5.MessageTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;

@Provider("provider-service")
@PactFolder("D:\\Documents\\Coding\\CONTRACT TESTING\\Pact_Spring\\pact-provider-app\\src\\test\\pacts")
@ExtendWith(SpringExtension.class)  
public class CancelMissionMessageContractTest {
	/*
	 * First try at making Provider
	 * 
	 */
	@TestTemplate
	@ExtendWith(PactVerificationSpringProvider.class)
    void verifyCancelMissionMessage(PactVerificationContext context) {
        context.verifyInteraction();
    }
	
	
   
    void pactVerificationTestTemplate(PactVerificationContext context) {
      context.verifyInteraction();
    }
	
	@PactVerifyProvider("cancel mission command")
    public String provideCancelMissionCommandMessage(){
		try {
			CancelMissionCommand cmd = new CancelMissionCommand(
		            "abc123",
		            Instant.parse("2025-05-25T12:00:00Z"),
		            "user-42"
		        );

		        // Serialize it to JSON
		        ObjectMapper mapper = new ObjectMapper();
		        mapper.registerModule(new JavaTimeModule());
		        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		        return mapper.writeValueAsString(cmd);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        
    }
	
	@BeforeEach
    void setup(PactVerificationContext context) {
        context.setTarget(new MessageTestTarget());
    }
	
	@State("mission with ID exists") 
	void testMissionId() {
		
	}
	
	

}
