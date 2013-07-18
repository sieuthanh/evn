<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=<%=encoding%>">
<link rel="Shortcut Icon" href="images/title_ico_voffice.gif" >
<style type="text/css">
/*css for voffice*/
@import url(<%=contextPath%>/styles/common.css);
@import url(<%=contextPath%>/styles/tab.css);
@import url(<%=contextPath%>/styles/style.css);
@import url(<%=contextPath%>/styles/SqueezeBox.css);
@import url(<%=contextPath%>/styles/calendar.css);
@import url(<%=contextPath%>/styles/addthis_widget.css);
@import url(<%=contextPath%>/styles/dtree.css);
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
<script language="javascript" src="<%=contextPath%>/js/tab.js"></script>
<script language="javascript" src="<%=contextPath%>/js/ajax-dynamic-list.js"></script>
<script language="javascript" src="<%=contextPath%>/js/ajax.js"></script>
<script language="javascript" src="<%=contextPath%>/js/addthis_widget.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/jquery.min.js"></script>
<script language="javascript" src="<%=contextPath%>/js/highcharts.js"></script>
<script language="javascript">var anchor='<%=anchor%>';</script>
<title>
<bean:message key="app.title" bundle="<%=interfaces%>"/></title>
 <script language="javascript">  
   function messageSave(){      
      return confirm(unescape('<bean:message key="alert.save" bundle="<%=interfaces%>"/>'));
  } 
  
  function messageDelete(){      
      return confirm(unescape('<bean:message key="alert.delete" bundle="<%=interfaces%>"/>'));
  } 
  
  function messageEnd(){      
      return confirm(unescape('<bean:message key="alert.end" bundle="<%=interfaces%>"/>'));
  } 
  
  function restore(){      
      return confirm(unescape('<bean:message key="alert.restore" bundle="<%=interfaces%>"/>'));
  }  
</script>



