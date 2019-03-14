package com.louis.platform.app.sevice;

import java.util.List;
import java.util.Set;

import com.louis.platform.app.model.SysUser;
import com.louis.platform.app.model.SysUserRole;
import com.louis.platform.common.service.ICurdService;

/**
 * 用户管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysUserService extends ICurdService<SysUser> {


	SysUser findByAccount(String account);

	SysUser findByName(String name);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param account
	 * @return
	 */
	Set<String> findPermissions(String account);

	/**
	 * 查找用户的角色集合
	 * @param userId
	 * @return
	 */
	List<SysUserRole> findUserRoles(Long userId);

}
