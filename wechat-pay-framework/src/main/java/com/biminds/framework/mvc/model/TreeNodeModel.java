package com.biminds.framework.mvc.model;

import java.io.Serializable;
import java.util.List;

/**
 * 通用树节点
 * 
 * @author Kevin Lv
 * @date 2015年5月22日 下午3:40:41
 * @since 1.0.0
 */
public class TreeNodeModel implements Serializable {

	private static final long serialVersionUID = -389842325584971993L;

	/** 结点ID */
	private String id;

	/** 结点名称 */
	private String text;

	/** 父节点id */
	private String pid;

	/** 子节点 */
	public List<TreeNodeModel> children;

	public List<TreeNodeModel> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeModel> children) {
		this.children = children;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

}
