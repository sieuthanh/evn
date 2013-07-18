<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="reportSystemSum" method="POST" >
<TABLE class="adminlist" >
<%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
<tr>
    <td colspan="4" nowrap>
        File xls: 
        <html:text name="reportSystemSum" property="nameFile" style="width:120px" />
        T&#234;n b&#225;o c&#225;o: 
        <html:text name="reportSystemSum" property="nameOfFileVn" style="width:320px" />
    </td>
</tr>
<%}else{%>
<html:hidden  name="reportSystemSum" property="nameFile" /> 
<html:hidden  name="reportSystemSum" property="nameOfFileVn" /> 
<tr>
    <th colspan="4" nowrap>
        <bean:write name="reportSystemSum" property="nameOfFileVn" />
    </th>
</tr>
<%}%>
<TR>
    <TD nowrap>
     T&#7915; ng&#224;y 
      <input type="text" name="fromDate" id="fromDate"  style="width:120px" value="<bean:write name="reportSystemSum" property="fromDate"/>" /><font color="Red">(yyyyMMdd)</font>
    &#272;&#7871;n ng&#224;y  
      <input type="text" name="toDate" id="toDate"  style="width:120px" value="<bean:write name="reportSystemSum" property="toDate"/>" /><font color="Red">(yyyyMMdd)</font>
    </td>
    <td colspan="3" align="right" nowrap >
    </td>
</TR>
<TR>
    <TD colspan="4" nowrap>
         Phone Number:<input type="text" name="phone_Number" id="phone_Number"  style="width:20px" value="<bean:write name="reportSystemSum" property="phone_Number"/>" /><font color="Red">(84xxxxxxxxx)</font>

           Telco<html:select name="reportSystemSum" property="telco" style="width:70px">
          <html:option value="">---ALL---</html:option>
          <html:option value="GPC">GPC</html:option>
          <html:option value="VMS">VMS</html:option>
          <html:option value="VIETEL">VIETEL</html:option>
          <html:option value="VNM">VNM</html:option>
          <html:option value="BEE">BEE</html:option>
          <html:option value="SFONE">SFONE</html:option>
          </html:select>
          Service Number<html:select name="reportSystemSum" property="service_number" style="width:70px">
          <html:option value="">---8x01---</html:option>
          <html:option value="8001">8001</html:option>
          <html:option value="8101">8101</html:option>
          <html:option value="8201">8201</html:option>
          <html:option value="8301">8301</html:option>
          <html:option value="8401">8401</html:option>
          <html:option value="8501">8501</html:option>
          <html:option value="8601">8601</html:option>
          <html:option value="8701">8701</html:option>
          </html:select>
          Service Code
              <html:select style="width:70px"  name="reportSystemSum" property="service_code" >  
               <html:option value="">---All---</html:option>
                      <html:options collection="BServicesCode" property="service_code" labelProperty="service_code"/>
              </html:select>   


    </td>
