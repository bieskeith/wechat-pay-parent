<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>

<body class="easyui-layout">

<script type="text/javascript">
window.onbeforeunload = function(){	
	AgentLogout();
};

$(function()
{
	$.ajax({
		type : "POST",
		async : false,
		url: 'base/cti/cfg/get?organId=${UC.organId}',
		dataType:"json",
		data: {},
		success:function(result)
		{
			if(result.cfg != null){
				$('#cIp').val(result.cfg.ctiIp);
				$('#cPort').val(result.cfg.ctiPort);
			}
		}
	});
	
	$.ajax({
		type : "POST",
		async : false,
		url: 'base/cti/caller/get?userId=${UC.userId}',
		dataType:"json",
		data: {},
		success:function(result)
		{
			if(result.caller != null){
				/* $('#callerName').val(result.caller.callerName); */
				$('#gNo').val(result.caller.groupNo);
				$('#callerNo').val(result.caller.callerNo);				
			}
		}
	});
	
});

</script>


<script>   
  var ocxRecord = {		   
		   phone:"",		   
		   recordfile:"",
		   calltype:"",	
		   begintime:null,
		   endtime:null,
		   isConnect:10,
		   errorinfo:"",
		   
		   clear:function(){
			   
			   ocxRecord.phone="";		   
			   ocxRecord.recordfile="";
			   ocxRecord.calltype="";
			   ocxRecord.begintime=null;
			   ocxRecord.endtime=null;
			   ocxRecord.isConnect=10;
			   ocxRecord.errorinfo="";
		   }
  };   
</script>

<OBJECT classid="clsid:4FA53FA7-1D0C-45B0-AF15-88765548FB6C" codebase="/ocx/SWebCTC.inf#version=6,3,1,1"  
        data=0 id=Agent name=Agent	style="DISPLAY: none"> </OBJECT>	

	<SCRIPT language="JavaScript" FOR="Agent" EVENT="OnCallin(ACallerID, AParam)">
		//---------------------------电话呼入事件-------------------------------		
		ocxRecord.clear();
		ocxRecord.calltype = "20";//呼入
		ocxRecord.phone = ACallerID;
		ocxRecord.begintime = new Date();
	</SCRIPT>

	<SCRIPT language="JavaScript" FOR="Agent" EVENT="OnHangup()">
		//---------------------------本方挂机事件-------------------------------		
		//做本方挂机后相应处理
		console.debug("OnHangup： 己方挂机。");
		ocxRecord.endtime = new Date();
	</SCRIPT>

	<SCRIPT language="JavaScript" FOR="Agent"
		EVENT="OnOpHangup(AParties, ACallNum)">
		//---------------------------对方挂机事件-------------------------------
		//做对方挂机后相应处理
		 console.debug("OnOpHangup： 对方挂机。Parties: " + AParties + ", CallNum: " + ACallNum); 
		 ocxRecord.endtime = new Date();
	</SCRIPT>

	<SCRIPT language="JavaScript" FOR="Agent"
		EVENT="OnStatusChange(AStatus)">
		//---------------------------座席状态-------------------------------		
		showAgentStatus(Agent.AgentStateToInfo(AStatus));	
		
	</SCRIPT>

	<SCRIPT language="JavaScript" FOR="Agent" EVENT="OnLogon()">
		//---------------------------座席登录-------------------------------
		showAgentStatus("已登录");
	</SCRIPT>

	<SCRIPT language="JavaScript" FOR="Agent" EVENT="OnLogoff(AIsOwnerOut)">
		//---------------------------座席登出-------------------------------
		showAgentStatus("已登出");		
	</SCRIPT>

	<SCRIPT language="JavaScript" FOR="Agent" EVENT="OnAnswer(ARecFile)">
		//---------------------------接听-------------------------------
		console.debug("OnAnswer：RecFile=" + ARecFile);		
		ocxRecord.isConnect = 20;
		ocxRecord.recordfile = ARecFile;		
	</SCRIPT>


	<SCRIPT language="JavaScript" FOR="Agent"
		EVENT="OnOpAnswer(ACallType, ACallStatus, ARecFile, ACallNum)">
		//---------------------------对方接听-------------------------------
		//showAgentStatus("通话");
		ocxRecord.isConnect = 20;
		ocxRecord.recordfile = ARecFile;
		ocxRecord.begintime = new Date();
	</SCRIPT>

	<SCRIPT language="JavaScript" FOR="Agent" EVENT="OnLinkDown(LinkType)">
		//---------------------------断线事件-------------------------------
		var strTemp;

		if (LinkType == 1)
			strTemp = "Eck的";
		else
			strTemp = "ADR Server的";
		ocxRecord.errorinfo = "OnLinkDown：" + strTemp + Agent.EventToInfo(Agent.Event) + "。";	
	</SCRIPT>

	<SCRIPT language="JavaScript" FOR="Agent"
		EVENT="OnCallOutSucc(ACallType, ACallStatus, ACallNum)">
		//---------------------------呼叫成功事件-------------------------------
		//showAgentStatus("呼叫");
		ocxRecord.clear();
		ocxRecord.calltype = "10";//呼出
		ocxRecord.begintime = new Date();
		ocxRecord.phone = ACallNum;
	</SCRIPT>


	 <SCRIPT language="JavaScript" FOR="Agent" EVENT="Ready()">
		//---------------------------恢复通话事件-------------------------------	
		showAgentStatus("就绪");
	 </SCRIPT>
	<script>
	
	function showAgentStatus(status){
		$('#agentStateId').text(status);		
		console.debug(status);
	}
	
	function AgentLogin(IP,Port,GroupID,ExtNumber) {		
			try{  		
					Agent.IsWriteLocalLog = true;
					Agent.IsOpenADR = false;
					if (Agent.Open(IP,Port, "2000", "20000") == true) {
						
						if (Agent.AssignChannel(IP, ExtNumber, true, true,2, ExtNumber) == true) {
							Agent.SetQuitInterval(10000, 10000);
							//座席没有登录
							if (Agent.Status == 1) {
								if (Agent.LoginEx(GroupID, ExtNumber, ExtNumber,ExtNumber, 1, 0, 255) == false){
									EasyUiUtil.closeLoading();
									console.debug("座席登录失败LoginEx。错误码：" + Agent.ErrorCode + "，描述：" + Agent.GetLastErrInfo());
									$.messager.alert('警告','登陆失败,请检查配置信息！'); 
								} else {
									 return;
								}
							}
						} else{
							EasyUiUtil.closeLoading();
							console.debug("分配信道失败。错误码：" + Agent.ErrorCode + "，错误：" + Agent.GetLastErrInfo());
							$.messager.alert('警告','登陆失败,请检查配置信息！'); 
						}
					} else{
						EasyUiUtil.closeLoading();
						console.debug("打开本地信道失败。错误码：" + Agent.ErrorCode + "，错误：" + Agent.GetLastErrInfo());
						$.messager.alert('警告','登陆失败,请检查配置信息！'); 
					}
					showAgentStatus("登陆失败");	
			}catch(e){	
				 EasyUiUtil.closeLoading();
				 if(e instanceof TypeError){					 
					 $.messager.alert('警告','请安装电话插件并使用IE9以上版本浏览器，否则不能使用电话功能！'); 
				}
			}finally{
				EasyUiUtil.closeLoading();				
			}
	}
 
	function AgentLogout() {
			Agent.Logout();
			window.setTimeout(Agent.DeAssignChannel(), 500);

	}		
    
	function MakeCall(mobile){	
		try{			
		    var extnumber = $('#callerNo').val();
			if(!Agent.MakeCall(mobile, extnumber)){		
				$.messager.alert('警告',Agent.GetLastErrInfo()); 
				return;
			}
			mainjs.showWriteResultDialog();
		}catch(e){		
			 if(e instanceof TypeError){					 
				 $.messager.alert('警告','请安装电话插件并使用IE9以上版本浏览器，否则不能使用电话功能！'); 
			 }
		}finally{
		}
	}
	
	function Login(callback){
	   EasyUiUtil.showLoading("坐席登陆中...");
	   var ip = $('#cIp').val();
	   var port = $('#cPort').val();
	   var groupid = $('#gNo').val();
	   var extnumber = $('#callerNo').val();
	   console.debug(  'ip:' + ip +','
			         + 'port:' + port +','  
			         + 'groupid:' + groupid +','  
			         + 'extnumber:' + extnumber );	  
	   setTimeout(function(){callback(ip,port,groupid,extnumber );}, 300);	
	}
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
					
					<c:choose>

					   <c:when test="${fn:contains(UC.roleCode,'SHOP_XBZY')}">    
					   	   <span>${UC.organName}-<strong>${UC.userRealName}</strong>|座席:(<span id="agentStateId" style="color:red;font-weight:hold;" >未登录</span>)</span><!-- <a class="ap-change-user"></a> -->
					   </c:when>					   
					   <c:otherwise>  
					   		   <span>${UC.organName}-<strong>${UC.userRealName}</strong></span><!-- <a class="ap-change-user"></a> -->
					   </c:otherwise>
					  
					</c:choose>
			
			   
			    </li>
			</ul>
		</div>
	</div>
		<!--header end-->
		
		<input type="hidden" id="cIp"/>
		<input type="hidden" id="cPort"/>
		<input type="hidden" id="callerNo"/>
		<input type="hidden" id="gNo"/>
