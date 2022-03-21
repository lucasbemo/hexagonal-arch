package com.lz.hexagonal.arch.domain.infra;

public class HexagonalNotFoundException extends Exception {
    public HexagonalNotFoundException(final String message) {
        super(message);
    }
}
