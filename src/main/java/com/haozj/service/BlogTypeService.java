package com.haozj.service;

import java.util.List;
import java.util.Map;

import com.haozj.entity.BlogType;

public interface BlogTypeService {
    //根据博客种类显示
    public List<BlogType> countList();
    
    //通过id查找博客类型实体
    public BlogType findById(Integer id);
    //列出记录
    public List<BlogType> list(Map<String,Object> map);
    //列出总记录数
    public long getTotal(Map<String,Object> map);
    //添加博客种类
    public Integer add(BlogType blogType);    
    //更新博客种类
    public Integer update(BlogType blogType);   
    //删除博客信息
    public Integer delete(Integer id);
}
