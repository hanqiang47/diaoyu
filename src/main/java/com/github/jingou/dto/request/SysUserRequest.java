package com.github.jingou.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserRequest {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话号码")
    private String phone;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private int roleId;
}
