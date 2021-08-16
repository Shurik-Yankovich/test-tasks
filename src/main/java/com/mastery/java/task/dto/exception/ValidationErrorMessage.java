package com.mastery.java.task.dto.exception;

import java.util.List;

public class ValidationErrorMessage {

    private final String message;
    private final List<Violation> violations;
    private final String description;

    public ValidationErrorMessage(String message, List<Violation> violations, String description) {
        this.message = message;
        this.violations = violations;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public String getDescription() {
        return description;
    }
}
