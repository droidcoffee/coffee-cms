<%@ page import="coffee.cms.core.Constants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
	if (session.getAttribute(Constants.KEY_SESSION_USER) == null) {
		response.sendRedirect(path + "/login.jsp");
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>CoffeeCMS 后台管理系统</title>
</head>

<frameset rows="72,*" frameborder="0" border="0" framespacing="0">
<frame src="${path}/admin/top.jsp" name="topFrame" noresize="noresize"
	id="leftFrame"> <frame src="${path}/admin/main.jsp"
	name="mainFrame" id="mainFrame"></frameset>
<noframes>
	<body></body>
</noframes>

</html>