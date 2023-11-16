package com.cbfacademy.apiassessment.controller;


import java.util.List;

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
    
    @Autowired
    private SavingsGoalsService savingsGoalsService;


    // Create a new goal
    @PostMapping
   public ResponseEntity<SavingsGoals> createGoal(@RequestBody SavingsGoals goal) {
    SavingsGoals createdGoal = savingsGoalsService.createGoal(goal);
    return ResponseEntity.ok(createdGoal);
}

    // Retrieve an existing goal by ID
    @GetMapping("/{id}")
    public ResponseEntity<SavingsGoals> getGoalById(@PathVariable UUID id) {
        return savingsGoalsService.getGoalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Retrieve all exisiting goals
    @GetMapping
    public ResponseEntity<List<SavingsGoals>> getAllGoals() {
        List<SavingsGoals> goals = savingsGoalsService.getAllGoals();
        return ResponseEntity.ok(goals);
    }

    // Update an existing goal by id
   
    @PutMapping("/{id}")
    public ResponseEntity<SavingsGoals> updateGoal(@PathVariable UUID id, @RequestBody SavingsGoals updatedGoal) {
        SavingsGoals updated = savingsGoalsService.updateGoal(id, updatedGoal);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Delete goal
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable UUID id) {
        savingsGoalsService.deleteGoal(id);
        return ResponseEntity.ok().build();
    }


}
