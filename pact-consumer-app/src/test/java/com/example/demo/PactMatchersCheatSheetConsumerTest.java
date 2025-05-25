package com.example.demo;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslJsonRootValue;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.dsl.Like;
import au.com.dius.pact.consumer.dsl.Term;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray.PactDslJsonArrayMinMax;
import au.com.dius.pact.consumer.dsl.PactDslJsonRootValue;

import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;

import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "provider-service", port = "8080")
public class PactMatchersCheatSheetConsumerTest {

    @Pact(consumer = "consumer-service")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        
        // Root JSON body with various fields showing different matchers
        PactDslJsonBody body = new PactDslJsonBody()
            // String type: any string value accepted
            .stringType("missionId", "abc-123")
            
            // String with regex pattern matcher (ISO 8601 UTC datetime)
            .stringMatcher("stateTime", 
                "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z$", 
                "2025-05-25T15:30:00Z")
            
            // String with exact value (no matcher, exact match)
            .stringValue("fixedValue", "EXACT_VALUE")
            
            // Integer type matcher (any integer accepted)
            .integerType("someInt", 42)
            
            // Decimal type matcher (any decimal accepted)
            .decimalType("someDecimal", 123.45)
            
            // Boolean type matcher (any boolean accepted)
            .booleanType("isActive", true)
            
            // Like matcher: expects same type as example (here a nested object)
            .like("nestedObject", new PactDslJsonBody()
                .stringType("nestedField", "nestedValue")
                .integerType("nestedInt", 10))
            
            // Each like matcher for arrays (array of strings)
            .eachLike("tags", "tag1", 3) // expect array of strings, min 3 items
            
            // Array of objects with eachLike
            .eachLike("missions", new PactDslJsonBody()
                .stringType("missionId", "m-001")
                .booleanType("completed", false), 2)
            
            // Term matcher: Regex with example for flexible string matching
            .stringMatcher("userId", "\\w{8,12}", "user12345")
            
            // Null value example: field may be null or a string
            .stringType("optionalField", "optionalValue")
            .nullValue("nullableField")
            ;
        
        return builder
            .given("test state")
            .uponReceiving("example request with all matchers")
            .path("/test/matchers")
            .method("POST")
            .body(body)
            .willRespondWith()
            .status(200)
            .toPact();
    }
    
    @Test
    @PactVerification
    public void testWithMatchers() {
        // Here, call your consumer code that triggers this request
        // For demo, you can just assert true or leave empty if unused
    }
}
