<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<%String action = "_VIEWUSERS";%>
<logic:present name="BGroup">
    <%action = "_VIEWGROUPS";%>
</logic:present>
<script language=javascript>
    function getInfor(id,pageIndex){
      message('tdMainBody','<bean:message key="search.loading" bundle="<%=interfaces%>"/>');
      postAjax('function','tdMainBody',anchor + ':<%=action%>:id:' + id + ':pageIndex:' + pageIndex);
    }
      dLeft = new dTree('dLeft');
      dLeft.add(0,-1,'<font style="font-size:11px;"><bean:message key="app.office" bundle="<%=interfaces%>"/></font>');
<logic:present name="BGroups">
      <logic:iterate id="bean" name="BGroups" type="com.form.admin.groups.FGroup">
          dLeft.add(<%=bean.getId()%>,<%=bean.getParentID()%>,'<%=bean.getName()%>',"javascript:getInfor(<%=bean.getId()%>,1)");
     </logic:iterate>         
</logic:present>
    document.write(dLeft);
</script>
