/*
 * 平台通用JS工具集合，包括：
 * 
 * window.console
 * 
 * KeyCodeEnum
 * 
 * jQuery: ajaxSetup
 * jQuery: ajaxError
 * jQuery: ajaxSuccess
 * 
 * EasyUI: $.parser.onComplete
 * 
 * MathUtils
 */

/**
 * Avoid `console` errors in browsers that lack a console.
 */
(function() {
  var console = (window.console = window.console || {});
  console.level = 'debug';

  var method;
  var noop = function() {
  };
  var methods = [ 'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error', 'exception',
      'group', 'groupCollapsed', 'groupEnd', 'info', 'log', 'markTimeline', 'profile',
      'profileEnd', 'table', 'time', 'timeEnd', 'timeStamp', 'trace', 'warn' ];
  var length = methods.length;
  while (length--) {
    method = methods[length];
    // Only stub undefined methods.
    if (!console[method]) {
      console[method] = noop;
    }
  }

  $.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
      if (o[this.name]) {
        if (!o[this.name].push) {
          o[this.name] = [ o[this.name] ];
        }
        o[this.name].push(this.value || '');
      } else {
        o[this.name] = this.value || '';
      }
    });
    return o;
  };
}());

/**
 * 键盘值枚举
 */
var KeyCodeEnum = {
  NULL: 0,
  BackSpace: 8,
  Tab: 9,
  Clear: 12,
  Enter: 13,
  Escape: 27,
  Space: 32,
  Prior: 33,
  Next: 34,
  End: 35,
  Home: 36,
  Left: 37,
  Up: 38,
  Right: 39,
  Down: 40,
  Select: 41,
  Print: 42,
  Execute: 43,
  Insert: 45,
  Delete: 46,
  Help: 47,
  Num0: 48,
  Num1: 49,
  Num2: 50,
  Num3: 51,
  Num4: 52,
  Num5: 53,
  Num6: 54,
  Num7: 55,
  Num8: 56,
  Num9: 57,
  A: 65,
  B: 66,
  C: 67,
  D: 68,
  E: 69,
  F: 70,
  G: 71,
  H: 72,
  I: 73,
  J: 74,
  K: 75,
  L: 76,
  M: 77,
  N: 78,
  O: 79,
  P: 80,
  Q: 81,
  R: 82,
  S: 83,
  T: 84,
  U: 85,
  V: 86,
  W: 87,
  X: 88,
  Y: 89,
  Z: 90,
  F1: 112,
  F2: 113,
  F3: 114,
  F4: 115,
  F5: 116,
  F6: 117,
  F7: 118,
  F8: 119,
  F9: 120,
  F10: 121,
  F11: 122,
  F12: 123
};

/**
 * 设置全局 AJAX 默认选项。
 * 
 * @author 何珏2014-9-11
 */
$.ajaxSetup({
  cache: false
// 所有Ajax请求默认均不启用缓存
});

/*
 * $.ajaxSetup({ contentType:"application/x-www-form-urlencoded;charset=utf-8",
 * complete:function(XMLHttpRequest,textStatus){ var
 * sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
 * if(sessionstatus=="timeout"){ alert("登录超时,请重新登录！"); //如果超时就处理 ，指定要跳转的页面
 * window.location.replace(webPath.webRoot + "/index"); } } });
 */

$(document).ajaxError(function(event, jqXHR, ajaxOptions, thrownError) {
  $(".datagrid-mask").remove();
  $(".datagrid-mask-msg").remove();
  if(jqXHR.status != 200){
	  $.messager.alert('错误', '请求失败：' + ajaxOptions.url, 'error'); 
  }
});

/*
 * $(document).ajaxStart(function() { EasyUiUtil.showLoading("加载中.."); });
 */

$(document).ajaxSuccess(function(event, jqXHR, ajaxOptions, data) {
  if (data != undefined && data != 'undefined') {
    if (!data.success) {
      if (data.isException || data.message) {
        $.messager.alert('提示', data.message, 'error');
      } else if (data.isSessionTimeout) {
        alert('会话过期，请重新登陆！');
        window.location = '';
      }
    } else {
      $.messager.alert('提示', '操作成功', 'info');
    }
  }
});

/**
 * 为JS提供精确计算
 * 
 * @author Jades.He
 * @date 2016-1-11
 */
