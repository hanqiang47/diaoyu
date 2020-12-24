package com.github.jingou.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.jingou.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lij
 * @since 2020-12-23
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> getByName(@Param("username") String username);
}
