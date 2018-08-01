package com.haozj.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haozj.dao.BlogTypeDao;
import com.haozj.entity.BlogType;
import com.haozj.service.BlogTypeService;
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{
    @Resource
    private BlogTypeDao blogTypeDao;
    @Override
    public List<BlogType> countList() {
        return blogTypeDao.countList();
    }
    @Override
    public BlogType findById(Integer id) {
        return blogTypeDao.findById(id);
    }
    @Override
    public List<BlogType> list(Map<String, Object> map) {
        return blogTypeDao.list(map);
    }
    @Override
    public long getTotal(Map<String, Object> map) {
        return blogTypeDao.getTotal(map);
    }
    @Override
    public Integer add(BlogType blogType) {
        return blogTypeDao.add(blogType);
    }
    @Override
    public Integer update(BlogType blogType) {
        return blogTypeDao.update(blogType);
    }
    @Override
    public Integer delete(Integer id) {
        return blogTypeDao.delete(id);
    }

}
