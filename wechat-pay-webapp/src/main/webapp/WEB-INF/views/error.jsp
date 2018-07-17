<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/taglibs.jsp" />
<jsp:include page="/WEB-INF/views/common/tag.jsp" />
<!-- 头部 -->
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div style="background:#efefef;width:100%;float:left;padding-bottom:30px;border-bottom:1px solid #fff;height:745px;">
	<div class="ap-404">
		<div id="rocket"></div>
		<p></p>
		<h2>对不起,您访问页面暂时无法找到</h2>
		<h3><a href="${ctx}/system/portal">返回主页</a></h3>
	</div>
</div>
<%@include file="common/footer.jsp" %>
<script src="${ctx}/js/script.js"></script>
</body>
</html>