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

	public static final String PAGE_API = "/api";
	public static final String PAGE_ALIGNMENT = "/alignment";
	public static final String PAGE_BACKGROUND = "/background";
	public static final String PAGE_BACKGROUND_TYPE = "/background-type";
	public static final String PAGE_BONDS = "/bonds";
	public static final String PAGE_CLASS = "/class";
	public static final String PAGE_EYES = "/eyes";
	public static final String PAGE_FAMILY_NAME = "/family-name";
	public static final String PAGE_FLAWS = "/flaws";
	public static final String PAGE_HAIR = "/hair";
	public static final String PAGE_IDEALS = "/ideals";
	public static final String PAGE_CHARACTER_NAME = "/character-name";
	public static final String PAGE_PERSONALITY_TRAITS = "/personality-traits";
	public static final String PAGE_RACE = "/race";
	public static final String PAGE_SKIN = "/skin";
	public static final String PAGE_SUBCLASS = "/subclass";
	public static final String PAGE_SUBRACE = "/subrace";

	public static final String PAGE_DTO = "/dto";
	public static final String PAGE_LIST = "/list";
	public static final String PAGE_SEARCH = "/search";
	public static final String PAGE_ACTION = "/action";
	public static final String PAGE_CHANGE_STATUS = "/change-status";
	public static final String PAGE_RANDOM = "/random";

	public static final String ATTRIBUTE_ID = "id";

	public static final String PAGE_ATTRIBUTE_ID = "/{" + ATTRIBUTE_ID + "}";

}
