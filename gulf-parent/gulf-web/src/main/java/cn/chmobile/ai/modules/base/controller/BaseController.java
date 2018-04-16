package cn.chmobile.ai.modules.base.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.chmobile.ai.util.InitConfig;
import cn.chmobile.ai.util.PageData;

public class BaseController {
	
	protected Logger logger=LoggerFactory.getLogger(this.getClass());

	/**
	 * 得到PageData
	 */
	public PageData getPageData(){
		PageData pageData=new PageData(this.getRequest());
		pageData.put("data_source_user_prefix", InitConfig.getProperty("data_source_user_prefix"));
		return pageData;
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}
}
