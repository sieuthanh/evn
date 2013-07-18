<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<script language=javascript>
function report(flag,id){  
    post('functionEvn',anchor + ':'+flag+':id:'+ id);remove('functionEvn',anchor);
}
</script>
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

<jsp:include page="/evn/alert.jsp" />

<html:form action="<%=fromAction%>" method="post">
    <table class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr>
        <TH width="99%">Danh s&#225;ch alert</TH>
        <th align="right">
        </th>
        </tr>
    </table>

          <logic:notEmpty name="BRecordList"> 
                    <bean:define name="BPaging" id="beans" type="com.form.FBeans"/>
    <div  style=" DISPLAY: block;  BACKGROUND: White;  OVERFLOW: auto;  WIDTH: 100%; HEIGHT: 500px;">

                <bean:define name="BRecordList" id="recordList" type="java.lang.String[][]"/>
                <%
                    for (int k = 1; k < recordList.length; k++) {%>
                    <%
                    if(k*beans.getPageCurrent()>beans.getTotalRows())break;
                    if (recordList[k]==null){
                        break;
                    }
                    %>
                        <table class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
                            <tr class="<%=(k%2==0)?"content1":"content"%>">
                                               <td><%=recordList[k][2]%>(<%=recordList[k][4]%>):<%=recordList[k][8]%></td>
                                               <td width="20px">
                                                  <img style="border:0px" src="images/download.png" title="Xuat xml" onClick="javascript:report('_GET_ALERT_XML',<%=recordList[k][0]%>);">
                                               </td>
                            </tr>
                            <tr class="<%=(k%2==0)?"content1":"content"%>">
                                               <td>Table: <%=recordList[k][10]%></td>
                                               <td width="20px"></td>
                            </tr>
   
                            <tr class="<%=(k%2==0)?"content1":"content"%>">
                                               <td title="<%=recordList[k][6]%>">
                                               <%String tt=recordList[k][6]+"";
                                               if(tt.length()>35){
                                               tt=tt.substring(0,35);
                                               }
                                               %>
                                               Error: <%=tt%></td>
                                               <td width="20px"></td>
                            </tr>
                        </table>
                <%}%>
           
        

    </div>
       <div>

        <logic:present name="BRecordList" >
            <table cellpadding="0" cellspacing="0" width="100%" border="0">
                <tr>
                    <td align="left"><Strong><bean:message key="page.caption.total" bundle="<%=interfaces%>"/> <%=beans.getTotalRows()%></strong></td>
                    <td>
                     <%String params = anchor + ":_VIEW_ALERT:nameTable:"+nameTable;%>
                    <jsp:include page="/evn/paging.jsp">
                        <jsp:param name="BEANS" value="BPaging"/>
                         <jsp:param name="PARAMS" value="<%=params%>"/>
                        <jsp:param name="FORM" value="functionEvn"/>
                        <jsp:param name="METHOD" value="post"/>
                  
                    </jsp:include>
                    </td>
                </tr>
            </table>
        </logic:present>
    </div>
            
     </logic:notEmpty>

</html:form>

 
     