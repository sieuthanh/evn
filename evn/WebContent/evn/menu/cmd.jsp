<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define name="BTables" property="nameTable" id="nameTable" type="java.lang.String" />

<%
    String fromAction = "reportsBuffer";
    String fromActionfunc = "functionEvn";
    String BFrom = "BReportsBuffer";
    String fromID = "report_Id";
    String anchorForm = "_VIEW";
    int checkT=0;
%>
<%if(nameTable.equals("EVN_REPORTS_NORM_BUFFER")){
    fromAction="reportsNormBuffer";
    BFrom="BReportsNormBuffer";
    fromID="norm_Id";
}else if(nameTable.equals("EVN_SOURCE_CONNECT_TO_BUFFER")){
    fromAction="sourceConnectBuffer";
    BFrom="BSourceConnectBuffer";
    fromID="src_Connect_Id";
    checkT=1;
}else if(nameTable.equals("EVN_SOURCE_ACCOUNT_BUFFER")){
    fromAction="sourceAccountBuffer";
    BFrom="BSourceAccountBuffer";
    fromID="account_Id";
}else if(nameTable.equals("EVN_REPORTS_BUFFER")){
    fromAction = "reportsBuffer";
    BFrom = "BReportsBuffer";
    fromID = "report_Id";
}else if(nameTable.equals("EVN_TABLES")){
    fromAction = "tables";
    BFrom = "BTables";
    fromID = "id";
}else if(nameTable.equals("EVN_TICKET_BUFFER")){
    fromAction = "ticket";
    BFrom = "BTicket";
    fromID = "ticket_id";
    fromActionfunc="ticket";
    anchorForm="_VIEW_TICKET";
}else if(nameTable.equals("EVN_ERROR_BUFFER")){
    fromAction = "evnErrorBuffer";
    BFrom = "BTables";
    fromID = "error_Id";
}else if(nameTable.equals("EVN_MODULE_BUFFER")){
    fromAction = "evnModuleBuffer";
    BFrom = "BTables";
    fromID = "moduleId";
}%>

<logic:present name="<%=BFrom%>">
<BR>
<TABLE id=toolbar cellSpacing=0 cellPadding=0 border=0>
<TBODY>
<TR vAlign=center align=middle>
<%if(checkT==1){%>
    <TD><A href="javascript:message('idcmd','loading...');openWindow('<%=fromAction%>',anchor + ':_CHECKCON:nameTable:<%=nameTable%>');"><IMG 
        title=checkConnection alt=checkConnection src="images/testConn.png" align=middle 
        border=0 name=checkConnection></A> </TD>
<%}%>
  <logic:equal name="<%=BFrom%>" property="<%=fromID%>" value="0">
    <TD><A href="javascript:openWindow('<%=fromAction%>',anchor + ':_CREATE:nameTable:<%=nameTable%>');"><IMG 
        title=add alt=add src="images/addnew.png" align=middle 
        border=0 name=save></A> </TD>
  </logic:equal>
  <logic:greaterThan name="<%=BFrom%>" property="<%=fromID%>" value="0">
    <TD>&nbsp;</TD>
    <TD><A href="javascript:openWindow('<%=fromAction%>',anchor + ':_EDIT:nameTable:<%=nameTable%>');"><IMG 
        title=edit alt=edit src="images/updatenew.png" align=middle 
        border=0 name=save></A> </TD>
  </logic:greaterThan>
    <TD>&nbsp;</TD>
    <TD><A href="javascript:post('<%=fromActionfunc%>',anchor + ':<%=anchorForm%>:nameTable:<%=nameTable%>');"><IMG 
        title=exit alt=exit src="images/exitnew.png" align=middle 
        border=0 name=exit></A> </TD>
        
</TR></TBODY></TABLE>          
</logic:present>
