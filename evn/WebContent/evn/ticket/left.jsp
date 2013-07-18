<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<bean:define name="BTotalDate" id="BTotalDate" type="com.form.FBeans" />
<bean:define name="BTotalMonth" id="BTotalMonth" type="com.form.FBeans" />
<bean:define name="BTotalYear" id="BTotalYear" type="com.form.FBeans" />
<html:form action="searchTickets" method="post">
	<html:hidden name="BTables" property="nameTable" />
	<table class="list-voffice" width="100%" cellpadding="0"
		cellspacing="0">
		<TR>
			<TH>T&#7893;ng h&#7907;p: Ng&#224;y:<%=BTotalDate.getTotalRows()%>
				-- Th&#225;ng:<%=BTotalMonth.getTotalRows()%> -- N&#259;m:<%=BTotalYear.getTotalRows()%></TH>
		</TR>

		<TR>
			<TD>Theo Ng&#224;y: <html:text name="BTicket"
					property="search_time" styleClass="inputbox" style="width:70px"
					onchange="javascript:post('searchTickets',anchor +':_SEARCH_TICKET')" />
				<img src="<%=contextPath%>/images/ew_calendar.gif"
				onClick="popUpCalendar(this,'search_time','dd/mm/yyyy','');" />
			</TD>
		</TR>
		<TR>
			<TD>Ch&#7885;n Lo&#7841;i: <html:select styleClass="inputbox"
					name="BTicket" property="search_type" style="width:70px"
					onchange="javascript:post('searchTickets',anchor +':_SEARCH_TICKET')">
					<html:option value="O">OPEN</html:option>
					<html:option value="C">CLOSE</html:option>
					<html:option value="P">PROCESS</html:option>
					<html:option value="F">FINISH</html:option>
					<html:option value="R">REVISE</html:option>
					<html:option value="D">DELETE</html:option>
					<html:option value="E1">ERROR</html:option>

				</html:select>
			</TD>
		</TR>
		<TR>
			<TD>Ch&#7885;n theo: <html:select styleClass="inputbox"
					name="BTicket" property="search_type_time" style="width:70px"
					onchange="javascript:post('searchTickets',anchor +':_SEARCH_TICKET')">
					<html:option value="0">Ng&#224;y</html:option>
					<html:option value="1">Th&#225;ng</html:option>
					<html:option value="2">N&#259;m</html:option>
				</html:select>
			</TD>
		</TR>
		</html:form>
		<TR>
			<TD id="idleftsearch"><jsp:include
					page="/evn/ticket/leftSearch.jsp" /></TD>
		</TR>
		<TR>
			<TD id="idleftrecordlist"><jsp:include
					page="/evn/ticket/leftRecordList.jsp" /></TD>
		</TR>

	</TABLE>