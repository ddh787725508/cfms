
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/tourpal/css/jquery.mobile-1.3.2.min.css" type="text/css"
          rel="stylesheet">
    <link href="/resources/tourpal/css/jquery.mobile.tabs.css" type="text/css" rel="stylesheet">
    <script src="/resources/tourpal/js/jquery-1.8.3.min.js"></script>
    <script src="/resources/tourpal/js/jquery.mobile-1.3.2.min.js"></script>
    <script src="/resources/tourpal/js/jquery.mobile.tabs.js"></script>
</head>
<body>
<div data-role="page">
    <div data-role="header">
        <a href="${pageContext.request.contextPath}/user_list.jsp" data-role="button" data-ajax="false">返回上一级</a>
        <h1>个人信息</h1>
    </div>
    <div data-role="content">
        <div class="ui-grid-a">
            <div class="ui-block-a" style="width: 22%;">账号：</div>
            <div class="ui-block-b" style="width: 78%;">${userInfo.userNumber }</div>
        </div>
        <div class="ui-grid-a">
            <div class="ui-block-a" style="width: 22%;">姓名：</div>
            <div class="ui-block-b" style="width: 78%;">${userInfo.userName }</div>
        </div>
        <div class="ui-grid-a">
            <div class="ui-block-a" style="width: 22%;">类名：</div>
            <div class="ui-block-b" style="width: 78%;">${userInfo.userType }</div>
        </div>

    </div>
    <div data-role="footer" data-position="fixed" align="center">
        <div data-role="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/tourpal/query.do" data-ajax="false">景点介绍</a></li>
                <li><a href="${pageContext.request.contextPath}/user_list.jsp" data-ajax="false"
                       class="ui-btn-active">个人信息</a></li>
            </ul>
        </div>

    </div>
</div>
</body>
</html>
