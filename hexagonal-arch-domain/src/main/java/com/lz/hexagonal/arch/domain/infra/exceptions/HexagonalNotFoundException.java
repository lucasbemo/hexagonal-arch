package com.lz.hexagonal.arch.domain.infra.exceptions;

public class HexagonalNotFoundException extends HexagonalException {

    public HexagonalNotFoundException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
