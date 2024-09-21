package com.nvt.pojo.Enum;

public enum CourseStatus {
    ACTIVE,
    INACTIVE,
    ARCHIVED;
    
    @Override
    public String toString() {
        return name(); // Đảm bảo rằng `name()` trả về tên của enum
    }
}