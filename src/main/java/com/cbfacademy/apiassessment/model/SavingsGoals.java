package com.cbfacademy.apiassessment.model;


import java.util.UUID;

public class SavingsGoals {

    private UUID id;
    private String goalName;
    private Double currentAmount;
    private Double targetAmount;
   

   public SavingsGoals(String goalName, double currentAmount, double targetAmount){
    this.id = UUID.randomUUID();
    this.goalName = goalName;
    this.currentAmount = currentAmount;
    this.targetAmount = targetAmount;
   }
   
    
    public UUID getId (){

        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    } 

     
    public String getGoalName (){

        return this.goalName;
    }

    public void setGoalName (String goalName){
        this.goalName = goalName;
    }

    public Double getCurrentAmount (){

        return this.currentAmount;
    }

    public void setCurrentAmount( Double currentAmount){
        this.currentAmount = currentAmount;
    }

    public Double getTargetAmount() {
        return targetAmount;
   
    }

    public void setTargetAmount(Double targetAmount){
        this.targetAmount = targetAmount;
    }

   
      
}
