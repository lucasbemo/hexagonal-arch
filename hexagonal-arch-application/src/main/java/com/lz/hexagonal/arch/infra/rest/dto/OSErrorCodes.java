package com.lz.hexagonal.arch.infra.rest.dto;

public enum OSErrorCodes {
    OS_ERROR_INVALID_ARGUMENTS(0);

    private int value;

    OSErrorCodes(int code) {
        this.value = code;
    }
}
