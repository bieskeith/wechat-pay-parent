/**
 * $Id:$
 * Copyright (c) 2014-2015 Dalian Roiland Technology Co., Ltd. All rights reserved.
 * 
 * add bu Wesyle.wei
 */
 $(function(){
	 
	 /**
	  *行内编辑密码框
	  {
	        field: "password",
	        title: "密码",
	        align: 'center',
	        width: 200,
	        formatter: function (value,row) {
		    	return "***********";
	        },
	        editor:{
	        	type:"password", 
	        	required:true
	        }
		}
	  * 
	  */
	 $.extend($.fn.datagrid.defaults.editors, {
		password: {
			init: function(container, options) {
				var input = $('<input class="datagrid-editable-input" type="password"/>').appendTo(container);

				if(!$.fn.validatebox.defaults.rules.safepass) {
					$.extend($.fn.validatebox.defaults.rules, {
						safepass: {
							validator: function(value, param) {
								return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));
							},
							message: '密码由字母和数字组成，至少6位'
						}
					});
				}
				input.validatebox(options);
				return input;
			},
			getValue: function(target) {
				return $(target).val();
			},
			setValue: function(target, value) {
				$(target).val(value);
			},
			resize: function(target, width) {
				var input = $(target);
				if($.boxModel == true) {
					input.width(width - (input.outerWidth() - input.width()));
				} else {
					input.width(width);
				}
			}
		}
	});
	 
	 /**
	  *  两次密码比较
	  */
	 $.extend($.fn.validatebox.defaults.rules, {
	 	/* 必须和某个字段相等 */
	 	equalTo : {
	 		validator : function(value, param) {
	 			return $(param[0]).val() == value;
	 		},
	 		message : '字段不匹配'
	 	}
	 });
 })