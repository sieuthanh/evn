<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/evn/layout/layoutTree.jsp" flush="true">
      <tiles:put name="tree" value="/evn/ticket/left.jsp" />
      <tiles:put name="content" value="/evn/ticket/content.jsp" />
      <tiles:put name="footer" value="/commons/footer.jsp" />
  </tiles:insert>
