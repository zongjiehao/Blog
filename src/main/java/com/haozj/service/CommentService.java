package com.haozj.service;

import java.util.List;
import java.util.Map;

import com.haozj.entity.Comment;

/**
 * 评论Service接口
 * @author haozj
 *
 */
public interface CommentService {

	/**
	 * 查询用户评论信息
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String,Object> map);
	
    //添加评论
    public int add(Comment comment);

    //获取总评论数
    public Long getTotal(Map<String,Object> map);   
    //审核评论
    public int update(Comment comment);   
    //删除评论
    public Integer delete(Integer id);
}
