package org.my.edu.libraryclean.resolver;

import org.my.edu.libraryclean.model.status.StatusApp;

public class StatusQueryResolver {
    public StatusApp status() {
        try {
            boolean isActive = true;
            return new StatusApp(isActive);
        } catch (Exception e) {
            return new StatusApp(false);
        }
    }
}
