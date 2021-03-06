<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path",path);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>CoffeeCMS-left</title>

<link href="${path}/res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="${path}/res/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="${path}/res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="${path}/res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="${path}/res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<script src="${path}/res/common/js/jquery.js" type="text/javascript"></script>
<script src="${path}/res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="${path}/res/js/admin.js" type="text/javascript"></script>
<!-- 
	注意该js有bug 
	不能写成<script src="${path}/res/common/js/pony.js" type="text/javascript"/>
	否则， 解析该js文件的时候报错， 则不会继续往下解析其他js文件/或者js脚本
 -->
<script src="${path}/res/common/js/pony.js" type="text/javascript"></script>

<script type="text/javascript">

	function menuClick(id)
	{
		var menuItem = $("#"+id + " ul");
		var obj = menuItem.eq(1);
		//如果： == none 或者  == undefined 
		if(obj.css("display") != 'block')
		{
			obj.css("display","block");
		}
		else
		{
			obj.css("display","none");
		}
	}
</script>

</head>
<body class="lbody">
	
	<!-- 左侧的时间和菜单操作  -->
	<div class="left">
		<div class="date">
			<span>今天： <script language="javascript">
				var day = "";
				var month = "";
				var ampm = "";
				var ampmhour = "";
				var myweekday = "";
				var year = "";
				mydate = new Date();
				myweekday = mydate.getDay();
				mymonth = mydate.getMonth() + 1;
				myday = mydate.getDate();
				year = mydate.getFullYear();
				if (myweekday == 0)
					weekday = " 星期日 ";
				else if (myweekday == 1)
					weekday = " 星期一 ";
				else if (myweekday == 2)
					weekday = " 星期二 ";
				else if (myweekday == 3)
					weekday = " 星期三 ";
				else if (myweekday == 4)
					weekday = " 星期四 ";
				else if (myweekday == 5)
					weekday = " 星期五 ";
				else if (myweekday == 6)
					weekday = " 星期六 ";
				document.write(year + "年" + mymonth + "月" + myday + "日 "
						+ weekday);
			</script>
			</span>
		</div>
	</div>
	
	<ul id="browser" class="filetree treeview">
		<li id="" class="open collapsable lastCollapsable">
			<div class="hitarea open-hitarea collapsable-hitarea lastCollapsable-hitarea "></div>
			<span class="folder"><a href="#" target="rightFrame">根目录</a></span>
			
			<!-- 菜单的顶级 ul -->
			<ul>
			 <c:forEach var="item" items="${menus}">
			 	<c:choose>
				 	<c:when test="${!empty item.children}">
				 		<li id="${item.id}" class="hasChildren expandable" >
							<!-- 菜单前面的加号 -->
							<div onclick="javascript:menuClick('${item.id}');" class="hitarea hasChildren-hitarea expandable-hitarea"></div>
							<span class="folder">
								<a href="#" onclick="javascript:menuClick('${item.id}');">${item.name}</a>
							</span>
							<ul style="display: none;">
								<li id="placeholder" class="last"><span>placeholder</span></li>
							</ul>
							<ul style="display: none; ">
								<c:forEach var="child" items="${item.children}">
									<li id="${child.id}">
										<span class="file">
											<a href="${child.url}" target="rightFrame">${child.name}&nbsp;[<span style="color:red">${item.name}</span>]</a>
										</span>
									</li>
								</c:forEach>
							</ul>
						</li>
				
				 	</c:when>
				 	<c:otherwise>
				 		<li id="${item.id}" class="last">
							<span class="file">
								<a href="${item.url}" target="rightFrame" class="">${item.name}</a>
							</span>
						</li>
				 	 </c:otherwise>	
			 	</c:choose>
			 </c:forEach>				
			</ul><!-- 菜单的顶级 ul--END -->
			
		</li>
	</ul>

</body>
</html>