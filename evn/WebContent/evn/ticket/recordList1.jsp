<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<bean:define name="BTables" property="nameTable" id="nameTable" type="java.lang.String" />
          <logic:notEmpty name="BRecordList1"> 
          <bean:define name="BPaging1" id="beans" type="com.form.FBeans"/>
    <div  style=" DISPLAY: block;  BACKGROUND: White;  OVERFLOW: auto;  WIDTH: 750px; HEIGHT: 200px;">

        <table class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
                <bean:define name="BRecordList1" id="recordList" type="java.lang.String[][]"/>
                <%
                    for (int k = 0; k < recordList.length; k++) {%>
                    <tr class="<%=(k%2==0)?"content1":"content"%>">
                    <%
                    if(k*beans.getPageCurrent()>beans.getTotalRows())break;
                    if (recordList[k]==null){
                        break;
                    }
                    for(int j = 0; j < recordList[k].length; j++){
                    if((k==0)&&(j<recordList[k].length-3)){%>
                       <th>
                       <%=recordList[k][j]%>
                       </th>
                    <%}else{%>
                       <%if(j>=1&&j<4){%>
                           <td title="<%=recordList[k][j+6]%>">
                               <%=recordList[k][j]%>
                           </td>
                       
                       <%}else if(j<recordList[k].length-3){%>
                           <td>
                               <%=recordList[k][j]%>
                           </td>
                       <%}%>
                    <%}}%>
                     </tr>
                <%}%>
           
        </table>

    </div>

     </logic:notEmpty>



 
     