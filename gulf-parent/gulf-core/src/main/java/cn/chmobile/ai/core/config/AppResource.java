package cn.chmobile.ai.core.config;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import cn.chmobile.ai.core.exception.JeeyxyException;
import cn.chmobile.ai.core.log.AppLog;

public final class AppResource {

	public static final String MESSAGES_RESOURCE_KEY = AppResource.class.getName() + ".MESSAGES_RESOURCE";
	public static final String DEFAULT_RESOURCE_KEY = AppResource.class.getName() + ".DEFAULT_RESOURCE";
	public static final String OTHER_RESOURCES_KEY = AppResource.class.getName() + ".OTHER_RESOURCES";

	private static Map<String, AppResource> resources;

	private ResourceBundle resourceBundle;

	private String name;

	private AppResource(String name,ResourceBundle resourceBundle) {
		this.name = name;
		this.resourceBundle = resourceBundle;
	}


	public static AppResource getResource(){
		return getResource(DEFAULT_RESOURCE_KEY);
	}

	public static AppResource getResource(String resource){
		if(resources == null){
			load();
		}

		if(!resources.containsKey(resource)){
			throw new JeeyxyException(resource + " is missing.Check smbc.properties.");
		}

		return resources.get(resource);
	}

	public static void load(){
		AppLog.i(AppResource.class,"load app-resource start");

		resources = new HashMap<String, AppResource>();

		ResourceBundle rb = getBundle("jeeyxy");
		if(rb == null){
			throw new JeeyxyException("smbc.properties is missing.");
		}

		AppLog.i(AppResource.class,"initialize app-resource end");
	}

	private static ResourceBundle getBundle(String name){
		try{
			ResourceBundle rb = ResourceBundle.getBundle(name, Locale.getDefault());
			return rb;
		} catch (MissingResourceException e) {
			AppLog.d(AppResource.class,"can't find resource {}.properties",name);
			return null;
		}
	}

	public String getValue(String key, String defaultValue){
		try {
			String value =  resourceBundle.getString(key);
			if (value == null || value.isEmpty())
				return defaultValue;
			return value;
		} catch (MissingResourceException e) {
			AppLog.d(AppResource.class,"missing resource name:{} key:{}",name,key);
			return null;
		}
	}

	public String getValue(String key){
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			AppLog.d(AppResource.class,"missing resource name:{} key:{}",name,key);
			return null;
		}
	}

	public boolean containsKey(String key){
		return resourceBundle.containsKey(key);
	}


	public Set<String> keySet(){
		return resourceBundle.keySet();
	}

	public boolean isEmpty(){
		return resourceBundle.keySet().size() == 0;
	}
}