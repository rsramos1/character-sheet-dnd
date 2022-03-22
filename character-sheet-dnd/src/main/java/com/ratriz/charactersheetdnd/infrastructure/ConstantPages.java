package com.ratriz.charactersheetdnd.infrastructure;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

public final class ConstantPages {

	private static final Integer PAGE = 0;
	private static final Integer SIZE = 10;
	private static final Direction DIRECTION = Direction.ASC;
	private static final String PROPERTIES = "name";

	public static Pageable PAGE_REQUEST = PageRequest.of(ConstantPages.PAGE, ConstantPages.SIZE);
	public static Pageable PAGE_REQUEST_ORDER_NAME = PageRequest.of(ConstantPages.PAGE, ConstantPages.SIZE,
			ConstantPages.DIRECTION, ConstantPages.PROPERTIES.split(","));

	public static final String ROUTE_API = "/api";
	public static final String ROUTE_ALIGNMENT = "/alignment";
	public static final String ROUTE_BACKGROUND = "/background";
	public static final String ROUTE_BACKGROUND_TYPE = "/background-type";
	public static final String ROUTE_BONDS = "/bonds";
	public static final String ROUTE_CHARACTER_NAME = "/character-name";
	public static final String ROUTE_CLASS = "/class";
	public static final String ROUTE_EYES = "/eyes";
	public static final String ROUTE_FAMILY_NAME = "/family-name";
	public static final String ROUTE_FLAWS = "/flaws";
	public static final String ROUTE_HAIR = "/hair";
	public static final String ROUTE_IDEALS = "/ideals";
	public static final String ROUTE_PERSONALITY_TRAITS = "/personality-traits";
	public static final String ROUTE_RACE = "/race";
	public static final String ROUTE_SKIN = "/skin";
	public static final String ROUTE_SUBCLASS = "/subclass";
	public static final String ROUTE_SUBRACE = "/subrace";
	public static final String ROUTE_USER = "/user";

	public static final String ROUTE_DTO = "/dto";
	public static final String ROUTE_LIST = "/list";
	public static final String ROUTE_SEARCH = "/search";
	public static final String ROUTE_ACTION = "/action";
	public static final String ROUTE_CHANGE_STATUS = "/change-status";
	public static final String ROUTE_RANDOM = "/random";
	public static final String ROUTE_LOGIN = "/login";
	public static final String ROUTE_SIGN_UP = "/signUp";
	public static final String ROUTE_CHANGE_PASSWORD = "/change-password";

	public static final String ATTRIBUTE_ID = "id";

	public static final String ROUTE_ATTRIBUTE_ID = "/{" + ATTRIBUTE_ID + "}";

}
