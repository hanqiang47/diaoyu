package com.github.jingou.common.mybatis.ext.handler;


import com.github.jingou.common.CodeDesc;

/**
 * @author caedmon
 */
public class CodeDescEnumConvertor {

    public static <E extends Enum<?> & CodeDesc> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
