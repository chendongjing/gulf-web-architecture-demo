package cn.chmobile.ai.modules.sys.service;

import java.util.List;

import cn.chmobile.ai.util.PageData;

public interface IGetCityAndAreaService {
	/**
	 * 
	 * 根据参数查询列表
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findListByParam(PageData pd)throws Exception;


}
