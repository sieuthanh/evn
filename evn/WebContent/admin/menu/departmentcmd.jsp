<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<logic:present name="BDepartment">
<BR>
<TABLE id=toolbar cellSpacing=0 cellPadding=0 border=0>
        <TBODY>
        <TR vAlign=center align=middle>
            <TD><A class=toolbar href="javascript:post('departments',anchor + ':_CREATE');"><IMG 
                title=Apply alt=Apply src="<%=contextPath%>/images/admin/apply_f2.png" align=middle 
                border=0 name=apply><BR><bean:message key="action.insert" bundle="<%=interfaces%>"/></A> </TD>
          <logic:greaterThan name="BDepartment" property="id" value="0">
              <TD>&nbsp;</TD>
              <TD><A class=toolbar href="javascript:post('departments',anchor + ':_EDIT');"><IMG 
                title=Save alt=Save src="<%=contextPath%>/images/admin/save_f2.png" align=middle 
                border=0 name=save><BR><bean:message key="action.update" bundle="<%=interfaces%>"/></A> </TD>
              <TD>&nbsp;</TD>
              <TD><A class=toolbar href="javascript:post('departments',anchor + ':_DELETE');"><IMG 
                title=Close alt=Close src="<%=contextPath%>/images/admin/cancel_f2.png" 
                align=middle border=0 name=cancel><BR><bean:message key="action.delete" bundle="<%=interfaces%>"/></A> </TD>
          </logic:greaterThan>
</TR></TBODY></TABLE>          
</logic:present>
