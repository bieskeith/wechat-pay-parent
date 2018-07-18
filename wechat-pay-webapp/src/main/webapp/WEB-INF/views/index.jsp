<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="common/taglibs.jsp" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="Content-Language" content="zh-cn"/>
	<meta http-equiv="Cache-Control" content="no-store"/>
	<title>大连博瑞运营管理平台</title>
	<base href="${basePath}/"/>
	<link rel="stylesheet" href="${ctx}/styles/common/comm.css"/>
	<link rel="stylesheet" href="${ctx}/componments/easyui/themes/common/easyui.css"/>
	<link rel="stylesheet" href="${ctx}/styles/biminds/login.css"/>
	<script src="${ctx}/componments/easyui/jquery.min.js"></script>
	<script src="${ctx}/componments/easyui/jquery.easyui.min.js"></script>
	<script src="${ctx}/js/index.js"></script>
	<script type="text/javascript">
        var base = '${ctx}';
	</script>
</head>

<body class="easyui-layout">
<div class="ap-login-north" data-options="region:'north'" style="height:82px;">
	<div class="ap-l">
		<div class="ap-lhl"></div>
		<div class="ap-lhr"></div>
	</div>
</div>
<div class="ap-l-content" data-options="region:'center', border:false">
	<div class="ap-login">
		<h3>大连博瑞运营管理平台</h3>
		<div class="ap-login-form">
			<ul>
				<li>
					<input class="ap-login-uname" id="userName" name="" type="text" placeholder="用户名"/>
				</li>
				<li>
					<input class="ap-login-pwd" id="password" name="" type="password" placeholder="密码"/>
				</li>
				<li class="ap-login-msg" id="error">
				</li>
				<span id="loading" style="display:none;"><img src="${ctx}/images/loading.gif"/></span>
				<li>
					<a class="ap-login-submit" href="javascript:void(0);" id="loginBtn"></a>
				</li>
			</ul>
		</div>
	</div>
</div>
<div id="login_lodopMsg"></div>
<div class="ap-login-south" data-options="region:'south', border:false">
	©2018 www.biminds.com
</div>
</body>
</html>
