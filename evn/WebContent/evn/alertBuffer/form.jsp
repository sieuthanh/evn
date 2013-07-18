<%@page import="com.inf.IKey"%>
<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<logic:equal name="BAlertBuffer" property="thread3" value="0">
	<table class="list-voffice" width="100%" cellpadding="0"
		cellspacing="0">
		<bean:define name="BPaging1" id="beans" type="com.form.FBeans" />
		<TR>
			<TH colspan="2">C&#243; <%=beans.getTotalRows()%> th&#244;ng
				b&#225;o m&#7899;i
			</TH>
		</TR>
		<TR>
			<td width="300px">
				<table class="list-voffice" width="100%" cellpadding="0"
					cellspacing="0">

					<TR>
						<td><logic:equal name="BAlertBuffer" property="thread3"
								value="0">
								<img style="border: 0px" src="<%=contextPath%>/images/Stop.png"
									onclick="javascript:post('alertAction',anchor +':_VIEW:thread3:1')"
									title="Stop" />
							</logic:equal> <logic:equal name="BAlertBuffer" property="thread3" value="1">
								<img style="border: 0px" src="<%=contextPath%>/images/Start.gif"
									onclick="javascript:post('alertAction',anchor +':_VIEW:thread3:0')"
									title="Start" />
							</logic:equal></td>
					</TR>
					<TR>
						<td>&#272;&#7897; tr&#7877;: <html:select style="width:50px"
								name="BAlertBuffer" property="delay"
								onchange="javascript:post('alertAction',anchor +':_VIEW')">
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
					page="/evn/alertBuffer/recordList.jsp" /></td>
		</TR>
	</TABLE>
</logic:equal>
<logic:equal name="BAlertBuffer" property="thread3" value="1">

	<script type="text/javascript">
		function callBackReload() {
			post("functionEvn", anchor + ":_VIEW_ALERT");
		}
		window.setInterval("callBackReload()", <%=IKey.getValue("REFRESH.TIME.THREAD3")%>);
	</script>
	<table class="list-voffice" width="100%" cellpadding="0"
		cellspacing="0">
		<bean:define name="BPaging1" id="beans" type="com.form.FBeans" />
		<TR>
			<TH colspan="2">C&#243; <%=beans.getTotalRows()%> th&#244;ng
				b&#225;o m&#7899;i
			</TH>
		</TR>
		<TR>
			<td width="300px">
				<table class="list-voffice" width="100%" cellpadding="0"
					cellspacing="0">

					<TR>
						<td><logic:equal name="BAlertBuffer" property="thread3"
								value="0">
								<img style="border: 0px" src="<%=contextPath%>/images/Stop.png"
									onclick="javascript:post('alertAction',anchor +':_VIEW:thread3:1')"
									title="Stop" />
							</logic:equal> <logic:equal name="BAlertBuffer" property="thread3" value="1">
								<img style="border: 0px" src="<%=contextPath%>/images/Start.gif"
									onclick="javascript:post('alertAction',anchor +':_VIEW:thread3:0')"
									title="Start" />
							</logic:equal></td>
					</TR>
					<TR>
						<td>&#272;&#7897; tr&#7877;: <html:select style="width:50px"
								name="BAlertBuffer" property="delay"
								onchange="javascript:post('alertAction',anchor +':_VIEW')">
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
					page="/evn/alertBuffer/recordList.jsp" /></td>
		</TR>
	</TABLE>
</logic:equal>

