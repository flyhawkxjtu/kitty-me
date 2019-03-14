package com.louis.platform.app.sevice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.platform.app.dao.SysLogMapper;
import com.louis.platform.app.model.SysLog;
import com.louis.platform.app.sevice.SysLogService;
import com.louis.platform.common.page.ColumnFilter;
import com.louis.platform.common.page.MybatisPageHelper;
import com.louis.platform.common.page.PageRequest;
import com.louis.platform.common.page.PageResult;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public int save(SysLog record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysLogMapper.insertSelective(record);
		}
		return sysLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysLog record) {
		return sysLogMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysLog> records) {
		for(SysLog record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysLog findById(Long id) {
		return sysLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysLogMapper,"findPageByCondition",pageRequest.getCondition());
	}
	
}
