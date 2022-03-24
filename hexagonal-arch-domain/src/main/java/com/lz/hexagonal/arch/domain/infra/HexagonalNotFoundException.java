package com.lz.hexagonal.arch.domain.infra;

public class HexagonalNotFoundException extends Exception {
    private final ErrorCodes oSErrorCode;

    public HexagonalNotFoundException(final String message) {
        super(message);
        oSErrorCode = null;
    }

    public HexagonalNotFoundException(final String message, final ErrorCodes oSErrorCode) {
        super(message);
        this.oSErrorCode = oSErrorCode;
    }
}
