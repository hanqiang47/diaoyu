package com.github.jingou.common.exception.forbidden403;


import com.github.jingou.common.exception.BaseException;

/**
 * @author Caedmon
 */
public class ForbiddenException extends BaseException {

    private static final int CODE = 403_001;

    public ForbiddenException(String message) {
        super(CODE, message);
    }

    public ForbiddenException(String message, Object... params) {
        super(CODE, message, params);
    }

}
