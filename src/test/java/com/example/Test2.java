package com.example;

import com.awesome.pro.report.TestReport;
import com.awesome.pro.report.TestReportTable;

public class Test2 {

	public static void main(String[] args) {
		TestReport report = TestReport.getTestReport("Spirit");

		TestReportTable table1 = report.addTable(
				report.getTableBuilder("table1")
				.setSubTitle("Data Quality")
				.enableCategorization(true)
				.enableComments(true)
				.enableHeader(true)
				.enableStatus(true)
				.build());

		table1.addResult("OE Data Points", "Page Loads", "Passed", "0% deviation.");
		table1.addResult("OE Data Points", "Hot Leads", "Passed", "0.2% deviation.");
		table1.addResult("OE Data Points", "Hot Leads", "Passed", "0.2% deviation.");
		table1.addResult("Assist Data Points", "Interactive Chats", "Skipped", "Data not found.");

		TestReportTable table2 = report.addTable(
				report.getTableBuilder("table2")
				.setSubTitle("Configuration Testing")
				.enableCategorization(false)
				.enableComments(false)
				.enableHeader(true)
				.enableStatus(false)
				.build());

		table2.addResult(null, "Check data point mappings.", null, null);
		table2.addResult(null, "Check report table mappings.", null, null);
		table2.addResult(null, "Check log folder paths.", null, null);

		report.publish("asdzxc.html");
	}

}
