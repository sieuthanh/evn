<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<bean:define name="BTables" property="nameTable" id="nameTable"
	type="java.lang.String" />
<%
	int checkView = 0;
%>
<%
	if (nameTable.equals("EVN_REPORTS_BUFFER")
			|| nameTable.equals("EVN_REPORTS_NORM_BUFFER")
			|| nameTable.equals("EVN_REPORTS_DATA_BUFFER")) {
		checkView = 1;
	} else if (nameTable.equals("EVN_TABLES")
			|| nameTable.equals("EVN_ALERT_BUFFER")
			|| nameTable.equals("EVN_TICKET_BUFFER")
			|| nameTable.equals("EVN_SOURCE_CONNECT_TO_BUFFER")
			|| nameTable.equals("EVN_SOURCE_ACCOUNT_BUFFER")
			|| nameTable.equals("EVN_ERROR_BUFFER")
			|| nameTable.equals("EVN_MODULE_BUFFER")) {
		checkView = 2;
	}
%>
<logic:equal parameter="<%=anchor%>" value="_VIEW_NV">
	<%
		checkView = 0;
	%>
</logic:equal>

<%
	if (checkView == 1) {
%>
<LI>B&#225;o c&#225;o
	<OL>
		<li><a
			href="javascript:post('functionEvn',anchor +':_VIEW:nameTable:EVN_REPORTS_BUFFER')">EVN_REPORT_BUFFER</a>
		</li>
		<li><a
			href="javascript:post('functionEvn',anchor +':_VIEW:nameTable:EVN_REPORTS_NORM_BUFFER')">EVN_REPORTS_NORM_BUFFER</a>
		</li>
		<li><a
			href="javascript:post('functionEvn',anchor +':_VIEW:nameTable:EVN_REPORTS_DATA_BUFFER')">EVN_REPORTS_DATA_BUFFER</a>
		</li>
	</OL> <%
 	} else if (checkView == 0) {
 %>

<LI>Nghi&#7879;p v&#7909;
	<OL>
		<logic:present name="BTabless">
			<logic:iterate id="app" name="BTabless"
				type="com.form.evn.tables.FTables">
				<li><a
					href="javascript:post('functionEvn',anchor +':_VIEW_NV:id:<%=app.getId()%>:nameTable:<%=app.getName()%>')"><bean:write
							name="app" property="name" /></a></li>

			</logic:iterate>
		</logic:present>

	</OL>
	<div>
		<html:form action="operationEvn" method="post">
			<html:hidden name="BTables" property="nameTable" />
			<html:hidden name="BTables" property="id" /> 

                  Connection:      <html:select styleClass="inputbox"
				name="BTables" property="type"
				onchange="javascript:post('operationEvn',anchor +':_VIEW_NV');remove('operationEvn',anchor);">
				<logic:present name="BSourceConnectBuffers">
					<html:options collection="BSourceConnectBuffers"
						property="src_Connect_Id" labelProperty="fullName" />
				</logic:present>
			</html:select>
		</html:form>
	</div> <%
 	} else if (checkView == 2) {
 %>

<LI>Gi&#225;m s&#225;t
	<OL>
		<li><a
			href="javascript:post('functionEvn',anchor +':_VIEW:nameTable:EVN_TABLES')">EVN_TABLES</a>
		</li>
		<li><a
			href="javascript:post('functionEvn',anchor +':_VIEW:nameTable:EVN_ALERT_BUFFER')">EVN_ALERT_BUFFER</a>
		</li>
		<li><a
			href="javascript:post('functionEvn',anchor +':_VIEW:nameTable:EVN_TICKET_BUFFER')">EVN_TICKET_BUFFER</a>
		</li>
		<li><a
			href="javascript:post('functionEvn',anchor +':_VIEW:nameTable:EVN_SOURCE_CONNECT_TO_BUFFER')">EVN_SOURCE_CONNECT_TO_BUFFER</a>
		</li>
		<li><a
			href="javascript:post('functionEvn',anchor +':_VIEW:nameTable:EVN_SOURCE_ACCOUNT_BUFFER')">EVN_SOURCE_ACCOUNT_BUFFER</a>
		</li>
		<li><a
			href="javascript:post('functionEvn',anchor +':_VIEW:nameTable:EVN_ERROR_BUFFER')">EVN_ERROR_BUFFER</a>
		</li>
		<li><a
			href="javascript:post('functionEvn',anchor +':_VIEW:nameTable:EVN_MODULE_BUFFER')">EVN_MODULE_BUFFER</a>
		</li>
	</OL> <%
 	}
 %>
	<div>
		<jsp:include page="/evn/columnsList.jsp" />
	</div>