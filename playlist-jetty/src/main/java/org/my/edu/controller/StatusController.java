package org.my.edu.controller;

import org.my.edu.model.status.StatusApp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/")
    public String getStatusTets() {
        return "true";
    }

    @GetMapping(value = "/status", produces = "application/json")
    public StatusApp getStatus() {
        return new StatusApp(true);
    }
}
