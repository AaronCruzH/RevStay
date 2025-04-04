package com.revature.stay.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a role in the system.
 * It's not an entity itself, but a value object for roles in the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    public static final String USER = "USER";
    public static final String OWNER = "OWNER";
    public static final String ADMIN = "ADMIN";

    private String name;

    public static boolean isValidRole(String role) {
        return USER.equals(role) || OWNER.equals(role) || ADMIN.equals(role);
    }
}