</div>

<!-- <script>
var permissions = '${permissions}';
</script> -->
<!--header start-->

  <!-- 子窗体 -->
<div id="headerWin" 
     data-options="iconCls:'icon-save',modal:true,shadow:false,minimizable:false,cache:false,maximizable:false,collapsible:false,resizable:false"
  style="padding-top:35px;"></div> 

<!-- JS -->
<script type="text/javascript" src="componments/util/Ap.js"></script>
<script type="text/javascript" src="componments/util/checkUtil.js"></script>
<script type="text/javascript" src="componments/util/easyuiUtil.js"></script>

<script type="text/javascript">
var ctx = '${ctx}';
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
		if(moduleName != 'P_AP' && moduleName != 'P_BASE' && moduleName != 'P_FM'){
			var pDate = getProductDate(groupId,moduleName);
			var currDate = getNowFormatDate();
			if(isNotEmpty(pDate) && !judegeDate(currDate,pDate)){
				$.messager.alert("提示", '该系统已于' + pDate + '过期,请联系管理员', 'info');
				return;
			}
		}
		
		//判断是否有子菜单
		if(userType != '30'){
			if(moduleName != 'P_AP' && moduleName != 'P_BASE' && moduleName != 'P_IS' && moduleName != 'P_ES' && moduleName != 'P_LN' && moduleName != 'P_RI' && moduleName != 'P_SL' && moduleName != 'P_MB' && moduleName != 'P_AC' && moduleName != 'P_BI'){
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
		//console.log($.cookie("LOGIN_TOKEN"));
		$.ajax({
			type : "GET",
			async : false,
			url : "http://sso.runlindms.com/user/logout",
			dataType : "jsonp",
			data : {'token' : $.cookie("LOGIN_TOKEN")},
			success : function(result) {
				location = ctx;
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