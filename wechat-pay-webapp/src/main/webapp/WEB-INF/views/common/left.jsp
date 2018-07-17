<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>


<div data-options="region:'west',split:true" class="ap-west">
	<!--main-left start-->
	<div class="ap-mytree">
	<c:if test="${rMap != null || fn:length(rMap) > 0}">
  		<c:forEach items="${rMap}" var="auth">
			<div class="ap-tree-box">
				<h3>  
					<a href="javascript:void(0);">${auth.key}</a>
				</h3>
				<c:if test="${auth.value != null || fn:length(auth.value) > 0}">
						<ul class="ap-tree-one" style="display: block;">
							<c:forEach items="${auth.value}" var="child">
								<li>
									<h4>
										<c:set var="url" value="${child.url }"/>	
					                	<c:choose>
												<c:when test="${fn:contains(url, '?')}">
					                				<a href="${child.url}&menuId=${child.resourceId}" id="menu_${child.resourceId}">${child.resourceName}</a>
												</c:when>
												<c:otherwise>
													 <a href="${child.url}?menuId=${child.resourceId}" id="menu_${child.resourceId}">${child.resourceName}</a>
												</c:otherwise>
										</c:choose>
									</h4>
								</li>
							 </c:forEach>
						</ul>
				</c:if>
			</div>
			</c:forEach>
	</c:if>
	</div>
	<!--min-left end-->
</div>


<!--左边树状菜单start-->
<script type="text/javascript">
    $(function(){
    	
    	//菜单选中
    	var mid = '${menuId}';
    	var menuId = 'menu_${menuId}';
    	$('h4 a').each(function()
    	{
    		if($(this).attr("id") == menuId){
    			$(this).css({'color':'#003f8e'});
    		}
    	});
    	
        var h3 = $(".ap-tree-box").find("h3");
        var tree_one = $(".ap-tree-box").find(".ap-tree-one");
        var h4 = $(".ap-tree-one").find("h4");
        
      if(mid == '' || mid == null){
        	$.each(tree_one,function(key, value)
      			{
      	        	if(key != 0){
      	        		 tree_one.eq(key).hide();
      	        	}
      			});
        }else{
        	//获取当前选中菜单所在的div
        	$(".ap-tree-one").hide();
        	$('#menu_${menuId}').parent().parent().parent().show();
        }
        
        h3.each(function(i){
            $(this).click(function(){
                tree_one.eq(i).slideToggle();
                tree_one.eq(i).parent().siblings().find(".ap-tree-one").slideUp();
            });
        });
        
        /*  h4.each(function(i){
            $(this).click(function(){
               
            });
        }); */
    });
</script>
<!---左边树状菜单end-->
