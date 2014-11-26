package com.awesome.pro.report.references;

import java.util.HashMap;
import java.util.Map;

import com.awesome.pro.report.HeaderType;

public class ReportReferences {
	
	// Default configurations.
	public static final String DEFAULT_TEST_CATEGORY = "Default";

	// Default color code keys.
	private static final String COLOUR_CODE_KEY_1 = "Default";
	private static final String COLOUR_CODE_KEY_2 = "Passed";
	private static final String COLOUR_CODE_KEY_3 = "Failed";
	private static final String COLOUR_CODE_KEY_4 = "Skipped";

	// Default color code hex values.
	private static final String COLOUR_CODE_HEX_1 = "#95a5a6";
	private static final String COLOUR_CODE_HEX_2 = "#27ae60";
	private static final String COLOUR_CODE_HEX_3 = "#c0392b";
	private static final String COLOUR_CODE_HEX_4 = "#f39c12";

	// Default color code map.
	public static final Map<String, String> DEFAULT_COLOUR_CODE = new HashMap<>();
	static {
		DEFAULT_COLOUR_CODE.put(COLOUR_CODE_KEY_1, COLOUR_CODE_HEX_1);
		DEFAULT_COLOUR_CODE.put(COLOUR_CODE_KEY_2, COLOUR_CODE_HEX_2);
		DEFAULT_COLOUR_CODE.put(COLOUR_CODE_KEY_3, COLOUR_CODE_HEX_3);
		DEFAULT_COLOUR_CODE.put(COLOUR_CODE_KEY_4, COLOUR_CODE_HEX_4);
	}

	// Initial color code map.
	public static final Map<String, String> INITIAL_COLOUR_CODE = new HashMap<>();
	static {
		INITIAL_COLOUR_CODE.put(COLOUR_CODE_KEY_1, COLOUR_CODE_HEX_1);
	}

	// Default header code keys.
	private static final HeaderType HEADER_CODE_KEY_1 = HeaderType.CATEGORY;
	private static final HeaderType HEADER_CODE_KEY_2 = HeaderType.SCENARIO;
	private static final HeaderType HEADER_CODE_KEY_3 = HeaderType.STATUS;
	private static final HeaderType HEADER_CODE_KEY_4 = HeaderType.COMMENTS;

	// Default header code names.
	private static final String HEADER_CODE_NAME_1 = "Test Category";
	private static final String HEADER_CODE_NAME_2 = "Test Case";
	private static final String HEADER_CODE_NAME_3 = "Status";
	private static final String HEADER_CODE_NAME_4 = "Comments";

	public static final Map<HeaderType, String> DEFAULT_HEADER_CODE = new HashMap<>();
	static {
		DEFAULT_HEADER_CODE.put(HEADER_CODE_KEY_1, HEADER_CODE_NAME_1);
		DEFAULT_HEADER_CODE.put(HEADER_CODE_KEY_2, HEADER_CODE_NAME_2);
		DEFAULT_HEADER_CODE.put(HEADER_CODE_KEY_3, HEADER_CODE_NAME_3);
		DEFAULT_HEADER_CODE.put(HEADER_CODE_KEY_4, HEADER_CODE_NAME_4);
	}

	// HTML References
	public static final String HTML_HEAD_TAG_START = "<html>\n<head>\n";
	public static final String HTML_HEAD_TAG_END = "</head>\n";
	public static final String HTML_STYLE_TAG_START = "<style type=\"text/css\">\n";
	public static final String HTML_STYLE_TAG_END = "</style>\n";
	public static final String HTML_SCRIPT_TAG_START = "<script type=\"text/javascript\">\n";
	public static final String HTML_SCRIPT_TAG_END = "</script>\n";
	public static final String HTML_TITLE = "<title>Test Report</title>\n";
	public static final String HTML_BODY = "<body onload=\"displayResults();\">\n"
			+ "\t<div id=\"heading\">\n"
				+ "\t</div>\n"
				+ "\t<div id=\"report\">\n"
				+ "\t</div>\n"
				+ "</body>\n"
			+ "</html>";
	public static final String FILE_JS = "script.js";
	public static final String FILE_CSS = "style.css";
	public static final String JS_RESULT_DATA = "var data = \n";
	public static final String DEFAULT_REPORT_NAME = "TestReport.html";
	public static final String DEFAULT_FILE_EXTENSION = ".html";

}
