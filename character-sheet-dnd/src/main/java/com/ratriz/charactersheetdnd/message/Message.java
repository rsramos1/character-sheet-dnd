package com.ratriz.charactersheetdnd.message;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

public class Message {

	@Value("com.ratriz.charactersheetdnd.message")
	private String messageProperties;

	private final String key;

	public Message(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public String getMessage() {
		return getProperties().getProperty(this.key);
	}

	public Properties getProperties() {
		try {
			Properties props = new Properties();
			props.load(this.getClass().getResourceAsStream(messageProperties));
			return props;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String get(String key) {
		return new Message(key).getMessage();
	}

}
