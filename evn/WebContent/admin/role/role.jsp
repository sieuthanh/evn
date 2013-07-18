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
<bean:define name="<%=bean%>" property="role" id="rl" type="java.lang.Integer" />
<% int role=rl.intValue(); %>
<TABLE width="100%" >
<TR>
    <TD nowrap colspan="2">
        <input type="checkbox" name="roles" value="<%=com.inf.IRoles.RADMINISTRATOR%>" <%=disabled%> <%=getChecked(role,com.inf.IRoles.RADMINISTRATOR)%>/>
        <bean:message key="role.administrator" bundle="<%=interfaces%>"/>        
    </TD>
</TR>
<TR>

</TR></TABLE>
</logic:present>