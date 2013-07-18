<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<bean:define name="BTables" property="nameTable" id="nameTable" type="java.lang.String" />
<%
    String fromAction = "reportsBuffer";
    String fromID = "report_Id";
    int checkInsert = 0;
    
%>
<%if(nameTable.equals("EVN_REPORTS_NORM_BUFFER")){
    fromAction="reportsNormBuffer";
    fromID="norm_Id";
    checkInsert=1;
}else if(nameTable.equals("EVN_SOURCE_CONNECT_TO_BUFFER")){
    fromAction="sourceConnectBuffer";
    fromID="src_Connect_Id";
    checkInsert=1;
}else if(nameTable.equals("EVN_SOURCE_ACCOUNT_BUFFER")){
    fromAction="sourceAccountBuffer";
    fromID="account_Id";
    checkInsert=1;
}else if(nameTable.equals("EVN_REPORTS_BUFFER")){
    fromAction = "reportsBuffer";
    fromID = "report_Id";
    checkInsert=1;
}%>


          <logic:notEmpty name="BRecordList"> 
                    <bean:define name="BPaging" id="beans" type="com.form.FBeans"/>
    <div  style=" DISPLAY: block;  BACKGROUND: White;  OVERFLOW: auto;  WIDTH: 950px; HEIGHT: 500px;">

        <table class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
                <bean:define name="BRecordList" id="recordList" type="java.lang.String[][]"/>
                <%
                    for (int k = 0; k < recordList.length; k++) {%>
                    <tr class="<%=(k%2==0)?"content1":"content"%>">
                    <%
                    if(k*beans.getPageCurrent()>beans.getTotalRows())break;
                    if (recordList[k]==null){
                        break;
                    }
                    for(int j = 0; j < recordList[k].length; j++){
                    if(k==0){%>
                       <th>
                       <%=recordList[k][j]%>
                       </th>
                       <%if(j==recordList[k].length-1&&checkInsert==1){%>
                       <TH class=title noWrap width="40px">
                        <img style="border:0px" src="images/addGroup.gif" title="<bean:message key="action.insert" bundle="<%=interfaces%>"/>" onClick="javascript:openWindow('<%=fromAction%>',anchor + ':_PREPARED_CREATE:nameTable:<%=nameTable%>');">
                       </TH>
                       <%}%>
                    <%}else{%>
                       <td>
                       <%=recordList[k][j]%>
                       </td>

                            <%if(j==recordList[k].length-1&&checkInsert==1){%>
                               <td width="40px">
                                  <img style="border:0px" src="images/update.png" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:openWindow('<%=fromAction%>',anchor + ':_PREPARED_EDIT:nameTable:<%=nameTable%>:<%=fromID%>:<%=recordList[k][0]%>');">
                                  <img style="border:0px" src="images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('<%=fromAction%>',anchor + ':_DELETE:nameTable:<%=nameTable%>:<%=fromID%>:<%=recordList[k][0]%>');">
                               </td>
                           <%}%>
                    <%}}%>
                     </tr>
                <%}%>
           
        </table>

    </div>

     </logic:notEmpty>



 
     