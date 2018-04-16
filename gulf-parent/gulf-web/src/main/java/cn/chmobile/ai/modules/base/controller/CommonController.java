package cn.chmobile.ai.modules.base.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.chmobile.ai.modules.base.service.ITdBaseService;
import cn.chmobile.ai.util.PageData;

@Controller  
@RequestMapping("/admin/common")
public class CommonController extends BaseController{
	
	@Autowired
	private ITdBaseService tdBaseService;

    @RequestMapping(value="/findObjectByParam",method=RequestMethod.POST)
    @ResponseBody
    public PageData findObjectByParam(HttpServletRequest request,Model model) throws Exception{
        String doMethod = request.getParameter("doMethod");
        PageData pd=new PageData();
        pd = this.getPageData();
        logger.info("PageData:"+pd.toString());
        PageData pageData = this.tdBaseService.findObjectByParam( Integer.parseInt(pd.getString("id")));
        return pageData;
    }
    
    @RequestMapping(value="/findhHaseByParam",method=RequestMethod.GET)
    @ResponseBody
    public Map<String, String> findhHaseByParam(HttpServletRequest request,Model model) throws Exception{
        PageData pd=new PageData();
        pd = this.getPageData();
        logger.info("PageData:"+pd.toString());
        Map<String, String>  testMap = this.tdBaseService.getTestById(pd.getString("id"));
        logger.info("pageDataList:"+testMap.toString());
        return testMap;
    }
    
    
}