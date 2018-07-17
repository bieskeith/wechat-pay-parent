/**
 * $Id: PrintModel.java 5658 2015-10-16 05:18:45Z Jades_He $
 * Copyright 2014-2015 Beijing Runlin Automobile Technology Company Ltd. All rights reserved.
 */
package com.biminds.framework.mvc.model;

import com.biminds.framework.mvc.entity.BaseModel;

import java.util.List;

/**
 * 打印 Model
 * 
 * @author Jades.He
 * @date 2015年10月16日 上午10:17:42
 */
public class PrintModel extends BaseModel {

	private static final long serialVersionUID = 2660528698957587788L;

	/** 总页数 */
	private Long total;

	/** 打印页 */
	private List<String> pages;

	/**
	 * 默认构造
	 */
	public PrintModel() {
		super();
	}

	/**
	 * 构造方法
	 * 
	 * @param pages 打印页
	 * @param total 总数
	 */
	public PrintModel(List<String> pages, Long total) {
		this.pages = pages;
		this.total = total;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return the pages
	 */
	public List<String> getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(List<String> pages) {
		this.pages = pages;
	}

}
