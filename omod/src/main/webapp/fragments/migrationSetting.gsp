
<table>
    <tr>
        <th>${ ui.message("DataImportTool.matchFile") }</th>
        <th>${ ui.message("DataImportTool.matchFormat") }</th>
        <th>${ ui.message("DataImportTool.matchLocation") }</th>
	<th>${ ui.message("DataImportTool.leftDbDriver") }</th>
        <th>${ ui.message("DataImportTool.leftUserName") }</th>
	<th>${ ui.message("DataImportTool.leftPassword") }</th>
	<th>${ ui.message("DataImportTool.leftDbName") }</th>
        <th>${ ui.message("DataImportTool.leftDbLocation") }</th>
	<th>${ ui.message("DataImportTool.rightDbDriver") }</th>
	<th>${ ui.message("DataImportTool.rightUserName") }</th>
	<th>${ ui.message("DataImportTool.rightPassword") }</th>
	<th>${ ui.message("DataImportTool.rightDbName") }</th>
        <th>${ ui.message("DataImportTool.rightDbLocation") }</th>
        <th>${ ui.message("DataImportTool.treeLimit") }</th>
	<th>${ ui.message("DataImportTool.allowCommit") }</th>
	<th>${ ui.message("DataImportTool.resetProcess") }</th>
    </tr>
    <% if (migrationSettings) { %>
        <% migrationSettings.each { %>
            <tr>
		<td>${ ui.format(it.matchFile) }</td>
        	<td>${ ui.format(it.matchFormat) }</td>
       		<td>${ ui.format(it.matchLocation) }</td>
		<td>${ ui.format(it.leftDbDriver) }</td>
        	<td>${ ui.format(it.leftUserName) }</td>
		<td>${ ui.format(it.leftPassword) }</td>
		<td>${ ui.format(it.leftDbName) }</td>
        	<td>${ ui.format(it.leftDbLocation) }</td>
		<td>${ ui.format(it.rightDbDriver) }</td>
		<td>${ ui.format(it.rightUserName) }</td>
		<td>${ ui.format(it.rightPassword) }</td>
		<td>${ ui.format(it.rightDbName) }</td>
        	<td>${ ui.format(it.rightDbLocation) }</td>
        	<td>${ ui.format(it.treeLimit) }</td>
		<td>${ ui.format(it.allowCommit) }</td>
		<td>${ ui.format(it.resetProcess) }</td>
                <td>${ ui.format(it.encounterType) }</td>
                <td>${ ui.format(it.encounterDatetime) }</td>
                <td>${ ui.format(it.location) }</td>
                <td>${ ui.format(it.provider) }</td>
            </tr>
        <% } %>
    <% } else { %>
        <tr>
            <td colspan="4">${ ui.message("general.none") }</td>
        </tr>
    <% } %>
</table>

