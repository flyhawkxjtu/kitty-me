package com.louis.platform.common.page;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页请求
 * @author Louis
 * @date Aug 19, 2018
 */
public class PageRequest {
	/**
	 * 当前页码
	 */
	private int pageNum = 1;
	/**
	 * 每页数量
	 */
	private int pageSize = 10;
	/**
	 * 每页数量
	 */
	private Map<String, ColumnFilter> columnFilters = new HashMap<String, ColumnFilter>();
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Map<String, ColumnFilter> getColumnFilters() {
		return columnFilters;
	}
	public void setColumnFilters(Map<String, ColumnFilter> columnFilters) {
		this.columnFilters = columnFilters;
	}
	public ColumnFilter getColumnFilter(String name) {
		return columnFilters.get(name);
	}

	//将columnFilters中的值取出来，构造‘column：value’的map，用于mybatis中作为参数传入sql语句中
	public Map<String,Object> getCondition(){
		Map<String,Object> condition=new HashMap<>();
		this.columnFilters.values().forEach(columnFilter->{
			if(null!=columnFilter.getName()&&null!=columnFilter.getValue()) condition.put(columnFilter.getName(),columnFilter.getValue());
		});
		return  condition;
	}
}
