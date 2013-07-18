<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/admin/layout/layoutTreeReport.jsp" flush="true">
      <tiles:put name="tree" value="/admin/reportSystem/sum/left.jsp" />
      <tiles:put name="content" value="/admin/reportSystem/sum/form.jsp" />
      <tiles:put name="footer" value="/commons/footer.jsp" />
  </tiles:insert>
