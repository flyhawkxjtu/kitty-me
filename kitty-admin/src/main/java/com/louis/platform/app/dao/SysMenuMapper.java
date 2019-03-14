package com.louis.platform.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.louis.platform.app.model.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
	List<SysMenu> findPage();

	List<SysMenu> findPageByName(@Param(value="name") String name);
	
	List<SysMenu> findAll();

	List<SysMenu> findByAccount(@Param(value="account") String account);

	List<SysMenu> findRoleMenus(@Param(value="roleId") Long roleId);
}