<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>大海驴友社区</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/resources/tourpal/css/jquery.mobile-1.3.2.min.css"
	type="text/css" rel="stylesheet">
<link href="/resources/tourpal/css/jquery.mobile.tabs.css"
	type="text/css" rel="stylesheet">
<script src="/resources/tourpal/js/jquery-1.8.3.min.js"></script>
<script src="/resources/tourpal/js/jquery.mobile-1.3.2.min.js"></script>
<script src="/resources/tourpal/js/jquery.mobile.tabs.js"></script>
</head>
<body>
	<div data-role="page">
		<div data-role="header">
			<a href="${pageContext.request.contextPath}/tourpal/query.do" data-role="button"
				data-ajax="false">返回上一级</a>
			<h1>欢迎来到驴友社区</h1>
		</div>
		<div data-role="content">
			<div class="ui-grid-a">
				<div class="ui-block-a" style="width: 22%;">景区名称：</div>
				<div class="ui-block-b" style="width: 78%;">${viewinfo.viewName }</div>
			</div>
			<div class="ui-grid-a">
				<div class="ui-block-a" style="width: 22%;">发布时间：</div>
				<div class="ui-block-b" style="width: 78%;">
					<fmt:formatDate value="${viewinfo.viewTime }" type="both" />
				</div>
			</div>
			<div class="ui-grid-a">
				<div class="ui-block-a" style="width: 22%;">发布人：</div>
				<div class="ui-block-b" style="width: 78%;">管理员</div>
			</div>
			<div class="ui-grid-a">
				<div class="ui-block-a" style="width: 22%;">景区照片：</div>
				<div class="ui-block-b" style="width: 78%;">
					<img alt="" width="100%" height="80%"
						src="${pageContext.request.contextPath}/upload/${viewinfo.viewPhoto }">
				</div>
			</div>
			<div class="ui-grid-a">
				<div class="ui-block-a" style="width: 22%;">景区详情：</div>
				<div class="ui-block-b" style="width: 78%;">
					${viewinfo.viewDesc }</div>
			</div>
		</div>
		<div data-role="footer" data-position="fixed" align="center">
			<div data-role="navbar">
				<ul>

					<li><a href="${pageContext.request.contextPath}/tourpal/query.do" data-ajax="false"
						class="ui-btn-active">景点介绍</a></li>
					<li><a href="${pageContext.request.contextPath}/user_list.jsp" data-ajax="false">个人信息</a></li>
				</ul>
			</div>

		</div>
	</div>
</body>
</html>