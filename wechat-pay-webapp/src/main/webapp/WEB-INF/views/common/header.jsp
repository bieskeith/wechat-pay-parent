<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="taglibs.jsp" %>
<body class="easyui-layout">

<div data-options="region:'north'" style="height:52px;">
    <!--header start-->
    <div class="ap-header">
        <div class="ap-header-logo">
        </div>
        <div class="ap-header-nav">
            <ul>
                <li class="ap-nav-li">
                    <ul class="ap-dropdown-menu">
                        <div class="ap-arrow-up"></div>
                    </ul>
                </li>
                <li class="ap-nav-li">
                    <a class="ap-nav-edit"><img alt="" src="images/biminds/frame/editor.png"/><span>更改资料</span></a>
                    <ul class="ap-change-msg">
                        <li><a href="javascript:void(0);" id="modifyPass">修改密码</a></li>
                    </ul>
                </li>
                <li class="ap-nav-li"><a href="${ctx}/user/logout" id="logoutBtn"> <img alt=""
                                                                                        src="images/biminds/frame/exit.png"/>
                    <span>退出系统</span> </a></li>
                <li class="ap-nav-li">
                    <div class="ap-touxiang">
                        <a href="javascript:void(0);" title="" class="easyui-tooltip">
                            <img src="images/biminds/frame/touxiang.png">
                        </a>
                    </div>
                    <span> Hello,<strong>${UC.nickName}</strong></span><!-- <a class="ap-change-user"></a> -->
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

<!-- JS -->
<script type="text/javascript" src="componments/util/Ap.js"></script>
<script type="text/javascript" src="componments/util/checkUtil.js"></script>
<script type="text/javascript" src="componments/util/easyuiUtil.js"></script>
<script type="text/javascript">
    var ctx = '${ctx}';
    var url = '${url}';
    var basePath = '${basePath}';
    $(function () {

        $('#modifyPass').click(function () {
            $("#headerWin").window({
                title: '修改密码',
                href: 'web/user/password/update',
                width: 500,
                height: 350
            });
        });

        /* $('#logoutBtn').click(function() {
            $.ajax({
                type : "GET",
                async : false,
                url : basePath + "/user/logout",
                dataType : "json",
                data : {},
                success : function(result) {
                    location = ctx;
                }
            });
        }); */


    });
</script>
<!--header end-->