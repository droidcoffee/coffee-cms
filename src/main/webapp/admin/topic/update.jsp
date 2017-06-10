<%@page import="coffee.cms.admin.action.PartnerAction"%>
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
			PartnerAction act = new PartnerAction();
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
						<span class="pn-frequired">*</span>店铺名称:
					</td>
					<td colspan="3" class="pn-fcontent">
						<input type="text" maxlength="150" name="pname"  
						 	value="${item.pname}" class="required" size="70" maxlength="150"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>纬度:
					</td>
					<td colspan="3" class="pn-fcontent">
						<input type="text" maxlength="150" name="lat" 
							value="${item.lat}" class="required" size="70" maxlength="150" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>经度:
					</td>
					<td colspan="3" class="pn-fcontent">
						<input type="text" maxlength="150" name="lon" 
							value="${item.lon}" class="required" size="70" maxlength="150" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>可见性:
					</td>
					<td colspan="3" class="pn-fcontent">
						<input type="text" maxlength="150" name="visible" 
							value="${item.visible}" class="required" size="70" maxlength="150" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>地址:
					</td>
					<td colspan="3" class="pn-fcontent">
						<input type="text" maxlength="150" name="address" 
							value="${item.address}" class="required" size="70" maxlength="150" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>电话:
					</td>
					<td colspan="3" class="pn-fcontent">
						<input type="text" maxlength="150" name="phone"
							value="${item.phone}" class="required" size="70" maxlength="150" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired"></span>备注:
					</td>
					<td colspan="3" class="pn-fcontent">
						<input type="text" maxlength="150" name="remark" 
							value="${item.remark}" class="required" size="70" maxlength="150" />
					</td>
				</tr>
				<c:if test="${action != 'toShow'}">
					<tr>
						<td colspan="4" class="pn-fbutton">
						<input type="submit" value="提交" /> &nbsp; 
						<input type="reset" value="重置" /></td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
</body>
</html>