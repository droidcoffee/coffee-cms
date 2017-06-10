<%@page import="coffee.cms.core.action.UserAction"%>
<%@page import="coffee.cms.core.bean.UserBean"%>
<%@page import="coffee.util.lang.RoleUtils"%>
<%@page import="coffee.cms.core.bean.MenuItemBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
	String local =  request.getRequestURI();
	pageContext.setAttribute("local", local);
	
	String action = request.getParameter("action");
	if(action != null)
	{
		if("update".equals(action))
		{
			UserAction act = new UserAction();
			act.update(request);
			response.sendRedirect("query.jsp");
			return;
		}
		else 
		{
			request.setAttribute("action", action);
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
<body <c:if test="${action == 'toShow'}"> onload="setInputReadOnly();"</c:if>>
	<div class="box-positon">
		<div class="rpos">当前位置: 内容管理 - 更新</div>
		<form class="ropt">
			<input type="submit" value="返回列表" onclick="this.form.action='query.jsp';" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form method="post" action="${local}">
			<input type="hidden" name="action" value="update"/>	
			<input type="hidden" name="id" value="${item.id}"/>	
			<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1"
				border="0">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>用户名:
					</td>
					<td colspan="3" class="pn-fcontent">
						<input type="text" maxlength="150" name="uname" value="${item.uname}" class="required" size="70" maxlength="150" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>密码:
					</td>
					<td colspan="3" class="pn-fcontent">
						<input type="password" maxlength="150" name="pwd" value="${item.pwd}"  class="required" size="70" maxlength="150" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>管理模块:
					</td>
					<td colspan="3" class="pn-fcontent">
						 <c:forEach var="menu" items="${menus}">
						 <%
						 	MenuItemBean menuItem = (MenuItemBean)pageContext.findAttribute("menu");
						 	UserBean user = (UserBean)pageContext.findAttribute("item");
						 	if(RoleUtils.hasPermission(user.getRole(), Integer.valueOf(menuItem.getId())))
						 	{
						 		pageContext.setAttribute("hasPermission", true);
						 	}
						 	else
						 	{
						 		pageContext.setAttribute("hasPermission", false);
						 	}
						  %>
						 	<input type="checkbox" name="roles" value="${menu.id}" 
						 		<c:if test="${hasPermission == true}">checked="checked"</c:if>/>${menu.name}
						 </c:forEach> ||
						 <input type="checkbox" onclick="doChecked('roles')"/>全选
					</td>
				</tr>
				<c:if test="${action != 'toShow'}">
					<tr>
						<td colspan="4" class="pn-fbutton">
						<input type="submit" value="提交" class="submit" class="submit" /> &nbsp; 
						<input type="reset" value="重置" class="reset" class="reset" /></td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
</body>
</html>