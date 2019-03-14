package com.louis.platform.app.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.mybatis.spring.annotation.MapperScan;


public interface ActivitiExtendDAO {
	List<Task> queryUserTask(Map<String, Object> params);
}
