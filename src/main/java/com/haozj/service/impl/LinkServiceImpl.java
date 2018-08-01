package com.haozj.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haozj.dao.LinkDao;
import com.haozj.entity.Link;
import com.haozj.service.LinkService;

@Service("linkService")
public class LinkServiceImpl implements LinkService{
    @Resource
    private LinkDao linkDao;
    @Override
    public List<Link> find(Map<String, Object> map) {
        return linkDao.find(map);
    }
    @Override
    public Long getTotal(Map<String, Object> map) {
        return linkDao.getTotal(map);
    }
    @Override
    public Integer add(Link link) {
        return linkDao.add(link);
    }
    @Override
    public Integer update(Link link) {
        return linkDao.update(link);
    }
    @Override
    public Integer delete(Integer id) {
        return linkDao.delete(id);
    }
}
