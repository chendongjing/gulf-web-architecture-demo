package cn.chmobile.ai.modules.sys.service.impl;

import cn.chmobile.ai.common.service.impl.CommonServiceImpl;
import cn.chmobile.ai.modules.sys.dao.DictMapper;
import cn.chmobile.ai.modules.sys.entity.Dict;
import cn.chmobile.ai.modules.sys.service.IDictService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("dictService")
public class DictServiceImpl extends CommonServiceImpl<DictMapper, Dict> implements IDictService {

	@Override
	public List<Dict> selectDictList() {
		return baseMapper.selectDictList();
	}

}
