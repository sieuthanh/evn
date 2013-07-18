<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<TABLE class="popupWin" width="410px" cellpadding="0" cellspacing="0">
  <TBODY>
		<TR>
			<TD vAlign=top><logic:present name="BTables">

					<TABLE>
						<TBODY>
							<TR>
								<TH colspan="2">TABLES</TH>
							</TR>

							<TR>
								<TD nowrap>NAME</TD>
								<TD><html:text name="BTables" property="name"
										styleClass="inputbox" size="20" /> <bean:message
										key="alert.type.information" bundle="<%=interfaces%>" /></TD>
							</TR>
							<TR>
								<TD nowrap>KEY</TD>
								<TD><html:text name="BTables" property="key"
										styleClass="inputbox" size="20" /> <bean:message
										key="alert.type.information" bundle="<%=interfaces%>" /></TD>
							</TR>
							<TR>
								<TD nowrap>SRC_CONNECT_ID</TD>
								<td><html:select styleClass="inputbox" name="BTables"
										property="src_connect_id">
										<logic:present name="BSourceConnectBuffers">
											<html:options collection="BSourceConnectBuffers"
												property="src_Connect_Id" labelProperty="fullName" />
										</logic:present>
									</html:select></td>
							</TR>
							<TR>
								<TD nowrap>DES_CONNECT_ID</TD>
								<td><html:select styleClass="inputbox" name="BTables"
										property="des_connect_id">
										<logic:present name="BSourceConnectBuffers">
											<html:options collection="BSourceConnectBuffers"
												property="src_Connect_Id" labelProperty="fullName" />
										</logic:present>
									</html:select></td>
							</TR>
							<TR>
								<TD nowrap>DATA_FIELDS</TD>
								<TD><html:textarea name="BTables" property="data_fields"
										styleClass="inputbox" disabled="true" /></TD>
							</TR>
							<TR>
								<TD nowrap>DESCRIPTION</TD>
								<TD><html:textarea name="BTables" property="description"
										styleClass="inputbox" /></TD>
							</TR>


							<TR>
								<TD colSpan=2>&nbsp;</TD>
							</TR>
						</TBODY>
					</TABLE>
				</logic:present></TD>
		</TR>
		<TR>
			<TD align=right><logic:present name="BTables">
					<div align="right">
						<jsp:include page="/evn/menu/cmd.jsp" />
					</div>
				</logic:present> <jsp:include page="/evn/alert.jsp" /></TD>
		</TR>
	</TBODY></TABLE>

