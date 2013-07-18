<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<TABLE class="popupWin" width="410px" cellpadding="0" cellspacing="0">
	<TBODY>
		<TR>
			<TD vAlign=top><logic:present name="BEvnModuleBuffer">

					<TABLE>
						<TBODY>
							<TR>
								<TH colspan="2">EVN_MODULE_BUFFER</TH>
							</TR>

							<TR>
								<TD nowrap>MODULE_CODE</TD>
								<TD><html:text name="BEvnModuleBuffer" property="moduleCode"
										styleClass="inputbox" size="20" />
									<bean:message key="alert.type.information"
										bundle="<%=interfaces%>" /></TD>
							</TR>
							<TR>
								<TD nowrap>MODULE_DESCRIPTION</TD>
								<TD><html:text name="BEvnModuleBuffer"
										property="moduleDescription" styleClass="inputbox" size="20" />
									<bean:message key="alert.type.information"
										bundle="<%=interfaces%>" /></TD>
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
	</TBODY>
</TABLE>

