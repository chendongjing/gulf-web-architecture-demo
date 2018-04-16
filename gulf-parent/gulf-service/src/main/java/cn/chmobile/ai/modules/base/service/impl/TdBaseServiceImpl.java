package cn.chmobile.ai.modules.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chmobile.ai.modules.base.dao.IUserDao;
import cn.chmobile.ai.modules.base.repository.HbaseQueryDao;
import cn.chmobile.ai.modules.base.service.ITdBaseService;
import cn.chmobile.ai.util.PageData;

@Service("tdBaseService")
public class TdBaseServiceImpl implements ITdBaseService{
	
//	@Autowired
//	private TdDaoSupport dao;
	
	@Resource  
    private IUserDao dao;  
	
	@Autowired
	private HbaseQueryDao hbaseQueryDao;
 
	public List<PageData> findListByParam(String tableName,PageData pd)throws Exception{
		List<PageData> list = hbaseQueryDao.queryForListByScanRange(tableName, pd.getString("startRow"), null);

		return list;
	}
	
	public PageData findObjectByParam(Integer id)throws Exception{
		return (PageData)dao.selectByPrimaryKey(id);
	}
	
	/**
	 * 大数据
	 */
	public Map<String, String> getTestById(String id) {
		Map<String, String> map = hbaseQueryDao.queryForMapByRowKey("test", id);

		return map;
	}


}
