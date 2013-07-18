<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="searchAlert" method="post">
<html:hidden  name="BTables" property="nameTable" /> 
<table class="list-voffice" align="left" width="100%" cellpadding="0" cellspacing="0">
    <TR>
        <TH colspan="2">T&#236;m ki&#7871;m</TH>
    </TR>
    <tr class="content">
        <td>Ch&#7885;n tr&#432;&#7901;ng:
            <html:select  styleClass="inputbox" property="columnName" style="width:120px">
               <logic:present name="BColumnsList">
                   <logic:iterate name="BColumnsList" id="bean" type="com.form.evn.FChangeData">
                   <%if(!bean.getColumnTypeName().equals("4")){%>
                        <html:option value="<%=bean.getColumnName()%>"><bean:write name="bean" property="columnName" /></html:option>
                   <%}%>
                   </logic:iterate>
               </logic:present>
            </html:select>
        </td>
                       
        <td><img style="border:0px" src="<%=contextPath%>/images/bt_search.png" onclick="javascript:post('searchAlert',anchor +':_VIEW_ALERT')" title="Search"/>
        </td>
    </tr>
    <tr class="content">
        <td colspan="2">&#272;i&#7873;u ki&#7879;n:
            <html:text property="sqlWhere" styleClass="inputbox" size="30" onkeydown="if(event.keyCode==13){post('searchAlert',anchor +':_VIEW_ALERT');return false;}" />        
        </td>
            
    </tr>
</table>
 </html:form> 


 
     