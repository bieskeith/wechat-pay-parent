/**
 * $Id:$
 * Copyright 2009-2014 河北联智信息科技有限公司. All rights reserved.
 */
package com.biminds.framework.mvc;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Web环境的调用类
 * 
 * @author 何珏 2014-6-15
 */
public final class WebContextHolder {

	/**
	 * 获得系统绝对路径 如：F:\webapps\CmsSys
	 * 
	 * @param path 可以传入空串
	 */
	public static String getContextRealPath(String path) {
		return getRequest().getSession().getServletContext().getRealPath(path);
	}
	
	/**
	 * 获取servlet context
	 * 
	 * @return
	 * @author Kevin Lv
	 * @date 2015年5月18日 下午2:18:46
	 */
	public static ServletContext getServletContext(){
		return getRequest().getSession().getServletContext();
	}

	/**
	 * 获得应用绝对根路径
	 */
	public static String getContextRoot() {
		return getContextRealPath("/");
	}

	/**
	 * 获得系统根路径 如：/CmsSys
	 */
	public static String getContextPath() {
		return getRequest().getContextPath();
	}

	/**
	 * 获得应用端口号
	 */
	public static int getServerPort() {
		return getRequest().getServerPort();
	}

	/**
	 * 注销
	 */
	public static void logout() {
		HttpSession session = getRequest().getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

	/**
	 * 获取request
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 获取session
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 从Request的Attribute中获取值
	 */
	public static Object getRequestAttr(String key) {
		return getRequest().getAttribute(key);
	}

	/**
	 * 从Request的Parameter中获取值
	 */
	public static Object getRequestParam(String key) {
		return getRequest().getParameter(key);
	}

	/**
	 * 向Request的Attribute中赋值
	 */
	public static void setRequestAttr(String key, Object value) {
		getRequest().setAttribute(key, value);
	}

	/**
	 * 从Session的Attribute中获取值
	 */
	public static Object getSessionAttr(String key) {
		HttpSession session = getRequest().getSession(false);
		if (session == null) {
			return null;
		} else {
			return session.getAttribute(key);
		}
	}

	/**
	 * 向Session的Attribute中赋值
	 */
	public static void setSessionAttr(String key, Object value) {
		HttpSession session = getRequest().getSession();
		session.setAttribute(key, value);
	}

	/**
	 * 判断Session中是否有此值
	 */
	public static boolean isSetSessionAttr(String key) {
		return getSessionAttr(key) != null;
	}

	/**
	 * 移除Session中的属性
	 */
	public static void removeSessionAttr(String key) {
		HttpSession session = getRequest().getSession();
		session.removeAttribute(key);
	}

	/**
	 * 从Application中获得值
	 */
	public static Object getApplicationAttr(String key) {
		return getRequest().getSession().getServletContext().getAttribute(key);
	}

	/**
	 * 向Application中赋值
	 */
	public static void setApplicationAttr(String key, Object value) {
		getRequest().getSession().getServletContext().setAttribute(key, value);
	}

	/**
	 * 获得SessionId
	 */
	public static String getSessionId(boolean isCreate) {
		HttpSession session = getRequest().getSession(isCreate);
		if (session == null) {
			return null;
		} else {
			return session.getId();
		}
	}

	/**
	 * 获得访问者IP
	 */
	public static String getRemoteIp() {
		return getRequest().getRemoteAddr();
	}

	/**
	 * 获得访问者端口号
	 */
	public static int getRemotePort() {
		return getRequest().getRemotePort();
	}

	/**
	 * 获得访问者URL
	 */
	public static String getRequestURL() {
		return getRequest().getRequestURL().toString();
	}

	/**
	 * 获得访问者浏览器
	 */
	public static String getRequestBrowser() {
		String userAgent = getUserAgent();
		String[] agents = userAgent.split(";");
		if (agents.length > 1) {
			return agents[1].trim();
		} else {
			return null;
		}
	}

	/**
	 * 获得访问者操作系统
	 */
	public static String getRequestOs() {
		String userAgent = getUserAgent();
		String[] agents = userAgent.split(";");
		if (agents.length > 2) {
			return agents[2].trim();
		} else {
			return null;
		}
	}

	/**
	 * 获得访问者的代理全部信息
	 */
	public static String getUserAgent() {
		String userAgent = getRequest().getHeader("user-agent");
		return userAgent;
	}

	/**
	 * 获取cookie
	 */
	public static Cookie getCookie(String name) {
		Cookie[] cookies = getRequest().getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(name)) {
					return c;
				}
			}
		}
		return null;
	}

	/**
	 * 是否是post请求
	 */
	public static boolean isMethodPost() {
		String method = getRequest().getMethod();
		if ("post".equalsIgnoreCase(method)) {
			return true;
		} else {
			return false;
		}
	}
	
	//	/**
	//	 * 获取response
	//	 */
	//	public static HttpServletResponse getResponse() {
	//		return response;
	//	}
	//	/**
	//	 * 添加cookie
	//	 */
	//	public static void addCookie(Cookie cookie) {
	//		response.addCookie(cookie);
	//	}
	//	/**
	//	 * 重定向
	//	 * 
	//	 * @param path 重定向的路径，如/Login
	//	 * @throws IOException
	//	 */
	//	public static void sendRedirect(String path) throws IOException {
	//		response.sendRedirect(getContextPath() + path);
	//	}

}
