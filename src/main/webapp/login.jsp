<%@page import="coffee.cms.core.Constants"%>
<%@page import="coffee.cms.core.action.UserAction"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path",path);
	
	String action = request.getParameter("action");
	if("login".equals(action))
	{
		UserAction act = new UserAction();
		act.login(request);
		if(session.getAttribute(Constants.KEY_SESSION_USER) != null){
			response.sendRedirect(path + "/admin/index.jsp");
			//out.println("login suddess");
			//out.flush();
			act.initUserMenu(session);	//初始化用户目录
			return;
		}
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>CoffeeCMS后台管理系统</title>
<link href="${path}/res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="${path}/res/css/theme.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
	if(top!=this) {
		top.location=this.location;
	}
	$(function() {
		$("#username").focus();
		$("#jvForm").validate();
	});
</script>
<style type="text/css">
body{margin:0;padding:0;font-size:12px;background:url(${path}/res/img/admin/bg.jpg) top repeat-x;}
.input{width:150px;height:17px;border-top:1px solid #404040;border-left:1px solid #404040;border-right:1px solid #D4D0C8;border-bottom:1px solid #D4D0C8;}
</style>
</head>
<body>
<form id="jvForm" action="${path}/login.jsp?action=login" method="post">

<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tbody>
  <tr>
    <td height="200">&nbsp;</td>
  </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tbody><tr>
          <td width="423" height="280" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tbody><tr>
                <td><img src="${path}/res/img/admin/ltop.jpg"/></td>
              </tr>
              <tr>
                <td><img src="${path}/res/img/admin/llogo.jpg"/></td>
              </tr>
            </tbody></table></td>
          <td width="40" align="center" valign="bottom"><img src="${path}/res/img/admin/line.jpg" width="23" height="232"/></td>
          <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tbody><tr>
                <td height="90" align="center" valign="bottom"><img src="${path}/res/img/admin/ltitle.jpg"/></td>
              </tr>
              <tr>
                <td>
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="5">
                    <tbody><tr>
                      <td width="91" height="40" align="right"><strong>用户名: </strong></td>
                      <td width="211"><input type="text" id="uname" name="uname" maxlength="100" class="input"/></td>
                    </tr>
                    <tr>
                      <td height="40" align="right"><strong>密码: </strong></td>
                      <td><input name="pwd" type="password" id="pwd" maxlength="32" class="input"/></td>
                    </tr>
                    <tr>
                      <td height="40" colspan="2" align="center">
					    <input type="image" src="${path}/res/img/admin/login.jpg" name="submit"/>
                        &nbsp; &nbsp; <img name="reg" style="cursor: pointer" src="${path}/res/img/admin/reset.jpg" onclick="document.forms[0].reset()"/> </td>
                    </tr>
                  </tbody></table></td>
              </tr>
            </tbody></table></td>
        </tr>
      </tbody></table></td>
  </tr>
</tbody></table>
</form>
</body></html>