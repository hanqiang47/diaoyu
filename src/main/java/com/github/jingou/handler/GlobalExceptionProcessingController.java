package com.github.jingou.handler;

import com.github.jingou.common.Result;
import com.github.jingou.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author caedmon
 */
@Slf4j
@RestController
public class GlobalExceptionProcessingController extends AbstractErrorController {

    private static final String ERROR_PATH = "/error";

    @Autowired
    public GlobalExceptionProcessingController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping("/error")
    public Result getErrorPath(HttpServletRequest request, HttpServletResponse response) {
        Throwable cause = getCause(request);

        if (cause instanceof BaseException) {
            BaseException e = (BaseException) cause;
            Integer code = e.getCode();
            response.setStatus(code / 1000);
            return new Result(code, e.getMessage());
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new Result(HttpServletResponse.SC_BAD_REQUEST, "bad request");
        }

    }

    private Throwable getCause(HttpServletRequest request) {
        Throwable error = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
        }
        return error;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
