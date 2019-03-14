package com.louis.platform.app.sevice;

import java.util.List;

import com.louis.platform.app.model.SysDict;
import com.louis.platform.common.service.ICurdService;

/**
 * 字典管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDictService extends ICurdService<SysDict> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysDict> findByLable(String lable);

	List<SysDict> findTree();
}
