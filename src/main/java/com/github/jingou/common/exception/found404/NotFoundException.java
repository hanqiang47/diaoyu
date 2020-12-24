package com.github.jingou.common.exception.found404;


import com.github.jingou.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Caedmon
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

    private static final Integer CODE = 404_001;

    public NotFoundException(String message) {
        super(CODE, message);
    }

    public NotFoundException(String message, Object... params) {
        super(CODE, message, params);
    }
}
