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
					                				<a class="ri_menu" href="javascript:void(0);" id="menu_${child.resourceId}" rname="${child.resourceName}" loc="${child.url}&menuId=${child.resourceId}">${child.resourceName}</a>
												</c:when>
												<c:otherwise>
													 <a class="ri_menu" href="javascript:void(0);" id="menu_${child.resourceId}" rname="${child.resourceName}" loc="${child.url}?menuId=${child.resourceId}">${child.resourceName}</a>
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
        
        $('.ri_menu').click(function()
        {
        	var title = $(this).attr('rname');
        	var url = $(this).attr('loc');
        	addRiTab(title,url);
        });
        
    });
 
 
    function addRiTab(title,url){ 
    	
    	if ($('#ri_tabs').tabs('exists', title)){       		 
	        $('#ri_tabs').tabs('select', title);    
	    } else {      	    	
       		$('#ri_tabs').tabs('add',{    
	            title:title,    
	            href:url,    
	            closable:true,
	            onLoad:function(pp){
	                 if(title =="销售管理"){	               		
	            		 window.top.ocxRecord.clear();
	            		 $('#main_histroyLeadsTable').datagrid('loadData',{
	        					rows:[],
	        					total:0
	        			 });
	            		 
	            		 $('#main_histroytable').datagrid('loadData',{
	        					rows:[],
	        					total:0
	        			 });
	            		 
	            		 $('#main_fastsearchtable').datagrid('loadData',{
	        					rows:[],
	        					total:0
	        			 });
	            		 
	            	 }
	            	 if (title =="变更客户信息"){
	            		 updateLeadsModel.loadData();
	                 }
	            	 if (title =="系统设置"){
	            		 configModel.getConfigData();
	            	 }	     
	            	 if(title == '销售业务报表'){
	            		 $('#businessReport_table').datagrid('loadData',{
	        					rows:[],
	        					total:0
	        			 });
	            	 }
	            	 if(title == '续保业务报表'){
	            		 $('#groupReport_table').datagrid('loadData',{
	         				rows:[],
	         				total:0
	         			});
	            	 }
	            }
	        });  
	    }  
    }
    
    function closeRiTab(title){      	
 
	    $('#ri_tabs').tabs('close', title); 
    }
    
    function selectRiTab(title){   
	    $('#ri_tabs').tabs('select', title); 
    }
</script>
<!---左边树状菜单end-->
