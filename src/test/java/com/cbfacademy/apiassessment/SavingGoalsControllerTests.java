package com.cbfacademy.apiassessment;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URL;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

//import com.cbfacademy.apiassessment.controller.SavingsGoalsController;

import com.cbfacademy.apiassessment.model.SavingsGoals;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SavingGoalsControllerTests {
    

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() throws Exception{
        this.base = new URL ("http://localhost:" + port + "/api/savingsgoals" );
    }

    @Test
    public void testCreateSavingsGoals(){
        SavingsGoals savingsGoals = new SavingsGoals("Drone", 50.0, 250.0);
        ResponseEntity<?> response = restTemplate.postForEntity("/api/savingsgoals", savingsGoals, SavingsGoals.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        
        
    }
    
    @Test
    public void testGetSavingsGoal_NotFound() {
    UUID randomId = UUID.randomUUID();
    ResponseEntity<SavingsGoals> response = restTemplate.getForEntity(base.toString() + "/" + randomId, SavingsGoals.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

   
    public void testCreateSavingsGoal_InvalidInput() {

        String invalidRequestBody = "{\"goalName\": \"New Goal\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(invalidRequestBody, headers);
    
        ResponseEntity<SavingsGoals> response = restTemplate.postForEntity(base.toString(), entity, SavingsGoals.class);
    
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


}
