<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/evn/layout/layoutTree.jsp" flush="true">
      <tiles:put name="tree" value="/evn/left.jsp" />
      <tiles:put name="content" value="/evn/content.jsp" />
      <tiles:put name="footer" value="/commons/footer.jsp" />
  </tiles:insert>
