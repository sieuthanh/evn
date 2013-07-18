<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutPopup.jsp" flush="true">
      <tiles:put name="content" value="/admin/users/popup.jsp" />
  </tiles:insert>