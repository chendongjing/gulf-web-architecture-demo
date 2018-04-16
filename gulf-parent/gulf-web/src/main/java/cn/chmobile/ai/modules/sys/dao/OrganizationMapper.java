package cn.chmobile.ai.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.chmobile.ai.common.mapper.BaseTreeMapper;
import cn.chmobile.ai.modules.sys.entity.Organization;

public interface OrganizationMapper extends BaseTreeMapper<Organization> {
	
	/**
	 * 
	 * @title: findListByUserId
	 * @description: 通过用户查找组织机构
	 * @param userId
	 * @return
	 * @return: List<Organization>
	 */
	List<Organization> findListByUserId(String userId);
	
	/**
	 * 
	 * @title: findListOrganization
	 * @description: 查找组织机构
	 * @return
	 * @return: List<Organization>
	 */
	List<Organization> findListOrganization();

	/**
	 * 
	 * @title: findListOrganization
	 * @description: 查找地市
	 * @return
	 * @return: List<Organization>
	 */
	List<Organization> findListCity(@Param("userid") String userid);

}