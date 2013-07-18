<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<TABLE class="popupWin" width="410px" cellpadding="0" cellspacing="0">
  <TBODY>
    <TR>
        <TD vAlign=top>
        <logic:present name="BReportsBuffer">
    
            <TABLE>
                <TBODY>
                    <TR><TH colspan="2">REPORTSBUFFER</TH></TR>

                    <TR>
                        <TD nowrap>REPORT_CODE</TD>
                        <TD> <html:text  name="BReportsBuffer" property="report_Code" styleClass="inputbox" size="20" /><bean:message key="alert.type.information" bundle="<%=interfaces%>"/> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>FULL_NAME</TD>
                        <TD> <html:text  name="BReportsBuffer" property="full_Name" styleClass="inputbox" size="20" /> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>short_Name</TD>
                        <TD> <html:text  name="BReportsBuffer" property="short_Name" styleClass="inputbox" size="20" /> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>TYPE</TD>
                        <td>
                        <html:select  styleClass="inputbox" name="BReportsBuffer" property="type"  >
                            <html:option value="H"><bean:message key="evn.reportsbuffer.type_h" bundle="<%=interfaces%>"/></html:option>
                            <html:option value="V"><bean:message key="evn.reportsbuffer.type_v" bundle="<%=interfaces%>"/></html:option>
                        </html:select>  
                        </td>
                    </TR>
                    <TR>
                        <TD nowrap>VERSION</TD>
                        <TD> <html:text  name="BReportsBuffer" property="version" styleClass="inputbox" size="20" />
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>DESCRIPTION</TD>
                        <TD> <html:textarea  name="BReportsBuffer" property="description" styleClass="inputbox"/> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>ACTIVE</TD>
                        <td >
                        <html:select  styleClass="inputbox" name="BReportsBuffer" property="active"  >
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
        <logic:present name="BReportsBuffer">
          <div align="right">
              <jsp:include page="/evn/menu/cmd.jsp" />
          </div>
        </logic:present>
          <jsp:include page="/evn/alert.jsp" />
    </TD></TR>
          </TBODY></TABLE>

