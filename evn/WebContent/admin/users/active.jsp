<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<bean:define name="beanusers" property="id" id="userid" />
<bean:define name="beanusers" property="active" id="active" />
<logic:notEqual name="beanusers" property="username" value="<%=com.inf.IKey.ADMINISTRATOR%>">
                    <A style="none" href="javascript:postAjax('users',<%=userid%>,anchor + ':_ACTIVE:id:<%=userid%>:active:<%=active%>');">
                    <logic:equal name="beanusers" property="active" value="1">
                    <IMG height=12 src="<%=contextPath%>/images/tick.png" width=12 border=0 title='<beanusers:message key="users.edit.active.enable" bundle="<%=interfaces%>"/>'>
                    </logic:equal>
                    <logic:equal name="beanusers" property="active" value="0">
                    <IMG height=12 src="<%=contextPath%>/images/disable.png" width=12 border=0 title='<beanusers:message key="users.edit.active.disable" bundle="<%=interfaces%>"/>'>
                    </logic:equal>
                    </A>
            </logic:notEqual>