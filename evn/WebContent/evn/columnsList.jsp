 <%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<table align="left" class="list-voffice" cellpadding="0" cellspacing="0">
                                <tr>
                                    <th width="100px"><bean:write  name="BTables" property="nameTable" /></th>
                                </tr>
                                <tr>
                                    <td>
                                <logic:notEmpty name="BColumnsList"> 
                                         <div  style=" DISPLAY: block;  BACKGROUND: White;  OVERFLOW: auto;  WIDTH: 270px;">
                                        
                                            <table class="list-voffice" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <th nowrap>STT</th>
                                                    <th nowrap><bean:message key="title.insertdata.field.clock" bundle="<%=interfaces%>"/></th>
                                                    <th width="100px"><bean:message key="title.insertdata.field.name" bundle="<%=interfaces%>"/></th>
                                                    <th width="100px"><bean:message key="title.insertdata.field.type" bundle="<%=interfaces%>"/></th>
                                                </tr>
                                            <%int i=0;int k=0;%>
                                                 <logic:iterate name="BColumnsList" id="bean" type="com.form.evn.FChangeData"> 
                                                 
                                                         <%int check=0;
                                                         if(bean.getData_Fields()!=null)
                                                            if(bean.getData_Fields().contains(bean.getColumnName()))check=1;
                                                        %>
                                                    <tr>
                                                       <td>
                                                        
                                                        <%=++k%>
                                                        </td>
                                                     <%if(bean.getNotNull()==2){%>

                                                        <td align="center">
                                                            <input type="checkbox" name="checkEmp" <%=(check>0?"checked":"checked")%> value="<%=bean.getColumnName()%>" title="not null"/>
                                                            <img style="border:0px" src="<%=contextPath%>/images/key.png" title="Primary Key"/>
                                                        </td>
                                                       
                                                    <%}else if(bean.getNotNull()==1){%>

                                                            <td width="100px" align="center">
                                                            <input type="checkbox" name="checkEmp" <%=(check>0?"checked":"checked")%> value="<%=bean.getColumnName()%>" title="not null"/>
                                                            </td>

                                                    <%}else{%>
                                                            <td width="100px" align="center">
                                                             <input type="checkbox" name="checkField" <%=(check>0?"checked":"")%> value="<%=bean.getColumnName()%>"/>
                                                            </td>
                                                    <%}%>                                                        
                                                       
                                                        <td width="100px">
                                                        
                                                        <%if(bean.getColumnFK()==10){%>
                                                            <a href="javascript:postAjax('functionEvn','tdMainBody',anchor +':_VIEW_FKEY:nameTable:<%=bean.getTableFK()%>')"><bean:write name="bean" property="columnName" /></a>
                                                        <%}else{%>
                                                            <bean:write name="bean" property="columnName" />
                                                        <%}%>
                                                        </td>
                                                     <%if(bean.getNotNull()==2){%>
                                                        <td width="100px">
                                                            <html:select  style="width:100px" name="bean" property="columnTypeName" disabled="true" >
                                                                    <html:option value="1">NUMBER</html:option>
                                                                    <html:option value="2">VARCHAR2</html:option>
                                                                    <html:option value="3">LONG</html:option>
                                                                    <html:option value="4">DATE</html:option>
                                                              </html:select>
                                                        </td>
                                                       
                                                    <%}else if(bean.getNotNull()==1){%>
                                                        <td width="100px">
                                                            <html:select  style="width:100px" name="bean" property="columnTypeName" disabled="true">
                                                                    <html:option value="1">NUMBER</html:option>
                                                                    <html:option value="2">VARCHAR2</html:option>
                                                                    <html:option value="3">LONG</html:option>
                                                                    <html:option value="4">DATE</html:option>
                                                              </html:select>
                                                        </td>
                                                    <%}else{%>
                                                        <td width="100px">
                                                            <html:select  style="width:100px" name="bean" property="columnTypeName" disabled="true">
                                                                    <html:option value="1">NUMBER</html:option>
                                                                    <html:option value="2">VARCHAR2</html:option>
                                                                    <html:option value="3">LONG</html:option>
                                                                    <html:option value="4">DATE</html:option>
                                                              </html:select>
                                                        </td>
                                                    <%}%>
                
                                                    </tr>
                                                    <%i++;%>
                                                </logic:iterate>
                                            </table>
                                         </div>
                                         </logic:notEmpty>
                                    </td>
                                </tr>

</table>
