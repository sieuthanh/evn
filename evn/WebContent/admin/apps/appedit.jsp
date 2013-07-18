<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
    function swap(inc){
        var obj = getObj('id');
        var index = obj.selectedIndex + inc;
        if(inc<0 && index<1){
            alert(<bean:message key="errors.apps.up" bundle="<%=interfaces%>"/>);
        }else if(inc>0 && (index==1 || index>=obj.length)){
            alert(<bean:message key="errors.apps.down" bundle="<%=interfaces%>"/>);
        }else{
            postAjax('apps','tdMainBody',anchor + ':_ACTIVE:idSwap:' + obj.options[index].value)
        }
    }
</script>
<html:form action="apps" method="post">
<TABLE class=adminheading>
  <TBODY>
  <TR>
    <TH class=app><bean:message key="apps.action.view" bundle="<%=interfaces%>"/></TH></TR></TBODY></TABLE>
<TABLE align="left">
  <TBODY>
  <TR>
    <TD vAlign=top>
    <logic:present name="BApp">
    
      <TABLE class=adminform>
        <TBODY>
        <TR><TH colSpan=3><bean:message key="apps.edit.caption" bundle="<%=interfaces%>"/></TH></TR>
        <tr>
            <td  rowspan="2"><bean:message key="apps.edit.apps" bundle="<%=interfaces%>"/></td>
            <td  rowspan="2"><html:select  styleClass="inputbox" name="BApp" property="id" onchange="javascript:postAjax('apps','tdMainBody',anchor + ':_VIEW');" >
                <html:option value="0"><bean:message key="apps.edit.unselect" bundle="<%=interfaces%>"/></html:option>
                <html:options collection="BApps" property="id" labelProperty="name"/>
              </html:select>   </td>
            <td>
                <img src="<%=contextPath%>/images/uparrow.png" align="right" title="<bean:message key="apps.up.caption" bundle="<%=interfaces%>"/>" onclick="javascript:swap(-1);">
            </td>
          </tr>
          <tr>
            <td>
                <img src="<%=contextPath%>/images/downarrow.png"  align="right" title="<bean:message key="apps.down.caption" bundle="<%=interfaces%>"/>" onclick="javascript:swap(1);" >
            </td>
        </tr>
        
          <TD><bean:message key="apps.edit.name" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2"><html:text  name="BApp" property="name" styleClass="inputbox" size="40" /> </TD></TR>
        <TR>
          <TD><bean:message key="apps.edit.code" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2">
          <html:text  name="BApp" property="code" styleClass="inputbox" size="40" /> 
          </TD></TR>
        <TR>
          <TD><bean:message key="apps.edit.link" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2">
          <html:text  name="BApp" property="link" styleClass="inputbox" size="40" /> 
          </TD></TR>
        <TR>
          <TD><bean:message key="apps.edit.class" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2">
          <html:text  name="BApp" property="className" styleClass="inputbox" size="40" /> 
          </TD></TR>
        <TR>
          <TD><bean:message key="apps.edit.block" bundle="<%=interfaces%>"/>
          <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
          <TD colspan="2">
          <html:checkbox name="BApp" property="block" value="0"/>
          </TD></TR>
<TR>
<TD colSpan=2>&nbsp; </TD></TR></TBODY></TABLE>
    </logic:present>
    </TD></TR>
<TR>
    <TD align=right>
        <logic:present name="BApp">
          <div>
              <jsp:include page="/admin/menu/appcmd.jsp" />
          </div>
        </logic:present>
          <jsp:include page="/admin/alert.jsp" />
    </TD></TR>
          </TBODY></TABLE>
          
</html:form>          