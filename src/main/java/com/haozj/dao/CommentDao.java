package com.haozj.dao;
/**
 * 评论dao接口
 * @author haozj
 *
 */

import java.util.List;
import java.util.Map;

import com.haozj.entity.Comment;


public interface CommentDao {
    
    public List<Comment> list(Map<String, Object> map);
    
    //添加评论
    public int add(Comment comment);
    //获取总评论数
    public Long getTotal(Map<String,Object> map);   
    //审核评论
    public int update(Comment comment);   
    //删除评论
    public Integer delete(Integer id);
}
