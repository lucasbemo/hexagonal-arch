package com.lz.hexagonal.arch.domain.infra.exceptions;

import com.lz.hexagonal.arch.domain.infra.HexagonalErrorCodes;

public class HexagonalResourceConflictException extends HexagonalException {
    public HexagonalResourceConflictException(final String message, final Throwable throwable, final HexagonalErrorCodes hexagonalErrorCodes) {
        super(message, throwable, hexagonalErrorCodes);
    }
}
