<%@page import="coffee.cms.core.action.LogAction"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path",path);
	
	String action = request.getParameter("action");
	LogAction act = new LogAction();
	if(action == null || "query".equals(action))
	{
		act.query(request);
	}
	else
	{
		if("delete".equals(action))
		{
			act.delete(request);
			act.query(request);
		}
		else if("toShow".equals(action))
		{
			act.toUpdate(request);
			Object file = request.getAttribute("file");
			response.sendRedirect(file + "");
			return;
		}
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>

<link href="${path}/res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="${path}/res/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="${path}/res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="${path}/res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="${path}/res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<script src="${path}/res/common/js/jquery.js" type="text/javascript"></script>
<script src="${path}/res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="${path}/res/js/admin.js" type="text/javascript"></script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 内容管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="submit" value="添加" onclick="this.form.action='insert.jsp';"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
	<form action="query.jsp" method="post" style="padding-top:5px;">
		<div>
		日期: <input type="text" name="date" value="${date}" style="width:100px"/>
			<input class="query" type="submit" value="查询"/>
		</div>
	</form>
	<!-- 批量删除  -->
	<div style="margin-top:15px;">
		<input type="button" value="批量删除" onclick="delBatch('query.jsp','cid');"/>
	</div>
	<!-- 查询结果  -->
	<form id="tableForm" method="post">
		<input type="hidden" name="action"/>
		<input type="hidden" name="sid"/>
		<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
			<thead class="pn-lthead">
				<tr>
					<th width="30"><input type='checkbox' onclick="doChecked('cid')"/>
					</th>
					<th>日期</th>
					</tr>
			</thead>
			<tbody class="pn-ltbody">
				<c:forEach items="${items}" var="item">
					<tr onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
						<td><input type="checkbox" name="cid" value="${item}"/></td>
						<td>${item}</td>
						<td align="center">		
							<a href="query.jsp?action=toShow&sid=${item}" target="_blank" class="pn-opt">查看</a> | 
							<a href="#" onclick="del('query.jsp','${item}')" class="pn-opt">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>	
</div>
</body>
</html>