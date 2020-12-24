package com.github.jingou.common.kits;



import com.github.jingou.common.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author caedmon
 */
public class HttpResponseKit {

    public static void sendJsonResponse(HttpServletResponse response, Result result) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(ObjectMapperKit.toJson(result));
        writer.flush();
        writer.close();
    }

}
