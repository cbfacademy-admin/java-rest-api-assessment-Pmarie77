package com.cbfacademy.apiassessment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbfacademy.apiassessment.model.SavingsGoals;
import com.cbfacademy.apiassessment.service.SavingsGoalsService;

@RestController
@RequestMapping("api/savingsgoals")
public class SavingsGoalsController {
    
   private final List<SavingsGoals> sGoals = new ArrayList<>();

    
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SavingsGoals> createGoal(@RequestBody SavingsGoals goal) {
       goal.setId(null);
       sGoals.add(goal);

       return ResponseEntity.ok(goal);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getGoalById(@PathVariable UUID id) {
        for (SavingsGoals goal:sGoals){
            if(goal.getId().equals(id)){
                return ResponseEntity.ok(goal);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping
    public List<SavingsGoals> getAllGoals() {
        return sGoals;
    }

    // Update an existing goal by id
    @PutMapping("/{id}")
    public SavingsGoals updateGoal(@PathVariable UUID id, @RequestBody SavingsGoals updatedGoal) {
        for (SavingsGoals goal:sGoals) {
            if (goal.getId().equals(id)){
                goal.setGoalName(updatedGoal.getGoalName());
                goal.setCurrentAmount(updatedGoal.getCurrentAmount());
                goal.setTargetAmount(updatedGoal.getTargetAmount());

                return goal;

            }
            
        }
       return null; 
       
    }

    @DeleteMapping("/{id}")
    public void deleteGoal(@PathVariable UUID id) {
        sGoals.removeIf(goal -> goal.getId().equals(id));
      
    }

}
