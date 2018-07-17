package com.biminds.framework.mvc.tag;

import com.biminds.framework.util.TokenHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.tags.form.AbstractHtmlInputElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;


/**
 * 自动生成token的标签
 * 
 * @author 何珏 2014-7-11
 */
public class TokenTag extends AbstractHtmlInputElementTag {

	private static final long serialVersionUID = -8414663454721954498L;

	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.tags.form.AbstractFormTag#writeTagContent(org.springframework.web.servlet.tags.form.TagWriter)
	 */
	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		tagWriter.startTag("input");
		tagWriter.writeAttribute("type", "hidden");
		tagWriter.writeAttribute("name", TokenHelper.TOKEN_NAME_FIELD);
		if (StringUtils.isBlank(getName())) {
			tagWriter.writeAttribute("data-value", TokenHelper.DEFAULT_TOKEN_NAME);
			tagWriter.writeAttribute("value", TokenHelper.DEFAULT_TOKEN_NAME);
		} else {
			tagWriter.writeAttribute("data-value", getName());
			tagWriter.writeAttribute("value", getName());
		}
		tagWriter.endTag();

		tagWriter.startTag("input");
		tagWriter.writeAttribute("type", "hidden");
		if (StringUtils.isBlank(getName())) {
			tagWriter.writeAttribute("name", TokenHelper.DEFAULT_TOKEN_NAME);
			tagWriter.writeAttribute("value", TokenHelper.setToken());
		} else {
			tagWriter.writeAttribute("name", getName());
			tagWriter.writeAttribute("value", TokenHelper.setToken(getName()));
		}
		tagWriter.endTag();

		return SKIP_BODY;
	}

}
