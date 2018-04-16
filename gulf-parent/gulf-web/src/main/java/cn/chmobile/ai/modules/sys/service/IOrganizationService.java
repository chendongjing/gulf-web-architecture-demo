package cn.chmobile.ai.modules.sys.service;

import java.util.List;

import cn.chmobile.ai.common.service.ITreeCommonService;
import cn.chmobile.ai.modules.sys.entity.Organization;

/**
 * @Title:
 * @Description:
 * @author jwcg
 * @date 2014-12-20 21:33:51
 * @version V1.0
 *
 */
public interface IOrganizationService extends ITreeCommonService<Organization, String> {
	/**
	 * 通过用户ID查找角色
	 */
	public List<Organization> findListByUserId(String userid);
	/**
	 * 查找角色所有
	 */
	public List<Organization> findListOrganization();
	/**
	 * 查找地市
	 */
	public List<Organization> findListCity(String userid);
	
}
