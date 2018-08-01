package com.haozj.service;

import java.util.List;
import java.util.Map;

import com.haozj.entity.Comment;

/**
 * ����Service�ӿ�
 * @author haozj
 *
 */
public interface CommentService {

	/**
	 * ��ѯ�û�������Ϣ
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String,Object> map);
	
    //�������
    public int add(Comment comment);

    //��ȡ��������
    public Long getTotal(Map<String,Object> map);   
    //�������
    public int update(Comment comment);   
    //ɾ������
    public Integer delete(Integer id);
}
