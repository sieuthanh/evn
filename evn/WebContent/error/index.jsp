<% request.setAttribute("SESSION.DENY.GUEST","FALSE");%>
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<head>
<title><bean:message key="app.title" bundle="<%=interfaces%>"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=<%=encoding%>">
<style type="text/css">@import url(<%=contextPath%>/styles/login.css);</style>
</head>
<body>
        <html:form action="login" method="post">
        <div align="center">
            <div class="inputlabel"><bean:message key="errors.caption" bundle="<%=interfaces%>"/></div>
            <div class="inputlabel"><bean:message key="errors.detail" bundle="<%=interfaces%>"/></div>
            <div><html:errors  bundle="<%=interfaces%>"/></div>
            <html:button property="back" styleClass="button" onclick="javascript:history.back(1);">
            <bean:message key="action.back" bundle="<%=interfaces%>"/>
            </html:button>
            &nbsp;
            <input type=hidden name="<%=anchor%>" value="_LOGOUT">
            <html:submit property="_CLOSE" styleClass="button">
            <bean:message key="action.close" bundle="<%=interfaces%>"/>
            </html:submit>
        </div>
        </html:form>
</body>
