package com.ratriz.charactersheetdnd.util;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public class ParseUtil {

	public static Long parseLong(String value) {
		return parseLongOrDefault(value, null);
	}

	public static Long parseLongOrDefault(String value, Long defaultValue) {
		return Optional.ofNullable(value).map(str -> {
			try {
				return Long.parseLong(str);
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		}).orElse(defaultValue);
	}

	public static Integer parseInt(String value) {
		return parseIntOrDefault(value, null);
	}

	public static Integer parseIntOrDefault(String value, Integer defaultValue) {
		return Optional.ofNullable(value).map(str -> {
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		}).orElse(defaultValue);
	}

	public static Character parseCharacter(String value) {
		return parseCharacterOrDefault(value, null);
	}

	public static Character parseCharacterOrDefault(String value, Character defaultValue) {
		return StringUtils.length(value) != 1 ? defaultValue : value.charAt(0);
	}

	public static Boolean parseBoolean(String value) {
		return parseBooleanOrDefault(value, null);
	}

	public static Boolean parseBooleanOrDefault(String value, Boolean defaultValue) {
		return Optional.ofNullable(value).map(b -> {
			Boolean bResult = Boolean.parseBoolean(value);
			if (!bResult) {
				if (value.equalsIgnoreCase("1") || value.equalsIgnoreCase("t")) {
					bResult = Boolean.TRUE;
				} else if (!value.equalsIgnoreCase("false") && !value.equalsIgnoreCase("0")
						&& !value.equalsIgnoreCase("f")) {
					bResult = defaultValue;
				}
			}
			return bResult;
		}).orElse(defaultValue);
	}

}
