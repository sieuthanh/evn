<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/admin/layout/layoutTree.jsp" flush="true">
      <tiles:put name="tree" value="/admin/departments/departmentTree.jsp" />
      <tiles:put name="content" value="/admin/departments/departmentForm.jsp" />
      <tiles:put name="footer" value="/commons/footer.jsp" />
  </tiles:insert>
