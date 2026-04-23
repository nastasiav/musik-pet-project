package org.my.edy.mainapi.controller;

import org.my.edy.mainapi.model.StatusApp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @GetMapping("/status")
    public ResponseEntity<StatusApp> getStatus() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StatusApp(true));
    }
}
