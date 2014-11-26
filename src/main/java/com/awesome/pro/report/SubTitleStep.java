package com.awesome.pro.report;

/**
 * The first step of report building process.
 * @author siddharth.s
 */
public interface SubTitleStep {

	/**
	 * @param title Title of the test report.
	 * @return Next step in report builder.
	 */
	EnableCategorizationStep setSubTitle(final String subTitle);

}
