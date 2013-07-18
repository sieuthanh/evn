<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<table class="list-voffice" width="100%" cellpadding="0" cellspacing="0">

        <TR>
            <td align="right" id="idrecordlist">
                    <html:form action="searchAlert" method="post">
                    <html:hidden  name="BTables" property="nameTable" /> 
                    <table class="list-voffice" align="left" width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <th>Ch&#7885;n tr&#432;&#7901;ng t&#236;m ki&#7871;m: </th>
                            <th><html:select  styleClass="inputbox" property="columnName"  >
                                   <logic:present name="BColumnsList">
                                       <logic:iterate name="BColumnsList" id="bean" type="com.form.evn.FChangeData">
                                       <%if(bean.getColumnTypeName().equals("2")){%>
                                            <html:option value="<%=bean.getColumnName()%>"><bean:write name="bean" property="columnName" /></html:option>
                                       <%}%>
                                       </logic:iterate>
                                   </logic:present>
                                </html:select>
                               </th>
                    
                            <th>&#272;i&#7873;u ki&#7879;n t&#236;m ki&#7871;m:</th>
                             <th><html:text property="sqlWhere" styleClass="inputbox" size="30" onkeydown="if(event.keyCode==13){post('searchAlert',anchor +':_VIEW_ALERT');return false;}" />        
                            </th>                               
                            <th><img style="border:0px" src="<%=contextPath%>/images/bt_search.png" onclick="javascript:post('searchAlert',anchor +':_VIEW_ALERT')" title="Search"/>
                            </th>
                            <th width="30%">
                            </th>
                        </tr>
                    </table>
                     </html:form> 
            </td>
        </TR>
        <TR>
            <td>
                            <jsp:include page="/evn/alertBuffer/recordList.jsp" />
            </td>
        </TR>

</TABLE>

    
