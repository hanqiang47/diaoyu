package com.github.jingou.filter;

import com.github.jingou.common.Result;
import com.github.jingou.common.exception.forbidden403.ForbiddenException;
import com.github.jingou.common.exception.unauthorized401.UnauthorizedException;
import com.github.jingou.common.properties.JwtProperties;
import com.github.jingou.util.JwtTokenUtil;
import com.github.jingou.util.RenderUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 对客户端请求的jwt token验证过滤器
 */
public class AuthFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());
    private final List<String> WHITE_LIST= new ArrayList<>();
    private final AntPathMatcher MATCHER = new AntPathMatcher();
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    {
        WHITE_LIST.add("/swagger-ui/**");
        WHITE_LIST.add("/swagger-resources/**");
        WHITE_LIST.add("/v2/api-docs");
        WHITE_LIST.add("/**/*.html");
        WHITE_LIST.add("/**/*.css");
        WHITE_LIST.add("/**/*.js");
        WHITE_LIST.add("/**/*.png");
        WHITE_LIST.add("/");
    };


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("OPTIONS")){
            return;
        }
        boolean isMatch;
        String uri = request.getRequestURI();
        for (String pattern : WHITE_LIST) {
            isMatch = MATCHER.match(pattern, uri);
            if (isMatch) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        String authToken = request.getHeader(jwtProperties.getHeader());
        if (authToken != null) {
//            先验证token有没有过期
            Boolean tokenExpired = jwtTokenUtil.isTokenExpired(authToken);
            if (tokenExpired) {
                throw new ForbiddenException("登录超时，请重新登录");
            }

//            进行权限判断


        } else {
            logger.error("========header中没有token========="+request.getRequestURI()+"======="+request.getMethod());
            RenderUtil.renderJson(response,Result.error("token为空,请登录"));
            return;
        }
        filterChain.doFilter(request, response);
    }
}
