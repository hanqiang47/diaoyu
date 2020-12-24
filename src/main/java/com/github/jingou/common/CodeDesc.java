package com.github.jingou.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author caedmon
 */
public interface CodeDesc {

    int getCode();

    @JsonValue
    String getDesc();

}
