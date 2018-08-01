package com.haozj.dao;

import java.util.List;
import java.util.Map;

import com.haozj.entity.Blog;

/**
 * ����Dao�ӿ�
 * @author haozj
 *
 */
public interface BlogDao {

	//�������ڷ�����²�ѯ
	public List<Blog> countList();
	
	//��ҳ��ѯ����
	public List<Blog> list(Map<String, Object> map);
	
	
	//�ܼ�¼����
	public int getTotal(Map<String, Object> map);
	
	//����id��ѯ����
	public Blog findById(Integer id);
	
	//���²���
	public Integer update(Blog blog);
	
	//��ȡ��һ������
	public Blog getLastBlog(Integer id);
	
	//��ȡ��һ������
	public Blog getNextBlog(Integer id);
	
	//��Ӳ���
	public Integer addBlog(Blog blog);
	
	//ɾ������
	public Integer deleteBlog(Integer id);
	//���ݲ��������ѯ����
    public Integer getBlogByTypeId(Integer typeId);
}
