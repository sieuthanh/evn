<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table width="100%" border="0px"><TR><TD>
<TABLE align="left" class=adminheading border=0>
  <TBODY>
  <TR>
    <TH class="user"><bean:message key="users.log.caption" bundle="<%=interfaces%>"/></TH>
    </TR></TBODY></TABLE>    
</td><TD>
<jsp:include page="/admin/paging.jsp">
    <jsp:param name="BEANS" value="BLogs"/>
    <jsp:param name="FORM" value="log"/>
    <jsp:param name="METHOD" value="post"/>
</jsp:include>
</td>
</tr></table>

<html:form action="log">
<input type="hidden" name="<%=anchor%>" value="_VIEW">
<TABLE class=adminlist>
  <TBODY>
  <TR>
    <TH class=title noWrap>
       <input type="text" name="dateLogin" id="dateLogin"  style="width:60px" value="<bean:write name="log" property="dateLogin"/>" onchange="this.form.submit();"/><img src="<%=contextPath%>/images/ew_calendar.gif"  onClick="popUpCalendar(this,'dateLogin','dd/mm/yyyy','document.log.submit()');"/>
    </TH>
    <TH class=title noWrap><bean:message key="users.log.Timelogin" bundle="<%=interfaces%>"/></TH>
    <TH class=title noWrap>    
        <html:select property="username"  style="width:100px" onchange="this.form.submit();">
            <html:option value=""> <bean:message key="users.log.username" bundle="<%=interfaces%>"/></html:option>
            <logic:present name="BUsernames" >  
            <html:options collection="BUsernames" property="username" labelProperty="username"/>
            </logic:present>
        </html:select>   
    </TH>
    <TH class=title noWrap>
        <html:select property="result"  style="width:140px" onchange="this.form.submit();" >
        <html:option value="-1"><bean:message key="users.log.result" bundle="<%=interfaces%>"/></html:option>
        <html:option value="0"><bean:message key="users.log.ResultFail" bundle="<%=interfaces%>"/></html:option>
        <html:option value="1"><bean:message key="users.log.ResultSuccess" bundle="<%=interfaces%>"/></html:option>
        <html:option value="2"><bean:message key="users.log.ResultLogout" bundle="<%=interfaces%>"/></html:option>
        <html:option value="3"><bean:message key="users.log.changePassFail" bundle="<%=interfaces%>"/></html:option>
        <html:option value="4"><bean:message key="users.log.changePassSuccess" bundle="<%=interfaces%>"/></html:option>
        </html:select>   
    </TH>
    <TH class=title noWrap>
        <html:select  property="host"  style="width:90px" onchange="this.form.submit();">
            <html:option value=""><bean:message key="users.log.Host" bundle="<%=interfaces%>"/></html:option>
    <logic:present name="BHosts">  
            <html:options collection="BHosts" property="host" labelProperty="host"/>
    </logic:present>
        </html:select>   
    </TH>
    <TH class=title noWrap><bean:message key="users.log.AppName" bundle="<%=interfaces%>"/></TH>
    </TR>
<logic:present name="BLogs">
<logic:iterate name="BLogs" id="bean" type="com.form.admin.log.FLog">                       
  <TR class=row0>
    <TD><bean:write name="bean" property="dateLogin"/></TD>
    <TD><bean:write name="bean" property="timeLogin"/></TD>
    <TD><bean:write name="bean" property="username"/></TD>
    <TD>
    <logic:equal name="bean" property="result" value="0"><bean:message key="users.log.ResultFail" bundle="<%=interfaces%>"/>
    </logic:equal>
    <logic:equal name="bean" property="result" value="1"><bean:message key="users.log.ResultSuccess" bundle="<%=interfaces%>"/>
    </logic:equal>
    <logic:equal name="bean" property="result" value="2"><bean:message key="users.log.ResultLogout" bundle="<%=interfaces%>"/>
    </logic:equal>
    <logic:equal name="bean" property="result" value="3"><bean:message key="users.log.changePassFail" bundle="<%=interfaces%>"/>
    </logic:equal>
    <logic:equal name="bean" property="result" value="4"><bean:message key="users.log.changePassSuccess" bundle="<%=interfaces%>"/>
    </logic:equal>
    </TD>
    <TD><bean:write name="bean" property="host"/></TD>
    <TD><bean:write name="bean" property="appName"/></TD>
    </TR>
</logic:iterate>
</logic:present>
</TBODY></TABLE>
</html:form>