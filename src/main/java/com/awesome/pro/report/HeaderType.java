package com.awesome.pro.report;

/**
 * Enumeration of the various header types in a report.
 * @author siddharth.s
 */
public enum HeaderType {

	CATEGORY, SCENARIO, STATUS, COMMENTS;

	/**
	 * @param headerType Header type.
	 * @return Name to be used in data field of report.
	 */
	public static final String getName(final HeaderType headerType) {
		return headerType.toString().toLowerCase();
	}

}
