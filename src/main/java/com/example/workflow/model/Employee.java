package com.example.workflow.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * The type Employee.
 */
@Getter @Setter @NoArgsConstructor @ToString
public class Employee implements Serializable {
    private static final long serialVersionUID = 300002228479017363L;

    private String id;
    private String firstName;
    private String lastName;

}
