package com.xiao.weather.common.exception;

public class DaoException extends BizException {

    private static final long serialVersionUID = -431077833164047654L;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
