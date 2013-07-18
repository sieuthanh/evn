<%
String encoding= com.lib.AppConfigs.APP_ENCODING;
String anchor=com.lib.AppConfigs.APP_ANCHOR;
String contextPath=request.getContextPath();
com.users.OnlineUsers Users = new com.users.OnlineUsers();
com.users.OnlineUser  me  = Users.getUser(request);
String interfaces = me.getLanguage() + "_" + me.getLocation();
String extention = ".html";

if(com.lib.AppConfigs.SESSION_DENY_GUEST && me.isRole(com.inf.IRoles.NONE)){
    String deny = (String)request.getAttribute("SESSION.DENY.GUEST");
    if(deny==null){
        response.sendRedirect("/" + contextPath);
    }
}
%>
