package com.github.jingou.common.exception.business200;

import com.github.jingou.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Caedmon
 */
@ResponseStatus(HttpStatus.OK)
public class BusinessException extends BaseException {

    private static final Integer CODE = 200_001;

    public BusinessException(String message) {
        super(CODE, message);
    }

    public BusinessException(String message, Object... params) {
        super(CODE, message, params);
    }

}
