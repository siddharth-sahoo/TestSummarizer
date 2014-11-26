package com.awesome.pro.report;

public interface EnableStatusStep {

	/**
	 * @param enableStatus Whether to enable status column in table.
	 * @return Next step in the report building process.
	 */
	BuildStep enableStatus(final boolean enableStatus);

}
