<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/admin/layout/layoutEdit.jsp" flush="true">
      <tiles:put name="content" value="/admin/log/log.jsp" />
      <tiles:put name="footer" value="/commons/footer.jsp" />
  </tiles:insert>
