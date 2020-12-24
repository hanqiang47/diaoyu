package com.github.jingou.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.jingou.dto.request.SysUserRequest;
import com.github.jingou.model.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lij
 * @since 2020-12-23
 */
public interface ISysUserService extends IService<SysUser> {

    void save(SysUserRequest request);
}
