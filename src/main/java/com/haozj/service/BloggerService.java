package com.haozj.service;

import com.haozj.entity.Blogger;

public interface BloggerService {
    public Blogger getByUserName(String userName);
    public Blogger find();
    //�޸Ĳ�����Ϣ
    public Integer update(Blogger blogger);
}
