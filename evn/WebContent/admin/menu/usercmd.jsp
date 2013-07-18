<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language="javascript">
    function click(action){
   
    if(getObj('md5pw').value==getObj('retypepw').value){     
            if(getObj('md5pw').value !='' || action =='_EDIT'){                    
               getObj('retypepw').value='';                                     
               if(getObj('md5pw').value!=''){                 
                   pw2md5(getObj('md5pw'),getObj('password'));
              }
                 postAjax('users','divAlert',anchor + ':' + action);
            }else{
                alert(<bean:message key="errors.users.edit.password" bundle="<%=interfaces%>"/>);
            }
    }else{
        alert(<bean:message key="errors.users.edit.password" bundle="<%=interfaces%>"/>);
    }
    }
</script>
<logic:present name="BUser">
<TABLE align="right" id=toolbar cellSpacing=0 cellPadding=0 border=0>
        <TBODY>
        <TR vAlign=center align=middle><TD>
          <%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
              <TD><A class=toolbar href="javascript:click('_CREATE');"><IMG 
                title=Apply alt=Apply src="<%=contextPath%>/images/admin/apply_f2.png" align=middle 
                border=0 name=apply ><BR><bean:message key="action.insert" bundle="<%=interfaces%>"/></A> </TD>
          <%}%>
              <logic:greaterThan name="BUser" property="id" value="0">
                  <TD><A class=toolbar href="javascript:click('_EDIT');"><IMG 
                    title=Save alt=Save src="<%=contextPath%>/images/admin/save_f2.png" align=middle 
                    border=0 name=save ><BR><bean:message key="action.update" bundle="<%=interfaces%>"/></A> </TD>
              </logic:greaterThan>
        </TR>
        </TBODY>
</TABLE>          
</logic:present>
