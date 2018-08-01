package com.haozj.dao;

import java.util.List;
import java.util.Map;

import com.haozj.entity.Blog;

/**
 * 博客Dao接口
 * @author haozj
 *
 */
public interface BlogDao {

	//根据日期分组分月查询
	public List<Blog> countList();
	
	//分页查询博客
	public List<Blog> list(Map<String, Object> map);
	
	
	//总记录条数
	public int getTotal(Map<String, Object> map);
	
	//根据id查询博客
	public Blog findById(Integer id);
	
	//更新博客
	public Integer update(Blog blog);
	
	//获取上一个博客
	public Blog getLastBlog(Integer id);
	
	//获取下一个博客
	public Blog getNextBlog(Integer id);
	
	//添加博客
	public Integer addBlog(Blog blog);
	
	//删除博客
	public Integer deleteBlog(Integer id);
	//根据博客种类查询数量
    public Integer getBlogByTypeId(Integer typeId);
}
