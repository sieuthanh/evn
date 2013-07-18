<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<HTML style="background-color:#F6F6F6;">
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=<%=encoding%>">
<style type="text/css">
@import url(<%=contextPath%>/styles/style.css);
@import url(<%=contextPath%>/styles/calendar.css);
@import url(<%=contextPath%>/styles/tab.css);
@import url(<%=contextPath%>/styles/addthis_widget.css);
@import url(<%=contextPath%>/styles/common.css);

</style>

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
<script language="javascript">var anchor='<%=anchor%>';</script>

<script language="javascript">  
  function messageDelete(){      
      return confirm(unescape('<bean:message key="alert.delete" bundle="<%=interfaces%>"/>'));
  } 
 </script> 

</HEAD>
<body style="background:#efefef;">
    <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><html:errors property="alert"  bundle="<%=interfaces%>" /></td>
            </tr>                                    
    </table>
    <div >
    <div  id="mainPopup">
       <tiles:insert attribute="content" ignore="true"/>
     </div>
</div>
</body>
</HTML>