
package com.biminds.interceptor;

import com.biminds.entity.RbUserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class LoginInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        RbUserEntity user = (RbUserEntity) request.getSession().getAttribute("UC");
        if (null == user) {
            String url = request.getScheme();
            String serverName = request.getServerName();
            int serverPort = request.getServerPort();
            String contextPath = request.getContextPath();
            String redirectUrl = "";
            if (serverPort == 80) {
                redirectUrl = url + "://" + serverName + "/" + contextPath;
            } else {
                redirectUrl = url + "://" + serverName + ":" + serverPort + "/" + contextPath;
            }
            response.sendRedirect(redirectUrl);
            return false;
        }
        return true;
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
