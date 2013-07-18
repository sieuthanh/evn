<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<html:form action="login" method="post" />
<html:form action="control" method="post" />
<table width="100%" cellpadding="0" cellspacing="0" >
<tr><td style="font-weight: bold;font-size:25px;color: #BB0000;font-family: Tahoma;width:200px;">
<bean:message key="app.vmail" bundle="<%=interfaces%>"/>
</td>
<td class="menuClass" align="left">
<bean:message key="app.wellcome.mail" bundle="<%=interfaces%>"/>, 
      <Strong>
      <%=me.getFullName()%>
      </Strong><br>
      [
      <A style="COLOR: #0000FF;" href="javascript:post('login',anchor + ':_LOGOUT');">
      <bean:message key="logout.caption" bundle="<%=interfaces%>"/>
      </A>|
      <A style="COLOR: #0000FF;" href="javascript:openWindow('login',anchor + ':_PREPARED_EDIT');">
      <bean:message key="login.change.password" bundle="<%=interfaces%>"/>
      </A>
      ]      
</td>
</tr></table>