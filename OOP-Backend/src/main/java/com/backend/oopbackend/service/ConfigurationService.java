package com.backend.oopbackend.service;

import com.backend.oopbackend.model.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ConfigurationService {
    private static final String filePath = "backendconfig.json";

    // save configuration to json file
    public void saveConfiguration(Configuration configuration){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter writer = new FileWriter(filePath)){
            gson.toJson(configuration, writer);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Load configuration from a JSON file
    public Configuration loadConfiguration() throws IOException {
        Gson gson = new Gson();
        File file = new File(filePath);

        try (FileReader reader = new FileReader(file)){
            return gson.fromJson(reader, Configuration.class);
        }
    }
}
