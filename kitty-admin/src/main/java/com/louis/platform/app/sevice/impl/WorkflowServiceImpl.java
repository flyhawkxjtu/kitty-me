package com.louis.platform.app.sevice.impl;

import java.io.InputStream;
import java.util.*;

import javax.annotation.Resource;

import com.louis.platform.app.dao.ActivitiExtendDAO;
import com.louis.platform.app.dao.WorkflowTraceDAO;
import com.louis.platform.app.dto.TodoDTO;
import com.louis.platform.app.sevice.WorkflowService;
import com.louis.platform.common.constants.PlatformConstant;
import com.louis.platform.common.page.MybatisPageHelper;
import com.louis.platform.common.page.PageRequest;
import com.louis.platform.common.page.PageResult;
import com.louis.platform.common.security.SecurityUtils;
import com.louis.platform.common.utils.StringUtils;
import com.louis.platform.app.model.WorkflowTrace;
import com.louis.platform.common.utils.WorkflowUtils;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.delegate.Expression;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.PropertyUtils;


@Service
public class WorkflowServiceImpl implements WorkflowService {
	@Resource
	ProcessEngine processEngine;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private IdentityService identityService;
	@Resource
	protected ManagementService managementService;
	@Autowired
	private WorkflowTraceDAO workflowTraceDAO;
	@Autowired
	private ActivitiExtendDAO activitiExtendDAO;


	private final Logger logger = LoggerFactory.getLogger(WorkflowServiceImpl.class);


