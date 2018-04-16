package cn.chmobile.ai.core.message;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageService {

	private static Logger log = LoggerFactory.getLogger(MessageService.class);

	private static Properties prop = null;

	private MessageService() {
	}

	public static void loadMessage(String... baseNames) {

		if (prop == null) {

			if (baseNames == null || baseNames.length == 0) {
				throw new NullPointerException("baseNames is either null or length zero.");
			}

			prop = new Properties();
			ResourceBundle bundle;

			for (String baseName : baseNames) {

				bundle = ResourceBundle.getBundle(baseName);

				Enumeration<String> enume = bundle.getKeys();

				while (enume.hasMoreElements()) {
					String key = enume.nextElement();
					prop.setProperty(key, bundle.getString(key));
				}

				log.info(null, null, baseName + " has been read successfully.", null);
			}
		}
	}

	public static String getMessage(String key, String... values) {

		if (!containsKey(key)) {
			return "";
		}

		String message = prop.getProperty(key);

		if (values == null || values.length == 0) {
			return message;
		}

		for (int i = 0; i < values.length; i++) {

			if (getMessage(values[i])!=null) {
				values[i] = prop.getProperty(values[i]);
			}
		}

		try {
			message = MessageFormat.format(message, (Object[]) values);
		} catch (IllegalArgumentException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("[message:");
			buf.append(message);
			buf.append("],[values:");

			for (String value : values) {
				buf.append(value);
				buf.append(",");
			}
			buf.append("] can not be formatted");

			log.info(null, null, buf.toString(), null);
		}

		return message;
	}

	public static String getMessage(AppMessage appMessage) {

		if (!containsKey(appMessage.getKey())) {
			return "";
		}

		String message = prop.getProperty(appMessage.getKey());

		if (appMessage.getValueList() == null || appMessage.getValueList().size() == 0) {
			return message;
		}
		String[] values = new String[appMessage.getValueList().size()];
		for (int i = 0; i < appMessage.getValueList().size(); i++) {
			if (getMessage(appMessage.getValueList().get(i))!= null) {
				values[i] = prop.getProperty(appMessage.getValueList().get(i));
			} else if (appMessage.getValueList().get(i) != null) {
				values[i] = appMessage.getValueList().get(i);
			}
		}

		try {
			message = MessageFormat.format(message, (Object[]) values);
		} catch (IllegalArgumentException e) {
			StringBuffer buf = new StringBuffer();
			buf.append("[message:");
			buf.append(message);
			buf.append("],[values:");

			for (String value : values) {
				buf.append(value);
				buf.append(",");
			}
			buf.append("] can not be formatted");

			log.info(null, null, buf.toString(), null);
		}

		return message;
	}

	public static boolean containsKey(String key) {

		if (prop == null) {
			log.warn(null, null, "Properties is null. Please call loadMessage method before calling this method.", null);
			return false;
		}

		if (key == null) {
			return false;
		}

		return prop.containsKey(key);
	}
}
