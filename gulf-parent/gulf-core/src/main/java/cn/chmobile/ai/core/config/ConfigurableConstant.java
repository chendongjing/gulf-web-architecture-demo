package cn.chmobile.ai.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 可用Properties文件配置的Constants基类.
 * 本类既保持了Constants的static和final(静态与不可修改)特性,又拥有了可用Properties文件配置的特征,
 * 主要是应用了Java语言中静态初始化代码的特性.子类可如下编写
 *
 * public class Constants extends ConfigurableConstant { static {
 * init("springside.properties"); }
 *
 * @author Felix
 */
public class ConfigurableConstant {
	protected static Logger logger = LoggerFactory.getLogger(ConfigurableConstant.class);
	protected static Properties p = new Properties();

	/**
	 * 静态读入属性文件到Properties p变量中
	 */
	protected static void init(String propertyFileName) {
		InputStream in = null;
		try {
			in = ConfigurableConstant.class.getClassLoader().getResourceAsStream(propertyFileName);
			if (in != null)
				p.load(in);
		} catch (IOException e) {
			logger.error("load " + propertyFileName + " into Constants error!");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close " + propertyFileName + " error!");
				}
			}
		}
	}

	/**
	 * 封装了Properties类的getProperty函数,使p变量对子类透明.
	 *
	 * @param key
	 *            property key.
	 * @param defaultValue
	 *            当使用property key在properties中取不到值时的默认值.
	 */
	protected static String getProperty(String key, String defaultValue) {
		try {
			String systemProperty = System.getProperty(key);
			if (systemProperty != null) {
				return systemProperty;
			}
			
			String value =  p.getProperty(key);

			if (value == null || value.isEmpty())
				return defaultValue;
			return new String(value.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("error in ConfigurableConstants.getProperty()" + p.getProperty(key, defaultValue));
		}
		return defaultValue;
	}
}