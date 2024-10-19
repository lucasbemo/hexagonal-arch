package com.lz.hexagonal.arch.domain.infra.exceptions;

public class HexagonalInternalException extends HexagonalException {

    public HexagonalInternalException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
