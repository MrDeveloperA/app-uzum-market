package org.example.enums;

public enum Role {
    OWNER,
    ADMIN,
    USER;

    public static Role getRole(String role) {
        for (Role value : values()) {
            if (value.name().equals(role)) {
                return value;
            }
        }
        return null;
    }
}
