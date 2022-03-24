package com.lz.hexagonal.arch.domain.infra;

public enum ErrorCodes {
    OS_ERROR_INVALID_ARGUMENTS(0);

    private final int value;

    ErrorCodes(final int code) {
        this.value = code;
    }

    public int getValue() {
        return value;
    }
}
