
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>景区管理-景区添加</title>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" />
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div>
    <ul class="breadcrumb" style="margin: 0px;">
        <li>系统管理</li>
        <li>景区管理</li>
        <li>景区添加</li>
    </ul>
</div>
<form action="${pageContext.request.contextPath}/viewinfo/add.do" method="post" enctype="multipart/form-data" class="form-horizontal">
    <h5 class="page-header alert-info"
        style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
    <!-- 开始1 -->
    <div class="row">
        <div class="col-xs-9">
            <div class="form-group ">
                <label class="col-xs-3 control-label">景区名称</label>
                <div class="col-xs-9 ">
                    <input type="text" class="form-control" name="viewName" placeholder="请输入景区姓名" />
                </div>
            </div>
        </div>
    </div>
    <!--结束1 -->
    <!-- 开始1 -->
    <div class="row">
        <div class="col-xs-9">
            <div class="form-group ">
                <label class="col-xs-3 control-label">景区照片</label>
                <div class="col-xs-9 ">
                    <input type="file" name="file"  />
                </div>
            </div>
        </div>
    </div>
    <!--结束1 -->


    <h5 class="page-header alert-info"
        style="margin: 0px; padding: 10px; margin-bottom: 10px;">账号信息</h5>
    <!-- 开始5 -->
    <div class="row">
        <div class="col-xs-9">
            <div class="form-group ">
                <label class="col-xs-3 control-label">景区详情</label>
                <div class="col-xs-9">
                    <textarea rows="" cols="" name="viewDesc" placeholder="请输入景区详情" class="form-control"></textarea>
                </div>
            </div>
        </div>
    </div>
    <!--结束5 -->

    <div class="row">
        <div class="col-xs-3 col-xs-offset-4">
            <input type="submit" class="btn btn-success" value="保存景区" /> <input
                type="reset" class="btn btn-danger" value="重置信息" />
        </div>

    </div>

</form>
</body>
