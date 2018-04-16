package cn.chmobile.ai.modules.base.service;

import java.util.List;
import java.util.Map;

import cn.chmobile.ai.util.PageData;

public interface ITdBaseService {

    /**
     * Hbase
     * 查询列表
     * @param tableName 数据表
     * @param pd 条件
     * @return
     * @throws Exception
     */
	public List<PageData> findListByParam(String tableName,PageData pd)throws Exception;
	
	/**
	 * 查询单一数据对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PageData findObjectByParam(Integer id)throws Exception;
	
    /**
     * 测试接口
     * @param id
     * @return
     */
	public Map<String, String> getTestById(String id);
	
	
}
