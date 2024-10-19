package com.lz.hexagonal.arch.domain.infra.exceptions;

import com.lz.hexagonal.arch.domain.infra.HexagonalErrorCodes;

public class HexagonalNotFoundException extends HexagonalException {

    public HexagonalNotFoundException(final String message, final Throwable throwable, final HexagonalErrorCodes hexagonalErrorCodes) {
        super(message, throwable, hexagonalErrorCodes);
    }
}
