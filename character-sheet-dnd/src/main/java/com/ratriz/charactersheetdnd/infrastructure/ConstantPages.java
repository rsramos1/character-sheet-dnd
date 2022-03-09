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
	public static final String PAGE_BACKGROUND = "/background";
	public static final String PAGE_BACKGROUND_TYPE = "/type";

	public static final String PAGE_DTO = "/dto";
	public static final String PAGE_LIST = "/list";
	public static final String PAGE_SEARCH = "/search";
	public static final String PAGE_ACTION = "/action";
	public static final String PAGE_CHANGE_STATUS = "/changeStatus";
	public static final String PAGE_RANDOM = "/random";

}
