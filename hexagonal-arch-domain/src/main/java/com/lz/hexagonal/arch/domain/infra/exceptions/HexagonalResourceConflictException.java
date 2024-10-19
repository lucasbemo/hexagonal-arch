package com.lz.hexagonal.arch.domain.infra.exceptions;

public class HexagonalResourceConflictException extends HexagonalException {
    public HexagonalResourceConflictException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
