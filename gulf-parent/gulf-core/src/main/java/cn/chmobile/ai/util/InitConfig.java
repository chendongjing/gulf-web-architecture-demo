package cn.chmobile.ai.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 *
 * @author 作者：LAM
 * @version 创建时间：2017-11-28 上午11:40:32
 * 
 * 类说明：加载config.properties配置文件
 */
public class InitConfig {
	private static Log logger = LogFactory.getLog(InitConfig.class);
	
	private InitConfig(){}
	
	private static Properties props = new Properties();
	
	public static void init(){
		loadProps();
	}
	
	synchronized static private void loadProps(){
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
        	//第一种，通过类加载器进行获取properties文件流
            in = InitConfig.class.getClassLoader().getResourceAsStream("config.properties");
            //第二种，通过类进行获取properties文件流
            //in = InitConfig.class.getResourceAsStream("/config.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("jdbc.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("jdbc.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props);
    }
	
	   public static String getProperty(String key){
	        if(null == props) {
	            loadProps();
	        }
	        return props.getProperty(key);
	    }
	   
	   public static String getProperty(String key, String defaultValue) {
	        if(null == props) {
	            loadProps();
	        }
	        return props.getProperty(key, defaultValue);
	    }
}
