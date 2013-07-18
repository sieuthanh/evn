<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<TABLE class="popupWin" width="410px" cellpadding="0" cellspacing="0">
  <TBODY>
    <TR>
        <TD vAlign=top>
        <logic:present name="BSourceAccountBuffer">
    
            <TABLE>
                <TBODY>
                    <TR><TH colspan="2">SOURCEACCOUNTBUFFER</TH></TR>

                    <TR>
                        <TD nowrap>ACCOUNT_PASS</TD>
                        <TD> <html:text  name="BSourceAccountBuffer" property="account_Pass" styleClass="inputbox" size="20" /><bean:message key="alert.type.information" bundle="<%=interfaces%>"/> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>FULLNAME</TD>
                        <TD> <html:text  name="BSourceAccountBuffer" property="fullName" styleClass="inputbox" size="20" /> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>SHORTNAME</TD>
                        <TD> <html:text  name="BSourceAccountBuffer" property="shortName" styleClass="inputbox" size="20" /> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>SRC_CONNECT_ID</TD>
                        <td>
                        <html:select  styleClass="inputbox" name="BSourceAccountBuffer" property="src_Connect_Id"  >
                           <logic:present name="BSourceConnectBuffers">
                              <html:options collection="BSourceConnectBuffers" property="src_Connect_Id" labelProperty="fullName"/>
                            </logic:present>
                        </html:select>  
                        </td>
                    </TR>
                    <TR>
                        <TD nowrap>QUOTA</TD>
                        <TD> <html:text  name="BSourceAccountBuffer" property="quota" styleClass="inputbox" size="20" /> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>DESCRIPTION</TD>
                        <TD> <html:textarea  name="BSourceAccountBuffer" property="description" styleClass="inputbox"/> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>ACTIVE</TD>
                        <td >
                        <html:select  styleClass="inputbox" name="BSourceAccountBuffer" property="active"  >
                            <html:option value="0"><bean:message key="evn.reportsbuffer.active_0" bundle="<%=interfaces%>"/></html:option>
                            <html:option value="1"><bean:message key="evn.reportsbuffer.active_1" bundle="<%=interfaces%>"/></html:option>
                        </html:select>  
                        </td>
                    </TR>

<TR>
<TD colSpan=2>&nbsp; </TD></TR></TBODY></TABLE>
    </logic:present>
    </TD></TR>
<TR>
    <TD align=right>
        <logic:present name="BSourceAccountBuffer">
          <div align="right">
               <jsp:include page="/evn/menu/cmd.jsp" />
          </div>
        </logic:present>
          <jsp:include page="/admin/alert.jsp" />
    </TD></TR>
          </TBODY></TABLE>

