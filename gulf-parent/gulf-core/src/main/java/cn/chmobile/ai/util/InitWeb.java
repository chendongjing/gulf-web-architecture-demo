package cn.chmobile.ai.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 
 *
 * @author 229260837@qq.com
 * @version 创建时间：2017-11-28 上午11:38:23
 * 
 * 类说明：服务初始化加载数据，同时监听服务是否启动关闭
 */
public class InitWeb implements ServletContextListener{
	
	private static Log log = LogFactory.getLog(InitWeb.class);
	
	static{
		try{
			//初始化加载资源文件信息
			InitConfig.init();
		}catch(Exception e){
			log.info("服务初始化加载数据出错：" + e.getMessage(), e);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
	/**
	 * 服务启动完毕触发事件
	 */
	public void contextInitialized(ServletContextEvent sce) {
		//服务启动完毕加载字典数据
		//DictionaryUtil.initDictionary();
		
		/*CacheUtil.initUserGuide();
		
		CacheUtil.initMonth();*/
	}
}
