package cn.chmobile.ai.modules.sys.service.impl;

import cn.chmobile.ai.common.service.impl.CommonServiceImpl;
import cn.chmobile.ai.modules.sys.dao.RoleMapper;
import cn.chmobile.ai.modules.sys.entity.Role;
import cn.chmobile.ai.modules.sys.service.IRoleService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("roleService")
public class RoleServiceImpl extends CommonServiceImpl<RoleMapper, Role> implements IRoleService {

	@Override
	public List<Role> findListByUserId(String userid) {
		return baseMapper.findRoleByUserId(userid);
	}

}
