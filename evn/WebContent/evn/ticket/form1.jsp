<%@page import="com.inf.IKey"%>
<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<logic:equal name="BTables" property="thread1" value="1">
	<logic:equal name="BTables" property="thread2" value="0">
		<script type="text/javascript">
			function callBackReload() {
				post("functionEvn", anchor + ":_VIEW_TICKET");
			}
			window.setInterval("callBackReload()", <%=IKey.getValue("REFRESH.TIME.THREAD1")%>);
		</script>
		<html:form action="searchEvn" method="post">
		</html:form>
		<body>
			<table class="list-voffice" width="100%" cellpadding="0"
				cellspacing="0">
				<bean:define name="BPaging1" id="beans" type="com.form.FBeans" />
				<TR>
					<TH colspan="2">R&#224; so&#225;t <%=beans.getTotalRows()%>
						ticket
					</TH>
				</TR>
				<TR>
					<td width="300px">
						<table class="list-voffice" width="100%" cellpadding="0"
							cellspacing="0">

							<TR>
								<td><logic:equal name="BTables" property="thread1"
										value="0">
										<img style="border: 0px"
											src="<%=contextPath%>/images/Stop.png"
											onclick="javascript:post('ticketAction',anchor +':_VIEW_TICKET:thread1:1')"
											title="Stop" />
									</logic:equal> <logic:equal name="BTables" property="thread1" value="1">
										<img style="border: 0px"
											src="<%=contextPath%>/images/Start.gif"
											onclick="javascript:post('ticketAction',anchor +':_VIEW_TICKET:thread1:0')"
											title="Start" />
									</logic:equal></td>
							</TR>
							<TR>
								<td>&#272;&#7897; tr&#7877;: <html:select
										style="width:50px" name="BTicket" property="delay"
										onchange="javascript:post('ticketAction',anchor +':_VIEW_TICKET')">
										<html:option value="1">1P</html:option>
										<html:option value="5">5P</html:option>
										<html:option value="10">10P</html:option>
										<html:option value="60">60P</html:option>
										<html:option value="120">120P</html:option>
										<html:option value="180">180P</html:option>
									</html:select>

								</td>
							</TR>

						</TABLE>

					</td>
					<td align="right" id="idrecordlist"><jsp:include
							page="/evn/ticket/recordList1.jsp" /></td>
				</TR>
			</TABLE>
		</body>
	</logic:equal>
</logic:equal>
<logic:equal name="BTables" property="thread1" value="1">
	<logic:equal name="BTables" property="thread2" value="1">

		<html:form action="searchEvn" method="post">
		</html:form>
		<body>
			<table class="list-voffice" width="100%" cellpadding="0"
				cellspacing="0">
				<bean:define name="BPaging1" id="beans" type="com.form.FBeans" />
				<TR>
					<TH colspan="2">R&#224; so&#225;t <%=beans.getTotalRows()%>
						ticket
					</TH>
				</TR>
				<TR>
					<td width="300px">
						<table class="list-voffice" width="100%" cellpadding="0"
							cellspacing="0">

							<TR>
								<td><logic:equal name="BTables" property="thread1"
										value="0">
										<img style="border: 0px"
											src="<%=contextPath%>/images/Stop.png"
											onclick="javascript:post('ticketAction',anchor +':_VIEW_TICKET:thread1:1')"
											title="Stop" />
									</logic:equal> <logic:equal name="BTables" property="thread1" value="1">
										<img style="border: 0px"
											src="<%=contextPath%>/images/Start.gif"
											onclick="javascript:post('ticketAction',anchor +':_VIEW_TICKET:thread1:0')"
											title="Start" />
									</logic:equal></td>
							</TR>
							<TR>
								<td>&#272;&#7897; tr&#7877;: <html:select
										style="width:50px" name="BTicket" property="delay"
										onchange="javascript:post('ticketAction',anchor +':_VIEW_TICKET')">
										<html:option value="1">1P</html:option>
										<html:option value="5">5P</html:option>
										<html:option value="10">10P</html:option>
										<html:option value="60">60P</html:option>
										<html:option value="120">120P</html:option>
										<html:option value="180">180P</html:option>
									</html:select>

								</td>
							</TR>

						</TABLE>

					</td>
					<td align="right" id="idrecordlist"><jsp:include
							page="/evn/ticket/recordList1.jsp" /></td>
				</TR>
			</TABLE>
		</body>
	</logic:equal>
</logic:equal>
<logic:equal name="BTables" property="thread1" value="0">

	<table class="list-voffice" width="100%" cellpadding="0"
		cellspacing="0">
		<bean:define name="BPaging1" id="beans" type="com.form.FBeans" />
		<TR>
			<TH colspan="2">R&#224; so&#225;t <%=beans.getTotalRows()%>
				ticket
			</TH>
		</TR>
		<TR>
			<td width="300px">
				<table class="list-voffice" width="100%" cellpadding="0"
					cellspacing="0">

					<TR>
						<td><logic:equal name="BTables" property="thread1" value="0">
								<img style="border: 0px" src="<%=contextPath%>/images/Stop.png"
									onclick="javascript:post('ticketAction',anchor +':_VIEW_TICKET:thread1:1')"
									title="Stop" />
							</logic:equal> <logic:equal name="BTables" property="thread1" value="1">
								<img style="border: 0px" src="<%=contextPath%>/images/Start.gif"
									onclick="javascript:post('ticketAction',anchor +':_VIEW_TICKET:thread1:0')"
									title="Start" />
							</logic:equal></td>
					</TR>
					<TR>
						<td>&#272;&#7897; tr&#7877;: <html:select style="width:50px"
								name="BTicket" property="delay"
								onchange="javascript:post('ticketAction',anchor +':_VIEW_TICKET')">
								<html:option value="1">1P</html:option>
								<html:option value="5">5P</html:option>
								<html:option value="10">10P</html:option>
								<html:option value="60">60P</html:option>
								<html:option value="120">120P</html:option>
								<html:option value="180">180P</html:option>
							</html:select>

						</td>
					</TR>

				</TABLE>

			</td>
			<td align="right" id="idrecordlist"><jsp:include
					page="/evn/ticket/recordList1.jsp" /></td>
		</TR>
	</TABLE>
</logic:equal>

