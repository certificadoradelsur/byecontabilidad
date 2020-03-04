<%
	request.logout();
	session.invalidate(); 
	response.sendRedirect("/byeContabilidad");
%>
