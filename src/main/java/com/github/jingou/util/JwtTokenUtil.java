package com.github.jingou.util;

import com.github.jingou.common.properties.JwtProperties;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * <p>jwt token工具类</p>
 * <pre>
 *     jwt的claim里一般包含以下几种数据:
 *         1. iss -- token的发行者
 *         2. sub -- 该JWT所面向的用户
 *         3. aud -- 接收该JWT的一方
 *         4. exp -- token的失效时间
 *         5. nbf -- 在此时间段之前,不会被处理
 *         6. iat -- jwt发布时间
 *         7. jti -- jwt唯一标识,防止重复使用
 * </pre>
 */
@Component
public class JwtTokenUtil {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取jwt接收者
     */
    public String getAudienceFromToken(String token) {
        return getClaimFromToken(token).getAudience();
    }

    /**
     * 获取私有的jwt claim
     */
    public String getPrivateClaimFromToken(String token, String key) {
        return getClaimFromToken(token).get(key).toString();
    }

    /**
     * 获取md5 key从token中
     */
    public String getMd5KeyFromToken(String token) {
        return getPrivateClaimFromToken(token, jwtProperties.getMd5Key());
    }

    /**
     * 获取jwt的payload部分
     */
    public Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 解析token是否正确,不正确会报异常<br>
     */
    public void parseToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
    }

    /**
     * <pre>
     *  验证token是否失效
     *  true:过期   false:没过期
     * </pre>
     */
    public Boolean isTokenExpired(String token){
        Boolean flag = false;
        try {
            final Date expiration = getExpirationDateFromToken(token);
            expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            flag = true;
        }
        return flag;
    }
    /**
     * 生成token(通过用户名和签名时候用的随机数,有些值需要放入claims)
     * userName 用户名
     * radomKey 随机生成的key
     * claims 需要放入token的数据
     */
    public String generateToken(String userName,Map<String, Object> claims) {
        return doGenerateToken(claims, userName);
    }


    /**
     * 刷新token
     */
    public String refshToken(String token) {
        Claims claims = getClaimFromToken(token);
        claims.put(jwtProperties.getMd5Key(), getRandomKey()+System.currentTimeMillis());
        return doGenerateToken(claims, jwtTokenUtil.getUsernameFromToken(token));
    }

    /**
     * 生成token
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
//        Date expirationDate = new Date(System.currentTimeMillis() + jwtProperties.getExpiration() * 1000);
        claims.put(jwtProperties.getMd5Key(), getRandomKey()+System.currentTimeMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
//                .setIssuedAt(createdDate)
//                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 获取混淆MD5签名用的随机字符串
     */
    public String getRandomKey() {
        return Md5Util.getRandomString(6);
    }
}
