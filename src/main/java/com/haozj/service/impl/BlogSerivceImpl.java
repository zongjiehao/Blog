package com.haozj.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haozj.dao.BlogDao;
import com.haozj.entity.Blog;
import com.haozj.service.BlogService;

@Service("blogService")
public class BlogSerivceImpl implements BlogService{
    @Resource
    private BlogDao blogDao;

    @Override
    public List<Blog> countList() {
        return blogDao.countList(); 
    }

    @Override
    public List<Blog> list(Map<String, Object> map) {
        return blogDao.list(map);
    }

    @Override
    public int getTotal(Map<String, Object> map) {
        return blogDao.getTotal(map);
    }

    @Override
    public Blog findById(Integer id) {
        return blogDao.findById(id);
    }

    @Override
    public Integer update(Blog blog) {
        return blogDao.update(blog);
    }

    @Override
    public Blog getLastBlog(Integer id) {
        return blogDao.getLastBlog(id);
    }

    @Override
    public Blog getNextBlog(Integer id) {
        return blogDao.getNextBlog(id);
    }

    @Override
    public Integer addBlog(Blog blog) {
        return blogDao.addBlog(blog);
    }

    @Override
    public Integer deleteBlog(Integer id) {
        return blogDao.deleteBlog(id);
    }

    @Override
    public Integer getBlogByTypeId(Integer typeId) {
        return blogDao.getBlogByTypeId(typeId);
    }
}
