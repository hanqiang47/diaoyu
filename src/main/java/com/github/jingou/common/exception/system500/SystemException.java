package com.github.jingou.common.exception.system500;

import com.github.jingou.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Caedmon
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemException extends BaseException {

    private static final int CODE = 500_001;

    public SystemException(String message) {
        super(CODE, message);
    }

    public SystemException(String message, Object... params) {
        super(CODE, message, params);
    }

}
