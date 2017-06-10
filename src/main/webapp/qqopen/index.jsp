<%@page import="java.util.Date"%>
<%@page import="coffee.cms.admin.bean.ReplyBean"%>
<%@page import="coffee.cms.admin.bean.TopicBean"%>
<%@page import="coffee.front.servlet.ReplyServlet"%>
<%@page import="coffee.cms.admin.action.TopicAction"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
	///droidcoffee/qqopen/index.jsp
	String localPath = request.getRequestURI().substring(0, request.getRequestURI().lastIndexOf("/"));
	out.println(localPath);
	String action = request.getParameter("action");
	ReplyServlet reply = new ReplyServlet();
	if ("reply".equals(action)) {
		reply.postReply(request);
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache,must-revalidate" />
<meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT" />
<title>争分夺秒</title>
<link href="main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	setInterval(refreshTime, 1000);
	var time = ${TIME_LEFT};
	function refreshTime() {
		var s = document.getElementById("s");
		s.innerHTML = time;
		time--;
		if (time <= 0) {
			time = 600;
			topicForm.submit();
		}
		//setTimeout(refreshTime, 1000);
	}
</script>
</head>

<body>
	<div class="bodyer">
		<div class="top">
			<div class="top_left">
				<img style="margin-left: 50px;" src="images/logo.jpg" width="400"
					height="200" border="0" />
				<c:forEach items="${winners}" var="item">
					<%
						ReplyBean tmp = (ReplyBean) pageContext.findAttribute("item");
						pageContext.setAttribute("addTime", new Date(tmp.getAddTime()));
					%>
					<div style="margin: 10px 0px 5px 50px; font-size: 15px;">
						<font color="red"><fmt:formatDate value="${addTime}"
								pattern="HH:mm" />(${item.tid})</font>获奖人<font color="red">${item.uname}</font> <font
							color="red">(${item.score})</font><a href="#">领奖去~~</a>
					</div>
				</c:forEach>
			</div>
			<div class="top_right">
				<div class="top_t1">
					<img src="images/title.jpg" />
				</div>
				<div class="top_t4">
					<table border="0" cellpadding="0" cellspacing="0"
						style="margin: 0px; padding: 0px;">
						<tr>
							<td><a href="#"><img src="images/0.jpg" width="140"
									border="0" /></a></td>
							<td valign="bottom"><span class="span_num">1000能量</span></td>
							<td><span class="chong"> <a href="#"><img
										src="images/chong.jpg" width="148" height="104" border="0" /></a>
							</span></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="ti">
			<form name="topicForm" action="${path}/qqopen/index.jsp?action=next"
				method="get">
				<table border="0" cellpadding="0" cellspacing="0"
					style="margin: 0px; padding: 0px;">
					<tr>
						<td width="256"><div id="s">60</div></td>
						<td>
							<div class="ti_con">
								<c:choose>
									<c:when test="${!empty item}">${item.content}
									<input type="hidden" name="tid" value="${item.id}" />
									</c:when>
									<c:otherwise>
										<input type="hidden" name="tid" value="5" />
										从小国家就教育我，废旧电池不要乱扔。尼玛我都22了，国家也没告诉我到底该扔到哪儿！到底该扔到哪儿啊？哈哈哈
									</c:otherwise>
								</c:choose>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 提交问题 -->
		<div class="da">
			<form action="${path}/qqopen/index.jsp?action=reply" method="post">
				<!-- 用户ID -->
				<input type="hidden" name="uid" value="1" />
				<!-- 用户名 -->
				<input type="hidden" name="uname" value="猪脑袋" />
				<!-- topicID -->
				<input type="hidden" name="tid" value="${item.id}" />
				<table border="0" cellpadding="0" cellspacing="0"
					style="margin: 0px; padding: 0px; width: 100%;">
					<tr>
						<td><img src="images/huafei.jpg" width="209" height="202"
							border="0" /></td>
						<td width="420"><textarea class="content" name="content"></textarea></td>
						<td><select class="ck" name="energy"
							style="vertical-align: top; margin-left: 10px; margin-right: 10px;">
								<%
									for (int i = 20; i > 0; i--) {
										out.print("<option>" + i * 100 + "</option>");
									}
								%>
						</select></td>
						<td width="140"><input id="bt" type="image"
							src="images/da.gif" width="130" height="110" /></td>
					</tr>
				</table>
			</form>
		</div>

		<div class="list_con">
			<div class="list_title1">
				<img src="./images/tab_0.jpg" alt="" onclick=""
					style="cursor: pointer;" /> <img src="./images/tab_1.jpg"
					onclick="" style="cursor: pointer;" /> <img
					src="./images/tab_2.jpg" onclick="" style="cursor: pointer;" />
			</div>
			<c:forEach items="${maxScore}" var="item" varStatus="vs">
				<div class="list">
					<table border="0" cellpadding="0" cellspacing="0"
						style="margin: 0px; padding: 0px; width: 100%; height: 120px;">
						<tr valign="top">
							<td width="120"><img src="images/${vs.index}.jpg"
								width="120" border="0" /></td>
							<td>
								<div class="zi1">${item.content}</div>
								<div class="fee">${item.energy}能量</div>
							</td>
							<td width="271">
								<div class="nn1"
									<c:if test="${vs.index==0}">style="background-image:url(images/fee.jpg)"</c:if>>${item.score}分</div>
							</td>
						</tr>
					</table>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>