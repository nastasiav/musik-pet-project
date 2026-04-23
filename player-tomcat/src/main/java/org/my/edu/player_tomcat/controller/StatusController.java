package org.my.edu.player_tomcat.controller;

import org.my.edu.player_tomcat.model.status.StatusApp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public ResponseEntity<StatusApp> getStatus() {
        return ResponseEntity
                .ok()
                .body(new StatusApp(true));
    }

}
