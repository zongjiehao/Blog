package com.haozj.service;

import java.util.List;
import java.util.Map;

import com.haozj.entity.Blog;

public interface BlogService {
    
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
