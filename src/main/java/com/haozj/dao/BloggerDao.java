package com.haozj.dao;

import com.haozj.entity.Blogger;
import com.haozj.entity.Link;

public interface BloggerDao {
    //通过用户名查询用户
    public Blogger getByUserName(String userName);
    public Blogger find();
    //修改博主信息
    public Integer update(Blogger blogger);
}
