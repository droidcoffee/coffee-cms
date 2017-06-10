<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
	String action = request.getParameter("action");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache,must-revalidate" />
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT" />
<meta property="qc:admins" content="74273566676427143766556375" />
<title>争分夺秒</title>
<link href="main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="http://qzonestyle.gtimg.cn/qzone/vas/open/open_v2.js"></script>
<script type="text/javascript">
	function toLogin() {
		//以下为按钮点击事件的逻辑。注意这里要重新打开窗口
		//否则后面跳转到QQ登录，授权页面时会直接缩小当前浏览器的窗口，而不是打开新窗口
		//http://openapi.qzone.qq.com/oauth/show?which=ConfirmPage&display=pc&client_id=100704915&response_type=token&scope=all&redirect_uri=http%3A%2F%2Fqzonestyle.gtimg.cn%2Fqzone%2Fopenapi%2Fredirect-1.0.1.html
		window.location.href = "http://www.droidcoffee.com/coffee/qqauth";
		//window.location.href = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=100431420&client_secret=appkey&redirect_uri=http://www.droidcoffee.com/coffee/sina/&code=";
	}
</script>
</head>

<body>
	<a href="#" onclick='toLogin()'>跳转啊</a>
</body>
</html>