var MathUtils = {
  _MAX_POW: Math.pow(10, 10),

  /**
   * 判断数据的类型
   * 
   * @param obj
   * @return
   */
  _typeOf: function(obj) {
    if (typeof obj == "undefined") {
      return "undefined";
    }
    if (obj == null) {
      return "null";
    }
    if (typeof obj.attr == "function" && typeof obj.is == "function") {
      return 'jquery';
    }
    var type = typeof obj;
    if (type == 'object' && obj.nodeName) {
      if (obj.nodeName == "attrName") {
        return "attribute";
      }
      switch (obj.nodeType) {
      case 1:
        return 'element';
      case 3:
        return (/\S/).test(obj.nodeValue) ? 'textnode' : 'whitespace';
      }
    }
    if (type == 'object' || type == 'function') {
      switch (obj.constructor) {
      case Array:
        return 'array';
      case RegExp:
        return 'regexp';
        // case Class: return 'class';
      }
      if (typeof obj.length == 'number') {
        if (obj.item)
          return 'collection';
        if (obj.callee)
          return 'arguments';
      }
    }
    return type;
  },

  /**
   * 将数值四舍五入(保留2位小数)后格式化成金额形式 123456789.12345 -> 123,456,789.12
   * 
   * @param num
   *          数值(Number或者String)
   * @return 金额格式的字符串,如'1,234,567.45'
   * @type String
   */
  _formatCurrency: function(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num)) {
      num = '0';
    }
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10) {
      cents = '0' + cents;
    }
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++) {
      num = num.substring(0, num.length - (4 * i + 3)) + ','
          + num.substring(num.length - (4 * i + 3));
    }
    return (((sign) ? '' : '-') + num + '.' + cents);
  },

  /**
   * 将数值四舍五入(保留4位小数)后格式化成金额形式 123456789.12345 -> 123,456,789.1235
   * 
   * @param num
   *          数值(Number或者String)
   * @return 金额格式的字符串,如'1,234,567.45'
   * @type String
   */
  _formatCurrency4: function(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num)) {
      num = '0';
    }
    sign = (num == (num = Math.abs(num)));
    var cents = num.toFixed(4);
    cents = cents.toString();
    cents = cents.substring(cents.indexOf('.'));
    num = Math.floor(num * 10000 + 0.50000000001);
    numStr = Math.floor(num / 10000).toString();

    for (var i = 0; i < Math.floor((numStr.length - (1 + i)) / 3); i++) {
      numStr = numStr.substring(0, numStr.length - (4 * i + 3)) + ','
          + numStr.substring(numStr.length - (4 * i + 3));
    }
    return (((sign) ? '' : '-') + numStr + cents);
  },

  /**
   * 循环数组array,依次地将数组的两个数值传到func函数,最终返回一个数值 array:{array}数值,每一个元素是{number |
   * string}金钱字符串或数值 callback:函数,有两个参数,都是数值
   */
  compute: function(array, callback) {
    if (typeof callback != 'function') {
      throw new TypeError();
    }
    if (this._typeOf(array) != 'array' && this._typeOf(array) != 'arguments') {
      return null;
    }
    var result = this.toDouble(array[0]);
    if (array.length == 1) {
      return result;
    }
    var thisp = arguments[2];
    for (var i = 1; i < array.length; i++) {
      result = callback.call(thisp, result, this.toDouble(array[i]));
    }
    return this.toDouble(result);
  },

  /**
   * 把字符串转化为double数值类型 s {number | string} 要格式化的字符串或数值
   */
  toDouble: function(s) {
    s = s || 0;
    if (typeof s == 'string') {
      s = s.replace(/\$|,/g, '');
      s = parseFloat(s);
    }
    if (typeof s != 'number') {
      return 0;
    }
    s = Math.floor(s * this._MAX_POW + 0.50000000001);
    s = s / this._MAX_POW;
    return isFinite(s) ? s : 0;
  },

  /**
   * 格式化为金钱格式 num {number | string} 要格式化的字符串或数值 n {number: 2 | 4}
   * 金钱小数点后的位数,现只有2位或4位
   */
  formatCurrency: function(num, n) {
    if (n && n == 4) {
      return this._formatCurrency4(num);
    }

    return this._formatCurrency(num);
  },

  /**
   * 加法运算:把所传的参数格式化为数值后依次相加 可传参数为:{number | string}金钱字符串或数值
   */
  add: function() {
    return this.compute(arguments, function(a, b) {
      return a + b;
    });
  },

  /**
   * 减法运算:第一个参数为总数,从第二个参数开始都为被减数 可传参数为:{number | string}金钱字符串或数值
   */
  subtract: function() {
    return this.compute(arguments, function(a, b) {
      return a - b;
    });
  },

  /**
   * 乘法运算
   */
  multiply: function() {
    return this.compute(arguments, function(a, b) {
      return a * b;
    });
  },

  /**
   * 除法
   */
  divide: function() {
    return this.compute(arguments, function(a, b) {
      return (b == 0) ? 0 : a / b;
    });
  },

  /**
   * 求最大值
   */
  max: function() {
    return this.compute(arguments, function(a, b) {
      return (a > b) ? a : b;
    });
  },

  /**
   * 求最小值
   */
  min: function() {
    return this.compute(arguments, function(a, b) {
      return (a > b) ? b : a;
    });
  }
};
