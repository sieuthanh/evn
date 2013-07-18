<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<bean:define name="BTables" property="nameTable" id="nameTable" type="java.lang.String" />
<%
    String fromAction = "ticket";
    String fromID = "ticket_id";
    int checkInsert = 0;
    
%>
<%if(nameTable.equals("EVN_TICKET_BUFFER")){
    fromAction = "ticket";
    fromID = "ticket_id";
    checkInsert=1;
}%>


<html:form action="<%=fromAction%>" method="post">
          <logic:notEmpty name="BRecordList3"> 
                    <bean:define name="BPaging" id="beans" type="com.form.FBeans"/>
    <div  style=" DISPLAY: block;  BACKGROUND: White;  OVERFLOW: auto;  WIDTH: 950px; HEIGHT: 500px;">

        <table class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
                <bean:define name="BRecordList3" id="recordList" type="java.lang.String[][]"/>
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
                       <%if(j==0&&checkInsert==1){%>
                       <TH class=title noWrap width="60px">
                        <img style="border:0px" src="images/addGroup.gif" title="<bean:message key="action.insert" bundle="<%=interfaces%>"/>" onClick="javascript:openWindow('<%=fromAction%>',anchor + ':_PREPARED_CREATE:nameTable:<%=nameTable%>');">
                       </TH>
                       <%}%>
                       <th>
                       <%=recordList[k][j]%>
                       </th>
                    <%}else{%>
                            <%if(j==0&&checkInsert==1){%>
                               <td width="60px">
                                  <img style="border:0px" src="images/update.png" title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" onClick="javascript:openWindow('<%=fromAction%>',anchor + ':_PREPARED_EDIT:nameTable:<%=nameTable%>:<%=fromID%>:<%=recordList[k][0]%>');">
                                  <img style="border:0px" src="images/delete.png" title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" onClick="javascript:if(messageDelete())post('<%=fromAction%>',anchor + ':_DELETE:nameTable:<%=nameTable%>:<%=fromID%>:<%=recordList[k][0]%>');">
                                  <img style="border:0px" src="images/download.png" title="Xuat xml" onClick="javascript:report('_GET_XML',<%=recordList[k][0]%>);">

                               </td>
                               <td> </td>
                           <%}%>
                               <td>
                                    <%=recordList[k][j]%>
                               </td>

                    <%}}%>
                     </tr>
                <%}%>
           
        </table>

    </div>
        <logic:present name="BRecordList3" >
            <div class="toolCmd" style="padding-left:10px;WIDTH: 100%;" align="left">
            <table cellpadding="0" cellspacing="0" width="100%" border="0">
                <tr>
                    <td align="left">
                    <Strong><bean:message key="page.caption.total" bundle="<%=interfaces%>"/> <%=beans.getTotalRows()%></strong></td>
                    <td align="right">
                     <%String params = anchor + ":_VIEW:nameTable:"+nameTable;%>
                    <jsp:include page="/evn/paging.jsp">
                        <jsp:param name="BEANS" value="BPaging"/>
                         <jsp:param name="PARAMS" value="<%=params%>"/>
                        <jsp:param name="FORM" value="functionEvn"/>
                        <jsp:param name="METHOD" value="post"/>
                  
                    </jsp:include>
                    </td>
                </tr>
            </table>
            </div>
            </logic:present>
     </logic:notEmpty>
</html:form>


 
     