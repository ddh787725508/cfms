
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>大海驴友社区</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/tourpal/css/jquery.mobile-1.3.2.min.css" type="text/css" rel="stylesheet">
    <link href="/resources/tourpal/css/jquery.mobile.tabs.css" type="text/css" rel="stylesheet">
    <script src="/resources/tourpal/js/jquery-1.8.3.min.js"></script>
    <script src="/resources/tourpal/js/jquery.mobile-1.3.2.min.js"></script>
    <script src="/resources/tourpal/js/jquery.mobile.tabs.js"></script>
</head>
<body>
<div data-role="page">
    <div data-role="header">
        <h1>欢迎来到大海驴友社区</h1>
    </div>
    <div data-role="content">
        <form method="post" action="${pageContext.request.contextPath}/tourpal/login.do" style="padding: 10px 20px;">
            <h3 align="center">
                <img alt="" src="/resources/tourpal/img/logo.png" height="200px">
            </h3>
            <div align="center" style="color: red">${info}</div>
            <label for="un" class="ui-hidden-accessible">账号:</label> <input
                type="text" name="userNumber" id="un" placeholder="请输入账号" /> <label
                for="un2" class="ui-hidden-accessible">密码:</label> <input
                type="password" name="userPw" id="un2" placeholder="请输入密码" />

            <div class="ui-grid-a">
                <div class="ui-block-a">
                    <input type="submit" data-theme="b" value="登录" data-role="button">
                </div>
                <div class="ui-block-b">
                    <a href="${pageContext.request.contextPath}/reg.jsp" data-role="button" data-ajax="false" data-theme="b">注册</a>
                </div>
            </div>
        </form>
    </div>
    <div data-role="footer" data-position="fixed">
        <h1>版权所有：大海</h1>
    </div>
</div>
</body>
</html>
