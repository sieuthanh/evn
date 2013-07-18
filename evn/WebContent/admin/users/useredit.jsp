<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="users" method="post">
<logic:present name="BUser">
<html:hidden  name="BUser" property="id" /> 
<TABLE class=adminheading>
  <TBODY>
  <TR>
    <TH class=user>
    <logic:equal name="BUser" property="id" value="0">
    <bean:message key="users.action.add" bundle="<%=interfaces%>"/>
    </logic:equal>
    <logic:notEqual name="BUser" property="id" value="0">
    <bean:message key="users.action.view" bundle="<%=interfaces%>"/>
    </logic:notEqual>
    </TH></TR></TBODY></TABLE>
<div align="left">
<jsp:include page="/admin/alert.jsp" />
</div>
<TABLE width="100%">
  <TBODY>
  <TR>
    <TD vAlign=top width="60%">
      <TABLE class=adminform>
        <TBODY>
        <TR>
          <TH colSpan=3><bean:message key="users.edit.caption" bundle="<%=interfaces%>"/></TH></TR>
        <TR>
          <TD nowrap width=130><bean:message key="users.edit.fullname" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2"><html:text  name="BUser" property="fullName" styleClass="inputbox" size="40" /> </TD></TR>
        <TR>
          <TD nowrap><bean:message key="users.edit.username" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2">
          <%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
          <html:text  name="BUser" property="username" styleClass="inputbox" size="40" /> 
          <%}else{%>
          <strong><bean:write name="BUser" property="username"/></strong>
          <%}%>
          </TD>
        <TR>
          <TD nowrap><bean:message key="users.edit.password" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2">
          <%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
          <INPUT class=inputbox type="password" size=16 name="md5pw" id="md5pw"> <input type="hidden" name="password" id="password">
          <html:checkbox name="BUser" property="changePassword" value="1" styleId="changePassword"/><bean:message key="Changepass.select" bundle="<%=interfaces%>"/>
          <%}else{%>
          <INPUT class=inputbox type="password" size=40 name="md5pw" id="md5pw"> <input type="hidden" name="password" id="password">
          <%}%>
          </TD>
        </TR>
        
        <TR>
          <TD nowrap><bean:message key="users.edit.verifypassword" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2"><INPUT class="inputbox" type="password" size="40" name="retypepw" id="retypepw"> 
        </TD></TR>
        
        
        <TR>
          <TD nowrap><bean:message key="users.edit.department" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2">
          <%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
              <html:select styleClass="inputbox"  name="BUser" property="departmentID" >                                     
                   <logic:present name="BDepartments">
                      <html:options collection="BDepartments" property="id" labelProperty="name"/>
                    </logic:present>
              </html:select>   
          <%}else{%>
              <strong><bean:write name="BUser" property="groupName"/></strong>
          <%}%>
          </TD></TR>
   <logic:present name="ADMIN.PANEL.DEPARTMENT">
        <TR>
          <TD nowrap><bean:message key="users.edit.group" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2">
          <%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
            <html:select styleClass="inputbox"  name="BUser" property="groupID" onchange="javascript:postAjax('users','menu_option',anchor + ':_MENU_OPTION')">                                     
                   <logic:present name="BGroups">
                      <html:options collection="BGroups" property="id" labelProperty="name"/>
                    </logic:present>
              </html:select>                 
          <%}else{%>
              <strong><bean:write name="BUser" property="departmentName"/></strong>
          <%}%>
          </TD></TR>
</logic:present>        
        <TR>
          <TD nowrap><bean:message key="users.edit.email" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2"><html:text  name="BUser" property="email" styleClass="inputbox" size="40" /></TD></TR>
        <TR>
          <TD nowrap><bean:message key="users.edit.phone" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2"><html:text  name="BUser" property="phone" styleClass="inputbox" size="40" /></TD></TR>
        <TR>
          <TD nowrap><bean:message key="users.edit.address" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2"><html:text  name="BUser" property="address" styleClass="inputbox" size="40" /></TD></TR>
        <TR>
          <TD nowrap><bean:message key="users.edit.period" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2">
          <%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
          <html:text  name="BUser" property="period" styleClass="inputbox" size="40" /> 
          <%}else{%>
          <strong><bean:write name="BUser" property="period"/></strong>
          <%}%>
          </TD></TR>
        <TR>
          <TD nowrap><bean:message key="users.edit.sex" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2">
          <html:select name="BUser" property="sex">
          <html:option value="1"><bean:message key="users.edit.sex.male" bundle="<%=interfaces%>"/></html:option>
          <html:option value="0"><bean:message key="users.edit.sex.female" bundle="<%=interfaces%>"/></html:option>
          </html:select>
            </TD></TR>
        <TR>
          <TD nowrap><bean:message key="users.edit.description" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2"><html:text  name="BUser" property="description" styleClass="inputbox" size="40" /></TD></TR>
          <%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
        <TR>
          <TD nowrap><bean:message key="users.edit.active" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2">
          <html:select name="BUser" property="active">
          <html:option value="1"><bean:message key="users.edit.active.enable" bundle="<%=interfaces%>"/></html:option>
          <html:option value="0"><bean:message key="users.edit.active.disable" bundle="<%=interfaces%>"/></html:option>
          </html:select>
            </TD></TR>
        <%}%>
        <TR>
<logic:greaterThan name="BUser" property="id" value="0">
          <TD nowrap><bean:message key="users.edit.datecreate" bundle="<%=interfaces%>"/></TD>
          <TD nowrap><strong><bean:write name="BUser" property="dateCreate"/></strong></TD>
</logic:greaterThan>
<logic:greaterThan name="BUser" property="id" value="0">
          <TD rowspan="3">
</logic:greaterThan>
<logic:equal name="BUser" property="id" value="0">
          <TD colspan="3">
</logic:equal>
                      <logic:notEqual name="BUser" property="username" value="<%=com.inf.IKey.ADMINISTRATOR%>">
                      <jsp:include page="/admin/menu/usercmd.jsp" />
                      </logic:notEqual>
            </td>
            </TR>
<logic:greaterThan name="BUser" property="id" value="0">
        <TR>
          <TD nowrap><bean:message key="users.edit.datepassword" bundle="<%=interfaces%>"/></TD>
          <TD nowrap><strong><bean:write name="BUser" property="datePassword"/></strong></TD></TR>
        <TR>
          <TD nowrap><bean:message key="users.edit.datelogin" bundle="<%=interfaces%>"/></TD>
          <TD nowrap><strong><bean:write name="BUser" property="dateLogin"/></strong></TD></TR>
</logic:greaterThan>
</TBODY></TABLE></TD>
    <TD vAlign=top width="40%"><Div id="reloadOption"><jsp:include page="/admin/menu/option.jsp" /></div>
          </TD></TR>
          </TBODY></TABLE>
          
</logic:present>          
</html:form>          
