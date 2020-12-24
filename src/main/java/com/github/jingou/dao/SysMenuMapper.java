package com.github.jingou.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.jingou.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author lij
 * @since 2020-12-23
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

}
