<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="ADMIN.PANEL.ROLE">
<TABLE class=adminform>
<TBODY>
<TR>
  <TH><bean:message key="option.panel.role" bundle="<%=interfaces%>"/></TH>
</TR>
<TR>
  <TD> <jsp:include page="/admin/role/role.jsp" /></TD></TR></TBODY></TABLE>
</logic:present>
<logic:present name="ADMIN.PANEL.PRIVILEGE">
<TABLE class=adminform>
<TBODY>
<TR>
  <TH><bean:message key="option.panel.app" bundle="<%=interfaces%>"/></TH></TR>
<TR>
  <TD> <jsp:include page="/admin/apps/app.jsp" /></TD></TR></TBODY></TABLE>
</logic:present>
<TABLE class=adminform>
<TBODY>
<TR>
  <TH><bean:message key="option.panel.privilege" bundle="<%=interfaces%>"/></TH></TR>
<TR>
  <TD id="menu_option"><jsp:include page="/admin/role/privilege.jsp" /> </TD></TR></TBODY>
  
  </TABLE>
