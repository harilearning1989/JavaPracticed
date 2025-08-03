package com.emp;

public record EmployeeRecord(Long id,
                             Long empId,
                             String name,
                             String gender,
                             String email,
                             String fatherName,
                             String dateOfBirth,
                             String dateOfJoining,
                             Long salary,
                             String placeName,
                             String county,
                             String city,
                             String state,
                             String zip,
                             String region,
                             int departmentId,
                             String departmentName) {
}
