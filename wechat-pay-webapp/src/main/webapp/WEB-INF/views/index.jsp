<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="common/taglibs.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Language" content="zh-cn" />
<meta http-equiv="Cache-Control" content="no-store" />
<title>大连博瑞云平台</title>
<base href="${basePath}/" />

<link rel="stylesheet" href="${ctx}/styles/common/k-new.css" />
<link rel="stylesheet" href="${ctx}/styles/common/k-login.css" />
<link rel="stylesheet" href="${ctx}/styles/common/font-awesome.min.css" />
<link rel="stylesheet" href="${ctx}/styles/common/swiper.min.css">

<link rel="shortcut icon" href="${ctx}/images/favicon.ico" type="image/x-icon" />
<script src="${ctx}/componments/easyui/jquery.min.js"></script>
<script src="${ctx}/componments/util/checkUtil.js"></script>
<script src="${ctx}/js/index.js"></script>
<script type="text/javascript">
	var base = '${ctx}';
</script>

<style>
			html,
			body {
				position: relative;
				height: 100%;
				overflow: hidden;
			}
			#login .login{
				height: 300px;
			}
			#login .loginform, #register .loginform{
				width:400px;
			}
			#login .loginform .pad, #register .loginform .pad{
				padding-top: 50px;
				padding-bottom: 30px;
			}
			#login .loginform .controls, #register .loginform .controls{ margin-bottom: 20px;}
			.swiper-container {
				width: 100%;
				height: 100%;
				position: inherit;
			}
			
			.swiper-slide {
				text-align: center;
				font-size: 18px;
				background: #fff;
				/* Center slide text vertically */
				display: -webkit-box;
				display: -ms-flexbox;
				display: -webkit-flex;
				display: flex;
				-webkit-box-pack: center;
				-ms-flex-pack: center;
				-webkit-justify-content: center;
				justify-content: center;
				-webkit-box-align: center;
				-ms-flex-align: center;
				-webkit-align-items: center;
				align-items: center;
			}
			
			.ap-banner {
				height: 80%;
			}
			
			.ap-dl {
				position: absolute;
				top: 200px;
				right: -122px;
				z-index: 9999;
			}
			
			.ap-footer {
				position: fixed;
				bottom: 0;
				left: 0;
				z-index: 999;
				height: 100px;
			}
			.ap-footer h1{
				margin-top: 30px;
			}
			.ap-footer p{
				margin-top: 15px;
			}
			
			@media only screen and (max-width:1440px ) {
				.ap-dl {
					top: 30px;
					right: 20px;
				}
				#login .loginform, #register .loginform{
					width:350px;
				}
			}
		</style>
		
</head>

<body>
	<div class="ap-t-top">
			<i></i>
			<em>400-8888-888</em>
		</div>
		<div class="ap-banner">
			<div class="ap-login">
				<div class="ap-dl">
					<!-- begin -->
					<div id="login">
						<div class="wrapper">
							<div class="login">
								<div class="container offset1 loginform">
									<div class="pad">
										<div class="control-group">
											<div class="controls">
												<label for="username" class="control-label fa fa-user"></label>
												<input id="username" type="username" name="username" placeholder="用户名" tabindex="1" autofocus="autofocus" class="form-control input-medium" maxlength="50"/>
											</div>
											<p style="color: #ff0030;margin-bottom: 20px;" id="uerror"></p>
										</div>
										<div class="control-group">
											<div class="controls">
												<label for="password" class="control-label fa fa-asterisk"></label>
												<input id="password" type="password" name="password" placeholder="密码" tabindex="2" class="form-control input-medium" maxlength="32"/>
											</div>
											<p style="color: #ff0030;margin-bottom: 20px;" id="perror"></p>
										</div>
									</div>
									<div class="form-actions"><!-- <a href="#" tabindex="5" class="btn pull-left btn-link text-muted">忘记密码</a><a href="#" tabindex="6" class="btn btn-link text-muted">免费注册</a> -->
										<button type="button" tabindex="4" class="btn btn-primary" id="loginBtn">登录平台</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- end -->
				</div>
			</div>

			<div class="swiper-container">
				<div class="swiper-wrapper">
					<div class="swiper-slide"><img src="${ctx}/images/common/login/f1.jpg" width="100%" /></div> 
					<div class="swiper-slide"><img src="${ctx}/images/common/login/f2.jpg" width="100%" /></div>
					<div class="swiper-slide"><img src="${ctx}/images/common/login/f3.jpg" width="100%" /></div>
				</div>
				<!-- Add Pagination -->
				<div class="swiper-button-prev swiper-button-white"></div>
				<div class="swiper-button-next swiper-button-white"></div>
			</div>

	</div>
		
<%@include file="common/footer.jsp" %>

<script src="${ctx}/js/swiper.min.js"></script>
<script src="${ctx}/js/jq22.js"></script>

<script>
$(function() {
	$.ajax({
		type : 'POST',
		url : 'banner/info',
		success : function(data){
			if(null != data.bmsConfig){
				var config = data.bmsConfig;
				if(null != config.banners){
					//  设置400电话
					$('.ap-t-top em').html(config.telphone);
					// 设置Logo
					$('.ap-t-top i').css('background-image','url(/upload/'+config.logo+')');
					//设置轮播图
					$('.swiper-wrapper').empty();
					var bmsBanners = $.parseJSON(config.banners);
					for(var i=0;i<bmsBanners.length;i++){
						var banner = bmsBanners[i];
						$('.swiper-wrapper').append('<div class="swiper-slide"><img src="/upload/'+banner.url+'" width="100%" /></div>');
					}
					new Swiper('.swiper-container', {
						pagination: '.swiper-pagination',
						paginationClickable: true,
						autoplayDisableOnInteraction : false,
						nextButton: '.swiper-button-next',
						prevButton: '.swiper-button-prev',
						speed: 600,
						autoplay: 2500
					});
				}
			}else{
				$('.swiper-wrapper').append('<div class="swiper-slide"><img src="${ctx}/images/common/login/f1.jpg" width="100%" /></div>'
						+'<div class="swiper-slide"><img src="${ctx}/images/common/login/f2.jpg" width="100%" /></div>'
						+'<div class="swiper-slide"><img src="${ctx}/images/common/login/f3.jpg" width="100%" /></div>');
				new Swiper('.swiper-container', {
					pagination: '.swiper-pagination',
					paginationClickable: true,
					autoplayDisableOnInteraction : false,
					nextButton: '.swiper-button-next',
					prevButton: '.swiper-button-prev',
					speed: 600,
					autoplay: 2500
				});
			}
		}
	});
});
		
</script>

</body>
</html>
