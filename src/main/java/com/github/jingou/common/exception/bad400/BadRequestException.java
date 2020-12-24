package com.github.jingou.common.exception.bad400;

import com.github.jingou.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Caedmon
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    private static final Integer CODE = 400_001;

    public BadRequestException(String message) {
        super(CODE, message);
    }

    public BadRequestException(String message, Object... params) {
        super(CODE, String.format(message, params));
    }
}
