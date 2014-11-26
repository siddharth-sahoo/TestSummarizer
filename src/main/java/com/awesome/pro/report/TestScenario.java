package com.awesome.pro.report;

/**
 * A test scenario represents the most basic unit of result
 * summarization.
 * @author siddharth.s
 */
class TestScenario {

	/**
	 * Name or description of test.
	 */
	private final String name;

	/**
	 * Status of the test.
	 * e.g. passed, failed etc.
	 */
	private final String status;

	/**
	 * Additional details for the test.
	 * Optional field.
	 */
	private final String comments;

	TestScenario(final String name, final String status,
			final String comments) {
		this.name = name;
		this.status = status;
		this.comments = comments;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

}
