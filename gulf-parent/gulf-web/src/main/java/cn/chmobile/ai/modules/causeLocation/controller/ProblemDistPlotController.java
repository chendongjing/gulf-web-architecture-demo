package cn.chmobile.ai.modules.causeLocation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
 *根因定位-问题小区画像控制类
 *
 */
@Controller
@RequestMapping(value =  "/home/prolemDistPlot")
public class ProblemDistPlotController extends BaseController {
	@Autowired
	private ITdBaseService tdBaseService;
	
    @RequestMapping(value="/getEciList.json",method=RequestMethod.GET)
    @ResponseBody
    public Object getEciList(HttpServletRequest request,Model model) throws Exception{

    	PageData pd=new PageData();
        pd = this.getPageData();
        
       // ArrayList<PageData> list =  new ArrayList<PageData>();
         List<PageData> list = this.tdBaseService.findListByParam("rpt_d_city_dist_eci_cause", pd);
        /*
        if(list.isEmpty()) {//造一组数据 ,正式开发时需要去掉
        	int max=100;
            int min=1;
            
        	for(int i = 0; i < 10 ; i++) {
        		Random random = new Random();
                int num = random.nextInt(max)%(max-min+1) + min;
        		PageData pageData = new PageData();
        		
        		if(i % 3 == 0) {
        			pageData.put("eciName", "小区_"+num);
            		pageData.put("lng", (double)(1102048+num)/10000);
            		pageData.put("lat", (double)(200497-num)/10000);
        			pageData.put("jzName", "基站_jz000"+num);
        			pageData.put("jz_lng", (double)(1102058+num)/10000);
            		pageData.put("jz_lat", (double)(200467+num)/10000);
        		}else {
        			pageData.put("eciName", "小区_"+num);
            		pageData.put("lng", (double)(1102048-num)/10000);
            		pageData.put("lat", (double)(200497-num)/10000);
        		}
        	
        		list.add(i, pageData);
        	}
        	
        }
        */
		return new RestResult<List<PageData>>(true, list);
         
    }
    

}
