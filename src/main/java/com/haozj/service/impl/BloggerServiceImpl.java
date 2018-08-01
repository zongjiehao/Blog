package com.haozj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haozj.dao.BloggerDao;
import com.haozj.entity.Blogger;
import com.haozj.service.BloggerService;
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{
    @Resource
    private BloggerDao bloggerDao;
    @Override
    public Blogger getByUserName(String userName) {
        return bloggerDao.getByUserName(userName);
    }
    @Override
    public Blogger find() {
        return bloggerDao.find();
    }
    @Override
    public Integer update(Blogger blogger) {
        return bloggerDao.update(blogger);
    }

}
