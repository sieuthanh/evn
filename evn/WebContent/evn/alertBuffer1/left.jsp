<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

                <html:form action="alertAction" method="post">
                <table class="list-voffice" width="100%" cellpadding="0" cellspacing="0">
       <TR><TH>R&#224; so&#225;t alert</TH></TR>
                    <TR><td>
                    <logic:equal name="BAlertBuffer" property="thread3" value="0">
                        <img style="border:0px" src="<%=contextPath%>/images/Stop.png" onclick="javascript:post('alertAction',anchor +':_VIEW:thread3:1')" title="Stop"/> 
                    </logic:equal>
                    <logic:equal name="BAlertBuffer" property="thread3" value="1">
                        <img style="border:0px" src="<%=contextPath%>/images/Start.gif" onclick="javascript:post('alertAction',anchor +':_VIEW:thread3:0')" title="Start"/> 
                    </logic:equal>
                    </td></TR>
                    <TR><td>
                         &#272;&#7897; tr&#7877;:
                         <html:select style="width:50px" name="BAlertBuffer" property="delay" onchange="javascript:post('alertAction',anchor +':_VIEW')" >
                              <html:option value="5">5P</html:option>
                              <html:option value="10">10P</html:option>
                              <html:option value="60">60P</html:option>
                              <html:option value="120">120P</html:option>
                              <html:option value="180">180P</html:option>
                        </html:select>  
                    </td></TR>
                    <tr>
                        <th>Th&#244;ng b&#225;o g&#7847;n &#273;&#226;y
                        </th>
                    </tr>
                    <tr>
                        <td>SRC:<bean:write name="BAlertBuffer" property="src_name" />
                        </td>
                    </tr>
                    <tr>
                        <td>TABLE_NAME:<bean:write name="BAlertBuffer" property="table_Name" />
                        </td>
                    </tr>
                    <tr>
                        <td>DETECT_TIME:<bean:write name="BAlertBuffer" property="detect_Time" />
                        </td>
                    </tr>
                    <tr>
                        <td>STATUS:<bean:write name="BAlertBuffer" property="status" />
                        </td>
                    </tr>

                </TABLE>
                </html:form> 


    
