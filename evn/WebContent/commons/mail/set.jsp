<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=<%=encoding%>">
<style type="text/css">
@import url(<%=contextPath%>/styles/mail/styles.css);
@import url(<%=contextPath%>/styles/mail/dtree.css);
@import url(<%=contextPath%>/styles/mail/template.css);
@import url(<%=contextPath%>/styles/addthis_widget.css);

</style>
<script language="javascript" src="<%=contextPath%>/js/ajax-dynamic-list.js"></script>
<script language="javascript" src="<%=contextPath%>/js/ajax.js"></script>
<script language="javascript" src="<%=contextPath%>/js/addthis_widget.js"></script>
<script language="javascript" src="<%=contextPath%>/js/action.js"></script>
<script language="javascript" src="<%=contextPath%>/js/ajaxlib.js"></script>
<script language="javascript" src="<%=contextPath%>/js/dtree.js"></script>
<script language="javascript">var anchor='<%=anchor%>';</script>
<title><bean:message key="app.title" bundle="<%=interfaces%>"/></title>

<script language="javascript">
  self.moveTo(0,0);
  self.resizeTo(screen.width,screen.height);
  function Delete(){
    return confirm(unescape("<bean:message key="alert.delete" bundle="<%=interfaces%>"/>"));
  } 
  function doOnLoad(){
  try{
    getObj('tdFrameTree').height=document.body.clientHeight-150;
  }catch(ex){
  }
  }
</script>