<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String bean = "BUser";
    String disabled = me.isRole(com.inf.IRoles.RADMINISTRATOR)?"":"disabled";
%>  
<%!
    String getChecked(int pr, int key_id){
        return (pr>0 && (key_id==(pr & key_id)))?"checked":"";
    }
%>
<logic:present name="BGroup">
    <% bean = "BGroup";%>
</logic:present>
<logic:present name="<%=bean%>">
<bean:define name="<%=bean%>" property="privilege" id="pr" type="java.lang.Integer" />
<% int privilege=pr.intValue(); %>
<TABLE width="100%">
<TR>
<TD nowrap>
<input type="checkbox" name="privileges" value="<%=com.inf.IRoles.pSELECT%>" <%=disabled%> <%=getChecked(privilege,com.inf.IRoles.pSELECT)%>/>
<bean:message key="privilege.select" bundle="<%=interfaces%>"/>
</TD>
<TD nowrap>
<input type="checkbox" name="privileges" value="<%=com.inf.IRoles.pUPDATE%>" <%=disabled%> <%=getChecked(privilege,com.inf.IRoles.pUPDATE)%>/>
<bean:message key="privilege.update" bundle="<%=interfaces%>"/>
</TD>
</TR>
<TR>
<TD nowrap>
<input type="checkbox" name="privileges" value="<%=com.inf.IRoles.pINSERT%>" <%=disabled%> <%=getChecked(privilege,com.inf.IRoles.pINSERT)%>/>
<bean:message key="privilege.insert" bundle="<%=interfaces%>"/>
</TD>
<TD nowrap>
<input type="checkbox" name="privileges" value="<%=com.inf.IRoles.pDELETE%>" <%=disabled%> <%=getChecked(privilege,com.inf.IRoles.pDELETE)%>/>
<bean:message key="privilege.delete" bundle="<%=interfaces%>"/>
</TD>
</TR>
<TR>
<TD nowrap>
<input type="checkbox" name="privileges" value="<%=com.inf.IRoles.pDOWNLOAD%>" <%=disabled%> <%=getChecked(privilege,com.inf.IRoles.pDOWNLOAD)%>/>
<bean:message key="privilege.download" bundle="<%=interfaces%>"/>
</TD>
<TD nowrap>
<input type="checkbox" name="privileges" value="<%=com.inf.IRoles.pUPLOAD%>" <%=disabled%> <%=getChecked(privilege,com.inf.IRoles.pUPLOAD)%>/>
T&#236;m ki&#7871;m
</TD>
</TR>
</TABLE>
</logic:present>