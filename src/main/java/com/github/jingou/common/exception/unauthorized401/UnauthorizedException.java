package com.github.jingou.common.exception.unauthorized401;


import com.github.jingou.common.exception.BaseException;

/**
 * @author Caedmon
 */
public class UnauthorizedException extends BaseException {

    private static final int CODE = 401_001;

    public UnauthorizedException(String message) {
        super(CODE, message);
    }

    public UnauthorizedException(String message, Object... params) {
        super(CODE, message, params);
    }

}
