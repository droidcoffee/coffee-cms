<%@page import="coffee.cms.admin.bean.ReplyBean"%>
<%@page import="coffee.cms.admin.action.ReplyAction"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path",path);
	
	String action = request.getParameter("action");
	ReplyAction act = new ReplyAction();
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
		else if("toUpdate".equals(action))
		{
			act.toUpdate(request);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;
		}
		else if("toShow".equals(action))
		{
			act.toUpdate(request);
			request.getRequestDispatcher("update.jsp?action=toShow").forward(request, response);
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
<script src="${path}/res/common/js/pony.js" type="text/javascript"></script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 评论管理 - 列表</div>
	<form class="ropt">
		<input  class="add" type="hidden" value="添加" onclick="this.form.action='insert.jsp';"/>
		<input type="submit" value="返回列表" onclick="this.form.action='query.jsp';" class="return-button" />
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
	<form action="query.jsp" method="post" style="padding-top:5px;">
		<div>
		评论内容: <input type="text" name="title" value="${title}" style="width:100px"/>
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
		<input type="hidden" name="pager.curpage" value="${pager.curpage}"/>
		<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
			<thead class="pn-lthead">
				<tr>
					<th width="30"><input type='checkbox' onclick="doChecked('cid')"/>
					</th>
					<th>ID</th>
					<th>评论内容</th>
					<th>能量 /得分</th>
					<th>作者</th>
					<th>时间</th>
					<th>操作</th>
					</tr>
			</thead>
			<tbody class="pn-ltbody">
				<c:forEach items="${pager.items}" var="item">
					<tr onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
						<td><input type="checkbox" name="cid" value="${item.id}"/></td>
						<td>${item.id}</td>
						<td>
						<c:choose>
							<c:when test="${fn:length(item.content) > 20}">
								${fn:substring(item.content,0,20)}...
							</c:when>
							<c:otherwise>${item.content}</c:otherwise>
						</c:choose>
						</td>
						<td>${item.energy}/${item.score}</td>
						<td>${item.uname}</td>
						<% 
							ReplyBean bean = (ReplyBean)pageContext.findAttribute("item");
							pageContext.setAttribute("addTime", new Date(bean.getAddTime()));
						%>
						<td><fmt:formatDate value="${addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td align="center">		
							<a href="query.jsp?action=toShow&tid=${tid}&sid=${item.id}" class="pn-opt">查看</a> | 
							<a href="#" onclick="del('query.jsp?tid=${tid}','${item.id}')" class="pn-opt">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>	
		<c:if test="${!empty pager}">
		<style type="text/css">
			.pager_a{font-size: 14px; padding: 6px;}
			.pager_a_cur{font-size: 16px; font-weight: bold;}
		</style>
		<script type="text/javascript">
			function setPagerSize()
			{
				var pagerSize = document.getElementById("pagerSize");
				var pagerSizeText = document.getElementById("pagerSizeText");
				pagerSize.value = pagerSizeText.value;
				pager_form.submit();
			}
		</script>
		<form name="pager_form" action="query.jsp" method="post">
		 <input type="hidden" name="action" value="query"/>
		 <input type="hidden" id="pagerSize" name="pager.size" value="${pager.size}"/>
		 <input type="hidden" id="pagerOffset" name="pager.offset"/>
		 <input type="hidden" name="pname" value="${pname}"/>
		 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="pager_table"
		 	style="margin-top: 10px;">
              <tr>
                <td width="50%">共 <span class="right-text09"> ${pager.page} </span> 页  (${fn:length(pager.items)}/${pager.count})
                </td>
                <td width="49%" align="right">
                 <input type="text" id="pagerSizeText" value="${pager.size}" size="1" onblur="setPagerSize()"/>
                 <pg:pager scope="request" maxIndexPages="5" index="center"
					    maxPageItems="${pager.size}" url="${localUrl}" items="${pager.count}"
					    export="currentPageNumber=pageNumber">
					    <!-- url参数 -->
					    <pg:param name="action" value="query"/>
					    <pg:param name="pager.size" value="${pagerSize}"/>
						<pg:param name="pname" value="${pname}"/>
					    <!-- 以下内容保持不变 -->
					    <pg:first> <a href="${pageUrl}">首页</a> </pg:first>
					    <pg:prev> <a href="${pageUrl}">前页</a>  </pg:prev>
					     <pg:pages>
						     <c:choose>
						  	    <c:when test="${pageNumber eq currentPageNumber }">
						       		<font color="red" class="pager_a_cur">${pageNumber }</font>
						      	</c:when>
						      	<c:otherwise>
						       		<a href="${pageUrl}" class="pager_a">${pageNumber}</a>
						      	</c:otherwise>
						     </c:choose>
					    </pg:pages>
					    <pg:next> <a href="${pageUrl}">下页</a> </pg:next>
					    <pg:last> <a href="${pageUrl}">尾页</a> </pg:last>
					    <pg:skip pages="<%= 5 %>"> 
					 	 	<a  href="${pageUrl}">[ 跳转 #${pageNumber} ]</a> 
						</pg:skip> 
					   </pg:pager>
                 </td>
              </tr>
          </table>
      </form>
      </c:if>
</div>
</body>
</html>