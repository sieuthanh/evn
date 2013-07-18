<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<TABLE class="popupWin" width="410px" cellpadding="0" cellspacing="0">
	<TBODY>
		<TR>
			<TD vAlign=top><logic:present name="BTicket">

					<TABLE>
						<TBODY>
							<TR>
								<TH colspan="2">TICKET</TH>
							</TR>
							<logic:greaterThan name="BTicket" property="ticket_id" value="0">
								<TR>
									<TD nowrap>TICKET_ID</TD>
									<TD><html:text name="BTicket" property="ticket_id"
											styleClass="inputbox" size="20" /></TD>
								</TR>
							</logic:greaterThan>
							<TR>
								<TD nowrap>TICKET_ERR</TD>
								<TD><html:text name="BTicket" property="ticket_id_err"
										styleClass="inputbox" size="20" /></TD>
							</TR>
							<TR>
								<TD nowrap>SRC_CONNECT_ID</TD>
								<td><html:select styleClass="inputbox" name="BTicket"
										property="src_connect_id">
										<logic:present name="BSourceConnectBuffers">
											<html:options collection="BSourceConnectBuffers"
												property="src_Connect_Id" labelProperty="fullName" />
										</logic:present>
									</html:select></td>
							</TR>
							<TR>
								<TD nowrap>STSTUS</TD>
								<td><html:select styleClass="inputbox" name="BTicket"
										property="status">
										<html:option value="O">OPEN</html:option>
										<html:option value="C">CLOSE</html:option>
										<html:option value="P">PROCESS</html:option>
										<html:option value="F">FINISH</html:option>
										<html:option value="R">REVISE</html:option>
										<html:option value="D">DELETE</html:option>
										<html:option value="E1">ERROR</html:option>

									</html:select></td>
							</TR>
							<logic:greaterThan name="BTicket" property="ticket_id" value="0">
								<TR>
									<TD nowrap>ACTIVE</TD>
									<td><html:select styleClass="inputbox" name="BTicket"
											property="active">
											<html:option value="-1">Ch&#432;a x&#7917; l&#253;</html:option>
											<html:option value="0">&#272;ang x&#7917; l&#253;</html:option>
											<html:option value="1">X&#7917; l&#253; xong</html:option>
										</html:select></td>
								</TR>
							</logic:greaterThan>
							<TR>
								<TD nowrap>EVN</TD>
								<td><html:select styleClass="inputbox" name="BTicket"
										property="evn_id">
										<logic:present name="BSourceAccountBuffer">
											<html:options collection="BSourceAccountBuffer"
												property="account_Id" labelProperty="shortName" />
										</logic:present>
									</html:select></td>
							</TR>
							<logic:greaterThan name="BTicket" property="ticket_id" value="0">
								<TR>
									<TD nowrap>EVN_TIME</TD>
									<TD><html:text name="BTicket" property="evn_time"
											styleClass="inputbox" size="20" />(yyyymmddHH24mi) <bean:message
											key="alert.type.information" bundle="<%=interfaces%>" /></TD>
								</TR>
								<TR>
									<TD nowrap>BUFFER_ID</TD>
									<TD><html:text name="BTicket" property="buffer_id"
											styleClass="inputbox" size="20" /></TD>
								</TR>
								<TR>
									<TD nowrap>BUFFER_TIME</TD>
									<TD><html:text name="BTicket" property="buffer_time"
											styleClass="inputbox" size="20" />(yyyymmddHH24mi) <bean:message
											key="alert.type.information" bundle="<%=interfaces%>" /></TD>
								</TR>
							</logic:greaterThan>
							<TR>
								<TD nowrap>START_TIME</TD>
								<TD><html:text name="BTicket" property="start_time"
										styleClass="inputbox" size="20" />(yyyymmddHH24mi) <bean:message
										key="alert.type.information" bundle="<%=interfaces%>" /></TD>
							</TR>
							<TR>
								<TD nowrap>END_TIME</TD>
								<TD><html:text name="BTicket" property="end_time"
										styleClass="inputbox" size="20" />(yyyymmddHH24mi)<bean:message
										key="alert.type.information" bundle="<%=interfaces%>" /></TD>
							</TR>
							<TR>
								<TD nowrap>TOTAL_RECORDS</TD>
								<TD><html:text name="BTicket" property="total_records"
										styleClass="inputbox" size="20" /></TD>
							</TR>
							<TR>
								<TD nowrap>CODE</TD>
								<TD><html:text name="BTicket" property="code"
										styleClass="inputbox" size="20" />
									<bean:message key="alert.type.information"
										bundle="<%=interfaces%>" /></TD>
							</TR>
							<TR>
								<TD nowrap>TABLE_NAME</TD>
								<TD><html:text name="BTicket" property="table_name"
										styleClass="inputbox" size="20" /> <bean:message
										key="alert.type.information" bundle="<%=interfaces%>" /></TD>
							</TR>
							<logic:greaterThan name="BTicket" property="ticket_id" value="0">
								<TR>
									<TD nowrap>WHERE</TD>
									<td><html:select styleClass="inputbox" name="BTicket"
											property="wheres">
											<html:option value="0">T&#7841;i EVN Buffer</html:option>
											<html:option value="1">T&#7841;i EBS Buffer</html:option>
											<html:option value="-1">L&#7895;i khi chuy&#7875;n l&#234;n Buffer</html:option>
											<html:option value="2">T&#7841;i EBS</html:option>
											<html:option value="-2">L&#7895;i khi chuy&#7875;n l&#234;n EBS</html:option>
										</html:select></td>
								</TR>
								<TR>
									<TD nowrap>DATA_FILE</TD>
									<TD><html:text name="BTicket" property="dataFile"
											styleClass="inputbox" size="40" /></TD>
								</TR>
							</logic:greaterThan>
							<TR>
								<TD nowrap>ERROR_CODE</TD>
								<TD><html:textarea name="BTicket" property="error_code"
										styleClass="inputbox" /></TD>
							</TR>

							<TR>
								<TD nowrap>DESCRIPTION</TD>
								<TD><html:textarea name="BTicket" property="description"
										styleClass="inputbox" /></TD>
							</TR>

						</TBODY>
					</TABLE>
				</logic:present></TD>
		</TR>
		<TR>
			<TD align=right><logic:present name="BTicket">
					<div align="right">
						<jsp:include page="/evn/menu/cmd.jsp" />
					</div>
				</logic:present> <jsp:include page="/evn/alert.jsp" /></TD>
		</TR>
	</TBODY>
</TABLE>

