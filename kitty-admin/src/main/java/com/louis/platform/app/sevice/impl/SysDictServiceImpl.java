package com.louis.platform.app.sevice.impl;

import java.util.ArrayList;
import java.util.List;

import com.louis.platform.app.model.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.platform.app.dao.SysDictMapper;
import com.louis.platform.app.model.SysDict;
import com.louis.platform.app.sevice.SysDictService;
import com.louis.platform.common.page.ColumnFilter;
import com.louis.platform.common.page.MybatisPageHelper;
import com.louis.platform.common.page.PageRequest;
import com.louis.platform.common.page.PageResult;

@Service
public class SysDictServiceImpl implements SysDictService {

	@Autowired
	private SysDictMapper sysDictMapper;

	@Override
	public int save(SysDict record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysDictMapper.insertSelective(record);
		}
		return sysDictMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysDict record) {
		return sysDictMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysDict> records) {
		for(SysDict record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysDict findById(Long id) {
		return sysDictMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		ColumnFilter columnFilter = pageRequest.getColumnFilter("label");
		if(columnFilter != null) {
			return MybatisPageHelper.findPage(pageRequest, sysDictMapper, "findPageByLabel", columnFilter.getValue());
		}
		return MybatisPageHelper.findPage(pageRequest, sysDictMapper);
	}

	@Override
	public List<SysDict> findByLable(String lable) {
		return sysDictMapper.findByLable(lable);
	}



	@Override
	public List<SysDict> findTree() {
		List<SysDict> sysDics = new ArrayList<SysDict>();
		List<SysDict> dics = sysDictMapper.findAll();
		for (SysDict dic : dics) {
			if (dic.getParentId()== null || dic.getParentId() == 0) {
				dic.setLevel(0);
				sysDics.add(dic);
			}
		}
		findChildren(sysDics, dics);
		return sysDics;
	}

	/**
	 *
	 * @param sysDics:返回值,所有的,带上下级关系的数据集合
	 * @param dics:所有的数据集合
	 */
	private void findChildren(List<SysDict> sysDics, List<SysDict> dics) {
		for (SysDict sysDic : sysDics) {
			List<SysDict> children = new ArrayList<SysDict>();
			for (SysDict dic : dics) {//找到sysdic中元素的子元素
				if (dic.getId() != null && sysDic.getId().equals(dic.getParentId())) {
					dic.setParentName(sysDic.getName());
					dic.setLevel(sysDic.getLevel() + 1);
					children.add(dic);
				}
			}
			sysDic.setChildren(children);
			findChildren(children, dics);
		}
	}

}
