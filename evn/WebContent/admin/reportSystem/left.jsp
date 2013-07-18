<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">
    function rencodeforsql(obj){
      
    }
</script>
<ol>
<logic:present name="BReportSystem">
<logic:iterate name="BReportSystem" id="bean" type="com.form.admin.reportSystem.FReportSystem">
<%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
        <li>
        <a href="javascript:postAjax('reportSystem','tdMainBody',anchor +':_PREPARED_EDIT:id:<%=bean.getId()%>')"><bean:write name="bean" property="nameOfFileVn" /></a>
        </li>
<%}else{%>
    <logic:equal name="bean" property="checked" value="1">
        <li>
        <a href="javascript:postAjax('reportSystem','tdMainBody',anchor +':_PREPARED_EDIT:id:<%=bean.getId()%>')"><bean:write name="bean" property="nameOfFileVn" /></a>
        </li>
    </logic:equal>
<%}%>

</logic:iterate>
</logic:present>
</ol>
<div><html:errors property="alert" bundle="<%=interfaces%>" /></div>
