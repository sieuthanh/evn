<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html>
<HEAD><jsp:include page="/commons/setAdmin.jsp"/></HEAD>
<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" >
	<table align="center" class="td" width="99%" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top" height="53px" style="margin:0px">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr valign="top">
					<td valign="middle" style="BORDER: #cccccc 1px solid; PADDING-LEFT: 15px; BACKGROUND: #f1f3f5;" width="100%">
                                        <img src="<%=contextPath%>/images/security_f2.png" alt="security" align="left">
                                        <span style="font-weight: bold;font-size: 25px;color: #BB0000;font-family: Tahoma;">QU&#7842;N TR&#7882; T&#205;CH H&#7906;P</span>
                                        </td>
				</tr>
				<tr>
					<td>
                                        <jsp:include page="/evn/menu/menu.jsp" />
                                        </td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td valign="top" style="margin:0px">
			<table height="470px" border="0" width="100%" cellspacing="2" cellpadding="2" bgcolor="#FFFFFF">
                                <tr>
                                        <td  width="300px" class="td" valign="top" nowrap>                                                        
                                        <div id="QFrameTree">
                                        <tiles:insert attribute="tree" ignore="true"/>
                                        </div>
                                        </td>
                                        <td class="td" align="center" id="tdMainBody" valign="top" style="padding-left:4px;padding-right:4px;padding-bottom:4px">
                                        <tiles:insert attribute="content" ignore="true"/>
                                        </td>
                                </tr>
                                
			</table>
			</td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF" valign="top" height="23px"  style="margin : 0px">
			<tiles:insert attribute="footer" ignore="true"/>
			</td>
		</tr>		
	</table>
</body>
</html>
