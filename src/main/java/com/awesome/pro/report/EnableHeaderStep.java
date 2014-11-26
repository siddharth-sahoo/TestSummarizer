package com.awesome.pro.report;

/**
 * Represents the fourth step in report building process.
 * @author siddharth.s
 */
public interface EnableHeaderStep {

	/**
	 * @param enableHeader Whether to enable table headers.
	 * @return Next step in the report building process.
	 */
	EnableStatusStep enableHeader(final boolean enableHeader);

}
