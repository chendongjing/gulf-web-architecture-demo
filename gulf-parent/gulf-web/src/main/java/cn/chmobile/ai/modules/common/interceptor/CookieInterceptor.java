package cn.chmobile.ai.modules.common.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.chmobile.ai.utils.CookieUtils;
import cn.chmobile.ai.utils.MsPropertiesUtil;
/**
 * 
 * 对外接口拦截器,权限控制
 *
 */
public class CookieInterceptor extends HandlerInterceptorAdapter {

	protected final Log logger = LogFactory.getLog(getClass());
 

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {
		String mscookieName = null;
		String businessUrl = null;
		String mCookieValue = null;
		String mCookieSwith = "off";
		
		try {
			mscookieName = MsPropertiesUtil.getValue("/business.properties", "ms.cookie.name");
			mCookieValue = MsPropertiesUtil.getValue("/business.properties", "ms.cookie.value");
			businessUrl = MsPropertiesUtil.getValue("/business.properties", "business.url");
			mCookieSwith = MsPropertiesUtil.getValue("/business.properties", "ms.cookie.swicth");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(!"on".equals(mCookieSwith)) {
			return true;
		}
		
		String bCookieValue =  CookieUtils.getCookie(request, mscookieName);
		if(bCookieValue == null || "".equals(bCookieValue)) {
			try {
				response.sendRedirect(businessUrl);
				return false;
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
		}
		 
		if(bCookieValue.equals(mCookieValue)) {
			return true;
		}
	 
		try {
			response.sendRedirect(businessUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		return false;
		
	}

}
