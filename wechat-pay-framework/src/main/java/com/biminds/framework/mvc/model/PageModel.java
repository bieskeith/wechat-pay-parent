/**
 * $Id: PageModel.java 4935 2015-09-28 07:32:31Z Jades_He $
 * Copyright 2014-2015 Beijing Runlin Automobile Technology Company Ltd. All rights reserved.
 */
package com.biminds.framework.mvc.model;

import com.biminds.framework.mvc.entity.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * 分页Model
 * 
 * @author 何珏 2014-11-5
 */
public class PageModel<T extends Serializable> extends BaseModel {

	private static final long serialVersionUID = 835146549449848276L;

	private Long total;

	private List<T> rows;

	/**
	 * 默认构造
	 */
	public PageModel() {
		super();
	}

	/**
	 * 构造方法
	 * 
	 * @param rows 记录集
	 * @param total 总数
	 */
	public PageModel(List<T> rows, Long total) {
		this.rows = rows;
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
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
