package com.biminds.framework.mvc.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * session拦截器
 *
 * @author Kevin Lv
 * @date 2015年9月22日 下午6:31:30
 * @since 1.0.0
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String[] noFilters = new String[]{"index", "login", "logout"};
        Boolean beFilter = true;
        String uri = request.getRequestURI();
        if (StringUtils.isNotBlank(uri)) {
            for (String s : noFilters) {
                if (uri.indexOf(s) != -1) {
                    beFilter = false;
                    break;
                }
            }
        }
        if (beFilter) {
            Object obj = request.getSession().getAttribute("UC");
            //未登录
            if (null == obj) {
                //如果是ajax请求响应头会有，x-requested-with
                if (request.getHeader("x-requested-with") != null
                        && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                    response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
                } else {
                    response.sendRedirect(request.getContextPath() + "/timeout");
                }
                return false;
            }
//			else{
            // 添加日志
//                String operateContent = Constants.operateContent(uri);  
//                if (null != operateContent) {  
//                    String url = uri.substring(uri.indexOf("background"));  
//                    String ip = request.getRemoteAddr();  
//                    Integer userId = ((SystemUserForm) obj).getId();  
//                    SystemLoggerForm form = new SystemLoggerForm();  
//                    form.setUserId(userId);  
//                    form.setIp(ip);  
//                    form.setOperateContent(operateContent);  
//                    form.setUrl(url);  
//                    this.systemLoggerService.edit(form);  
//                }  
//			}
        }
        return super.preHandle(request, response, handler);
    }

}
