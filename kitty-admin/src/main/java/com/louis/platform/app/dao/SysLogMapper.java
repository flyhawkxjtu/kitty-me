package com.louis.platform.app.dao;

import java.util.List;
import java.util.Map;

import com.louis.platform.app.model.SysUser;
import org.apache.ibatis.annotations.Param;

import com.louis.platform.app.model.SysLog;

public interface SysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    //改造默认的findpage方法，使之可以接收所有的参数
    List<SysLog> findPageByCondition(@Param("condition") Map<String, Object> condition);
    
    List<SysLog> findPageByUserName(@Param(value="userName") String userName);
}