package com.haozj.dao;

import java.util.List;
import java.util.Map;

import com.haozj.entity.Link;
/**
 * 友情链接
 * @author haozj
 *
 */
public interface LinkDao {
    /**
     * 列出所有链接
     * @param map
     * @return
     */
    public List<Link> find(Map<String, Object> map);
    /**
     * 获取总记录数
     * @param map
     * @return
     */
    public Long getTotal(Map<String,Object> map);
    
    /**
     * 添加友情链接信息
     * @param blogType
     * @return
     */
    public Integer add(Link link);
    
    /**
     * 修改友情链接信息
     * @param blogType
     * @return
     */
    public Integer update(Link link);
    
    /**
     * 删除友情链接信息
     * @param id
     * @return
     */
    public Integer delete(Integer id);
}