	public void startProcessAndCompleteSubmitActivity(String processDefinitionKey, String businessKey, Map<String, Object> params) {
		ProcessInstance pInstance = this.processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey, businessKey, params);
		Task task = taskService.createTaskQuery().processInstanceId(pInstance.getProcessInstanceId()).singleResult();
		String userId = params.get("creatorId").toString();
		//插入流程跟踪表
		insertWorkflowTrack(
				new WorkflowTrace(task.getProcessInstanceId(), null, PlatformConstant.WORKFLOW_ACTIVITY_TYPE_START_TEXT,
						new Date(), userId, PlatformConstant.WORKFLOW_INSTANCE_OP_TYPE_SUBMIT_TEXT, "", businessKey,
						pInstance.getProcessDefinitionKey()));
		taskService.complete(task.getId());
	}



	public PageResult queryTodo(PageRequest pageRequest){
		ProcessDefinition processDefinition;
		ProcessInstance processInstance;
		TaskService taskService;
		String title = "";
		PageResult pageResult =  new PageResult();

		List<TodoDTO> todoDTOList = new ArrayList();
		TodoDTO todoDTO = null;

		//当前登录的账号作为activiti查询的参数
        Map<String, Object> condition=new HashMap<String, Object>();
        condition.put("assignee",SecurityUtils.getUsername());
		pageResult = MybatisPageHelper.findPage(pageRequest,activitiExtendDAO ,"queryUserTask", condition);
		//按照不同的流程，构造前端显示的内容
		taskService=processEngine.getTaskService();
        for(TaskEntity task: (List<TaskEntity>)pageResult.getContent()){
			processInstance=processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).active().includeProcessVariables().singleResult();
			switch(processInstance.getProcessDefinitionKey()) {
				case PlatformConstant.WORKFLOW_DEFINATIONKEY_HOLIDAY: {
					//【流程定义名称】表单标题  发送人  发送时间,这里仅仅是示例
					title = "【" + processInstance.getProcessDefinitionName() + ":】" + processInstance.getProcessVariables().get(PlatformConstant.WORKFLOW_INSTANCE_TITLE_KEY);
					todoDTO = new TodoDTO();
					todoDTO.setTitle(title);
					todoDTO.setApproveUrl(task.getFormKey());
					todoDTO.setBusinessDataId(processInstance.getBusinessKey());
					todoDTO.setCreateTime(task.getCreateTime());
					todoDTO.setTaskId(task.getId());
					todoDTO.setProcessInstanceId(processInstance.getProcessInstanceId());
					todoDTO.setProcessDefinationKey(processInstance.getProcessDefinitionKey());
					//todoDTO.setProcessDefinationName(processDefinationName);
					//todoDTO.setTaskName(task.getName());
					//todoDTO.setType(task.getCategory());
					todoDTO.setSendUserName(taskService.getVariable(task.getId(), PlatformConstant.WORKFLOW_INSTANCE_PREVIOUS_USERNAME).toString());
					todoDTOList.add(todoDTO);
					break;
				}
			}
		}
		pageResult.setContent(todoDTOList);
		return pageResult;
	}

	@Override
	public PageResult queryWorkflowTraceLog(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest,workflowTraceDAO ,"queryWorkflowTraceLog", pageRequest.getCondition());
	}



	@Override
	public InputStream getProcessDefination(String resourceType, String processInstanceId) {
		HistoricProcessInstance historicProcessInstance;

		historyService = processEngine.getHistoryService();
		historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();

		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(historicProcessInstance.getProcessDefinitionId());

		String resourceName = "";
		if (resourceType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resourceType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}
		return repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
	}


	/**
	 * 流程跟踪图
	 *
	 * @param processInstanceId
	 *            流程实例ID
	 * @return 封装了各种节点信息
	 */
	@Override
	public List<Map<String, Object>> queryWorkflowTraceImage(String processInstanceId){
		RuntimeService runtimeService = processEngine.getRuntimeService();
		HistoricProcessInstance historicProcessInstance;

		Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();// 执行实例
		if (execution != null) {// 流程未结束，读运行表
			Object property =null;
			try{
				property = PropertyUtils.getProperty(execution, "activityId");
			}catch (Exception e){
				logger.error("queryWorkflowTraceImage 产生异常");
			}

			String activityId = "";
			if (property != null) {
				activityId = property.toString();
			}
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
					.getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
			List<ActivityImpl> activitiList = processDefinition.getActivities();// 获得当前任务的所有节点

			List<Map<String, Object>> activityInfos = new ArrayList<Map<String, Object>>();
			for (ActivityImpl activity : activitiList) {

				boolean currentActiviti = false;
				String id = activity.getId();

				// 当前节点
				if (id.equals(activityId)) {
					currentActiviti = true;
				}

				Map<String, Object> activityImageInfo = packageSingleActivitiInfo(activity, processInstance,
						currentActiviti);

				activityInfos.add(activityImageInfo);
			}

			return activityInfos;
		} else {// 流程已经结束，读历史表
			historyService = processEngine.getHistoryService();
			historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();

			ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
					.getDeployedProcessDefinition(historicProcessInstance.getProcessDefinitionId());
			List<ActivityImpl> activitiList = processDefinition.getActivities();// 获得当前任务的所有节点

			List<Map<String, Object>> activityInfos = new ArrayList<Map<String, Object>>();
			for (ActivityImpl activity : activitiList) {

				Map<String, Object> activityImageInfo = packageSingleActivitiInfo(activity, null, false);

				if ("endEvent".equals(activity.getProperty("type"))) {
					activityImageInfo.put("isEnd", true);
				}
				activityInfos.add(activityImageInfo);
			}

			return activityInfos;

		}

	}


	private int insertWorkflowTrack(WorkflowTrace trace) {
		trace.setId(StringUtils.gengerteUUID());
		return workflowTraceDAO.insertWorkflowTrace(trace);
	}

	/**
	 * 封装输出信息，包括：当前节点的X、Y坐标、变量信息、任务类型、任务描述
	 *
	 * @param activity
	 * @param processInstance
	 * @param currentActiviti
	 * @return
	 */
	private Map<String, Object> packageSingleActivitiInfo(ActivityImpl activity, ProcessInstance processInstance,
														  boolean currentActiviti)  {
		Map<String, Object> vars = new HashMap<String, Object>();
		Map<String, Object> activityInfo = new HashMap<String, Object>();
		activityInfo.put("currentActiviti", currentActiviti);
		setPosition(activity, activityInfo);
		setWidthAndHeight(activity, activityInfo);

		Map<String, Object> properties = activity.getProperties();
		vars.put("任务类型", WorkflowUtils.parseToZhType(properties.get("type").toString()));

		ActivityBehavior activityBehavior = activity.getActivityBehavior();
		logger.debug("activityBehavior={}", activityBehavior);
		if (activityBehavior instanceof UserTaskActivityBehavior) {

			Task currentTask = null;

			/*
			 * 当前节点的task
			 */
			if (currentActiviti) {
				currentTask = getCurrentTaskInfo(processInstance);
			}

			/*
			 * 当前任务的分配角色
			 */
			UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
			TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
			Set<Expression> candidateGroupIdExpressions = taskDefinition.getCandidateGroupIdExpressions();
			if (!candidateGroupIdExpressions.isEmpty()) {

				// 任务的处理角色
				setTaskGroup(vars, candidateGroupIdExpressions);

				// 当前处理人
				if (currentTask != null) {
					setCurrentTaskAssignee(vars, currentTask);
				}
			}
		}

		vars.put("节点说明", properties.get("documentation"));

		String description = activity.getProcessDefinition().getDescription();
		vars.put("描述", description);

		logger.debug("trace variables: {}", vars);
		activityInfo.put("vars", vars);
		return activityInfo;
	}

	/**
	 * 设置坐标位置
	 *
	 * @param activity
	 * @param activityInfo
	 */
	private void setPosition(ActivityImpl activity, Map<String, Object> activityInfo) {
		activityInfo.put("x", activity.getX());
		activityInfo.put("y", activity.getY());
	}

	/**
	 * 设置宽度、高度属性
	 *
	 * @param activity
	 * @param activityInfo
	 */
	private void setWidthAndHeight(ActivityImpl activity, Map<String, Object> activityInfo) {
		activityInfo.put("width", activity.getWidth());
		activityInfo.put("height", activity.getHeight());
	}

	/**
	 * 获取当前节点信息
	 *
	 * @param processInstance
	 * @return
	 */
	private Task getCurrentTaskInfo(ProcessInstance processInstance) {
		Task currentTask = null;
		try {
			String activitiId = (String) PropertyUtils.getProperty(processInstance, "activityId");
			logger.debug("current activity id: {}", activitiId);

			currentTask = taskService.createTaskQuery().processInstanceId(processInstance.getId())
					.taskDefinitionKey(activitiId).singleResult();
			logger.debug("current task for processInstance: {}", ToStringBuilder.reflectionToString(currentTask));

		} catch (Exception e) {
			logger.error("can not get property activityId from processInstance: {}", processInstance);
		}
		return currentTask;
	}

	private void setTaskGroup(Map<String, Object> vars, Set<Expression> candidateGroupIdExpressions) {
		String roles = "";
		for (Expression expression : candidateGroupIdExpressions) {
			String expressionText = expression.getExpressionText();
			String roleName = identityService.createGroupQuery().groupId(expressionText).singleResult().getName();
			roles += roleName;
		}
		vars.put("任务所属角色", roles);
	}

	/**
	 * 设置当前处理人信息
	 *
	 * @param vars
	 * @param currentTask
	 */
	private void setCurrentTaskAssignee(Map<String, Object> vars, Task currentTask) {
		String assignee = currentTask.getAssignee();
		if (assignee != null) {
			User assigneeUser = identityService.createUserQuery().userId(assignee).singleResult();
			String userInfo = assigneeUser.getFirstName() + " " + assigneeUser.getLastName();
			vars.put("当前处理人", userInfo);
		}
	}


	
}

