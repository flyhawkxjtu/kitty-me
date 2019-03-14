package com.louis.platform.app.sevice;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.louis.platform.common.http.HttpResult;
import com.louis.platform.common.page.PageRequest;
import com.louis.platform.common.page.PageResult;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;


@Service
public interface WorkflowService {
	/**
	 * 启动流程,并自动完成第一个节点
	 * @param processDefinitionKey:流程定义的key，对应ACT_RE_PROCDEF表中的KEY字段
	 * @param businessKey:与当前流程相关的业务数据的id
	 * @param params: 与当前流程相关的其他参数，这些参数会在流程进行过程中使用
	 * @return
	 */

	public void startProcessAndCompleteSubmitActivity(String processDefinitionKey,String businessKey, Map<String, Object> params);

	/**
	 * 分页查询待办事项,pageRequest中，仅需要分页数和当前页数，不需要其他参数。当前处理人从spring security中取
	 * @param pageRequest
	 * @return
	 */
	public PageResult queryTodo(PageRequest pageRequest);

	/**
	 * 查询流程处理信息：日志
	 * @param pageRequest
	 * @return
	 */
	public PageResult queryWorkflowTraceLog(PageRequest pageRequest);

	/*
	查询流程定义文件
	 */
	public InputStream getProcessDefination(String resourceType, String processInstanceId);
	/*
	查询流程处理信息:图片
	 */
	public List<Map<String, Object>> queryWorkflowTraceImage(String processInstanceId);





}
