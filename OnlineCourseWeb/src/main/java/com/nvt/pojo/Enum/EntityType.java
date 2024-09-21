package com.nvt.pojo.Enum;

public enum EntityType {
    LECTURE("Lecture"),
    VIDEO("Video"),
    INFORMATION("Information");

    private final String displayName;

    EntityType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
