package cn.chmobile.ai.modules.causeLocation.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.chmobile.ai.core.dto.RestResult;
import cn.chmobile.ai.modules.base.controller.BaseController;
import cn.chmobile.ai.modules.base.service.ITdBaseService;
import cn.chmobile.ai.util.PageData;

/**
 * 
 *根因定位-问题小区列表
 *
 */
@Controller
@RequestMapping(value =  "/home/ProblemDist")
public class ProblemDistListController extends BaseController {
	@Autowired
	private ITdBaseService tdBaseService;
	
    @RequestMapping(value="/getEciList.json",method=RequestMethod.GET)
    @ResponseBody
    public Object getEciList(HttpServletRequest request,Model model) throws Exception{

    	PageData pd=new PageData();
        pd = this.getPageData();
        if(pd.get("startRow").equals("")) {
        	return new RestResult<List<PageData>>(true, "");
        }
        List<PageData> list = this.tdBaseService.findListByParam("rpt_d_city_dist_eci_cause", pd);
		return new RestResult<List<PageData>>(true, list);
         
    }
    

}
