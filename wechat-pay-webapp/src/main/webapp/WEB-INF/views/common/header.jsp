<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<body class="easyui-layout">
<script type="text/javascript">
	var ctx = '${ctx}';
	$(function()
	{
		$.ajax({
			type : 'POST',
			url : ctx + '/banner/info',
			success : function(data){
				if(null != data.bmsConfig){
					var config = data.bmsConfig;
					if(null != config.banners){
						// 设置Logo
						$('.ap-header-logo').css('background-image','url(/upload/'+config.logo+')');
					}
				}
			}
		});
	});
</script>
<div data-options="region:'north'" style="height:52px;">
<!--header start-->

	<div class="ap-header">
		<div class="ap-header-logo">
			<!-- 已授权产品列表 -->
				<c:if test="${UC.productList != null || fn:length(UC.productList) > 0}">
					<c:forEach items="${UC.productList}" var="product">
						<c:if test="${module == product.productCode}">
							${product.productName}
						</c:if>
					</c:forEach>
				</c:if>
		</div>
		<div class="ap-header-nav">
			<ul>
				<li class="ap-nav-li"> 
					<a class="ap-nav-system"><img alt="" src="images/common/frame/system.png" /><span>系统切换</span></a>
					<ul class="ap-dropdown-menu">
						<div class="ap-arrow-up"></div>
						
						<!-- 已授权产品列表 -->
						<c:if test="${UC.productList != null || fn:length(UC.productList) > 0}">
							<c:forEach items="${UC.productList}" var="module">
								<li>
									<div  class="ap-sm-product">
										<a class="product_nav" module="${module.productCode}">
											<img src="images/new-l/${module.smallImg}" width="58" height="58"/>
											<span module="${module.productCode}">${module.productName}</span>
										</a> 
									</div>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</li>
				<li class="ap-nav-li"> 
					<a class="ap-nav-edit"><img alt="" src="images/common/frame/editor.png" /><span>更改资料</span></a>
			    	<ul class="ap-change-msg">
						<li><a href="javascript:void(0);" id="modifyPass">修改密码</a></li>
					</ul>
			    </li>
			    <li class="ap-nav-li"><a href="javascript:void(0);" id="logoutBtn"><img alt="" src="images/common/frame/exit.png" /> <span>退出系统</span> </a></li>
			    <li class="ap-nav-li">
					<div class="ap-touxiang" >
						<a href="javascript:void(0);" title="${UC.roles}" class="easyui-tooltip">
		      				<img src="images/common/frame/touxiang.png">
		      			</a>
					</div>
					<span>${UC.organName}-<strong>${UC.userRealName}</strong></span><!-- <a class="ap-change-user"></a> -->
			    </li>
			</ul>
		</div>
	</div>
		<!--header end-->
</div>

<!-- <script>
var permissions = '${permissions}';
</script> -->
<!--header start-->

  <!-- 子窗体 -->
<div id="headerWin" 
     data-options="iconCls:'icon-save',modal:true,shadow:false,minimizable:false,cache:false,maximizable:false,collapsible:false,resizable:false"
  style="padding-top:35px;"></div> 
  
<%-- <div id='loading_mask' style="position: absolute; z-index: 1000; top: 0px; left: 0px; width: 100%; height: 100%; filter:alpha(opacity=50); background:#FFF; text-align: center;">  
    <div style="top: 48%; position: relative;">  
       <img src="${ctx}/images/loading.gif"/> 
    </div>  
</div> --%>
	

<!-- JS -->
<script type="text/javascript" src="componments/util/Ap.js"></script>
<script type="text/javascript" src="componments/util/checkUtil.js"></script>
<script type="text/javascript" src="componments/util/easyuiUtil.js"></script>
<script type="text/javascript">
var ctx = '${ctx}';
var basePath = '${basePath}';
var userType = '${UC.userType}';
var groupId = '${UC.groupOrganId}';
$(function()
{
	$(".ap-header-logo").mouseover(function(){
	  $(this).css("cursor","pointer");
	});
	
	$('.ap-header-logo').click(function()
	{
		location = ctx + '/system/portal';
	});
	
	//系统切换
	$('.product_nav').click(function()
	{
		var moduleName = $(this).attr('module');
		
		//判断时间是否已过期
		if(moduleName != 'P_AP' && moduleName != 'P_BASE' && moduleName != 'P_FM' && moduleName != 'P_BN'){
			var pDate = getProductDate(groupId,moduleName);
			var currDate = getNowFormatDate();
			if(isNotEmpty(pDate) && !judegeDate(currDate,pDate)){
				$.messager.alert("提示", '该系统已于' + pDate + '过期,请联系管理员', 'info');
				return;
			}
		}
		
		//判断是否有子菜单
		if(userType != '30'){
			if(moduleName != 'P_AP' && moduleName != 'P_BASE' && moduleName != 'P_BN' && moduleName != 'P_CC' && moduleName != 'P_FS' && moduleName != 'P_SL' && moduleName != 'P_MB' && moduleName != 'P_AC' && moduleName != 'P_FM' && moduleName != 'P_BI'){
				$.messager.alert("提示", '该系统下无操作菜单', 'info');
				return;
			}
		}
		if(moduleName == 'P_RI'){
			location = ctx + '/system/RI/'+moduleName;
		}else{
			location = ctx + '/system/'+$(this).attr('module');
		}
	});	
	
	//修改密码
	$('#modifyPass').click(function()
	{
		$("#headerWin").window({
			title: '修改密码',
			href: 'web/ap/security/user/password/update',
			width: 500,
			height: 350
		});
	});
	
	//退出系统
	$('#logoutBtn').click(function() {
		$.ajax({
			type : "GET",
			async : false,
			url : ctx + "/user/logout",
			dataType : "json",
			data : {'token' : $.cookie("LOGIN_TOKEN")},
			success : function(result) {
				location = basePath;
			}
		});
	});
	
});

//获取产品时间
function getProductDate(groupId,moduleName){
	
	var pDate = '';
	$.ajax({
		type : "GET",
		async : false,
		url : ctx + "/ap/security/config/authinfo/date/check",
		dataType : "json",
		data : {'groupOrganId' : groupId, 'productCode' : moduleName},
		success : function(result) {
			pDate = result;
		}
	});
	
	return pDate;
}

</script>
<!--header end-->