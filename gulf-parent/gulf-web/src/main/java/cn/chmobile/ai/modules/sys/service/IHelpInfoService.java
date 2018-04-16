package cn.chmobile.ai.modules.sys.service;

import java.util.List;

import cn.chmobile.ai.common.service.ICommonService;
import cn.chmobile.ai.modules.sys.entity.HelpInfo;

public interface IHelpInfoService extends ICommonService<HelpInfo>{

	/**
	 * 帮助信息查询
	 * @return list
	 */
	List<HelpInfo> findHelpInfo();

}
