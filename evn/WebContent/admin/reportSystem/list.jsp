<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
        

<logic:present name="BDatas">
<div   class="classReportList" >
    <logic:equal name="reportSystem" property="stylePrint" value="0" >
        <TABLE class=adminlist>
            <TBODY>
                <logic:iterate name="BDatas" id="bean" indexId="j"  type="java.lang.String[]">
                    <TR class=row0>
                    <%if(j==0){%>
                            <%for(int i =0;i<bean.length;i++){%>    
                            <th><%=bean[i]%></th>
                            <%}%>    
                    <%}else{%>
                        <%for(int i =0;i<bean.length;i++){%>    
                            <TD><%=bean[i]%></TD>
                        <%}%>
                    <%}%>
                    </TR>
                </logic:iterate>
            </TBODY>
        </TABLE>
    </logic:equal>
    <logic:equal name="reportSystem" property="stylePrint" value="1" >
        <TABLE class=adminlist>
            <TBODY>
                <logic:iterate name="BDatas" id="bean" indexId="j"  type="java.lang.String[][]">
                    <%for(int i =0;i<bean.length;i++){%>    
                    <TR class=row0>
                            <td width="25px"><%=i+1%></td>
                            <TD><Strong><%=bean[i][0]%></Strong></TD>
                            <TD><%=bean[i][1]%></TD>
                    </TR>
                <%}%>
                </logic:iterate>
            </TBODY>
        </TABLE>
    </logic:equal>
</div>
</logic:present>
<div style="color:red;font-size: 11px;font-weight:bold;">
        <logic:notEmpty name="errorValue"><bean:write name="errorValue" /></logic:notEmpty>
        <logic:notEmpty name="sussecValue"><bean:write name="sussecValue" /></logic:notEmpty>
</div>

<div style="float:left">
<bean:define name="reportSystem" property="id" id="id" type="java.lang.Integer" />
<%String params = anchor + ":_VIEW:id:" + id ;%>
<jsp:include page="/admin/reportSystem/paging.jsp">
    <jsp:param name="BEANS" value="BDatas"/>
    <jsp:param name="PARAMS" value="<%=params%>"/>
    <jsp:param name="FORM" value="reportSystem"/>
    <jsp:param name="METHOD" value="postAjax"/>
    <jsp:param name="POSITION" value="tdodyList"/>
</jsp:include>
</div>


