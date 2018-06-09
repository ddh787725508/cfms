
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>用户管理-用户修改</title>
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet" />
        <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
        <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
    </head>
<body>
<div>
    <ul class="breadcrumb" style="margin: 0px;">
        <li>系统管理</li>
        <li>用户管理</li>
        <li>用户修改</li>
    </ul>
</div>
<form action="${pageContext.request.contextPath}/userinfo/update.do" class="form-horizontal">
    <h5 class="page-header alert-info"
        style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
    <!-- 开始1 -->
    <div class="row">
        <div class="col-xs-5">
            <div class="form-group ">
                <label class="col-xs-3 control-label">用户姓名</label>
                <div class="col-xs-9 ">
                    <input type="text" class="form-control" name="userName"
                           placeholder="请输入用户姓名" value="${user.userName }" />
                </div>
            </div>
        </div>
        <div class="col-xs-5">
            <div class="form-group ">
                <label class="col-xs-3 control-label">用户类型</label>
                <div class="col-xs-9 ">
                    <select name="userType" class="form-control">
                        <option value="管理员" ${user.userType=='管理员'?'selected':'' } >管理员</option>
                        <option  value="普通用户" ${user.userType=='普通用户'?'selected':'' }>普通用户</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <!--结束1 -->



    <h5 class="page-header alert-info"
        style="margin: 0px; padding: 10px; margin-bottom: 10px;">账号信息</h5>
    <!-- 开始5 -->
    <div class="row">
        <div class="col-xs-5">
            <div class="form-group ">
                <label class="col-xs-3 control-label">用户账号</label>
                <div class="col-xs-9">
                    <input type="text" name="userNumber" class="form-control"
                           placeholder="请输入用户账号" value="${user.userNumber }" readonly="readonly"/>
                </div>
            </div>
        </div>
        <div class="col-xs-5">
            <div class="form-group ">
                <label class="col-xs-3 control-label">用户密码</label>
                <div class="col-xs-9 ">
                    <input type="text" name="userPw" class="form-control"
                           placeholder="请输入用户密码" value="${user.userPw }"  />
                </div>
            </div>
        </div>
    </div>
    <!--结束5 -->

    <div class="row">
        <div class="col-xs-3 col-xs-offset-4">
            <input type="submit" class="btn btn-success" value="保存用户" /> <input
                type="reset" class="btn btn-danger" value="重置信息" />
        </div>

    </div>

</form>
</body>
</html>