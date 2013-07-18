<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String disabled = me.isRole(com.inf.IRoles.RADMINISTRATOR)?"":"disabled";
%>  
<%int j=0;int i=0;%>
<logic:present name="BReportSystem">
<table width="100%">
<logic:iterate name="BReportSystem" id="bean" type="com.form.admin.reportSystem.FReportSystem" >    
<%i++;%>
      
          <tr><td colspan="2" align="center">
          <input type="checkbox"  name="menus" value="<%=bean.getId()%>" <%=(bean.getChecked()==1?"checked":"")%> <%=disabled%> />
          <Strong>
          <bean:write name="bean" property="nameOfFileVn" />
          </Strong>
          </td></tr>
          <%j=0;%>


</logic:iterate>
</table>
</logic:present>