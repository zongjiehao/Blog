package com.haozj.dao;
/**
 * ����dao�ӿ�
 * @author haozj
 *
 */

import java.util.List;
import java.util.Map;

import com.haozj.entity.Comment;


public interface CommentDao {
    
    public List<Comment> list(Map<String, Object> map);
    
    //�������
    public int add(Comment comment);
    //��ȡ��������
    public Long getTotal(Map<String,Object> map);   
    //�������
    public int update(Comment comment);   
    //ɾ������
    public Integer delete(Integer id);
}
