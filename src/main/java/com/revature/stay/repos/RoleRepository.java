package com.revature.stay.repos;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Since we no longer have a separate Role entity (roles are stored directly in the User entity),
 * this repository provides utility methods for working with role values.
 */
@Repository
public class RoleRepository {

    // Available roles in the system
    private static final List<String> AVAILABLE_ROLES = Arrays.asList("USER", "OWNER");

    /**
     * Checks if a role exists in the system
     *
     * @param role the role to check
     * @return true if the role exists, false otherwise
     */
    public boolean roleExists(String role) {
        return AVAILABLE_ROLES.contains(role);
    }

    /**
     * Get all available roles in the system
     *
     * @return list of all roles
     */
    public List<String> getAllRoles() {
        return AVAILABLE_ROLES;
    }

    /**
     * Validates if the provided role is valid
     *
     * @param role the role to validate
     * @throws IllegalArgumentException if the role is invalid
     */
    public void validateRole(String role) {
        if (!roleExists(role)) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}