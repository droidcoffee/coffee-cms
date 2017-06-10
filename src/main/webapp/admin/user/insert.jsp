<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="coffee.cms.core.action.UserAction"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
	
	String action = request.getParameter("action");
	if(action != null)
	{
		if("insert".equals(action))
		{
			UserAction act = new UserAction();
			act.insert(request);
			response.sendRedirect("query.jsp");
			return;
		}
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link href="${path}/res/css/admin.css" rel="stylesheet" type="text/css" />
<link href="${path}/res/css/theme.css" rel="stylesheet" type="text/css" />
<script src="${path}/res/common/js/jquery.js" type="text/javascript"></script>
<script src="${path}/res/js/admin.js" type="text/javascript"></script>
</head>
<body onload="javascript:document.mainForm.reset();">
	<div class="box-positon">
		<div class="rpos">当前位置: 内容管理 - 添加</div>
		<form class="ropt">
			<input type="submit" value="返回列表" onclick="this.form.action='query.jsp';" class="return-button" />
			<input type="hidden" name="cid" value="" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form name="mainForm" method="post" action="insert.jsp?action=insert">
			<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
				border="0">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>用户名:
					</td>
					<td colspan="3" class="pn-fcontent">
						<input type="text" maxlength="150" name="uname" class="required" size="70" maxlength="150" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>密码:
					</td>
					<td colspan="3" class="pn-fcontent">
						<input type="password" maxlength="150" name="pwd" class="required" size="70" maxlength="150" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>管理模块:
					</td>
					<td colspan="3" class="pn-fcontent">
						 <c:forEach var="item" items="${menus}">
						 	<input type="checkbox" name="roles" value="${item.id}"/>${item.name}
						 </c:forEach> ||
						 <input type="checkbox" onclick="doChecked('roles')"/>全选
					</td>
				</tr>
				<tr>
					<td colspan="4" class="pn-fbutton">
					<input type="submit" value="提交" /> &nbsp; 
					<input type="reset" value="重置" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>