package cn.chmobile.ai.modules.sys.dao;


import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.chmobile.ai.modules.sys.entity.Role;

public interface RoleMapper extends BaseMapper<Role> {
	/**
	 * 
	 * @title: findRoleByUserId   
	 * @description: 通过用户查找角色
	 * @param userId
	 * @return      
	 * @return: List<Role>
	 */
	List<Role> findRoleByUserId(String userId);
}
