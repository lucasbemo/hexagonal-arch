package com.lz.hexagonal.arch.domain.infra.exceptions;

import com.lz.hexagonal.arch.domain.infra.ErrorCodes;

public class HexagonalException extends Exception {
    private final ErrorCodes oSErrorCode;
    private Throwable throwable;

    public HexagonalException(final String message, final Throwable throwable) {
        super(message);
        this.oSErrorCode = null;
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
