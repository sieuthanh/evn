<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="searchEvn" method="post">
<html:hidden  name="BTables" property="nameTable" /> 
<html:hidden  name="BTables" property="type" /> 
<table class="list-voffice" align="left" width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <th>Ch&#7885;n tr&#432;&#7901;ng t&#236;m ki&#7871;m:
 
        </th>
        <th>
            <html:select  styleClass="inputbox" property="columnName"  >
               <logic:present name="BColumnsList">
                   <logic:iterate name="BColumnsList" id="bean" type="com.form.evn.FChangeData">
                   <%if(!bean.getColumnTypeName().equals("4")){%>
                        <html:option value="<%=bean.getColumnName()%>"><bean:write name="bean" property="columnName" /></html:option>
                   <%}%>
                   </logic:iterate>
               </logic:present>
            </html:select>
           </th>

        <th>&#272;i&#7873;u ki&#7879;n t&#236;m ki&#7871;m:</th>
        <th><html:text property="sqlWhere" styleClass="inputbox" size="30" onkeydown="if(event.keyCode==13){post('searchEvn',anchor +':_VIEW');return false;}" />        
        </th>            
        <th><img style="border:0px" src="<%=contextPath%>/images/bt_search.png" onclick="javascript:post('searchEvn',anchor +':_VIEW')" title="Search"/>
        </th>
        <th width="30%">
        </th>
    </tr>
</table>
 </html:form> 
 
<table class="list-voffice" width="100%" cellpadding="0" cellspacing="0"   >
                    <tr>
                        <td id="idrecordlist">
                            <jsp:include page="/evn/recordList.jsp" />
                        </td>
                    </tr>
                </table>
           