</TR>
  <%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
  <TR>
    <TD nowrap colspan="4">
     Point Interval<input type="text" name="pointInterval" id="pointInterval"  style="width:80px" value="<bean:write name="reportSystemSum" property="pointInterval"/>" />
     Max Zoom<input type="text" name="maxZoom" id="maxZoom"  style="width:80px" value="<bean:write name="reportSystemSum" property="maxZoom"/>" />
     Time Refresh<input type="text" name="timeRefresh" id="timeRefresh"  style="width:80px" value="<bean:write name="reportSystemSum" property="timeRefresh"/>" /><font color="Red">(60*1000=1')</font>
     Date Start<input type="text" name="dateStart" id="dateStart"  style="width:20px" value="<bean:write name="reportSystemSum" property="dateStart"/>" /><font color="Red">(fromDate=curentDate-dateStart)</font>
    <html:radio name="reportSystemSum" property="typeChart" value="0" />Line 
    <html:radio name="reportSystemSum" property="typeChart" value="1" />Time Zoom

        
    </td>
</TR> 
<TR>
    <TD nowrap colspan="4">
    C&#7897;t <html:text name="reportSystemSum" property="columNumber" style="width:25px" />
    D&#242;ng <html:text name="reportSystemSum" property="rowNumber" style="width:25px" />
    
    <html:radio name="reportSystemSum" property="stylePrint" value="0" />
    Xu&#7845;t d&#7885;c 
    <html:radio name="reportSystemSum" property="stylePrint" value="1" />
    Xu&#7845;t ngang
    


    <label for="total" style="cursor:pointer;">
    <html:checkbox name="reportSystemSum" property="total" styleId="total" value="1"  />
    T&#7893;ng h&#7907;p b&#225;o c&#225;o
    </label>
    <label for="enableTitle" style="cursor:pointer;">
    <html:checkbox name="reportSystemSum" property="enableTitle" styleId="enableTitle" value="1"  />
    &#7848;n ti&#234;u &#273;&#7873;
    </label>
    <label for="enableChart" style="cursor:pointer;">
    <html:checkbox name="reportSystemSum" property="enableChart" styleId="enableChart" value="1"  />
        <html:radio name="reportSystemSum" property="enableChart" value="0" />
    Disable Chart
    <html:radio name="reportSystemSum" property="enableChart" value="1" />
    Enable Chart
        <html:radio name="reportSystemSum" property="enablePaging" value="0" />
    Disable Paging
    <html:radio name="reportSystemSum" property="enablePaging" value="1" />
    Enable Paging
    </label>
    <%}else{%>
    <html:hidden  name="reportSystemSum" property="columNumber" /> 
    <html:hidden  name="reportSystemSum" property="rowNumber" /> 
    <html:hidden  name="reportSystemSum" property="stylePrint" /> 
    <html:hidden  name="reportSystemSum" property="total" /> 
    <html:hidden  name="reportSystemSum" property="enableTitle" /> 
  <html:hidden  name="reportSystemSum" property="typeChart" /> 
  <html:hidden  name="reportSystemSum" property="pointInterval" /> 
  <html:hidden  name="reportSystemSum" property="maxZoom" /> 
  <html:hidden  name="reportSystemSum" property="timeRefresh" /> 
        <html:hidden  name="reportSystemSum" property="enableChart" /> 
    </td>
</TR> 
<%}%>
<%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
<tr>
    <td colspan="4">
    
    <div align="left">Truy v&#7845;n :</div>
        <html:textarea name="reportSystemSum" property="selectSql" styleId="selectSql" onkeyup="rencodeforsql(this)" style="width:750px;height:80px" />
    
    </td>
</tr>
<%}else{%>
<html:hidden  name="reportSystemSum" property="selectSql" /> 
<%}%>

<tr>
     <bean:define name="reportSystemSum" property="id" id="id" type="java.lang.Integer" />

    <td colspan="4">
    <%if(me.isRole(com.inf.IRoles.RADMINISTRATOR)){%>
        <%String onclickCreate="postAjax('reportSystemSum','QFrameTree',anchor +':_CREATE')";%>
        <html:button property="_CREATE" onclick="<%=onclickCreate%>"  styleClass="button">
        Th&#234;m m&#7899;i
        </html:button>
       
        <%String onclickUpdate="postAjax('reportSystemSum','QFrameTree',anchor +':_EDIT:id:"+id+"')";%>
        <html:button property="_EDIT" onclick="<%=onclickUpdate%>"  styleClass="button">
        L&#432;u l&#7841;i
        </html:button>
        <%String onclickDelete="postAjax('reportSystemSum','QFrameTree',anchor +':_DELETE:id:"+id+"')";%>
        <html:button property="_DELETE" onclick="<%=onclickDelete%>"  styleClass="button">
        X&#243;a
        </html:button>
    <%}%>
        <%String onclickReport="post('reportSystemSum',anchor +':_REPORT');remove('reportSystemSum',anchor)";%>
        <html:button property="_REPORT"   styleClass="button" onclick="<%=onclickReport%>" style="width:120px">
        Xu&#7845;t b&#225;o c&#225;o
        </html:button>
        <%String onclickView="viewReportSum("+id+")";%>

        <html:button property="_VIEW" onclick="<%=onclickView%>"  styleClass="button">
        Xem d&#7919; li&#7879;u
        </html:button>
    </td>

</tr>

<tr>
    <td colspan="4" id="tdodyList" >
        <jsp:include page="/admin/reportSystem/sum/list.jsp" />
    </td>
</tr>

</TABLE>
</html:form>
