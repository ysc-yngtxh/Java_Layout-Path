package com.example.enums;

import lombok.Getter;

@Getter
public enum DisplayModeEnum {

    SIMPLE("simple"),
    DETAILED("detailed");

    private final String mode;

    DisplayModeEnum(String mode) {
        this.mode = mode;
    }
}
