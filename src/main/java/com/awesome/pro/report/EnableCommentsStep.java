package com.awesome.pro.report;

/**
 * Represents the third step in the report building process.
 * @author siddharth.s
 */
public interface EnableCommentsStep {

	/**
	 * @param enableComments Whether to enable display of comments.
	 * @return Next step in build process.
	 */
	EnableHeaderStep enableComments(final boolean enableComments);

}
