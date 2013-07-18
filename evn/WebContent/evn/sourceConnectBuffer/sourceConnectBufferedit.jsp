<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<TABLE class="popupWin" width="410px" cellpadding="0" cellspacing="0">
  <TBODY>
    <TR>
        <TD vAlign=top>
        <logic:present name="BSourceConnectBuffer">
    
            <TABLE>
                <TBODY>
                    <TR><TH colspan="2">SOURCECONNECTBUFFER</TH></TR>

                    <TR>
                        <TD nowrap>SRC_CODE</TD>
                        <TD> <html:text  name="BSourceConnectBuffer" property="src_Code" styleClass="inputbox" size="20" /><bean:message key="alert.type.information" bundle="<%=interfaces%>"/> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>FULLNAME</TD>
                        <TD> <html:text  name="BSourceConnectBuffer" property="fullName" styleClass="inputbox" size="20" /> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>SHORTNAME</TD>
                        <TD> <html:text  name="BSourceConnectBuffer" property="shortName" styleClass="inputbox" size="20" /> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                        </TD>
                    </TR>

                    <TR>
                        <TD nowrap>TYPE</TD>
                        
                        <TD> 
                         <html:select  styleClass="inputbox" name="BSourceConnectBuffer" property="type"  >
                            <html:option value="D">Database</html:option>
                            <html:option value="W">Web</html:option>
                        </html:select>
                        
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>DESCRIPTION</TD>
                        <TD> <html:textarea  name="BSourceConnectBuffer" property="description" styleClass="inputbox"/> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>URL</TD>
                        <TD> <html:textarea  name="BSourceConnectBuffer" property="url" styleClass="inputbox"/> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>CONNECTTION</TD>
                        <TD> <html:textarea  name="BSourceConnectBuffer" property="connection" styleClass="inputbox"/> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>ACTIVE</TD>
                        <td >
                        <html:select  styleClass="inputbox" name="BSourceConnectBuffer" property="active"  >
                            <html:option value="0"><bean:message key="evn.reportsbuffer.active_0" bundle="<%=interfaces%>"/></html:option>
                            <html:option value="1"><bean:message key="evn.reportsbuffer.active_1" bundle="<%=interfaces%>"/></html:option>
                        </html:select>  
                        </td>
                    </TR>

</TBODY></TABLE>
    </logic:present>
    </TD></TR>
<TR>
    <TD align=right>
        <logic:present name="BSourceConnectBuffer">
          <div align="right">
              <jsp:include page="/evn/menu/cmd.jsp" />
          </div>
        </logic:present>
          <div id="idcmd">
          <jsp:include page="/admin/alert.jsp" />
          </div>
    </TD></TR>
          </TBODY></TABLE>

