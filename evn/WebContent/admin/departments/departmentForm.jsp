<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="departments" method="post">
<html:hidden name="BDepartment" property="id"/>
<TABLE class=adminheading>
  <TBODY>
  <TR>
    <TH class=department><bean:message key="departments.action.view" bundle="<%=interfaces%>"/>
    </TH></TR></TBODY></TABLE>
<TABLE align="left">
  <TBODY>
  <TR>
    <TD vAlign=top>
    <logic:present name="BDepartment">
      <TABLE class=adminform>
        <TBODY>
        <TR>
          <TH colSpan=2><bean:message key="departments.edit.caption" bundle="<%=interfaces%>"/></TH></TR>
        <TR>
          <TD><bean:message key="departments.edit.name" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD><html:text  name="BDepartment" property="name" styleClass="inputbox" size="40" /> </TD></TR>
        <TR>
          <TD><bean:message key="departments.edit.code" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD>
          <html:text  name="BDepartment" property="code" styleClass="inputbox" size="40" /> 
          </TD></TR>
        <TR>
          <TD vAlign=top><bean:message key="departments.edit.parent" bundle="<%=interfaces%>"/></TD>
          <TD>
              <html:select styleClass="inputbox" name="BDepartment" property="parentID">
                <html:option value="0"><bean:message key="app.office" bundle="<%=interfaces%>"/></html:option>
                <html:options collection="BDepartments" property="id" labelProperty="name"/>
              </html:select>   
          </TD></TR>
<TR>
<TD colSpan=2>&nbsp; </TD></TR></TBODY></TABLE>
    </logic:present>
    </TD></TR>
<TR>
    <TD align=right>
        <logic:present name="BDepartment">
          <div>
              <jsp:include page="/admin/menu/departmentcmd.jsp" />
          </div>
        </logic:present>
          <jsp:include page="/admin/alert.jsp" />
    </TD></TR>
          </TBODY></TABLE>
          
</html:form>          