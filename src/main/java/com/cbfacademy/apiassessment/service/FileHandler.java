package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.model.SavingsGoals;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public abstract class FileHandler {

        protected final Gson gson = new Gson();

         // Reads JSON file and converts into a list of SavingsGoals objects

        protected List<SavingsGoals> readFromFile(String filePath, Type type) throws IOException {

                try (FileReader reader = new FileReader(filePath)) {
                        return gson.fromJson(reader, type);
                }
        }

        // Writes list of SavingsGoals objects to JSON file format

        protected void writeToFile(String filePath, List<SavingsGoals> data) throws IOException {

                try (FileWriter writer = new FileWriter(filePath)) {
                        gson.toJson(data, writer);
                }
        }



}

