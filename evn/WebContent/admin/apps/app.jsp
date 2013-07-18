<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%
    String bean = "BUser";
    String disabled = me.isRole(com.inf.IRoles.RADMINISTRATOR)?"":"disabled";
%>
<logic:present name="BGroup">
    <% bean = "BGroup";%>
</logic:present>
<logic:present name="<%=bean%>">
    <bean:define name="<%=bean%>" property="app" id="apps" type="java.lang.String" />
    <TABLE width="100%">
        <logic:present name="BApps">
              <logic:iterate id="app" name="BApps" type="com.form.admin.apps.FApp">
                <TR><TD nowrap>
                    <%if(apps.indexOf(com.inf.admin.IConstantsAdmin.APP_SEPARATE_ + app.getId() + com.inf.admin.IConstantsAdmin.APP_SEPARATE_)>=0){%>
                        <input type="checkbox" name="apps" checked value="<%=app.getId()%>" <%=disabled%>/>
                    <%}else{%>
                        <input type="checkbox" name="apps" value="<%=app.getId()%>" <%=disabled%>/>
                    <%}%>
                    <bean:write name="app" property="name" /> <font color="#BB0000;">(<bean:write name="app" property="code" />)</font>
                </TD></TR>
              </logic:iterate>
        </logic:present>
    </TABLE>
</logic:present>
