package com.github.jingou.util;

import com.alibaba.fastjson.JSON;
import com.github.jingou.common.exception.system500.SystemException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 渲染工具类
 *
 * @author fengshuonan
 */
public class RenderUtil {

    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new SystemException("结果写回失败");
        }
    }
}