package com.haozj.service;

import com.haozj.entity.Blogger;

public interface BloggerService {
    public Blogger getByUserName(String userName);
    public Blogger find();
    //修改博主信息
    public Integer update(Blogger blogger);
}
