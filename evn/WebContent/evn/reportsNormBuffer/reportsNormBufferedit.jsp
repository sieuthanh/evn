<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<TABLE class="popupWin" width="410px" cellpadding="0" cellspacing="0">
  <TBODY>
    <TR>
        <TD vAlign=top>
        <logic:present name="BReportsNormBuffer">
    
            <TABLE>
                <TBODY>
                    <TR><TH colspan="2">REPORTSNORMBUFFER</TH></TR>

                    <TR>
                        <TD nowrap>NORM_CODE</TD>
                        <TD> <html:text  name="BReportsNormBuffer" property="norm_Code" styleClass="inputbox" size="20" /><bean:message key="alert.type.information" bundle="<%=interfaces%>"/> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>NORM_NAME</TD>
                        <TD> <html:text  name="BReportsNormBuffer" property="norm_Name" styleClass="inputbox" size="20" /> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/>
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>TYPE</TD>
                        <td>
                        <html:select  styleClass="inputbox" name="BReportsNormBuffer" property="type"  >
                            <html:option value="R">H&#224;ng</html:option>
                            <html:option value="C">C&#7897;t</html:option>
                            <html:option value="N">Kh&#244;ng ki&#7875;u</html:option>
                        </html:select>  
                        </td>
                    </TR>
                    <TR>
                        <TD nowrap>NORM_TOTAL</TD>
                        <TD> <html:text  name="BReportsNormBuffer" property="norm_Total" styleClass="inputbox" size="20" /> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>REPORT_ID</TD>
                        <td>
                        <html:select  styleClass="inputbox" name="BReportsNormBuffer" property="report_Id"  >
                           <logic:present name="BReportsBuffers">
                              <html:options collection="BReportsBuffers" property="report_Id" labelProperty="full_Name"/>
                            </logic:present>
                        </html:select>  
                        </td>
                    </TR>
                    <TR>
                        <TD nowrap>VERSION</TD>
                        <TD> <html:text  name="BReportsNormBuffer" property="version" styleClass="inputbox" size="20" /> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>DESCRIPTION</TD>
                        <TD> <html:textarea  name="BReportsNormBuffer" property="description" styleClass="inputbox"/> 
                        </TD>
                    </TR>
                    <TR>
                        <TD nowrap>ACTIVE</TD>
                        <td >
                        <html:select  styleClass="inputbox" name="BReportsNormBuffer" property="active"  >
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
        <logic:present name="BReportsNormBuffer">
          <div align="right">
              <jsp:include page="/evn/menu/cmd.jsp" />
          </div>
        </logic:present>
          <jsp:include page="/admin/alert.jsp" />
    </TD></TR>
          </TBODY></TABLE>

