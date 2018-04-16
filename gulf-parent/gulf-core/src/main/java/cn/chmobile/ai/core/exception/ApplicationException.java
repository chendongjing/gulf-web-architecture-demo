package cn.chmobile.ai.core.exception;

import java.util.HashMap;
import java.util.Map;

import cn.chmobile.ai.core.i18n.AppMessage;


public class ApplicationException extends Exception {
	private static final long serialVersionUID = -983543228322220916L;

	private AppMessage[] appMessages;

	private Map<String, String> attributes = new HashMap<String, String>();

	public ApplicationException(AppMessage... appMessages) {
		this.appMessages = appMessages;
	}

	protected ApplicationException(String s) {
		super(s);
	}

	protected ApplicationException(String s,Throwable t) {
		super(s, t);
	}

	protected void setMessages(AppMessage... appMessages){
		this.appMessages = appMessages;
	}

	public AppMessage[] getAppMessages() {
		if(appMessages != null){
			return appMessages;
		}
		return new AppMessage[0];
	}

	public String getAttribute(String key){
		return attributes.get(key);
	}

	public void putAttribute(String key,String value){
		attributes.put(key, value);
	}

	@Override
	public String getMessage() {
		return super.getMessage()!=null?super.getMessage():"application error";
	}
}