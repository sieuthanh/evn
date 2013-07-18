<%@page import="com.inf.IKey"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ include file="/commons/tags.jsp"%>
<%@ include file="/commons/params.jsp"%>
<%
	String pattern = "dd/MM/yyyy";
	SimpleDateFormat format = new SimpleDateFormat(pattern);
	Calendar c = Calendar.getInstance();
	String today = format.format(c.getTime());
	c.add(Calendar.DATE,-Integer.parseInt(IKey.getValue("NUMBER.OF.SYNC.ERROR.DAYS")));
	String pass = format.format(c.getTime());
%>
<table class="list-voffice" width="100%" cellpadding="0" cellspacing="0">
	<bean:define name="BPaging2" id="beans" type="com.form.FBeans" />
	<TR>
		<TH colspan="2">&#272;&#227; chuy&#7875;n <%=beans.getTotalRows()%>
			th&#244;ng b&#225;o (From <%=pass %> To <%=today %>)
		</TH>
	</TR>
	<TR>
		<td align="right" id="idrecordlist"><jsp:include
				page="/evn/alertBuffer/recordList1.jsp" /></td>
	</TR>
</TABLE>


