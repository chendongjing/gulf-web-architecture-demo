package cn.chmobile.ai.core.i18n;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.chmobile.ai.core.config.AppResource;

public class AppMessage {

	private static final Pattern MESSAGE_PARAMETER_PATTERN = Pattern.compile("(\\{[^\\}]+?\\})"); 
	
	private Level level = Level.INFO;
	private String key;
	private Object[] args;
	protected Map<String, String> replacements;
	
	
	public static String getTemplate(String key){
		AppResource messages = AppResource.getResource(AppResource.MESSAGES_RESOURCE_KEY);
		if(messages != null && messages.containsKey(key)){
			return messages.getValue(key);
		}
		return null;
	}

	public String getMessage(){
		String temp = AppMessage.getTemplate(getKey());
		if(temp == null){
			temp = getKey();
			if(temp != null && temp.startsWith("{") && temp.endsWith("}")){
				temp = temp.substring(1, temp.length()-1);
			}
		}

		for(Entry<String, String> e : replacements.entrySet()){
			temp = temp.replace(String.format("{%s}", e.getKey()), e.getValue());
		}

		String message = MessageFormat.format(temp, getArgs());
		message = replaceVariables(message);
		
		return message;
	}

	public AppMessage(String key,Object...args) {
		this(null, key, args);
	}

	public AppMessage(Level level, String key,Object...args) {
		if(level != null){
			this.level = level;
		}
		this.key = key;
		this.args = args;
		replacements = new HashMap<String, String>();
		replacements.put("prefix", "");//for default. XXX for what?
	}

	public String getKey() {
		return key;
	}

	public Object[] getArgs() {
		return args;
	}


	public Level getLevel() {
		return level;
	}

	public static enum Level{
		INFO,
		WARN,
		ERROR;
	}

	private String replaceVariables(String message) { 
		final Matcher matcher = MESSAGE_PARAMETER_PATTERN.matcher(message); 
		final StringBuffer stringBuffer = new StringBuffer(); 
		String resolvedParameterValue; 
		while (matcher.find()) { 
			final String parameter = matcher.group(1); 
			resolvedParameterValue = AppMessage.getTemplate(removeCurlyBrace(parameter));
			matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(resolvedParameterValue)); 
		} 
		matcher.appendTail(stringBuffer); 
		return stringBuffer.toString(); 
	} 

	private String removeCurlyBrace(String parameter) { 
		return parameter.substring(1, parameter.length() - 1); 
	} 

}
