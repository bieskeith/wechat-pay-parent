/**
 * EasyUI 工具类
 * 
 * @author Kevin Lv
 * @date 2015年5月9日 下午2:27:47
 */
var EasyUiUtil = {

	/**
	 * 通用消息提示
	 */		
	message : {

		"SUCCESS" : "操作成功",
		"ERROR" : "操作失败"
	},
	
	/**
	 * 开启loading状态
	 * 
	 */
	showLoading : function(msg){
		$("<div class=\"datagrid-mask\"></div>").css({
			display : "block",
			width : "100%",
			zIndex:99999,
			height : $(window).height()
		}).appendTo("body");
		$("<div class=\"datagrid-mask-msg\"></div>").html(msg).appendTo(
				"body").css({
			display : "block",
			zIndex:99999,
			left : ($(document.body).outerWidth(true) - 190) / 2,
			top : ($(window).height() - 45) / 2
		});
	},
	
	//文本溢出省略号
	formatContent: function(val, row) {
		if(!isEmpty(val)){
			if(val.length > 10){
				var vs = val.substring(0, 10);
	            return '<a href="javascript:void(0);" title="'+val+'" class="easyui-tooltip">'+vs+'</a>'+'...';
			}else{
				return '<a href="javascript:void(0);" title="'+val+'" class="easyui-tooltip">'+val+'</a>';
			}
		}else{
			return "/";
		}
    },
    
  //格式化空
  formatEmpty: function(val, row) {
		if(!isEmpty(val)){
			return val;
		}else{
			return "/";
		}
    },
    
	/**
	 * 关闭loading状态
	 */
	closeLoading : function(){
		$(".datagrid-mask").remove();
		$(".datagrid-mask-msg").remove();
	},
	
	/**
	 * 关闭模态窗口
	 * 
	 * @param {String}
	 *            win 模态窗口ID
	 * @param {String}
	 *            options 模态窗口属性
	 */
	showWindow : function(win, options) {
		$("#" + win).window(options);
	},

	/**
	 * 关闭模态窗口
	 * 
	 * @param {String}
	 *            win 模态窗口ID
	 */
	closeWindow : function(win) {
		$("#" + win).window('close');
	},
	
	/**
	 * form表单属性转JSON格式
	 * 
	 * @param {String}
	 *            formId 当前表单的ID值
	 * @return {String}
	 */
	form2Json : function(formId) {
		var arr = $('#' + formId).serializeArray();
		var jsonStr = "";
		jsonStr += '{';
		for (var i = 0; i < arr.length; i++) {
			var jName =arr[i].name.replace(/\"/g,"'").replace(/\\/g,"\\\\");
			var jValue = arr[i].value.replace(/\"/g,"'").replace(/\\/g,"\\\\");
			jsonStr += '"' + jName.replace(/[\r\n]/g, "") + '":"' + jValue.replace(/[\r\n]/g, "") + '",';
		}
		jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
		jsonStr += '}';
		var json = JSON.parse(jsonStr);
		
		return json;
	},
	
	/**
	 * 字符串转JSON格式
	 * 
	 * @param {String}
	 *            str 字符串
	 * @return {String}
	 */
	str2Json : function(str) {
		return JSON.parse(str);
	},

	/**
	 * Json转字符串
	 * 
	 * @param {String}
	 *            jsonStr json字符串
	 * @return {String}
	 */
	json2Str : function(jsonStr) {
		return JSON.stringify(jsonStr);
	},

	/**
	 * EasyUi通用提示
	 * 
	 * @param {String} msgType 类型
	 * @return {String}
	 */
	alert : function(msgType) {
		if (msgType.toLowerCase() == 'success') {
			$.messager.alert('提示', EasyUiUtil.message.SUCCESS,'info');
		} else if (msgType.toLowerCase() == 'error') {
			$.messager.alert('提示', EasyUiUtil.message.ERROR, 'error');
		}

	},
	
	/**
	 * add by wesley.wei
	 * 去除数组重复数据
	 * @param data 数组
	 * @returns 
	 */
	uniqueArray:function(data) {
		data = data || [];
		var a = {};
		for (var i = 0; i < data.length; i++) {
			var v = data[i];
			if (typeof (a[v]) == 'undefined') {
				a[v] = 1;
			}
		}
		data.length = 0;
		for ( var i in a) {
			data[data.length] = i;
		}
		return data;
	},
	
	/**
     * 返回yyyy-MM-dd HH:mm格式 
     * 
     */
    dateTimeFormatter: function (value, rec, index) {
    	if(value != undefined && value != 'undefined'){
			var t = value.lastIndexOf(":");
    		return value.substr(0,t);
		}
		return value;
    },
    
    /**
     * 返回yyyy-MM-dd格式 
     * 
     */
    dateFormatter: function (value, rec, index) {
    	if(value != undefined && value != 'undefined'){
			var t = value.split(' ');
    		return t[0];
		}
		return value;
    },
    
	//格式化金钱
	formatPrice : function(value, row){
		if(value != undefined && value != 'undefined' && isNotEmpty(value)){
			return accounting.formatNumber(value,2);
		}else{
			return "/";
		}
	},
	
	//动态Tab
	addTab : function(element,title,url){
		if ($('#'+element+'').tabs('exists', title)){    
	        $('#'+element+'').tabs('select', title);    
	    } else {    
	        $('#'+element+'').tabs('add',{    
	            title:title,    
	            href:url,    
	            closable:true    
	        });    
	    }    
	},
	
	/**
	 * 判断form内属性值是否被修改
	 * 
	 * @param jsonForm 对应修改的form序列化后的json数据
	 * @param row 对应datagrid选中的数据源
	 * 
	 * @Return true  存在修改项
	 * 		   false 不存在修改项
	 */
	isModified:function(jsonForm,row){
		for(key in jsonForm){
			//form中存在，但数据源datagrid中不存在
			if(row[key] == undefined){
				continue;
			}
			//form中""或null，但数据源datagrid中为null或""时，不进行比较
			if(!((jsonForm[key] == null || jsonForm[key] == '') && (row[key] == null || row[key] == ''))){
				//如果值不同，则返回true
				if(jsonForm[key] != row[key])
					return true;
			}
		}
		return false;
	},
	
	/**
	 * 获取form被修改信息
	 * 
	 * @param jsonForm 对应修改的form序列化后的json数据
	 * @param row 对应datagrid选中的数据源
	 * @param columnJSModel 为表单form中name属性中英对照js，需在update.jsp中引用此js文件
	 * 
	 * @Return json  数据如：[{"updateColumn":"loanRatio","updateColName":"贷款额比例","updateContent":"由11变更为100.00"},
	 * 			{"updateColumn":"loanQuotaTypeName","updateColName":"贷款额比例形式","updateContent":"由油卡变更为实物"}]
	 * 
	 */
	getModifiedField:function(jsonForm,row,columnJSModel){
		var modifiedArry = [];
		var jsonArrStr = '';
		for(key in jsonForm){
			//form中存在，但数据源datagrid中不存在
			if(row[key] == undefined){
				continue;
			}
			//form中""或null，但数据源datagrid中为null或""时，不进行比较
			if(!((jsonForm[key] == null || jsonForm[key] == '') && (row[key] == null || row[key] == ''))){
				//如果值不同，则返回true
				if(jsonForm[key] != row[key]){
					var jsonObj = {};
					
					jsonObj.updateColumn = key;
					jsonObj.updateColName = columnJSModel[key];
					jsonObj.updateContent = '由'+ row[key] + '变更为' + jsonForm[key];
					
					modifiedArry[modifiedArry.length] = jsonObj;
				}
			}
		}
		jsonArrStr = JSON.stringify(modifiedArry);
		//console.log(jsonArrStr);
		return jsonArrStr;
	}

};

$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
           
});

