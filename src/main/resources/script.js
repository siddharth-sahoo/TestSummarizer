function displayResults() {
	// If data is not present.
	try {
		if (data == null || data == undefined) {
			document.write("ERROR: Failed to display results because there is no data.");
			return;
		}
	} catch (e) {
		document.write("ERROR: Failed to display results because there is no data.");
		return;
	}

	// If data is present, but there are no test results.
	if (!data.reportTables) {
		document.write("ERROR: Failed to display results because there are no test results.");
		return;
	}
	
	// Modify page title & header
	if (data.title) {
		document.title += ": " + data.title;
		var heading = document.createElement("h1");
		heading.appendChild(document.createTextNode("Test Report: " + data.title));
		document.getElementById("heading").appendChild(heading);
	} else {
		var heading = document.createElement("h1");
		heading.appendChild(document.createTextNode("Test Report"));
		document.getElementById("heading").appendChild(heading);
	}

	var tables = Object.keys(data.reportTables);
	for (var i = 0; i < tables.length; i ++) {
		document.getElementById("report").appendChild(
			processTable(tables[i], data.reportTables[tables[i]])
		);
	}

}

function processTable(divId, data) {
	var tableDiv = document.createElement("div");
	tableDiv.id = divId;
	
	// Modify subtitle
	if (data.subtitle) {
		var subtitle = document.createElement("h4");
		subtitle.appendChild(document.createTextNode(data.subtitle));
		tableDiv.appendChild(subtitle);
	}

	// Display test results
	var resultTable = document.createElement("table");
	resultTable.id = "report_table";

	// Display table headers
	if (data.enableHeader) {
		var tableHeader = resultTable.createTHead();
		var tableHeaderRow = tableHeader.insertRow(0);
		
		if (data.enableCategorization) {
			var cell = document.createElement("th");
			cell.innerHTML = data.headerCode.CATEGORY;
			tableHeaderRow.appendChild(cell);
		}

		var cell = document.createElement("th");
		cell.innerHTML = data.headerCode.SCENARIO;
		tableHeaderRow.appendChild(cell);
		
		if (data.enableStatus) {
			cell = document.createElement("th");
			cell.innerHTML = data.headerCode.STATUS;
			tableHeaderRow.appendChild(cell);
		}
			
		if (data.enableComments) {
			cell = document.createElement("th");
			cell.innerHTML = data.headerCode.COMMENTS;
			tableHeaderRow.appendChild(cell);
		}
	}
		
	var categoryCount = data.results.length;
	var tableBody = resultTable.createTBody();
	var rowIndex = 0;
	
	for (var i = 0; i < categoryCount; i ++) {
		var currentCategory = data.results[i];
		if (!currentCategory.scenarios || currentCategory.scenarios.length == 0) {
			continue;
		}
		
		var row = tableBody.insertRow(rowIndex);
		rowIndex ++;
		var scenarioCount = currentCategory.scenarios.length;
		if (data.enableCategorization) {
			var cell = document.createElement("th");
			cell.innerHTML = currentCategory.category;
			cell.rowSpan = scenarioCount;
			row.appendChild(cell);
		}
		
		for (var j = 0; j < scenarioCount; j ++) {
			var cellIndex = 0;
			if (data.enableCategorization && j == 0) {
				cellIndex = 1;
			}

			var cell = row.insertCell(cellIndex);
			cellIndex ++;
			cell.innerHTML = currentCategory.scenarios[j].name;
			
			if (data.enableStatus) {
				cell = row.insertCell(cellIndex);
				cellIndex ++;
				cell.innerHTML = currentCategory.scenarios[j].status;
				cell.style.backgroundColor = getBackgroundColour(cell.innerHTML.toLowerCase(), data.colourCode);
			}
			
			if (data.enableComments) {
				cell = row.insertCell(cellIndex);
				cellIndex ++;
				if (currentCategory.scenarios[j].comments) {
					cell.innerHTML = currentCategory.scenarios[j].comments;
				} else {
					cell.innerHTML = "";
				}
			}
			
			row = tableBody.insertRow(rowIndex);
			rowIndex ++;
		}
	}
	
	tableDiv.appendChild(resultTable);
	return tableDiv;
}

function getBackgroundColour(text, colourCode) {
	var keys = Object.keys(colourCode);
	for (var i = 0; i < keys.length; i ++) {
		key = keys[i].toLowerCase();
		if (text.contains(key)) {
			return colourCode[keys[i]];
		}
	}
	return colourCode["Default"];
}