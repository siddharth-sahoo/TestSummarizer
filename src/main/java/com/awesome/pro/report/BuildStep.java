package com.awesome.pro.report;


/**
 * Represents the final step in the report building process.
 * @author siddharth.s
 */
public interface BuildStep {

	/**
	 * @param key Key to compared for indicating status. e.g. passed, failed.
	 * @param hexCode Background color described in hex values. e.g. #8e44ad.
	 * @return Next step in build process.
	 */
	BuildStep addColourCode(final String key, final String hexCode);

	/**
	 * @param headerType Header type.
	 * @param name Name for the corresponding header type.
	 * @return Next step in build process.
	 */
	BuildStep addHeaderCode(final HeaderType headerType, final String name);

	/**
	 * @return Configures the report and returns it.
	 */
	TestReportTable build();

}
