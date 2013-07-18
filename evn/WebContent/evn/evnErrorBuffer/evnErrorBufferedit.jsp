<%@page import="com.lib.LibUtil"%>
<%@page import="com.form.evn.evnErrorBuffer.FEvnErrorBuffer"%>
<%@page import="java.util.List"%>
<%@page import="com.form.evn.evnModuleBuffer.FEvnModuleBuffer"%>
<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>

<TABLE class="popupWin" width="410px" cellpadding="0" cellspacing="0">
	<TBODY>
		<TR>
			<TD vAlign=top><logic:present name="BEvnErrorBuffer">

					<TABLE>
						<TBODY>
							<TR>
								<TH colspan="2">EVN_ERROR_BUFFER</TH>
							</TR>

							<TR>
								<TD nowrap>ERROR_CODE</TD>
								<TD><html:text name="BEvnErrorBuffer" property="error_Code"
										styleClass="inputbox" size="20" /> <bean:message
										key="alert.type.information" bundle="<%=interfaces%>" /></TD>
							</TR>
							<TR>
								<TD nowrap>ERROR_DESCRIPTION</TD>
								<TD><html:text name="BEvnErrorBuffer"
										property="error_Description" styleClass="inputbox" size="20" />
									<bean:message key="alert.type.information"
										bundle="<%=interfaces%>" /></TD>
							</TR>

							<logic:present name="BOptionModule">
								<tr>
									<td nowrap>MODULE</td>
									<td><html:select styleClass="inputbox"
											name="BEvnErrorBuffer" property="module_Id">
											<%
												FEvnErrorBuffer bean = (FEvnErrorBuffer) request
																	.getAttribute("BTables");
															List<FEvnModuleBuffer> temp = (List<FEvnModuleBuffer>) request
																	.getAttribute("BOptionModule");
															for (int i = 0; i < temp.size(); i++) {
											%>
											<option value="<%=temp.get(i).getModuleId()%>"
												<%=LibUtil.getSelected(bean.getModule_Id(),
									temp.get(i).getModuleId())%>><%=temp.get(i).getModuleDescription()%></option>
											<%
												}
											%>
										</html:select></td>
								</tr>
							</logic:present>



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

