package com.cbfacademy.apiassessment.service;


import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.cbfacademy.apiassessment.model.SavingsGoals;


@Service
public class SavingsGoalsService extends FileHandler {


   private static final Logger logger = LoggerFactory.getLogger(SavingsGoalsService.class);

   private static final String FILE_PATH = "C:\\Users\\Phoeb\\cbfacademy\\java-rest-api-assessment-Pmarie77\\src\\main\\resources\\SavingsGoals.json";
   
    private final Type savingsGoalListType = new TypeToken<ArrayList<SavingsGoals>>() {}.getType();

    public SavingsGoals createGoal(SavingsGoals goal) {
        try {
            List<SavingsGoals> goals = readFromFile(FILE_PATH, savingsGoalListType);
            if (goal.getId() == null) {
                goal.setId(UUID.randomUUID()); // Set a new UUID if ID is null
            }
            goals.add(goal);
            writeToFile(FILE_PATH,goals);
            return goal;
        } catch (IOException e) {
            logger.error("Could not save the goal", e);
            return null; 
        }
    }

    public List<SavingsGoals> getAllGoals() {
        try {
            return readFromFile(FILE_PATH, savingsGoalListType);
        } catch (IOException e) {
            logger.error("Could not read goals", e);
            return new ArrayList<>(); 
        }
    }


    public Optional<SavingsGoals> getGoalById(UUID id) {
        try {
        return getAllGoals().stream().filter(goal -> goal.getId().equals(id)).findFirst();

        }  catch (Exception e) { // Catching a broader exception if getAllGoals() throws something other than IOException
            logger.error("Error retrieving goal by ID", e);
            return Optional.empty(); 
        }
    }


    public SavingsGoals updateGoal(UUID id, SavingsGoals updatedGoal) {
        try {
            List<SavingsGoals> goals = readFromFile(FILE_PATH, savingsGoalListType);
            goals.stream().filter(goal -> goal.getId().equals(id)).findFirst()
                    .ifPresent(goal -> {
                        goals.remove(goal);
                        updatedGoal.setId(id);
                        goals.add(updatedGoal);
                    });
            writeToFile(FILE_PATH,goals);
            return updatedGoal;
        } catch (IOException e) {
            logger.error("Could not update the goal", e);
            return null; 
        }
        }

    public Optional<SavingsGoals> searchGoalByName(String goalName) {
        try {
            List<SavingsGoals> goals = readFromFile(FILE_PATH, savingsGoalListType);
            for (SavingsGoals goal : goals) {
                if (goal.getGoalName().equalsIgnoreCase(goalName)) {
                    return Optional.of(goal);
                }
            }
        } catch (IOException e) {
            logger.error("Error reading the goals file", e);
           
        }
        return Optional.empty();
    }
    

    public void deleteGoal(UUID id) {
        try {
            List<SavingsGoals> goals = readFromFile(FILE_PATH, savingsGoalListType);
            goals.removeIf(goal -> goal.getId().equals(id));
            writeToFile(FILE_PATH,goals);
        } catch (IOException e) {
           
        }

   }
}
