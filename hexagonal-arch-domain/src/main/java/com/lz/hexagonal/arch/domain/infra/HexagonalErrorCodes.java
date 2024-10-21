package com.lz.hexagonal.arch.domain.infra;

public enum HexagonalErrorCodes {
    HA_ERROR_INVALID_ARGUMENTS(0),
    HA_ERROR_CONSTRAINT_VIOLATION(1),
    HA_ERROR_INTERNAL_ERROR(2);

    private final int value;

    HexagonalErrorCodes(final int code) {
        this.value = code;
    }

    public int getValue() {
        return value;
    }
}
