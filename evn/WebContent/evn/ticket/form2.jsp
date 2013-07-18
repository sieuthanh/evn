
<%@page import="com.inf.IKey"%>
<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<logic:equal name="BTables" property="thread2" value="0">

	<table class="list-voffice" width="100%" cellpadding="0"
		cellspacing="0">
		<bean:define name="BPaging2" id="beans" type="com.form.FBeans" />
		<TR>
			<TH colspan="2">X&#7917; l&#253; <%=beans.getTotalRows()%>
				ticket
			</TH>
		</TR>
		<TR>
			<td width="300px">
				<table class="list-voffice" width="100%" cellpadding="0"
					cellspacing="0">
					<TR>
						<td><logic:equal name="BTables" property="thread2" value="0">
								<img style="border: 0px" src="<%=contextPath%>/images/Stop.png"
									onclick="javascript:post('ticketAction',anchor +':_VIEW_TICKET:thread2:1')"
									title="Stop" />
							</logic:equal> <logic:equal name="BTables" property="thread2" value="1">
								<img style="border: 0px" src="<%=contextPath%>/images/Start.gif"
									onclick="javascript:post('ticketAction',anchor +':_VIEW_TICKET:thread2:0')"
									title="Start" />
							</logic:equal></td>
					</TR>
					<TR>
						<td>S&#7889; ti&#7871;n tr&#236;nh x&#7917; l&#253; ticket: <logic:equal
								name="BTables" property="thread2" value="0">
								<html:select style="width:40px" name="BTicket"
									property="amountT"
									onchange="javascript:post('ticketAction',anchor +':_VIEW_TICKET')"
									disabled="false">
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
									<html:option value="6">6</html:option>
									<html:option value="7">7</html:option>
									<html:option value="8">8</html:option>
									<html:option value="9">9</html:option>
									<html:option value="10">10</html:option>
								</html:select>
							</logic:equal> <logic:equal name="BTables" property="thread2" value="1">
								<html:select style="width:40px" name="BTicket"
									property="amountT"
									onchange="javascript:post('ticketAction',anchor +':_VIEW_TICKET')"
									disabled="true">
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
									<html:option value="6">6</html:option>
									<html:option value="7">7</html:option>
									<html:option value="8">8</html:option>
									<html:option value="9">9</html:option>
									<html:option value="10">10</html:option>
								</html:select>
							</logic:equal>
						</td>
					</TR>
				</TABLE>

			</td>
			<td align="right" id="idrecordlist"><jsp:include
					page="/evn/ticket/recordList2.jsp" /></td>
		</TR>
	</TABLE>
</logic:equal>
<logic:equal name="BTables" property="thread2" value="1">
	<script type="text/javascript">
		function callBackReload() {
			post("functionEvn", anchor + ":_VIEW_TICKET");
		}
		window.setInterval("callBackReload()", <%=IKey.getValue("REFRESH.TIME.THREAD2")%>);
	</script>
	<table class="list-voffice" width="100%" cellpadding="0"
		cellspacing="0">
		<bean:define name="BPaging2" id="beans" type="com.form.FBeans" />
		<TR>
			<TH colspan="2">X&#7917; l&#253; <%=beans.getTotalRows()%>
				ticket
			</TH>
		</TR>
		<TR>
			<td width="300px">
				<table class="list-voffice" width="100%" cellpadding="0"
					cellspacing="0">
					<TR>
						<td><logic:equal name="BTables" property="thread2" value="0">
								<img style="border: 0px" src="<%=contextPath%>/images/Stop.png"
									onclick="javascript:post('ticketAction',anchor +':_VIEW_TICKET:thread2:1')"
									title="Stop" />
							</logic:equal> <logic:equal name="BTables" property="thread2" value="1">
								<img style="border: 0px" src="<%=contextPath%>/images/Start.gif"
									onclick="javascript:post('ticketAction',anchor +':_VIEW_TICKET:thread2:0')"
									title="Start" />
							</logic:equal></td>
					</TR>
					<TR>
						<td>S&#7889; ti&#7871;n tr&#236;nh x&#7917; l&#253; ticket: <logic:equal
								name="BTables" property="thread2" value="0">
								<html:select style="width:40px" name="BTicket"
									property="amountT"
									onchange="javascript:post('ticketAction',anchor +':_VIEW_TICKET')"
									disabled="false">
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
									<html:option value="6">6</html:option>
									<html:option value="7">7</html:option>
									<html:option value="8">8</html:option>
									<html:option value="9">9</html:option>
									<html:option value="10">10</html:option>
								</html:select>
							</logic:equal> <logic:equal name="BTables" property="thread2" value="1">
								<html:select style="width:40px" name="BTicket"
									property="amountT"
									onchange="javascript:post('ticketAction',anchor +':_VIEW_TICKET')"
									disabled="true">
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
									<html:option value="6">6</html:option>
									<html:option value="7">7</html:option>
									<html:option value="8">8</html:option>
									<html:option value="9">9</html:option>
									<html:option value="10">10</html:option>
								</html:select>
							</logic:equal>
						</td>
					</TR>
				</TABLE>

			</td>
			<td align="right" id="idrecordlist"><jsp:include
					page="/evn/ticket/recordList2.jsp" /></td>
		</TR>
	</TABLE>
</logic:equal>