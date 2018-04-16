package cn.chmobile.ai.modules.sys.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.chmobile.ai.modules.sys.entity.HelpInfo;

public interface HelpInfoMapper  extends BaseMapper<HelpInfo> {
	
	List<HelpInfo> findAllHelpInfo();


}
