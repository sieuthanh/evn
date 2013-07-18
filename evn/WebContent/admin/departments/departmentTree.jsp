<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
    function getInfor(id){
      postAjax('function','tdMainBody',anchor + ':_VIEWDEPARTMENTS:id:' + id);
    }
      dLeft = new dTree('dLeft');
      dLeft.add(0,-1,'<font style="font-size:11px;"><bean:message key="app.office" bundle="<%=interfaces%>"/></font>');
<logic:present name="BDepartments">
      <logic:iterate id="bean" name="BDepartments" type="com.form.admin.departments.FDepartment">
          dLeft.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInfor(<%=bean.getId()%>)");
     </logic:iterate>         
</logic:present>
    document.write(dLeft);
</script>
