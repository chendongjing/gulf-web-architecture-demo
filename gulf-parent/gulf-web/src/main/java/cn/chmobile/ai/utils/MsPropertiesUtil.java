package cn.chmobile.ai.utils;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class MsPropertiesUtil {
	private static Logger logger = Logger.getLogger(PropertiesUtil.class.getName());
    /**
     * 根据key获得value
     * @param key
     * @return value
     * @throws IOException 
     */
    public static String getValue(String resourceString,String key) throws IOException {
        String value="";
        Resource resource = new ClassPathResource(resourceString);
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            value=props.getProperty(key);
        } catch (IOException e) {
            logger.error("读取配置文件失败", e);
            throw new IOException("读取配置文件失败");
        }
        return value;
    }
    /**
     * 更新配置文件
     * @param key
     * @param value
     * @throws IOException
     */
    public static void setValue(String key,String value) throws IOException{
        try{
            Resource resource = new ClassPathResource("business.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            OutputStream fos = new FileOutputStream(resource.getFile());
            props.setProperty(key, value);
            props.store(fos, "update"+key);
        }catch(Exception e){
            logger.error("写入配置文件失败", e);
            throw new IOException("写入配置文件失败");
        }
        
    }

}
