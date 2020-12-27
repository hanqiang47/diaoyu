package com.github.jingou.handler;

import com.github.jingou.common.Result;
import com.github.jingou.common.exception.bad400.BadRequestException;
import com.github.jingou.common.exception.bad400.ParameterInvalidException;
import com.github.jingou.common.exception.business200.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @author caedmon
 */
@ControllerAdvice
public class BaseExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result handleBusinessException(BusinessException exception) {
        log.debug("BaseExceptionHandler 拦截到 BusinessException 异常", exception);
        return new Result(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ParameterInvalidException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleMethodArgumentNotValidException(Exception exception) {
        log.debug("BaseExceptionHandler 拦截到 MethodArgumentNotValidException 异常", exception);
        String msg = "参数错误";
        MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fe : fieldErrors) {
            msg = fe.getField() + " 参数格式错误（" + fe.getDefaultMessage() + "）";
            break;
        }
        return new Result(HttpStatus.BAD_REQUEST.value(), msg);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, BadRequestException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleBadRequestException(Exception exception) {
        log.info("BaseExceptionHandler 拦截到 BAD_REQUEST 异常", exception);
        return new Result(HttpStatus.BAD_REQUEST.value(), "请求错误");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result handleForbiddenException(AccessDeniedException exception) {
        log.debug("BaseExceptionHandler 拦截到 FORBIDDEN 异常", exception);
        return new Result(HttpStatus.FORBIDDEN.value(), "您没有权限访问该资源");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleException(Exception exception) {
        log.error("BaseExceptionHandler 拦截到 INTERNAL_SERVER_ERROR 异常", exception);
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal server error");
    }

}