package com.haozj.dao;

import com.haozj.entity.Blogger;
import com.haozj.entity.Link;

public interface BloggerDao {
    //ͨ���û�����ѯ�û�
    public Blogger getByUserName(String userName);
    public Blogger find();
    //�޸Ĳ�����Ϣ
    public Integer update(Blogger blogger);
}
