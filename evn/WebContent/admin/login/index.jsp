<% request.setAttribute("SESSION.DENY.GUEST","FALSE");%>
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<head>
<title><bean:message key="app.title" bundle="<%=interfaces%>"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=<%=encoding%>">
<style type="text/css">
@import url(<%=contextPath%>/styles/admin_login.css);
</style>
<script language="javascript" src="<%=contextPath%>/js/md5.js"></script>
<script language="JavaScript">
	self.moveTo(0,0);
        self.resizeTo(screen.width,screen.height);
        self.focus();
</script>
  </head>
  <body>
</head>
<body onload="document.login.username.select();document.login.username.focus();">
<div id="ctr" align="center">
		<div class="login">
		<div class="login-form">
			<img src="images/login/login.gif" alt="Login" />
                        <html:form action="/login" method="post" onsubmit="pw2md5(document.login.md5pw,document.login.password);">
                        <input type="hidden" name="password">
                        <input type="hidden" name="<%=anchor%>" value="_LOGIN">
			<div class="form-block">
                                <%
                                if(com.lib.AppConfigs.ADMIN_PANEL_APPS){
                                    try  {
                                        com.bo.admin.apps.BApps apps = new com.bo.admin.apps.BApps();
                                        com.form.FBeans beans = apps.getActiveRecord();
                                        request.setAttribute("BApps",beans);
                                    } catch (Exception ex)  {   
                                        response.sendRedirect(contextPath + "/error/");
                                    } finally  {
                                    }                                
                                }
                                %>
                                <logic:present name="BApps">
				<div class="inputlabel"><bean:message key="login.application" bundle="<%=interfaces%>"/></div>
				<div>              
                                <html:select styleClass="inputbox" property="appID">
                                    <html:options collection="BApps" property="id" labelProperty="name"/>
                                </html:select>   
                                </div>
                                </logic:present>
				<div class="inputlabel"><bean:message key="login.username" bundle="<%=interfaces%>"/></div>
				<div><html:text property="username" styleClass="inputbox" size="15" maxlength="90" /></div>
				<div class="inputlabel"><bean:message key="login.password" bundle="<%=interfaces%>"/></div>
				<div><input name="md5pw" type="password" class="inputbox" size="15" maxlength="90"></div>
				<div align="left">
                                <html:submit property="signin" styleClass="button">
                                <bean:message key="login.auth" bundle="<%=interfaces%>"/>
                                </html:submit>
                                </div>
			</div>
                        </html:form>
		</div>
		<div class="login-text">
			<div class="ctr"><img src="images/login/security.png" width="64" height="64" alt="security" /></div>
			<p class="inputlabel"><bean:message key="login.caption" bundle="<%=interfaces%>"/></p>
			<p><bean:message key="login.notice" bundle="<%=interfaces%>"/></p>
		</div>
                <div class="clr"></div>
	</div>
</div>
<div class="footer" align="center">
		<html:errors property="loginError" bundle="<%=interfaces%>" />
</div>
</body>
</html>