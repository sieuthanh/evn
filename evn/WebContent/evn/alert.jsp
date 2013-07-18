<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<span id="divAlert">
<html:errors property="alert" bundle="<%=interfaces%>" />
</span>
<div style="color:red;font-size: 11px;font-weight:bold;">
<logic:notEmpty name="errorValue"><bean:write name="errorValue" /></logic:notEmpty>
</div>
<script language="javascript">
setTimeout("clear()",4500);
</script>