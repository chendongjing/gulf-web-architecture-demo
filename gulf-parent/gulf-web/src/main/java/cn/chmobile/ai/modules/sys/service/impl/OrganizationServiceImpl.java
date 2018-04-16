package cn.chmobile.ai.modules.sys.service.impl;

import cn.chmobile.ai.common.service.impl.TreeCommonServiceImpl;
import cn.chmobile.ai.modules.sys.dao.OrganizationMapper;
import cn.chmobile.ai.modules.sys.entity.Organization;
import cn.chmobile.ai.modules.sys.service.IOrganizationService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("organizationService")
public class OrganizationServiceImpl extends TreeCommonServiceImpl<OrganizationMapper, Organization, String>
		implements IOrganizationService {

	@Override
	public List<Organization> findListByUserId(String userid) {
		return baseMapper.findListByUserId(userid);
	}
	
	@Override
	public List<Organization> findListOrganization() {
		return baseMapper.findListOrganization();
	}
	
	@Override
	public List<Organization> findListCity(String userid) {
		return baseMapper.findListCity(userid);
	}
}
