package com.haozj.entity;

/**
 * ��ҳModel��
 * @author 
 *
 */
public class PageBean {

	private int page; // ��ǰҳ
	private int pageSize; // ÿҳ��¼��
	private int start;  // ÿҳ��һ����¼
	
	
	public PageBean(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return (page-1)*pageSize;
	}
	
	
}
