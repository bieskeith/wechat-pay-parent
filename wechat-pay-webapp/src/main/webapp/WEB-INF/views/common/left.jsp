<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="taglibs.jsp" %>

<div data-options="region:'west',split:true" class="ap-west">
    <!--main-left start-->
    <div class="ap-mytree">
            <div class="ap-tree-box">
                <h3>
                    <a href="javascript:void(0);">组织机构管理</a>
                </h3>
                <ul class="ap-tree-one" style="display: block;">
                    <li>
                        <h4>
                            <a href="web/user/list?menuId=1_1" id="menu_1_1">用户管理</a>
                            <a href="web/dealer/list?menuId=1_2" id="menu_1_2">商户管理</a>
                        </h4>
                    </li>
                </ul>
            </div>
    </div>
    <!--min-left end-->
</div>
<!--左边树状菜单start-->
<script type="text/javascript">
    $(function () {

        //菜单选中
        var mid = '${menuId}';
        var menuId = 'menu_${menuId}';
        $('h4 a').each(function () {
            if ($(this).attr("id") == menuId) {
                $(this).css({'color': '#bd2229'});
            }
        });

        var h3 = $(".ap-tree-box").find("h3");
        var tree_one = $(".ap-tree-box").find(".ap-tree-one");
        var h4 = $(".ap-tree-one").find("h4");

        if (mid == '' || mid == null) {
            $.each(tree_one, function (key, value) {
                if (key != 0) {
                    tree_one.eq(key).hide();
                }
            });
        } else {
            //获取当前选中菜单所在的div
            $(".ap-tree-one").hide();
            $('#menu_${menuId}').parent().parent().parent().show();
        }

        h3.each(function (i) {
            $(this).click(function () {
                tree_one.eq(i).slideToggle();
                tree_one.eq(i).parent().siblings().find(".ap-tree-one").slideUp();
            });
        });
    });
</script>
<!---左边树状菜单end-->
