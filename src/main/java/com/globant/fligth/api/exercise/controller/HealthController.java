package com.globant.fligth.api.exercise.controller;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class HealthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthController.class);

    @GetMapping(path = "isHealthy", produces = TEXT_PLAIN_VALUE)
    public String isHealthy() {
        LOGGER.info("Doing health check...");
        return "HEALTHY";
    }
}
