<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%><!DOCTYPE html>
<html>
<%@ include file="taglibs.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta http-equiv="Cache-Control" content="no-store" />
<base href="${basePath}/" />

<!-- 已授权产品列表 -->
<c:if test="${UC.productList != null || fn:length(UC.productList) > 0}">
	<c:forEach items="${UC.productList}" var="product">
		<c:if test="${module == product.productCode}">
			<title>智@车商-${product.productName}</title>
		</c:if>
	</c:forEach>
</c:if>

<!-- Loading Styles -->

<link rel="stylesheet" href="styles/common/k-new.css" />
<link rel="stylesheet" href="styles/common/font-awesome.min.css" />
<link rel="shortcut icon" href="${ctx}/images/favicon.ico" type="image/x-icon" />
<link href="styles/common/comm.css" rel="stylesheet "/>
<link href="styles/common/error.css" rel="stylesheet" />
<link href="componments/easyui/themes/common/easyui.css" rel="stylesheet" />
<link href="componments/easyui/themes/common/easyui_plus.css" rel="stylesheet"/>
<link rel="stylesheet" href="componments/easyui/themes/icon.css" />
<link href="styles/common/frame.css" rel="stylesheet" />
<!-- Loading Js -->
<script src="componments/easyui/jquery.min.js"></script>
<script src="componments/easyui/jquery.easyui.min.js"></script>
<script src="componments/easyui/easyui-lang-zh_CN.js"></script>
<script src="componments/jquery.cookie.js"></script>
<script type="text/javascript"> javascript:window.history.forward(1); </script>
</head>