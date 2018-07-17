package com.biminds.framework.mvc.interceptor;


import com.biminds.framework.excepetion.TokenInvalidException;
import com.biminds.framework.mvc.WebContextHolder;
import com.biminds.framework.util.TokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * token验证拦截器
 *
 * @author 何珏 2014-8-14
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    /**
     * token验证拦截器，如果请求中无令牌信息则不进行验证。GET请求不进行拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // GET请求不进行拦截
        if ("get".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        Object tokenNameParam = WebContextHolder.getRequestParam(TokenHelper.TOKEN_NAME_FIELD);
        if (tokenNameParam == null && "post".equalsIgnoreCase(request.getMethod())) {
            logger.warn("POST请求无Token信息，可能会导致重复提交，请求地址为：{}", request.getRequestURI());
        }

        // 请求中无令牌信息则不进行验证
        if (tokenNameParam == null) {
            return true;
        }

        HttpSession session = request.getSession(true);
        synchronized (session) {
            if (!TokenHelper.validToken()) {
                logger.warn("表单重复提交或令牌过期");
                // BaseController中会对此异常进行处理
                throw new TokenInvalidException("表单重复提交或令牌过期！");
            }
        }

        return true;
    }

}
