package com.louis.platform.app.sevice;

import java.util.List;

import com.louis.platform.app.model.SysDept;
import com.louis.platform.common.page.PageRequest;
import com.louis.platform.common.service.ICurdService;

/**
 * 机构管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDeptService extends ICurdService<SysDept> {

	/**
	 * 查询机构树,
	 * @param
	 * @return
	 */
	//TODO 按照查询条件，比如机构名称进行查询，这个功能还没开发
	List<SysDept> findTree();
}
