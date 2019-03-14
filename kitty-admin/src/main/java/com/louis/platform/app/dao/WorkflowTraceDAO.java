package com.louis.platform.app.dao;

import com.louis.platform.app.model.WorkflowTrace;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;



public interface WorkflowTraceDAO {
	public int insertWorkflowTrace(WorkflowTrace trace);
	public List<WorkflowTrace> queryWorkflowTraceLog(Map<String, Object> params);
	public List<Map> getAllTraceInfo(@Param("bid") String id, @Param("procDefKey") String pdid) ;

}
