package com.github.jingou.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.jingou.common.exception.business200.BusinessException;
import com.github.jingou.dao.SysUserMapper;
import com.github.jingou.dto.request.SysUserRequest;
import com.github.jingou.model.SysUser;
import com.github.jingou.service.ISysUserService;
import com.github.jingou.util.Md5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lij
 * @since 2020-12-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void save(SysUserRequest request) {
        String username = request.getUsername();
        List<SysUser> sysUsers = sysUserMapper.getByName(username);
        if (sysUsers.size() > 0) {
            throw new BusinessException("该用户名称已存在");
        }
        SysUser user = new SysUser();
        BeanUtils.copyProperties(request,user,new String[]{"userId"});
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        String salt = Md5Util.getRandomString(4);
        user.setSalt(salt);
        user.setPassword(Md5Util.encrypt(request.getPassword(), salt));
        sysUserMapper.insert(user);
    }
}
