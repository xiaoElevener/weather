package com.xiao.weather.common.exception;

public class OptLockException extends DaoException {

    private static final long serialVersionUID = 6057799968454610015L;

    public OptLockException() {
        super();
    }

    public OptLockException(String message) {
        super(message);
    }

    public OptLockException(Throwable cause) {
        super(cause);
    }

    public OptLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
