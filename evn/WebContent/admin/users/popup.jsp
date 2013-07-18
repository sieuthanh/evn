<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript" src="<%=contextPath%>/js/md5.js"></script>
<script language="javascript">
    function changePassword(){
        if(getObj('md5pw_').value=='' || getObj('md5pw_').value!=getObj('retypepw_').value ){
           alert(<bean:message key="errors.users.edit.password" bundle="<%=interfaces%>"/>);
           return false;
        }else{
           getObj('retypepw_').value='';
           pw2md5(getObj('md5pw_'),getObj('password_'));
           pw2md5(getObj('md5oldpw_'),getObj('oldpassword'));         
           post('login',anchor + ':_EDIT');
        }
    }
</script>

<div class="col1-ctn1 clearfix">
 <table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
            <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_19.gif" width="8" height="43" /></td>
                <td class="sharebackground">
                <img src="<%=contextPath%>/images/security_f2.gif"  height="18px" />
                <Strong>Thay &#273;&#7893;i m&#7853;t kh&#7849;u</strong></td>
                <td width="15" class="sharebackground"></td>
                <td width="10"><img src="<%=contextPath%>/images/newImages/i_20.gif" width="10" height="43" /></td>
            </tr>
</table>
 <div style="clear:both"></div>
<html:form action="/login" method="post" target="_top" onsubmit="javascript:return changePassword();" >
            <table cellpadding="0" cellspacing="0" width="90%" border="0px" align="center" style="padding-top:10px;" >
            <tr>
            <TD nowrap height="22px" width="30%">
            <bean:message key="users.edit.oldpassword" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
            </TD>
            <TD height="22px">
            <INPUT style="width:120px" type=password name="md5oldpw_" id="md5oldpw_" /> <input type="hidden" name="oldpassword" id="oldpassword">
            </TD>
            </TR>
            <TR>
            <TD nowrap height="22px"><bean:message key="users.edit.password" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
            <TD><INPUT  style="width:120px" type=password name="md5pw_" id="md5pw_" /> <input type="hidden" name="password_" id="password_"></TD>
            </TR>
            <TR>
            <TD nowrap height="22px"><bean:message key="users.edit.verifypassword" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
            <TD><INPUT  style="width:120px" type=password name="retypepw_" id="retypepw_" /></TD>
            </TR>
            <TR>
            </tr>
            </table>
<div style="padding-top:10px" align="center">
<input type="submit" value='<bean:message key="action.update" bundle="<%=interfaces%>"/>' class="button">
</div>
           </html:form>
</div>