package com.louis.platform.app.model;

import java.util.Date;

public class WorkflowTrace {
	private String id;
	private String processInstanceId;
	private String taskId;
	private String taskName;
	private Date opTime;
	private String opUserId;
	private String opUserName;
	private String opType;
	private String opComment;
	private String opDataId;
	private String procDefKey;

	//新增下面三个非数据库字段，是因为aspect统一要给每天数据增加creator，createTime，updateTime三个字段，但当前类确实用不上
	private String creator;
	private Date createTime;
	private Date updateTime;
	
	
	public WorkflowTrace(){}
	public WorkflowTrace(String processInstanceId,String taskId,
			String taskName,Date opTime,String opUserId,String opType,String opComment,
			String opDataId,String procDefKey){
		this.processInstanceId=processInstanceId;
		this.taskId=taskId;
		this.taskName=taskName;
		this.opTime=opTime;
		this.opUserId=opUserId;
		this.opType=opType;
		this.opComment=opComment;
		this.opDataId=opDataId;
		this.setProcDefKey(procDefKey);
	}

	
	public String getOpDataId() {
		return opDataId;
	}
	public void setOpDataId(String opDataId) {
		this.opDataId = opDataId;
	}
	public String getOpUserName() {
		return opUserName;
	}
	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}
	public String getOpComment() {
		return opComment;
	}
	public void setOpComment(String opComment) {
		this.opComment = opComment;
	}
	public String getProcDefKey() {
		return procDefKey;
	}
	public void setProcDefKey(String procDefKey) {
		this.procDefKey = procDefKey;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
