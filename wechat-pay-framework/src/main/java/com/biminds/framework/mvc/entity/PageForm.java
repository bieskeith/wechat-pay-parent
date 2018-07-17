package com.biminds.framework.mvc.entity;

/**
 * PageForm
 * 
 * @author Kevin Lv
 * @date 2015年5月22日 上午9:52:18
 * @since 1.0.0
 */
public class PageForm {

	/**
	 * 当前页
	 */
	private Integer page;

	/**
	 * 每页显示条数
	 */
	private Integer rows;

	/**
	 * 排序
	 */
	private Integer sort;

	private Integer pageIndex;

	private Integer pageSize;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
