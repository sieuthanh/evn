<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define name="reportSystemSum" property="typeChart" id="typeChart" type="java.lang.Integer" />
<bean:define name="reportSystemSum" property="enableChart" id="enableChart" type="java.lang.Integer" />
<%if(enableChart==1){%>
    <jsp:include page="/admin/reportSystem/sum/chart.jsp" />
<%}%>
        

<logic:present name="BDatas">
<div   class="classReportList" >
    <logic:equal name="reportSystemSum" property="stylePrint" value="0" >
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
    
    <logic:equal name="reportSystemSum" property="stylePrint" value="1" >
        <TABLE class=adminlist>
            <TBODY>
            <bean:define name="reportSystemSum" property="values" id="values" type="java.lang.String[][]" />
            <bean:define name="reportSystemSum" property="xlength" id="xlength" type="java.lang.Integer" />

                   <%for(int i =values.length-1;i>=0;i--){%>  
                    <TR class=row0>
                     <% if(i==0){%>
                        <th><Strong><%=values[i][0]%></Strong></th>
                        <%for (int k=1 ;k<=xlength ;k++ )  {%>
                            <th><%=values[i][k]%></th>
                        <%}%>
                   
                      <%}else{%>
                        <TD><Strong><%=values[i][0]%></Strong></TD>
                        <%for (int k=1 ;k<=xlength ;k++ )  {%>
                            <TD><%=values[i][k]%></TD>
                        <%}}%>
                    </TR>
                 <%}%>
            </TBODY>
        </TABLE>
    </logic:equal>
</div>
</logic:present>
<div style="color:red;font-size: 11px;font-weight:bold;">
        <logic:notEmpty name="errorValue"><bean:write name="errorValue" /></logic:notEmpty>
        <logic:notEmpty name="sussecValue"><bean:write name="sussecValue" /></logic:notEmpty>
</div>




