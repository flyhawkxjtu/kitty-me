package com.louis.platform.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.louis.platform.app.model.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    //改造默认的findpage方法，使之可以接收所有的参数
    List<SysUser> findPageByCondition(@Param("condition") Map<String, Object> condition);

    SysUser findByAccount(@Param(value="account") String account);

    SysUser findByName(@Param(value="name") String name);


	List<SysUser> findPageByName(@Param(value="name") String name);
	
	List<SysUser> findPageByNameAndEmail(@Param(value="name") String name, @Param(value="email") String email);
}