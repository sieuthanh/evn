<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BApp">
    <bean:define name="BApp" property="link" id="link" type="java.lang.String" />
    <jsp:include page="<%=link%>" />
</logic:present>
<logic:notPresent name="BApp">
    <jsp:forward page="/" />
</logic:notPresent>