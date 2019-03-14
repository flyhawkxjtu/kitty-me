package com.louis.platform.common.constants;

/**
 * 常量管理
 * @author Louis
 * @date Oct 29, 2018
 */ 
public interface PlatformConstant {

	/**
	 * 系统管理员用户名
	 */
	String ADMIN = "app";

	/**
	 * 工作流相关常量
	 */
	public static String WORKFLOW_INSTANCE_CREATOR_KEY="creatorId";
	public static String WORKFLOW_ACTIVITY_TYPE_START_TEXT="开始";
	public static String WORKFLOW_INSTANCE_OP_TYPE_SUBMIT_TEXT="启动";
	public static String WORKFLOW_INSTANCE_PREVIOUS_USERNAME = "workflow_instance_previous_username";
	public static String WORKFLOW_ACTIVITY_TYPE_END_TEXT="结束";
	public static String WORKFLOW_INSTANCE_TITLE_KEY = "workflow_instance_title_key"; //各业务表单titile,用于产生待办的title
	public static String WORKFLOW_DEFINATIONKEY_HOLIDAY= "holiday";
	
}
