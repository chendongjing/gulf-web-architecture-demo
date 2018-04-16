package cn.chmobile.ai.core.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppMessage {

	private List<String> valueList = new ArrayList<String>();

	private String key;

	private List<String> properties = new ArrayList<String>();

	public AppMessage(String key, String... values) {

		this.key = key;

		if (values != null && values.length > 0) {
			this.valueList = Arrays.asList(replaceNullToBlank(values));
		}
	}

	public AppMessage(List<String> properties, String key, String... values) {

		this.key = key;
		if (properties != null) {
			for (int i = 0; i < properties.size(); i++) {
				this.properties.add(properties.get(i));
			}
		}

		if (values != null && values.length > 0) {
			this.valueList = Arrays.asList(replaceNullToBlank(values));
		}
	}

	public String getKey() {
		return key;
	}

	public List<String> getProperties() {
		return properties;
	}

	public List<String> getValueList() {
		return valueList;
	}

	private String[] replaceNullToBlank(String[] ary) {

		for (int i = 0; i < ary.length; i++) {
			if (ary[i] == null) {
				ary[i] = "";
			}
		}

		return ary;
	}
}
