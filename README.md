TestSummarizer
==============

Generic test summary report builder.

Sample Code:
````java
TestReport report = TestReport.getBuilder()
				.setTitle("Spirit")
				.setSubtitle("Comparison of Spirit counters against raw logs.")
				.enableCategorization(true)
				.enableComments(true)
				.enableHeader(true)
				.addHeaderCode(HeaderType.CATEGORY, "Test Type")
				.addHeaderCode(HeaderType.SCENARIO, "Test Case")
				.addHeaderCode(HeaderType.STATUS, "Status")
				.build();
				
report.addResult("Sanity", "Data flow", "Passed",
	"Pipeline setup was verified.");
report.addResult("Data Quality", "Count of Page Loads", "Passed",
	"Count difference was 0.53%.");
report.addResult("Data Quality", "Count of Hot Leads", "Failed",
	"Count mismatch was greater than 1%.");
report.addResult("Data Quality", "Count of Invites Offered", "Skipped",
	"No events were found.");
		
report.publish("asd.html");
````

Here is how the above report would look like:
![Sample Report](https://github.com/siddharth-sahoo/TestSummarizer/blob/master/SampleReport.png)
