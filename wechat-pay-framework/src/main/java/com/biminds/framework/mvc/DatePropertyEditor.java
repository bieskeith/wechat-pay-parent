/**
 * $Id:$
 * Copyright 2009-2014 河北联智信息科技有限公司. All rights reserved.
 */
package com.biminds.framework.mvc;

import com.biminds.framework.date.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.util.Date;


/**
 * 自定义SpringMVC日期的转换器
 * 
 * @author 何珏 2014-7-13
 */
public class DatePropertyEditor extends PropertyEditorSupport {

	private static final Logger logger = LoggerFactory.getLogger(DatePropertyEditor.class);

	//	private static Date defaultDate = new Date(0L);

	public String getAsText() {
		return getValue() == null ? "" : DateTimeUtils.formatDateTime((Date) getValue());
	}

	@Override
	public void setAsText(String textValue) {
		if (textValue == null) {
			setValue(null);
			return;
		}
		Date retDate = DateTimeUtils.parseDateWithTry(textValue);
		if (retDate == null) {
			logger.error("Cannot parse {} as date.", textValue);
		} else {
			setValue(retDate);
		}
	}

}
