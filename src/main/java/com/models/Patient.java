package com.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Patient(
        int patientId,
        String firstName,
        String lastName
) {
}
