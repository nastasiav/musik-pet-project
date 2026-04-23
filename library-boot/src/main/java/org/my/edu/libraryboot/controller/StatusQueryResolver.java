package org.my.edu.libraryboot.controller;

import org.my.edu.libraryboot.model.status.StatusApp;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StatusQueryResolver {
    @QueryMapping
    public StatusApp status() {
        try {
            boolean isActive = true;
            return new StatusApp(isActive);
        } catch (Exception e) {
            return new StatusApp(false);
        }
    }
}
