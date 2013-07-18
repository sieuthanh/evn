<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="users" method="post">

<div align="left">
Ch&#7885;n ph&#242;ng:
<bean:define name="function" property="id" id="id" />
<% String CMDsearch="javascript:postAjax('users','listUsers',anchor + ':_SEARCH:id:"+ id +"')";%>
<html:select styleClass="inputbox" name="BDepartment" property="departmentID" onchange="<%=CMDsearch%>">
<html:option value="0"><bean:message key="app.office" bundle="<%=interfaces%>"/></html:option>
<html:options collection="BDepartments" property="id" labelProperty="name"/>
</html:select>   

<input maxlength="100" type="text" name="nameUser" style="width:200px" onkeydown="if(event.keyCode==13){postAjax('users','listUsers',anchor+':_SEARCH');return false;}" />
    <html:button property="end" styleClass="button" onclick="<%=CMDsearch%>">
      <bean:message key="cmd.search" bundle="<%=interfaces%>"/>
    </html:button>
 </div>
<br>
<div id="listUsers">
<jsp:include page="/admin/users/list.jsp" />
</div>
</html:form>
