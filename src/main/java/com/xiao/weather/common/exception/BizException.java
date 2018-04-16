package com.xiao.weather.common.exception;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = 7935408141184859889L;

    private ExceptionAction action = ExceptionAction.SHOW;

    public ExceptionAction getAction() {
        return action;
    }

    public void setAction(ExceptionAction action) {
        this.action = action;
    }

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
