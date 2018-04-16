package cn.chmobile.ai.modules.sys.service;


import java.util.List;

import cn.chmobile.ai.common.service.ICommonService;
import cn.chmobile.ai.modules.sys.entity.Dict;

/**
 * @Title:
 * @Description:
 * @author jwcg
 * @date 2017-02-09 09:05:29
 * @version V1.0
 *
 */
public interface IDictService extends ICommonService<Dict> {
    public List<Dict> selectDictList();
}
