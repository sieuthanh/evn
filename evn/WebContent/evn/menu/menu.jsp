<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="login" method="post"/>
<html:form action="function" method="post" />
<html:form action="functionEvn" method="post" />
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
				[null,'<bean:message key="menu.top.home" bundle="<%=interfaces%>"/>','javascript:post("functionEvn",anchor +":_VIEW:nameTable:EVN_REPORTS_BUFFER")',null,'']
                                 ,_cmSplit,
				[null,'B&#225;o c&#225;o','javascript:post("functionEvn",anchor +":_VIEW:nameTable:EVN_REPORTS_BUFFER")',null,'']
                                  ,_cmSplit,
				[null,'Nghi&#7879;p v&#7909;','javascript:post("functionEvn",anchor +":_VIEW_NV")',null,'']    
                                 ,_cmSplit,
				[null,'Gi&#225;m s&#225;t','javascript:post("functionEvn",anchor +":_VIEW:nameTable:EVN_ALERT_BUFFER")',null,'']  
                                 ,_cmSplit,
				[null,'Qu&#7843;n tr&#7883; ticket','javascript:post("functionEvn",anchor +":_VIEW_TICKET")',null,''] 
                                 ,_cmSplit,
				[null,'Qu&#7843;n l&#253; th&#244;ng b&#225;o','javascript:post("functionEvn",anchor +":_VIEW_ALERT")',null,''] 
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
