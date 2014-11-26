package com.awesome.pro.report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.awesome.pro.report.references.ReportReferences;
import com.google.gson.Gson;

/**
 * Represents a single report which can contain multiple
 * test categories.
 * @author siddharth.s
 */
public class TestReport {

	/**
	 * Title of the test report.
	 */
	private final String title;

	/**
	 * List of tables in the report.
	 */
	private final Map<String, TestReportTable> reportTables;

	/**
	 * Root logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(TestReport.class);

	/**
	 * @param name Name of the table to uniquely reference it.
	 * @return If table exists, reference to it, else null. 
	 */
	public final TestReportTable getTable(final String name) {
		if (reportTables.containsKey(name)) {
			return reportTables.get(name);
		}
		
		LOGGER.warn("Specified table not found: " + name);
		return null;
	}

	/**
	 * @param name Name of the table to uniquely reference it.
	 * @return Table builder.
	 */
	public final SubTitleStep getTableBuilder(final String name) {
		return TestReportTable.getBuilder(name);
	}

	public final TestReportTable addTable(final TestReportTable table) {
		reportTables.put(table.getName(), table);
		return table;
	}

	// Private constructor.
	private TestReport(final String title) {
		this.title = title;
		this.reportTables = new HashMap<>();
	}

	/**
	 * @return Report builder instance.
	 * @param title Title of the test report.
	 */
	public static final TestReport getTestReport(final String title) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Report title is null or empty.");
		}
		
		return new TestReport(title);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Publishes the report into an HTML file.
	 * @param fileName Name of file to be used.
	 */
	public final void publish(String fileName) {
		if (fileName == null || fileName.length() == 0) {
			fileName = ReportReferences.DEFAULT_REPORT_NAME;
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(fileName, false));
			writer.write(ReportReferences.HTML_HEAD_TAG_START);
			writer.write(ReportReferences.HTML_TITLE);
			writer.write(ReportReferences.HTML_STYLE_TAG_START);

			// Print style sheet
			InputStream is = this.getClass().getClassLoader().
					getResourceAsStream(ReportReferences.FILE_CSS);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			while (reader.ready()) {
				writer.write(reader.readLine() + '\n');
			}
			reader.close();
			is.close();
			writer.write(ReportReferences.HTML_STYLE_TAG_END);
			
			// Print javascript
			writer.write(ReportReferences.HTML_SCRIPT_TAG_START);
			is = this.getClass().getClassLoader().
					getResourceAsStream(ReportReferences.FILE_JS);
			reader = new BufferedReader(
					new InputStreamReader(is));
			while (reader.ready()) {
				writer.write(reader.readLine() + '\n');
			}
			reader.close();
			is.close();
			writer.write(ReportReferences.HTML_SCRIPT_TAG_END);
			
			// Write result data
			writer.write(ReportReferences.HTML_SCRIPT_TAG_START);
			writer.write(ReportReferences.JS_RESULT_DATA);
			
			Gson gson = new Gson();
			writer.write(gson.toJson(this));
			writer.write(ReportReferences.HTML_SCRIPT_TAG_END);
			writer.write(ReportReferences.HTML_HEAD_TAG_END);
			writer.write(ReportReferences.HTML_BODY);
			
			writer.close();
			LOGGER.info("Report published to " + fileName);
		} catch (IOException e) {
			LOGGER.error("Unable to write to file.", e);
			System.exit(1);
		}
	}

}
