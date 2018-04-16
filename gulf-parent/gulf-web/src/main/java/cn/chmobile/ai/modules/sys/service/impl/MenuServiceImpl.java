package cn.chmobile.ai.modules.sys.service.impl;

import cn.chmobile.ai.common.service.impl.TreeCommonServiceImpl;
import cn.chmobile.ai.modules.sys.dao.MenuMapper;
import cn.chmobile.ai.modules.sys.entity.Menu;
import cn.chmobile.ai.modules.sys.service.IMenuService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("menuService")
public class MenuServiceImpl extends TreeCommonServiceImpl<MenuMapper, Menu, String> implements IMenuService {

	@Override
	public List<Menu> findMenuByUserId(String userId) {
		return baseMapper.findMenuByUserId(userId);
	}

	@Override
	public List<Menu> findMenuByRoleId(String roleId) {
		return baseMapper.findMenuByRoleId(roleId);
	}

}
