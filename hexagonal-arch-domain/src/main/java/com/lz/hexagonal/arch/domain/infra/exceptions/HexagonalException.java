package com.lz.hexagonal.arch.domain.infra.exceptions;

import com.lz.hexagonal.arch.domain.infra.HexagonalErrorCodes;

public class HexagonalException extends Exception {
    private final HexagonalErrorCodes oSErrorCode;
    private Throwable throwable;
    private HexagonalErrorCodes hexagonalErrorCodes;

    public HexagonalException(
            final String message, final Throwable throwable, final HexagonalErrorCodes hexagonalErrorCodes) {
        super(message);
        this.oSErrorCode = null;
        this.throwable = throwable;
        this.hexagonalErrorCodes = hexagonalErrorCodes;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HexagonalErrorCodes getHexagonalErrorCodes() {
        return hexagonalErrorCodes;
    }
}
