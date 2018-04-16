package cn.chmobile.ai.modules.wholeNetFacts.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.chmobile.ai.modules.base.controller.BaseController;
import cn.chmobile.ai.modules.base.service.ITdBaseService;
import cn.chmobile.ai.util.PageData;

@Controller
@RequestMapping("/admin/wholeNetFact")
public class WholeNetFacts extends BaseController{
	@Autowired
	private ITdBaseService tdBaseService;
	
	/**
	 * 全网分布（问题小区和正常小区比例和数目）
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/showScalePie",method=RequestMethod.GET)
    @ResponseBody
    public Map<String, String> showWholeNetFact(HttpServletRequest request,Model model) throws Exception{
        PageData pd=new PageData();
        pd = this.getPageData();
        logger.info("PageData:"+pd.toString());
        Map<String, String>  testMap = this.tdBaseService.getTestById(pd.getString("id"));
        logger.info("pageDataList:"+testMap.toString());
        return testMap;
    }
	
	/**
	 * 问题小区地域分布比例和数目（地市）
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/showAreaPie",method=RequestMethod.GET)
    @ResponseBody
    public Map<String, String> showAreaPie(HttpServletRequest request,Model model) throws Exception{
        PageData pd=new PageData();
        pd = this.getPageData();
        logger.info("PageData:"+pd.toString());
        Map<String, String>  testMap = this.tdBaseService.getTestById(pd.getString("id"));
        logger.info("pageDataList:"+testMap.toString());
        return testMap;
    }
	
	/**
	 * 问题小区数量月趋势图
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/tendencyChart",method=RequestMethod.GET)
    @ResponseBody
    public Map<String, String> tendencyChart(HttpServletRequest request,Model model) throws Exception{
        PageData pd=new PageData();
        pd = this.getPageData();
        logger.info("PageData:"+pd.toString());
        Map<String, String>  testMap = this.tdBaseService.getTestById(pd.getString("id"));
        logger.info("pageDataList:"+testMap.toString());
        return testMap;
    }
	
	/**
	 * 问题小区问题类别表现数目
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/classChart",method=RequestMethod.GET)
    @ResponseBody
    public Map<String, String> classChart(HttpServletRequest request,Model model) throws Exception{
        PageData pd=new PageData();
        pd = this.getPageData();
        logger.info("PageData:"+pd.toString());
        Map<String, String>  testMap = this.tdBaseService.getTestById(pd.getString("id"));
        logger.info("pageDataList:"+testMap.toString());
        return testMap;
    }
	
	
	
    

}
