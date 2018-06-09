
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
        <h1>查看个人信息</h1>
    </div>
    <div data-role="content">
        <form action="${pageContext.request.contextPath}/tourpal/updatePw.do" method="post">
            <input name="userNumber" type="hidden" value="${userInfo.userNumber }"/>
        <label for="passWord">原密码：</label>
        <input type="password"  name="passWord" id="passWord" placeholder="请输入原密码"/>
        <label for="userPw">新密码：</label>
            <input type="text" name="userPw" id="userPw" placeholder="请输入新密码"/>
            <div align="center" style="color: red;">${info }</div>
        <input type="submit" value="修改" data-theme="b"/>
        </form>
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