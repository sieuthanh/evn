<%@page import="com.form.evn.sourceConnectBuffer.FSourceConnectBuffer"%>
<%@page import="com.form.evn.reportBuffer.FReportBuffer"%>
<%@page import="com.form.evn.reportNormBuffer.FReportNormBuffer"%>
<%@page import="com.bo.evn.tables.BTables"%>
<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<bean:define name="BTables" property="nameTable" id="nameTable"
	type="java.lang.String" />
<bean:define name="BTables" property="type" id="type"
	type="java.lang.Integer" />


<%
	String fromAction = "reportsBuffer";
	String fromID = "report_Id";
	int checkInsert = 0;
%>
<%
	if (nameTable.equals("EVN_REPORTS_NORM_BUFFER")) {
		fromAction = "reportsNormBuffer";
		fromID = "norm_Id";
		checkInsert = 1;
	} else if (nameTable.equals("EVN_SOURCE_CONNECT_TO_BUFFER")) {
		fromAction = "sourceConnectBuffer";
		fromID = "src_Connect_Id";
		checkInsert = 1;
	} else if (nameTable.equals("EVN_SOURCE_ACCOUNT_BUFFER")) {
		fromAction = "sourceAccountBuffer";
		fromID = "account_Id";
		checkInsert = 1;
	} else if (nameTable.equals("EVN_REPORTS_BUFFER")) {
		fromAction = "reportsBuffer";
		fromID = "report_Id";
		checkInsert = 1;
	} else if (nameTable.equals("EVN_TABLES")) {
		fromAction = "tables";
		fromID = "id";
		checkInsert = 1;
	} else if (nameTable.equals("APPS")) {
		fromAction = "tables";
		fromID = "id";
		checkInsert = 1;
	} else if (nameTable.equals("EVN_ERROR_BUFFER")) {
		fromAction = "evnErrorBuffer";
		fromID = "error_Id";
		checkInsert = 1;
	} else if (nameTable.equals("EVN_MODULE_BUFFER")) {
		fromAction = "evnModuleBuffer";
		fromID = "moduleId";
		checkInsert = 1;
	}
%>


<html:form action="<%=fromAction%>" method="post">
	<div style="WIDTH: 100%;" align="center">
		<b> Danh s&#225;ch b&#7843;n ghi table:<bean:write name="BTables"
				property="nameTable" /></b>

	</div>

	<logic:notEmpty name="BRecordList">
		<bean:define name="BPaging" id="beans" type="com.form.FBeans" />
		<div
			style="DISPLAY: block; BACKGROUND: White; OVERFLOW: auto; WIDTH: 950px; HEIGHT: 500px;">

			<table class="list-voffice" cellpadding="0" cellspacing="0"
				width="100%" border="0">
				<bean:define name="BRecordList" id="recordList"
					type="java.lang.String[][]" />
				<%
					for (int k = 0; k < recordList.length; k++) {
				%>
				<tr class="<%=(k % 2 == 0) ? "content1" : "content"%>">
					<%
						if (k * beans.getPageCurrent() > beans.getTotalRows())
										break;
									if (recordList[k] == null) {
										break;
									}
									for (int j = 0; j < recordList[k].length; j++) {
										if (k == 0) {
					%>
					<%
						if (j == 0 && checkInsert == 1) {
					%>
					<TH class=title noWrap width="40px"><img style="border: 0px"
						src="images/addGroup.gif"
						title="<bean:message key="action.insert" bundle="<%=interfaces%>"/>"
						onClick="javascript:openWindow('<%=fromAction%>',anchor + ':_PREPARED_CREATE:nameTable:<%=nameTable%>');">
					</TH>
					<%
						}
					%>
					<th><%=recordList[k][j]%></th>

					<%
						} else {
					%>
					<%
						if (j == 0 && checkInsert == 1) {
					%>
					<td width="40px"><img style="border: 0px"
						src="images/update.png"
						title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>"
						onClick="javascript:openWindow('<%=fromAction%>',anchor + ':_PREPARED_EDIT:nameTable:<%=nameTable%>:<%=fromID%>:<%=recordList[k][0]%>');">
						<img style="border: 0px" src="images/delete.png"
						title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>"
						onClick="javascript:if(messageDelete())post('<%=fromAction%>',anchor + ':_DELETE:nameTable:<%=nameTable%>:<%=fromID%>:<%=recordList[k][0]%>');">
					</td>

					<%
						}
					%>
					<td><%=recordList[k][j]%></td>


					<%
						}
									}
					%>
				</tr>
				<%
					}
				%>

			</table>

		</div>

		<logic:present name="BRecordList">
			<div class="toolCmd" align="center">
				<table cellpadding="0" cellspacing="0" width="100%" border="0">
					<tr>
						<td align="left" nowrap><Strong><bean:message
									key="page.caption.total" bundle="<%=interfaces%>" /> <%=beans.getTotalRows()%></strong></td>
						<td>
							<%
								String anchorT = request.getParameter("Anchor");
											if (anchorT.equals("_VIEW_FKEY")) {
							%> <%
 	String params = anchor + ":" + anchorT
 							+ ":nameTable:" + nameTable;
 %>
							<jsp:include page="/evn/paging.jsp">
								<jsp:param name="BEANS" value="BPaging" />
								<jsp:param name="PARAMS" value="<%=params%>" />
								<jsp:param name="FORM" value="functionEvn" />
								<jsp:param name="METHOD" value="postAjax" />
								<jsp:param name="POSITION" value="tdMainBody" />
							</jsp:include> <%
 	} else {
 %> <%
 	String params = anchor + ":" + anchorT
 							+ ":nameTable:" + nameTable + ":type:"
 							+ type;
 %>
							<jsp:include page="/evn/paging.jsp">
								<jsp:param name="BEANS" value="BPaging" />
								<jsp:param name="PARAMS" value="<%=params%>" />
								<jsp:param name="FORM" value="functionEvn" />
								<jsp:param name="METHOD" value="post" />
							</jsp:include> <%
 	}
 %>
						</td>
					</tr>
				</table>
			</div>
		</logic:present>
	</logic:notEmpty>
</html:form>

<div style="color: red; font-size: 11px; font-weight: bold;">
	<logic:notEmpty name="errorValue">
		<bean:write name="errorValue" />
	</logic:notEmpty>
</div>

