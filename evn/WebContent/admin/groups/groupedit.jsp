<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="groups" method="post">
<html:hidden  name="BGroup" property="id" /> 
<TABLE class=adminheading>
  <TBODY>
  <TR>
    <TH class=group>
    <bean:message key="groups.action.view" bundle="<%=interfaces%>"/>
    </TH></TR></TBODY></TABLE>
<TABLE width="100%">
  <TBODY>
  <TR>
    <TD vAlign=top width="60%">
    <logic:present name="BGroup">
      <TABLE class=adminform>
        <TBODY>
        <TR>
          <TH colSpan=2><bean:message key="groups.edit.caption" bundle="<%=interfaces%>"/></TH></TR>
        <TR>
          <TD width=130><bean:message key="groups.edit.name" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD><html:text  name="BGroup" property="name" styleClass="inputbox" size="40" /> </TD></TR>
        <TR>
          <TD><bean:message key="groups.edit.code" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD>
          <html:text  name="BGroup" property="code" styleClass="inputbox" size="40" /> 
          </TD></tr>
        <TR>
          <TD vAlign=top><bean:message key="groups.edit.parent" bundle="<%=interfaces%>"/></TD>
          <TD>
              <html:select  styleClass="inputbox" name="BGroup" property="parentID" >
                <html:option value="0"><bean:message key="app.office" bundle="<%=interfaces%>"/></html:option>
                <logic:present name="BGroups">
                <html:options collection="BGroups" property="id" labelProperty="name"/>
                </logic:present>
              </html:select>   
          </TD></TR>
        <TR>
    
          <TD><bean:message key="groups.edit.datecreate" bundle="<%=interfaces%>"/></TD>
          <TD><strong><bean:write name="BGroup" property="dateCreate"/></strong></TD></TR>
</TBODY></TABLE>
          <div align="right">
          <jsp:include page="/admin/menu/groupcmd.jsp" />
          </div>
          <jsp:include page="/admin/alert.jsp" />

    </logic:present>
</TD>
    <TD vAlign=top width="40%"><jsp:include page="/admin/menu/option.jsp" />   </TD></TR>
          </TBODY></TABLE>
          
</html:form>          