package com.awesome.pro.report;

/**
 * Second step of report building process.
 * @author siddharth.s
 */
public interface EnableCategorizationStep {

	/**
	 * @param enableCategorization Whether to enable test categorization.
	 * @return Next step in build process.
	 */
	EnableCommentsStep enableCategorization(final boolean enableCategorization);

}
