
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
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
        <a href="${pageContext.request.contextPath}/login.jsp" data-ajax="false" data-role="button">返回登录</a>
        <h1>驴友社区注册</h1>
    </div>
    <div data-role="content">
        <div align="center" style="color: red;">${info }</div>
        <form action="${pageContext.request.contextPath}/tourpal/reg.do" method="post" data-ajax="true">
        <label for="userNumber">账号：</label>
        <input type="text" name="userNumber" id="userNumber" placeholder="请输入账号"/>
        <label for="userPw">密码:</label>
        <input type="password" name="userPw" id="userPw" placeholder="请输入密码"/>
        <label for="userName">姓名:</label>
        <input type="text" name="userName" id="userName" placeholder="请输入姓名"/>
        <input type="submit" value="免费注册" data-theme="b"/>
        </form>
        <div data-role="footer" data-position="fixed">
            <h1>版权所有：大海</h1>
        </div>
    </div>
</div>
</body>
</html>