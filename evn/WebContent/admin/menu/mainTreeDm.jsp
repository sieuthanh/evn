<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
          dLeft = new dTree('dLeft');
          dLeft.add(0,-1,'<span style="font-weight:bold;font-family:Tahoma,Arial;font-size=11px"><bean:message key="app.titledanhmuc" bundle="<%=interfaces%>"/></span>');
          dLeft.add(1,0,'<bean:message key="menu.top.doc.caption" bundle="<%=interfaces%>"/>',null);
          dLeft.add(2,1,'<bean:message key="category.tree.secure" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY')");
          dLeft.add(3,1,'<bean:message key="category.tree.express" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY_EXPRESS')");
          dLeft.add(4,1,'<bean:message key="category.tree.via" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY_VIA')");
          dLeft.add(5,1,'<bean:message key="category.tree.doctype" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY_DOC_TYPE')");
          dLeft.add(6,1,'<bean:message key="category.tree.form" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY_FORM')");
          dLeft.add(7,1,'<bean:message key="category.tree.typeState" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY_STATUS')");
          dLeft.add(8,1,'<bean:message key="category.tree.transfer" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY_TRANSFER')");
          dLeft.add(9,1,'<bean:message key="category.tree.thePen" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY_THEPEN')");
          dLeft.add(10,1,'<bean:message key="category.tree.branch" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY_BRANCH')");
          dLeft.add(11,1,'<bean:message key="category.tree.classify" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY_CLASSIFY')");
          dLeft.add(12,0,'<bean:message key="require.manager.caption" bundle="<%=interfaces%>"/>',null);
          dLeft.add(13,12,'<bean:message key="rm.category.form.caption" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_SHOW_CAT_REQUIRE')");
          dLeft.add(14,12,'<bean:message key="rm.status.form.caption" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_SHOW_RM_STATUS')");
          dLeft.add(15,0,'<bean:message key="category.tree.typeReport" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY_REPORT_TYPE')");
          dLeft.add(16,0,'<bean:message key="category.tree.typeTemplate" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_CATEGORY_TEMPLATE_TYPE')");         
          dLeft.add(17,0,'<bean:message key="category.tree.mailAccount" bundle="<%=interfaces%>"/>',"javascript:post(\'function\',anchor + \':_MAIL_ACCOUNT')");
          
         
          document.write(dLeft);
</script>
