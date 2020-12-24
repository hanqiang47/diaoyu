package com.github.jingou.common.kits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caedmon
 */
public class IpKit {

    private static final Logger log = LoggerFactory.getLogger(IpKit.class);
    private static final String UNKNOWN = "unknown";

    /**
     * 获取请求的客户端ip
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        log.debug("X-Forwarded-For: " + ip);
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        log.debug("Proxy-Client-IP: " + request.getHeader("Proxy-Client-IP"));
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        log.debug("X-Real-IP: " + request.getHeader("X-Real-IP"));
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        log.debug("WL-Proxy-Client-IP: " + request.getHeader("WL-Proxy-Client-IP"));
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        log.debug("HTTP_CLIENT_IP: " + request.getHeader("HTTP_CLIENT_IP"));
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        log.debug("HTTP_X_FORWARDED_FOR: " + request.getHeader("HTTP_X_FORWARDED_FOR"));
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        log.debug("request.getRemoteAddr(): " + request.getRemoteAddr());
        log.debug("=================== getIp 分割线======================");
        return ip;
    }

    public static Map<String, String> getIps(HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();

        String ip = request.getHeader("X-Forwarded-For");
        map.put("X-Forwarded-For", ip);
        log.debug("X-Forwarded-For: " + ip);

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        log.debug("Proxy-Client-IP: " + request.getHeader("Proxy-Client-IP"));
        map.put("Proxy-Client-IP", request.getHeader("Proxy-Client-IP"));

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        log.debug("X-Real-IP: " + request.getHeader("X-Real-IP"));
        map.put("X-Real-IP", request.getHeader("X-Real-IP"));

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        log.debug("WL-Proxy-Client-IP: " + request.getHeader("WL-Proxy-Client-IP"));
        map.put("WL-Proxy-Client-IP", request.getHeader("WL-Proxy-Client-IP"));

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        log.debug("HTTP_CLIENT_IP: " + request.getHeader("HTTP_CLIENT_IP"));
        map.put("HTTP_CLIENT_IP", request.getHeader("HTTP_CLIENT_IP"));

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        log.debug("HTTP_X_FORWARDED_FOR: " + request.getHeader("HTTP_X_FORWARDED_FOR"));
        map.put("HTTP_X_FORWARDED_FOR", request.getHeader("HTTP_X_FORWARDED_FOR"));

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        log.debug("request.getRemoteAddr(): " + request.getRemoteAddr());
        map.put("request.getRemoteAddr()", request.getRemoteAddr());

        log.debug("=================== getIps 分割线======================");

        return map;
    }

}
