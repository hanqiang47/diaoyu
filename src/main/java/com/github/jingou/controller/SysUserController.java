package com.github.jingou.controller;


import com.github.jingou.common.Result;
import com.github.jingou.dto.request.SysUserRequest;
import com.github.jingou.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lij
 * @since 2020-12-23
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 新增
     */
    @ApiOperation(value = "新增")
    @PostMapping()
    public Result save(@Valid @RequestBody SysUserRequest request) {
        sysUserService.save(request);
        return Result.success();
    }



}

