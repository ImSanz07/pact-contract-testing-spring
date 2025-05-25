package com.example.demo;

import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
public class CancelMissionCommandRequestService {

    // This method simulates fetching or creating a CancelMissionCommand message
    public CancelMissionCommand getCancelMissionCommand() {
        // In real usage, you might fetch mission info from DB or other services
    	System.out.println("--------------CANCELMISSIONCOMMANDREQUEST SERVICE CALLED--------------");
        
        // For now, just return a stubbed example with dummy values
        return new CancelMissionCommand(
                "example-mission-id-001",
                Instant.now(),
                "user-123"
        );
    }
}
