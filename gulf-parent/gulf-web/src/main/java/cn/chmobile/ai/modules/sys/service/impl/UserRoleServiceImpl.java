package cn.chmobile.ai.modules.sys.service.impl;

import cn.chmobile.ai.common.service.impl.CommonServiceImpl;
import cn.chmobile.ai.modules.sys.dao.UserRoleMapper;
import cn.chmobile.ai.modules.sys.entity.UserRole;
import cn.chmobile.ai.modules.sys.service.IUserRoleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userRoleService")
public class UserRoleServiceImpl extends CommonServiceImpl<UserRoleMapper,UserRole> implements IUserRoleService {

}
