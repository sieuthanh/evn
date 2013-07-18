<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript"> 
function postTab(obj,params){
if(obj.className==''){
        for(i=0;i<obj.parentNode.parentNode.childNodes.length;i++){
                if(obj.parentNode.parentNode.childNodes[i].className=='ui-tabs-selected') obj.parentNode.parentNode.childNodes[i].className='';
        }
        obj.parentNode.className='ui-tabs-selected';
        post('change',anchor + ':' + params);
    }
}
</script>
<%int optionTab=0;%>

<%
if (request.getParameter("optionmenu")!=null && !request.getParameter("optionmenu").toString().equals("")){
  optionTab = Integer.parseInt(request.getParameter("optionmenu").toString());
}else {
    optionTab = -2;
}
%>


<ul id="ui-tabs-nav">
   <% if (request.getSession().getAttribute("01.01")!=null){  %>
    <li class="<%=(optionTab==0)?"ui-tabs-selected":""%>"><a href="#" onclick="javascrip:postTab(this,'_DOCS_RECV_LIST:statusId:-4:views:0');"><bean:message key="doc.recv.caption" bundle="<%=interfaces%>"/></a></li>
   <%}%>
    <% if (request.getSession().getAttribute("01.02")!=null){  %>
    <li class="<%=(optionTab==1)?"ui-tabs-selected":""%>"><a href="#" onclick="javascrip:postTab(this,'_DOCS_SEND_LIST:statusId:-4:views:0');"><bean:message key="doc.send.caption" bundle="<%=interfaces%>"/></a></li>
   <%}%>
    <% if (request.getSession().getAttribute("01.04")!=null){  %>
    <li class="<%=(optionTab==2)?"ui-tabs-selected":""%>"><a href="#" onclick="javascrip:postTab(this,'_DOCS_REPORTS');"><bean:message key="menu.top.report.caption" bundle="<%=interfaces%>"/></a></li>
    <%}%>
    <% if (request.getSession().getAttribute("01.05")!=null){  %>
    <li class="<%=(optionTab==3)?"ui-tabs-selected":""%>"><a href="#" onclick="javascrip:postTab(this,'_DOSSIERS_LIST');"><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/></a></li>
    <%}%>
    <% if (request.getSession().getAttribute("01.06")!=null){  %>
    <li class="<%=(optionTab==4)?"ui-tabs-selected":""%>"><a href="#" onclick="javascrip:postTab(this,'_UNITS_LIST');"><bean:message key="form.docs.fromId2" bundle="<%=interfaces%>"/></a></li>
    <%}%>
    <% if (request.getSession().getAttribute("01.07")!=null){  %>
    <li class="<%=(optionTab==5)?"ui-tabs-selected":""%>"><a href="#" onclick="javascrip:postTab(this,'_FORYOU_EDIT');"><bean:message key="form.docs.forYouId" bundle="<%=interfaces%>"/></a></li>
    <%}%>
    <% if (request.getSession().getAttribute("01.08")!=null){  %>
    <li class="<%=(optionTab==6)?"ui-tabs-selected":""%>"><a href="#" onclick="javascrip:postTab(this,'_FORYOU_LIST');"><bean:message key="forYou.header" bundle="<%=interfaces%>"/></a></li>
    <%}%>
</ul>