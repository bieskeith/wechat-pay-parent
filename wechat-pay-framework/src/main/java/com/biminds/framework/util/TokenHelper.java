package com.biminds.framework.util;

import com.biminds.framework.mvc.WebContextHolder;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;

/**
 * TokenHelper
 * 
 * @author 何珏 2014-7-31
 */
public class TokenHelper {

	/**
	 * The default namespace for storing token session values
	 */
	public static final String TOKEN_NAMESPACE = "springmvc.tokens";

	/**
	 * The default name to map the token value
	 */
	public static final String DEFAULT_TOKEN_NAME = "token";

	/**
	 * The name of the field which will hold the token name
	 */
	public static final String TOKEN_NAME_FIELD = "springmvc.token.name";

	private static final Logger LOG = LoggerFactory.getLogger(TokenHelper.class);

	private static final Random RANDOM = new Random();

	/**
	 * 最大允许的token数量
	 */
	private static final int MAX_TOKEN_COUNT = 20;

	/**
	 * Sets a transaction token into the session using the default token name.
	 * 
	 * @return the token string
	 */
	public static String setToken() {
		return setToken(DEFAULT_TOKEN_NAME);
	}

	/**
	 * Sets a transaction token into the session based on the provided token
	 * name.
	 * 
	 * @param tokenName the token name based on which a generated token value is
	 *        stored into session; for actual session store, this name will be
	 *        prefixed by a namespace.
	 * 
	 * @return the token string
	 */
	public static String setToken(String tokenName) {
		String token = generateGUID();
		setSessionToken(tokenName, token);
		return token;
	}

	/**
	 * Put a given named token into the session map. The token will be stored
	 * with a namespace prefix prepended.
	 * 
	 * @param tokenName the token name based on which given token value is
	 *        stored into session; for actual session store, this name will be
	 *        prefixed by a namespace.
	 * @param token the token value to store
	 */
	public static void setSessionToken(String tokenName, String token) {
		String sessionTokenName = buildTokenSessionAttributeName(tokenName);

		@SuppressWarnings("unchecked")
		LinkedList<String> tokenList = (LinkedList<String>) WebContextHolder
				.getSessionAttr(sessionTokenName);

		try {
			if (tokenList == null) {
				tokenList = new LinkedList<String>();
				WebContextHolder.setSessionAttr(sessionTokenName, tokenList);
			}

			// 如果token已存在则不添加
			if (tokenList.contains(token)) {
				return;
			}

			// 添加token并确保不超过最大数量
			if (tokenList.size() >= MAX_TOKEN_COUNT) {
				tokenList.remove();
			}

			LOG.debug("set token. {}:{}", sessionTokenName, token);
			tokenList.add(token);
		} catch (IllegalStateException e) {
			// WW-1182 explain to user what the problem is
			String msg = "Error creating HttpSession due response is commited to client. You can use the CreateSessionInterceptor or create the HttpSession from your action before the result is rendered to the client: "
					+ e.getMessage();
			LOG.error(msg, e);
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * Build a name-spaced token session attribute name based on the given token
	 * name.
	 * 
	 * @param tokenName the token name to prefix
	 * 
	 * @return the name space prefixed session token name
	 */
	public static String buildTokenSessionAttributeName(String tokenName) {
		return TOKEN_NAMESPACE + "." + tokenName;
	}

	/**
	 * Gets a transaction token from the params in the ServletActionContext
	 * using the default token name.
	 * 
	 * @return token
	 */
	public static String getToken() {
		return getToken(DEFAULT_TOKEN_NAME);
	}

	/**
	 * Gets the Token value from the params in the ServletActionContext using
	 * the given name
	 * 
	 * @param tokenName the name of the parameter which holds the token value
	 * @return the token String or null, if the token could not be found
	 */
	public static String getToken(String tokenName) {
		if (tokenName == null) {
			return null;
		}

		Object tokenParam = WebContextHolder.getRequestParam(tokenName);
		String token = tokenParam == null ? null : tokenParam.toString();

		if (StringUtils.isEmpty(token)) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Could not find token mapped to token name " + tokenName);
			}

			return null;
		}

		return token;
	}

	/**
	 * Gets the token name from the Parameters in the ServletActionContext
	 * 
	 * @return the token name found in the params, or null if it could not be
	 *         found
	 */
	public static String getTokenName() {
		Object tokenNameParam = WebContextHolder.getRequestParam(TOKEN_NAME_FIELD);
		if (tokenNameParam == null) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Could not find token name in params.");
			}

			return null;
		}

		String tokenName = tokenNameParam.toString();

		if (StringUtils.isEmpty(tokenName)) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Got a null or empty token name.");
			}

			return null;
		}

		return tokenName;
	}

	/**
	 * Checks for a valid transaction token in the current request params. If a
	 * valid token is found, it is removed so the it is not valid again.
	 * 
	 * @return false if there was no token set into the params (check by looking
	 *         for {@link #TOKEN_NAME_FIELD}), true if a valid token is found
	 */
	public static boolean validToken() {
		String tokenName = getTokenName();

		if (tokenName == null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("no token name found -> Invalid token ");
			}
			return false;
		}

		String token = getToken(tokenName);

		if (token == null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("no token found for token name " + tokenName + " -> Invalid token ");
			}
			return false;
		}

		String tokenSessionName = buildTokenSessionAttributeName(tokenName);

		@SuppressWarnings("rawtypes")
		LinkedList sessionTokens = (LinkedList) WebContextHolder.getSessionAttr(tokenSessionName);

		if (sessionTokens == null || !sessionTokens.contains(token)) {
			if (LOG.isWarnEnabled()) {
				LOG.warn("Form token {} does not match the session token.", token);
			}

			return false;
		}

		// remove the token so it won't be used again
		sessionTokens.remove(token);

		return true;
	}

	/**
	 * 创建一个随机的token
	 * 
	 * @return token
	 */
	public static String generateGUID() {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}

}
