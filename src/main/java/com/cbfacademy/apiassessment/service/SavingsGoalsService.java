package com.cbfacademy.apiassessment.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.cbfacademy.apiassessment.model.SavingsGoals;

// service makes call to the repository

@Service
public class SavingsGoalsService  {

   private static final String FILE_PATH = "C:\\Users\\Phoeb\\cbfacademy\\java-rest-api-assessment-Pmarie77\\src\\main\\resources\\SavingsGoals.json";
    private final Gson gson = new Gson();
    private final Type savingsGoalListType = new TypeToken<ArrayList<SavingsGoals>>() {}.getType();

    public SavingsGoals createGoal(SavingsGoals goal) {
        try {
            List<SavingsGoals> goals = findAllInternal();
            if (goal.getId() == null) {
                goal.setId(UUID.randomUUID()); // Set a new UUID if ID is null
            }
            goals.add(goal);
            writeToFile(goals);
            return goal;
        } catch (IOException e) {
            throw new RuntimeException("Could not save the goal", e);
        }
    }

    public List<SavingsGoals> getAllGoals() {
        try {
            return findAllInternal();
        } catch (IOException e) {
            throw new RuntimeException("Could not read goals", e);
        }
    }

    public Optional<SavingsGoals> getGoalById(UUID id) {
        return getAllGoals().stream().filter(goal -> goal.getId().equals(id)).findFirst();
    }

    public SavingsGoals updateGoal(UUID id, SavingsGoals updatedGoal) {
        try {
            List<SavingsGoals> goals = findAllInternal();
            goals.stream().filter(goal -> goal.getId().equals(id)).findFirst()
                    .ifPresent(goal -> {
                        goals.remove(goal);
                        updatedGoal.setId(id);
                        goals.add(updatedGoal);
                    });
            writeToFile(goals);
            return updatedGoal;
        } catch (IOException e) {
            throw new RuntimeException("Could not update the goal", e);
        }
    }

    public void deleteGoal(UUID id) {
        try {
            List<SavingsGoals> goals = findAllInternal();
            goals.removeIf(goal -> goal.getId().equals(id));
            writeToFile(goals);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete the goal", e);
        }
    }

    private List<SavingsGoals> findAllInternal() throws IOException {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, savingsGoalListType);
        }
    }

    private void writeToFile(List<SavingsGoals> goals) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(goals, writer);
        }
    }

  
    
}
