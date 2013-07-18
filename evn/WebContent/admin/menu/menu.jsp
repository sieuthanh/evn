<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="login" method="post"/>
<html:form action="function" method="post" />
<script type="text/javascript">
window.addEvent('domready', function() {
	SqueezeBox.assign($$('a.modal-button'), {
		parse: 'rel'
	});
});
</script>
<TABLE class=menubar cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD class=menubackgr style="PADDING-LEFT: 5px">
      <DIV id=myMenuID></DIV>
      <SCRIPT language=JavaScript type=text/javascript>
			var myMenu =
			[
                                <%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
				[null,'<bean:message key="menu.top.home" bundle="<%=interfaces%>"/>','javascript:post("function",anchor + ":_HOME")',null,'']
                                        ,_cmSplit,
                                        [null,'<bean:message key="menu.top.admin" bundle="<%=interfaces%>"/>',null,null,'',
                                            ['<img src="<%=contextPath%>/images/menu/user.png" />','<bean:message key="menu.top.admin.useradd" bundle="<%=interfaces%>"/>','javascript:post("function",anchor + ":_EDITUSERS:id:0")',null,''],
                                            _cmSplit,
                                            ['<img src="<%=contextPath%>/images/menu/users.png" />', '<bean:message key="menu.top.admin.userlist" bundle="<%=interfaces%>"/>', 'javascript:post("function",anchor + ":_LISTUSERS")', null, ''],
                                            _cmSplit,
                                            ['<img src="<%=contextPath%>/images/menu/group.png" />','<bean:message key="menu.top.admin.grouplist" bundle="<%=interfaces%>"/>','javascript:post("function",anchor + ":_LISTGROUPS")',null,'']
                                            <%if(com.lib.AppConfigs.ADMIN_PANEL_DEPARTMENT){%>                                   
                                            ,_cmSplit,
                                            ['<img src="<%=contextPath%>/images/menu/department.png" />','<bean:message key="menu.top.admin.department" bundle="<%=interfaces%>"/>','javascript:post("function",anchor + ":_LISTDEPARTMENTS")',null,'']
                                    <%}%>                                    
				]
                                <%if(me.getUsername().equals(com.inf.IKey.ADMINISTRATOR)){%>                                   
				,_cmSplit,
				[null,'<bean:message key="menu.top.config" bundle="<%=interfaces%>"/>',null,null,'',
                                    ['<img src="<%=contextPath%>/images/menu/app.png" />','<bean:message key="menu.top.config.applist" bundle="<%=interfaces%>"/>','javascript:post("function",anchor + ":_LISTAPPS")',null,''],
                                    _cmSplit,
                                    ['<img src="<%=contextPath%>/images/menu/users.png" />','<bean:message key="menu.top.config.log" bundle="<%=interfaces%>"/>','javascript:openWindow("function",anchor + ":_ACTIVE:id:3")',null,''],
                                    _cmSplit,
                                    ['<img src="<%=contextPath%>/images/menu/role.png" />','<bean:message key="menu.top.config.role" bundle="<%=interfaces%>"/>','javascript:openWindow("function",anchor + ":_ACTIVE:id:1")',null,''],
                                    ['<img src="<%=contextPath%>/images/menu/privilege.png" />','<bean:message key="menu.top.config.privilege" bundle="<%=interfaces%>"/>','javascript:openWindow("function",anchor + ":_ACTIVE:id:2")',null,'']
				]
                                <%}%>  
                                
				,_cmSplit,
				[null,'<bean:message key="menu.top.log" bundle="<%=interfaces%>"/>','javascript:post("function",anchor + ":_LISTLOGS")',null,'']
                                <%}%>
			];
			cmDraw ('myMenuID', myMenu, 'hbr', cmThemeOffice, 'ThemeOffice');
            </SCRIPT>
    </TD>
    <TD class=menubackgr style="PADDING-RIGHT: 5px" align=right valign=middle>
      <div class="topmenu" valign=middle>      
      <%=me.getUsername()%>
      [
      <A style="COLOR: #0000FF;" href="javascript:post('login',anchor + ':_LOGOUT');">
      <bean:message key="logout.caption" bundle="<%=interfaces%>"/>
      </A> |       
      <a class="modal-button" href="login<%=extention%>?<%=anchor%>=_PREPARED_EDIT" rel="{handler: 'iframe', size: {x: 310, y: 210}}">
      <bean:message key="login.change.password" bundle="<%=interfaces%>"/>
      </A>
      ]      
      </div>
    </TD>
</TR></TBODY></TABLE>
