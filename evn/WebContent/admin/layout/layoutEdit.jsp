<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<HTML>
<HEAD><jsp:include page="/commons/setAdmin.jsp"/></HEAD>
<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" >
	<table align="center" class="td" width="99%" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top" height="53px" style="margin:0px">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr valign="top">
					<td valign="middle" style="BORDER: #cccccc 1px solid; PADDING-LEFT: 15px; BACKGROUND: #f1f3f5;" width="100%">
                                        <img src="<%=contextPath%>/images/security_f2.png" alt="security" align="left">
                                        <span style="font-weight: bold;font-size: 25px;color: #BB0000;font-family: Tahoma;">ADMINISTRATION</span>
                                        </td>
				</tr>
				<tr>
					<td>
                                        <jsp:include page="/admin/menu/menu.jsp" />
                                        </td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td valign="top" height="400px"  style="margin:0px">
			<table height="400px" border="0" width="100%" cellspacing="2" cellpadding="2" bgcolor="#FFFFFF">
                                <tr>
                                        <td class="td" align="center" id="tdMainBody" valign="top" style="padding-left:4px;padding-right:4px;padding-bottom:4px">
                                        <tiles:insert attribute="content" ignore="true"/>
                                        </td>
                                </tr>
			</table>
			</td>
		</tr>
		<tr>
			<td height="50px">
			<p class="footer"><b><bean:message key="app.software" bundle="<%=interfaces%>"/></b></p>
			<p class="footer"><bean:message key="app.company" bundle="<%=interfaces%>"/></p>
			</td>
		</tr>
	</table>
</body>

</HTML>
