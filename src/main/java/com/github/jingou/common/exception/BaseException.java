package com.github.jingou.common.exception;

/**
 * @author Caedmon
 */
public class BaseException extends RuntimeException {
    private Integer code;

    protected BaseException(BaseExceptionMessage baseExceptionMessage) {
        super(baseExceptionMessage.getMessage());
        this.code = baseExceptionMessage.getCode();
    }

    protected BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    protected BaseException(Integer code, String message, Object... params) {
        super(String.format(message, params));
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
