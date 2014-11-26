package com.awesome.pro.report;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.awesome.pro.report.references.ReportReferences;

/**
 * A single report can have multiple tables.
 * @author siddharth.s
 */
public class TestReportTable {

	/**
	 * Unique identifier of the table.
	 */
	private final String name;

	/**
	 * Sub title or description of test objective.
	 */
	private final String subtitle;

	/**
	 * Whether to enable test categorization in the report.
	 */
	private final boolean enableCategorization;

	/**
	 * Whether to enable display of comments in the report.
	 */
	private final boolean enableComments;

	/**
	 * Whether to enable the status column or not.
	 */
	private final boolean enableStatus;

	/**
	 * Whether to enable display of table headers in the report.
	 */
	private final boolean enableHeader;

	/**
	 * Map of keyword to hex code to highlight status fields.
	 * e.g. #27ae60
	 */
	private final Map<String, String> colourCode;

	/**
	 * Specifies table header names in the report.
	 */
	private final Map<HeaderType, String> headerCode;

	/**
	 * List of test results.
	 */
	private final List<TestCategory> results;

	/**
	 * Root logger instance.
	 */
	private static final Logger LOGGER = Logger.getLogger(
			TestReportTable.class);

	public final void addResult(String category, final String testCase,
			final String status, final String comments) {
		if (enableCategorization) {
			if (category == null || category.length() == 0) {
				LOGGER.warn("Category is null or empty. Result was not added.");
				return;
			}
		} else {
			category = ReportReferences.DEFAULT_TEST_CATEGORY;
		}

		if (enableStatus) {
			if (status == null || status.length() == 0) {
				LOGGER.warn("Test status is null or empty. Result was not added.");
			}
		}
		
		if (testCase == null || testCase.length() == 0) {
			LOGGER.warn("Test case is null or empty. Result was not added.");
			return;
		}

		int size = results.size();
		TestCategory testCategory = null;
		int index = 0;

		for (index = 0; index < size; index ++) {
			if (results.get(index).getCategory().equals(category)) {
				testCategory = results.remove(index);
				break;
			}
		}

		if (testCategory == null) {
			testCategory = new TestCategory(category);
		}

		testCategory.addScenario(new TestScenario(testCase, status, comments));
		results.add(testCategory);
	}

	private TestReportTable(final ReportTableBuilder builder) {
		this.name = builder.name;
		
		if (builder.subtitle != null) {
			this.subtitle = builder.subtitle;
		} else {
			this.subtitle = null;
		}

		this.enableCategorization = builder.enableCategorization;
		this.enableComments = builder.enableComments;
		this.enableHeader = builder.enableHeader;
		this.enableStatus = builder.enableStatus;

		if (builder.colourCode.equals(ReportReferences.INITIAL_COLOUR_CODE)) {
			this.colourCode = ReportReferences.DEFAULT_COLOUR_CODE;
		} else {
			this.colourCode = builder.colourCode;
		}
		this.headerCode = builder.headerCode;

		this.results = new LinkedList<>();
	}

	/**
	 * @param name Unique name of the table, will not be appearing anywhere in
	 * the report.
	 * @return Report table builder.
	 */
	static final SubTitleStep getBuilder(final String name) {
		return new ReportTableBuilder(name);
	}

	/**
	 * Report builder which guides in configuration.
	 * @author siddharth.s
	 */
	public static final class ReportTableBuilder implements SubTitleStep,
	EnableCategorizationStep, EnableCommentsStep, EnableHeaderStep,
	EnableStatusStep, BuildStep {

		private final String name;
		private String subtitle;
		private boolean enableCategorization;
		private boolean enableComments;
		private boolean enableHeader;
		private boolean enableStatus;
		private Map<String, String> colourCode;
		private final Map<HeaderType, String> headerCode;

		protected ReportTableBuilder(final String name) {
			this.name = name;
			headerCode = ReportReferences.DEFAULT_HEADER_CODE;
			colourCode = ReportReferences.INITIAL_COLOUR_CODE;
		}

		/* (non-Javadoc)
		 * @see com.awesome.pro.report.builder.BuildStep#addColourCode(java.lang.String, java.lang.String)
		 */
		@Override
		public BuildStep addColourCode(final String key, final String hexCode) {
			if (key == null || hexCode == null ||
					key.length() == 0 || hexCode.length() == 0) {
				throw new IllegalArgumentException(
						"Input is either null or empty.");
			}

			colourCode.put(key, hexCode);
			return this;
		}

		/* (non-Javadoc)
		 * @see com.awesome.pro.report.builder.BuildStep#addHeaderCode(com.awesome.pro.report.HeaderType, java.lang.String)
		 */
		@Override
		public BuildStep addHeaderCode(final HeaderType headerType,
				final String name) {

			if (headerType == null || name == null ||
					name.length() == 0) {
				throw new IllegalArgumentException(
						"Input is either null or empty.");
			}

			headerCode.put(headerType, name);
			return this;
		}

		/* (non-Javadoc)
		 * @see com.awesome.pro.report.builder.BuildStep#build()
		 */
		@Override
		public TestReportTable build() {
			return new TestReportTable(this);
		}
		
		/* (non-Javadoc)
		 * @see com.awesome.pro.report.EnableStatusStep#enableStatus(boolean)
		 */
		@Override
		public BuildStep enableStatus(final boolean enableStatus) {
			this.enableStatus = enableStatus;
			return this;
		}

		/* (non-Javadoc)
		 * @see com.awesome.pro.report.builder.EnableHeaderStep#enableHeader(boolean)
		 */
		@Override
		public EnableStatusStep enableHeader(final boolean enableHeader) {
			this.enableHeader = enableHeader;
			return this;
		}

		/* (non-Javadoc)
		 * @see com.awesome.pro.report.builder.EnableCommentsStep#enableComments(boolean)
		 */
		@Override
		public EnableHeaderStep enableComments(final boolean enableComments) {
			this.enableComments = enableComments;
			return this;
		}

		/* (non-Javadoc)
		 * @see com.awesome.pro.report.builder.EnableCategorizationStep#enableCategorization(boolean)
		 */
		@Override
		public EnableCommentsStep enableCategorization(
				final boolean enableCategorization) {
			this.enableCategorization = enableCategorization;
			return this;
		}

		/* (non-Javadoc)
		 * @see com.awesome.pro.report.builder.EnableCategorizationStep#setSubtitle(java.lang.String)
		 */
		@Override
		public EnableCategorizationStep setSubTitle(final String subtitle) {
			if (subtitle == null || subtitle.length() == 0) {
				throw new IllegalArgumentException(
						"Input is either null or empty.");
			}

			this.subtitle = subtitle;
			return this;
		}

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @return the enableCategorization
	 */
	public boolean isEnableCategorization() {
		return enableCategorization;
	}

	/**
	 * @return the enableComments
	 */
	public boolean isEnableComments() {
		return enableComments;
	}

	/**
	 * @return the enableStatus
	 */
	public boolean isEnableStatus() {
		return enableStatus;
	}

	/**
	 * @return the enableHeader
	 */
	public boolean isEnableHeader() {
		return enableHeader;
	}

	/**
	 * @return the colourCode
	 */
	public Map<String, String> getColourCode() {
		return colourCode;
	}

	/**
	 * @return the headerCode
	 */
	public Map<HeaderType, String> getHeaderCode() {
		return headerCode;
	}

	/**
	 * @return the results
	 */
	public List<TestCategory> getResults() {
		return results;
	}

}
