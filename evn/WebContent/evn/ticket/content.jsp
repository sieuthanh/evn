<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<html:form action="ticketAction" method="post">
<table class="list-voffice" width="100%" cellpadding="0" cellspacing="0">

                    <tr>
                        <td id="idform1">
                            <jsp:include page="/evn/ticket/form1.jsp" />
                        </td>
                    </tr>
                    <tr>
                        <td id="idform2">
                            <jsp:include page="/evn/ticket/form2.jsp" />
                        </td>
                    </tr>

                </table>
    </html:form>       
