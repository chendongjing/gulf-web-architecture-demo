package cn.chmobile.ai.modules.sys.tags;

import javax.servlet.http.Cookie;
import cn.chmobile.ai.utils.JeewebPropertiesUtil;
import cn.chmobile.ai.utils.ServletUtils;
import cn.chmobile.ai.utils.StringUtils;

/**
 * 
 * All rights Reserved, Designed By www.zhisuaninfo.com
 * 
 * @title: SysFunctions.java
 * @package cn.chmobile.ai.modules.sys.tags
 * @description: 提供一些公用的函数
 * @author: lam
 * @date: 2017年9月28日 下午10:04:07
 * @version V1.0
 * @copyright: 2017 www.zhisuaninfo.com Inc. All rights reserved.
 *
 */
public class SysFunctions {
	/**
	 * 获得后台地址
	 * 
	 * @title: getAdminUrlPrefix
	 * @description: 获得后台地址
	 * @return
	 * @return: String
	 */
	public static String getAdminUrlPrefix() {
		String adminUrlPrefix = JeewebPropertiesUtil.getConfig("admin.url.prefix");
		return adminUrlPrefix;
	}

	/**
	 * 获得后台地址
	 * 
	 * @title: getAdminUrlPrefix
	 * @description: 获得后台地址
	 * @return
	 * @return: String
	 */
	public static String get() {
		String adminUrlPrefix = JeewebPropertiesUtil.getConfig("admin.url.prefix");
		return adminUrlPrefix;
	}

	/**
	 * 加载风格
	 * 
	 * @title: getTheme
	 * @description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @return
	 * @return: String
	 */
	public static String getTheme() {
		// 默认风格
		String theme = JeewebPropertiesUtil.getConfig("admin.default.theme");
		if (StringUtils.isEmpty(theme)) {
			theme = "uadmin";
		}
		
		// cookies配置中的模版
		/*
		Cookie[] cookies = ServletUtils.getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
				continue;
			}
			if (cookie.getName().equalsIgnoreCase("theme")) {
				theme = cookie.getValue();
			}
		}
		*/
		
		return theme;
	}
}
