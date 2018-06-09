<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>大海驴友社区</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/tourpal/css/jquery.mobile-1.3.2.min.css" type="text/css"
          rel="stylesheet">
    <link href="/resources/tourpal/css/jquery.mobile.tabs.css" type="text/css" rel="stylesheet">
    <script src="/resources/tourpal/js/jquery-1.8.3.min.js"></script>
    <script src="/resources/tourpal/js/jquery.mobile-1.3.2.min.js"></script>
    <script src="/resources/tourpal/js/jquery.mobile.tabs.js"></script>
</head>
<body><div data-role="page">
    <div data-role="header">
        <a href="${pageContext.request.contextPath}/login.jsp" data-role="button" data-ajax="false">退出登录</a>
        <h1>欢迎来到驴友社区</h1>
    </div>
    <div data-role="content">

        <ul data-role="listview" data-filter="true" data-filter-placeholder="请输入景点名称" >
            <c:forEach items="${list }" var="view">
                <li>
                    <a href="${pageContext.request.contextPath}/tourpal/queryInfo.do?viewId=${view.viewId}" data-ajax="false">
                        <img alt="" src="${pageContext.request.contextPath}/upload/${view.viewPhoto }" width="200px" height="100px">
                        <h2>${view.viewName }</h2>
                        <p>${view.viewDesc }
                        </p>
                    </a>
                </li>
            </c:forEach>
        </ul>

    </div>
    <div data-role="footer" data-position="fixed" align="center">
        <div data-role="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/tourpal/query.do" data-ajax="false"  class="ui-btn-active">景点介绍</a></li>
                <li><a href="${pageContext.request.contextPath}/user_list.jsp" data-ajax="false"  >个人信息</a></li>
            </ul>
        </div>

    </div>
</div>
</body>
</html>