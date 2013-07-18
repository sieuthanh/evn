<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:hidden name="function" property="pageIndex"/>
<TABLE class=adminlist>
  <TBODY>
  <TR>
    <TH class=title noWrap width="20px"><bean:message key="users.list.no" bundle="<%=interfaces%>"/></TH>
    <TH class=title noWrap><bean:message key="users.list.name" bundle="<%=interfaces%>"/></TH>
    <TH class=title noWrap><bean:message key="users.list.username" bundle="<%=interfaces%>"/></TH>
    <TH class=title noWrap width="60px"><bean:message key="users.list.active" bundle="<%=interfaces%>"/></TH>    
    <TH class=title noWrap width="100px"><bean:message key="users.list.lastvisit" bundle="<%=interfaces%>"/></TH>
    <TH class=title noWrap width="50px"><bean:message key="users.list.days" bundle="<%=interfaces%>"/></TH>
    <%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
    <TH class=title noWrap width="20px">&nbsp;</TH>
    <%}%>

    </TR>
<logic:present name="BUsers">
<bean:define name="BUsers" id="beans" type="com.form.FBeans"/>
<%
  int i = beans.getFirstRecord();
  //System.out.println(i);
%>               
<logic:iterate name="BUsers" id="bean" type="com.form.admin.users.FUser">    

  <TR class=row0>
    <TD><%=i++%></TD>
    <TD><bean:write name="bean" property="fullName"/></TD>
    <bean:define name="bean" property="id" id="userid" />
    <TD><A href="javascript:post('function',anchor + ':_EDITUSERS:id:<%=userid%>')"><bean:write name="bean" property="username"/></A> </TD>
    <TD align=center id="<%=bean.getId()%>">
            <logic:notEqual name="bean" property="username" value="<%=com.inf.IKey.ADMINISTRATOR%>">
                    <A style="none" href="javascript:postAjax('users',<%=bean.getId()%>,anchor + ':_ACTIVE:id:<%=userid%>:active:<%=bean.getActive()%>');">
                    <logic:equal name="bean" property="active" value="1">
                    <IMG height=12 src="<%=contextPath%>/images/tick.png" width=12 border=0 title='<bean:message key="users.edit.active.enable" bundle="<%=interfaces%>"/>'>
                    </logic:equal>
                    <logic:equal name="bean" property="active" value="0">
                    <IMG height=12 src="<%=contextPath%>/images/disable.png" width=12 border=0 title='<bean:message key="users.edit.active.disable" bundle="<%=interfaces%>"/>'>
                    </logic:equal>
                    </A>
            </logic:notEqual>
    </TD>    
    <TD noWrap><bean:write name="bean" property="dateLogin"/></TD>
    <TD >
    <logic:equal name="bean" property="period" value="0">
    <bean:message key="action.none" bundle="<%=interfaces%>"/>
    </logic:equal>
    <logic:notEqual name="bean" property="period" value="0">
    <bean:write name="bean" property="period"/> <bean:message key="users.list.day" bundle="<%=interfaces%>"/>
    </logic:notEqual>
    </TD>
<logic:notEqual name="bean" property="username" value="<%=com.inf.IKey.ADMINISTRATOR%>">
<%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
<%if(!bean.getUsername().equals(me.getUsername())){%>
    <td width="20px">
      <img style="border:0px" src="<%=contextPath%>/images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())javascript:postAjax('users','tdMainBody',anchor + ':_DELETE:id:<%=userid%>:groupID:<%=bean.getGroupID()%>');">
    </td>                                           
<%}%>
<%}%>
</logic:notEqual>

    </TR>
</logic:iterate>
</logic:present>
</TBODY>
</TABLE>
<div>
<div style="float:left">
<Strong><bean:message key="page.caption.total" bundle="<%=interfaces%>"/> 
<bean:write name="beans" property="totalRows"/></strong>
</div>
<div style="float:right">
<bean:define name="BDepartment" property="departmentID" id="departmentID"/>
<bean:define name="function" property="id" id="idGroupt"/>
<%String params = anchor + ":_VIEWUSERS:id:" + idGroupt + ":departmentID:" + departmentID ;%>
<jsp:include page="/admin/paging.jsp">
    <jsp:param name="BEANS" value="BUsers"/>
    <jsp:param name="PARAMS" value="<%=params%>"/>
    <jsp:param name="FORM" value="function"/>
    <jsp:param name="METHOD" value="postAjax"/>
    <jsp:param name="POSITION" value="tdMainBody"/>
</jsp:include>
</div>
</div>
<jsp:include page="/admin/alert.jsp" />