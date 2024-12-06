package com.backend.oopbackend.controller;

import com.backend.oopbackend.model.Configuration;
import com.backend.oopbackend.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    // injecting ConfigurationService class to connect end points
    @Autowired
    private ConfigurationService configurationService;

    // connecting API for save config inputs
    @PostMapping("/save")
    public String saveConfiguration(@RequestBody Configuration configuration) {
        configurationService.saveConfiguration(configuration);
        return "Saved";
    }

    // connecting API for load configuration
    @GetMapping("/load")
    public Configuration loadConfiguration() throws IOException {
        return configurationService.loadConfiguration();
    }

}
