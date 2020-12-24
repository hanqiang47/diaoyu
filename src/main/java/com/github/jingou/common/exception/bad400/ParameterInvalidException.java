package com.github.jingou.common.exception.bad400;


import com.github.jingou.common.exception.BaseException;

/**
 * @author Caedmon
 */
public class ParameterInvalidException extends BaseException {

    private static final Integer CODE = 400_002;

    public ParameterInvalidException(String message) {
        super(CODE, message);
    }

    public ParameterInvalidException(String message, Object... params) {
        super(CODE, message, params);
    }

}
