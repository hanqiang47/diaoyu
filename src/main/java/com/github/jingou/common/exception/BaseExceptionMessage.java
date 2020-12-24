package com.github.jingou.common.exception;

/**
 * @author Caedmon
 */
public interface BaseExceptionMessage {
    /**
     * 获取 code
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取消息
     *
     * @return
     */
    String getMessage();
}
