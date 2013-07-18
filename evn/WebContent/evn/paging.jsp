<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present parameter="BEANS">
<%String BS = request.getParameter("BEANS");%>
<logic:present name="<%=BS%>">
<bean:define name="<%=BS%>" id="BEANS" type="com.form.FBeans"/>
<%
    String FORM = request.getParameter("FORM");
    String PARAMS = request.getParameter("PARAMS");
    if(PARAMS==null){
        PARAMS = "";
    }else{
        PARAMS = PARAMS + ":";
    }
    String METHOD = request.getParameter("METHOD");
    String POSITION = request.getParameter("POSITION");
    if(POSITION==null){
        POSITION="";
    }else{
        POSITION=",'" + POSITION + "'";    
    }
%>
<logic:greaterThan name="BEANS" property="pagesCount" value="1">
<%
int pagesCount = BEANS.getPagesCount();
int pageCurrent = BEANS.getPageCurrent();
%>
<TABLE style="table-layout:fixed;border:0px">
  <TBODY>
  <TR>
    <TH style="width:50px;padding-top:6px">
    <%if(pageCurrent>1){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/record-first.png" onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:1')">
    <%}else{%>
        <img src="<%=contextPath%>/images/record-first-g.png">
    <%}%>
    <%if(BEANS.havePrevPage()){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/record-prev.png"  onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:<%=(pageCurrent-1)%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/record-prev-g.png">
    <%}%>
    </TH>
    <TH style="padding-top:0px;font-family:Tahoma;font-size:11px;font-weight: normal;" nowrap>
            <bean:message key="page.caption" bundle="<%=interfaces%>"/>
            <input onkeydown="if(event.keyCode==13){<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:' + this.value);return false;}" type="text" name="selectPage" value="<%=pageCurrent%>" style="font-family:Tahoma;font-size:11px;width:15px;text-align: right;">
            <bean:message key="page.separate" bundle="<%=interfaces%>"/><%=pagesCount%>
     </TH> 
     <TH style="width:50px;padding-top:6px" nowrap>
    <%if(BEANS.haveNextPage()){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/record-next.png"  onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:<%=(pageCurrent+1)%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/record-next-g.png">
    <%}%>
    <%if(pageCurrent<pagesCount){%>
        <img style="cursor: pointer" src="<%=contextPath%>/images/record-last.png" onclick="<%=METHOD%>('<%=FORM%>'<%=POSITION%>,'<%=PARAMS%>pageIndex:<%=(pagesCount)%>')">
    <%}else{%>
        <img src="<%=contextPath%>/images/record-last-g.png">
    <%}%>
</TH></TR>
</TBODY></TABLE>
</logic:greaterThan>
</logic:present>
</logic:present>
