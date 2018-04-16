package cn.chmobile.ai.modules.sys.dao;

import java.util.List;

import cn.chmobile.ai.util.PageData;
/**
 * 
 * 获取省市区县联动的DAO
 *
 */
public interface IGetCityAndAreaDao {
	/**
	 * 根据rank查询，1省级，2市级，3区级，4小区
	 * @param vrank
	 * @return
	 */
	List<PageData> selectByVrank(String vrank);
}
