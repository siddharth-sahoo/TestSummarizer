package com.awesome.pro.report;

import java.util.LinkedList;
import java.util.List;

/**
 * Logical categorization of test cases or scenarios.
 * @author siddharth.s
 */
class TestCategory {

	/**
	 * Name of the test category.
	 */
	private final String category;

	/**
	 * List of tests under this category.
	 */
	private final List<TestScenario> scenarios;

	TestCategory(final String testCategory) {
		this.category = testCategory;
		this.scenarios = new LinkedList<>();
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @return the scenarios
	 */
	public List<TestScenario> getScenarios() {
		return scenarios;
	}

	/**
	 * @param scenarios the scenarios to set
	 */
	public void addScenario(final TestScenario scenario) {
		this.scenarios.add(scenario);
	}

}
