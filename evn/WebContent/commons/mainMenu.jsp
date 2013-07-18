<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="login" method="post"/>
<html:form action="lits" method="post"/>
<html:form action="tasks" method="post"/>
<html:form action="change" method="post"/>
<html:form action="function" method="post" />
<html:form action="main" method="post" />
<html:form action="docssendMain" method="post" />
<html:form action="docsrecvMain" method="post" />
<html:form action="loginEmail" method="post" />
<html:form action="messsagesListMain" method="post" />
<html:form action="problemMain" method="post" />
<html:form action="addPortlet" method="post" />
<html:form action="emailListMain" method="post" />

<script type="text/javascript">buildpopup();</script>

<table cellpadding="0" cellspacing="0" border="0" width="100%" class="tblmenu">
                <tr><td width="2%"><img src="<%=contextPath%>/images/newImages/i_01.gif" /></td>
	                <td><table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <tr><td width="10%"><a href="javascript:post('main',anchor + ':_HOME')"><Strong><bean:message key="menu.top.home" bundle="<%=interfaces%>"/></strong></a></td>
                                <td><table cellpadding="0" cellspacing="0" border="0" class="group5">
                            		    <tr><td width="8px"><img src="<%=contextPath%>/images/newImages/i_04.gif"/></td>
                                                <td>
                                                
                                                <html:select  name="change" property="app" onchange="javascript:eval(this.value)"  styleClass="fieldSelect" style="width:90px;"> 
                                                            <html:option value="0">---<bean:message key="action.insert" bundle="<%=interfaces%>"/>---</html:option>
                                                            <logic:equal name="BCheckRulesCreatorDataRecv" value="1">
                                                            <html:option value="post('docsrecvMain',anchor + ':_PREPARED_CREATE:type:1:app:1')" ><bean:message key="menu.top.doc.recivers.caption" bundle="<%=interfaces%>"/></html:option>
                                                            </logic:equal>
                                                            <logic:equal name="BCheckRulesCreatorDataSend" value="1">
                                                            <html:option value="post('docssendMain',anchor + ':_PREPARED_CREATE:type:1:app:1')"><bean:message key="menu.top.doc.send.caption" bundle="<%=interfaces%>"/></html:option>
                                                            </logic:equal>
                                                            

                                                            
                                                            <%if(request.getSession().getAttribute("03")!=null && request.getSession().getAttribute("03.02")!=null){%>
                                                             <html:option value="post('messsagesListMain',anchor + ':_PREPARED_CREATE:id:0:app:0')"><bean:message key="menu.messages.create" bundle="<%=interfaces%>"/></html:option>
                                                            <%}%>
                                                            
                                                            <logic:present name="Assign">
                                                             <html:option value="post('problemMain',anchor + ':_PREPARED_CREATE:root:0:problemId:0:app:2')"><bean:message key="problem.detail.caption" bundle="<%=interfaces%>"/></html:option>
                                                            </logic:present>   
                                                            
                                                            <html:option value="post('change',anchor + ':_PREPARED_CABIN')"><bean:message key="title.template.label.cabin" bundle="<%=interfaces%>"/></html:option>
                                                            
                                                            <logic:notEmpty name="BRuleTemplateTypes">
                                                            <html:option value="post('change',anchor + ':_PREPARED_TEMPLATE')"><bean:message key="menu.top.template.caption" bundle="<%=interfaces%>"/></html:option>
                                                            </logic:notEmpty>
                                                            
                                                            
                                                            <%if(me.isRole(com.inf.IRoles.rBROADCAST)){%>
                                                                <html:option value="post('change',anchor + ':_PREPARE_BROADCAST');"><bean:message key="broadcast.main.caption" bundle="<%=interfaces%>"/></html:option>
                                                           <%}%> 
                                                           
                                                           <html:option value="post('main',anchor + ':_MANAGER_REQUIRE');"><bean:message key="require.menu.cation.endUser" bundle="<%=interfaces%>"/></html:option>
                                                           <%if(request.getSession().getAttribute("folderStore")!=null){%>
                                                           <html:option value="post('emailListMain',anchor + ':_PREPARE_SEND')"><bean:message key="menu.email.create" bundle="<%=interfaces%>"/></html:option>
                                                           <%}%>
                                                </html:select>
                                                
                                                
                                                </td>
                                                <td>
                                                <span id="openCombo">
                                                <jsp:include page="/commons/application.jsp" />
                                                </span>
                                                </td>
                                                
                                             
                                            <td><img src="<%=contextPath%>/images/newImages/i_06.gif" /></td>
                                         </tr>
                                        </table>
                                    </td>
                                    <td width="35%" align="right"><a href="#"><%=me.getFullName()%></a><span>|</span>
                                    <a class="modal-button" href="login<%=extention%>?<%=anchor%>=_PREPARED_EDIT" rel="{handler: 'iframe', size: {x:250, y: 180}}">
                                    <bean:message key="login.change.password" bundle="<%=interfaces%>"/>
                                    </A>
                                    <span>|</span>
                                        <a href="javascript:post('change',anchor + ':_HELP')"><bean:message key="app.help" bundle="<%=interfaces%>"/></a><span>|</span><a href="javascript:post('login',anchor + ':_LOGOUT');"><bean:message key="logout.caption" bundle="<%=interfaces%>"/></a></td>
                                </tr>
                            </table>
                        </td>
                        <td width="2%" align="right"><img src="<%=contextPath%>/images/newImages/i_03.gif" /></td>
                    </tr>
                </table>

