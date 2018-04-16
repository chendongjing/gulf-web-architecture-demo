package cn.chmobile.ai.modules.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.chmobile.ai.common.service.impl.CommonServiceImpl;
import cn.chmobile.ai.modules.sys.dao.HelpInfoMapper;
import cn.chmobile.ai.modules.sys.entity.HelpInfo;
import cn.chmobile.ai.modules.sys.service.IHelpInfoService;
@Service("helpInfoService")
@Transactional
public class HelpInfoServiceImpl extends CommonServiceImpl<HelpInfoMapper,HelpInfo> implements IHelpInfoService {

	@Resource
	private HelpInfoMapper helpInfoMapper;
	@Override
	public List<HelpInfo> findHelpInfo() {
		return helpInfoMapper.findAllHelpInfo();
	}
	
}
