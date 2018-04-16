package cn.chmobile.ai.modules.sys.controller;

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
import cn.chmobile.ai.modules.sys.service.IGetCityAndAreaService;
import cn.chmobile.ai.util.PageData;

@Controller
@RequestMapping(value =  "/home/getCityAndArea")
public class GetCityAndAreaController extends BaseController{
	
	@Autowired
	private IGetCityAndAreaService getCityAndAreaService;
	
    @RequestMapping(value="/list.json",method=RequestMethod.GET)
    @ResponseBody
    public Object getEciList(HttpServletRequest request,Model model) throws Exception{

    	PageData pd=new PageData();
        pd = this.getPageData();
        
        List<PageData> list = this.getCityAndAreaService.findListByParam(pd);
        
		return new RestResult<List<PageData>>(true, list);
         
    } 

}
