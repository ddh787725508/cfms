
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>用户登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="/resources/assets/css/font-awesome.min.css" />
    <link rel="stylesheet" href="/resources/assets/css/ace.min.css" />
    <link rel="stylesheet" href="/resources/assets/css/ace-rtl.min.css" />
</head>

<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="icon-leaf green"></i> <span class="red">大海</span> <span
                                class="white">驴友社区后台管理</span>
                        </h1>
                        <h4 class="blue"></h4>
                    </div>
                    <div class="space-6"></div>
                    <div class="position-relative">
                        <div id="login-box"
                             class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header red lighter bigger">
                                        <i class="icon-coffee red"></i>
                                        ${info ==null?'请输入您的账号和密码':info }
                                    </h4>
                                    <div class="space-6"></div>
                                    <form action="${pageContext.request.contextPath}/userinfo/login.do" method="post" onsubmit="return check()">
                                        <fieldset>
                                            <label class="block clearfix"> <span
                                                    class="block input-icon input-icon-right"> <input id="userId"
                                                                                                      name="userNumber" type="text" class="form-control"
                                                                                                      placeholder="请输入账号" /> <i class="icon-user"></i>
												</span>
                                            </label> <label class="block clearfix"> <span
                                                class="block input-icon input-icon-right"> <input id="userPw"
                                                                                                  name="userPw" type="password" class="form-control"
                                                                                                  placeholder="请输入密码" /> <i class="icon-lock"></i>
												</span>
                                        </label>
                                            <div class="clearfix">
                                                <button type="submit"
                                                        class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="icon-key"></i> 登陆

                                                </button>
                                            </div>
                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>
