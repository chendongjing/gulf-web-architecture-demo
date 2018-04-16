package cn.chmobile.ai.modules.sys.service.impl;

import cn.chmobile.ai.common.service.impl.CommonServiceImpl;
import cn.chmobile.ai.modules.sys.dao.DictGroupMapper;
import cn.chmobile.ai.modules.sys.entity.DictGroup;
import cn.chmobile.ai.modules.sys.service.IDictGroupService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("dictGroupService")
public class DictGroupServiceImpl extends CommonServiceImpl<DictGroupMapper,DictGroup> implements IDictGroupService {
}
