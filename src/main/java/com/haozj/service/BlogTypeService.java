package com.haozj.service;

import java.util.List;
import java.util.Map;

import com.haozj.entity.BlogType;

public interface BlogTypeService {
    //���ݲ���������ʾ
    public List<BlogType> countList();
    
    //ͨ��id���Ҳ�������ʵ��
    public BlogType findById(Integer id);
    //�г���¼
    public List<BlogType> list(Map<String,Object> map);
    //�г��ܼ�¼��
    public long getTotal(Map<String,Object> map);
    //��Ӳ�������
    public Integer add(BlogType blogType);    
    //���²�������
    public Integer update(BlogType blogType);   
    //ɾ��������Ϣ
    public Integer delete(Integer id);
}
