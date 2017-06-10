<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="coffee.front.servlet.ReplyServlet"%>
<!doctype html>
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
	ReplyServlet reply = new ReplyServlet();
	String action = request.getParameter("action");
	if ("reply".equals(action)) {
		reply.postReply(request);
	}
	//查询当前的评论列表
	reply.queryReply(request);
	//
	reply.queryMyReply(request, null);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>屌丝神经</title>
<link href="main.css" rel="stylesheet" type="text/css" />
<link
	href="${path}/jquery/themes/ui-lightness/jquery.mCustomScrollbar.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/jquery/themes/base/jquery.ui.all.css">
<script src="${path}/jquery/jquery-1.9.1.js"></script>
<script src="${path}/jquery//ui/jquery.ui.core.js"></script>
<script src="${path}/jquery//ui/jquery.ui.widget.js"></script>
<script src="${path}/jquery//ui/jquery.ui.mouse.js"></script>
<script src="${path}/jquery//ui/jquery.ui.draggable.js"></script>
<script src="${path}/jquery//ui/jquery.ui.position.js"></script>
<script src="${path}/jquery//ui/jquery.ui.resizable.js"></script>
<script src="${path}/jquery//ui/jquery.ui.button.js"></script>
<script src="${path}/jquery//ui/jquery.ui.dialog.js"></script>
<script src="${path}/jquery//ui/jquery.ui.effect.js"></script>
<script src="${path}/jquery//ui/jquery.ui.effect-blind.js"></script>
<script src="${path}/jquery//ui/jquery.ui.effect-explode.js"></script>
<script src="${path}/jquery/ui/jquery.mCustomScrollbar.min.js"></script>
<script type="text/javascript">
	 
		$(window).load(function() {
			$("#main_reply_other").mCustomScrollbar({
				scrollButtons : {
					enable : true
				}
			});
		});

	$(function() {
		$("#dialog").dialog({
			autoOpen: false,
			show: {
				effect: "blind",
				duration: 1000
			},
			hide: {
				effect: "explode",
				duration: 1000
			}
		});
		$("#opener").click(function() {
			$( "#dialog" ).dialog( "open" );
		});
	});
	////******************************************
	setInterval(refreshTime, 1000);
	//var time = ${TIME_LEFT};
	var time = ${TIME_LEFT};
	//刷新剩余时间
	function refreshTime() {
		var s = document.getElementById("ds_time_remain");
		s.innerHTML = time;
		time--;
		if (time <= 0) {
			time = 600;
			window.location.href = "index.jsp";
		}
		//setTimeout(refreshTime, 1000);
	}
	//金币增加
	function addCoin() {
		var coin = document.getElementById("game_coin");
		var coinRemain = document.getElementById("game_coin_remain");
		var reduceImg = document.getElementById("ds_reduce");
		var addImg = document.getElementById("ds_add");
		//
		var remainInt = parseInt(coinRemain.innerHTML);
		if (remainInt - 100 >= 0) {
			coin.value = parseInt(coin.value) + 100;
			coinRemain.innerHTML = remainInt - 100;
			reduceImg.src = "img/reduce_btn.png";
		} else {
			//置灰
			//addImg.src = "img/add_btn_disable.png";
		}
	}
	function reduceCoin() {
		var coin = document.getElementById("game_coin");//input
		var coinRemain = document.getElementById("game_coin_remain");//div
		var reduceImg = document.getElementById("ds_reduce");
		var addImg = document.getElementById("ds_add");
		//
		var coinInt = parseInt(coin.value);
		if (coinInt - 100 >= 100) {
			coin.value = coinInt - 100;
			coinRemain.innerHTML = parseInt(coinRemain.innerHTML) + 100;
			//最低100
			if (coinInt - 100 == 100) {
				reduceImg.src = "img/reduce_btn.png";
			}
		} else {
			//置灰
			//addImg.src = "img/add_btn_disable.png";
		}
	}
</script>
</head>
<body style="width: 100%; height: 600px; overflow: auto;">
	<div id="main">
		<div id="main_title">
			<div id="top_div_1">
				<span id="ds_time_remain">600</span>
			</div>
			<div id="top_div_2">
				<img src="img/user_logo.png"
					style="position: relative; left: 25px; top: 20px;" />
				<div
					style="position: relative; left: 100px; top: -30px; color: white;">
					<div>昵称</div>
					<div style="margin-top: 10px;">
						金币 <span>1000</span>
					</div>
				</div>
			</div>
			<div id="top_div_3">
				<img src="img/title_center.png" />
			</div>
		</div>
		<div id="main_center">
			<div id="main_center_left">
				<form name="main_form" action="index.jsp?action=reply" method="post">
					<div id="center_left_1">
						<div id="ds_topic_content">
							<input type="hidden" name="tid" value="${item.id}">
							${item.content}
						</div>
						<div id="ds_topic_answer">
							<textarea name="content" maxlength="15">说几句吧~~</textarea>
						</div>
						<div id="ds_topic_btn">
							<img src="img/recharge_btn.png">
							<!-- 减  -->
							<img id="ds_reduce" style="cursor: pointer;"
								onclick="reduceCoin();" src="img/reduce_btn_disable.png">
							<!-- 金币数值  -->
							<input id="game_coin" type="text" name="energy"
								style="width: 80px; text-align: center;" value="100">
							<!--  -->
							<img id="ds_add" style="cursor: pointer;" onclick="addCoin();"
								src="img/add_btn.png">
							<!-- 提交 答案 -->
							<img src="img/answer_btn.png" style="cursor: pointer;"
								onclick="javascript:main_form.submit();">
						</div>
						<div id="center_left_4">
							剩余金币 <span id="game_coin_remain">900</span>
						</div>
					</div>
				</form>
				<div>
					<div style="position: relative; left: 10px;">
						<img src="img/game_desc.png" />
					</div>
					<div id="opener" style="width: 395px; text-align: center;">
						<img src="img/game_desc_btn.png" />
					</div>
					<div  id="dialog" itle="Basic dialog">
					fffffffffffff
					</div>
				</div>
			</div>
			<!-- 右侧答案区 -->
			<div id="main_reply">
				<div id="main_reply_me">
					<div>
						<img src="img/user_logo.png" style="position: relative;">
					</div>
					<div
						style="position: relative; left: 60px; top: -55px; width: 230px;">
						<img src="img/answer_bg_top.png">
						<div id="ds_answer_item">${myReply.content}</div>
						<img src="img/answer_bg_bottom.png">
					</div>
					<div>
						<img src="img/answer_line.png"
							style="margin-left: -8px; position: relative; top: -60px;">
					</div>
				</div>
				<div id="main_reply_other">
					<c:if test="${!empty maxScore}">
						<c:forEach items="${maxScore}" var="reply" varStatus="vs">
							<div style="position: relative; height: 85px; overflow: hidden;">
								<div
									style="position: relative; left: 60px; top: 5px; width: 220px; color: white;">
									<img src="img/answer_bg_top.png">
									<div id="ds_answer_item">${reply.content}dfjhdfjhdfj</div>
									<img src="img/answer_bg_bottom.png">
								</div>
								<div style="float: left; position: relative; top: -60px;">
									<img src="img/user_logo.png">
								</div>
								<div>
									<img src="img/answer_line.png"
										style="margin-left: -8px; position: relative; top: -60px;">
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>