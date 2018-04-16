package cn.chmobile.ai.modules.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.chmobile.ai.modules.sys.dao.IGetCityAndAreaDao;
import cn.chmobile.ai.modules.sys.service.IGetCityAndAreaService;
import cn.chmobile.ai.util.PageData;
@Service("getCityAndAreaService")
public class GetCityAndAreaServiceImpl implements IGetCityAndAreaService {
	@Resource  
    private IGetCityAndAreaDao getCityAndAreaDao;
	public List<PageData> findListByParam(PageData pd) throws Exception {
		return getCityAndAreaDao.selectByVrank(pd.getString("vrank"));
	}

}
