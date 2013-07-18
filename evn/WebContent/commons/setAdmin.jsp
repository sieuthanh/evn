<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=<%=encoding%>">
<style type="text/css">
@import url(<%=contextPath%>/styles/style.css);
@import url(<%=contextPath%>/styles/theme.css);
@import url(<%=contextPath%>/styles/dtree.css);
@import url(<%=contextPath%>/styles/template.css);
@import url(<%=contextPath%>/styles/tag.css);
@import url(<%=contextPath%>/styles/calendar.css);
@import url(<%=contextPath%>/styles/SqueezeBox.css);
</style> 

<script language="javascript" src="<%=contextPath%>/js/mootools-trunk.js"></script>
<script language="javascript" src="<%=contextPath%>/js/SqueezeBox.js"></script>
<script language="javascript" src="<%=contextPath%>/js/action.js"></script>
<script language="javascript" src="<%=contextPath%>/js/ajaxlib.js"></script>
<script language="javascript" src="<%=contextPath%>/js/cookmenu.js"></script>
<script language="javascript" src="<%=contextPath%>/js/option.js"></script>
<script language="javascript" src="<%=contextPath%>/js/dtree.js"></script>
<script language="javascript" src="<%=contextPath%>/js/md5.js"></script>
<script language="javascript" src="<%=contextPath%>/js/popcalendar.js"></script>
<script language="javascript">var anchor='<%=anchor%>';</script>
<title><bean:message key="app.title" bundle="<%=interfaces%>"/></title>





<script language="javascript">
  self.moveTo(0,0);
  self.resizeTo(screen.width,screen.height); 
  
   function messageDelete(){      
      return confirm(unescape('<bean:message key="alert.delete" bundle="<%=interfaces%>"/>'));
  } 
  function restore(){      
      return confirm(unescape('<bean:message key="alert.restore" bundle="<%=interfaces%>"/>'));
  } 
  function doOnLoad(){
  try{
    getObj('tdFrameTree').height=document.body.clientHeight-150;
  }catch(ex){
  }
  }	
  
function doUnload()
 {
    postAjax('change','pnone',anchor + ':_REMOVE_SESSION');
 }
</script>

