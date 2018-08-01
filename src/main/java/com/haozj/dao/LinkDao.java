package com.haozj.dao;

import java.util.List;
import java.util.Map;

import com.haozj.entity.Link;
/**
 * ��������
 * @author haozj
 *
 */
public interface LinkDao {
    /**
     * �г���������
     * @param map
     * @return
     */
    public List<Link> find(Map<String, Object> map);
    /**
     * ��ȡ�ܼ�¼��
     * @param map
     * @return
     */
    public Long getTotal(Map<String,Object> map);
    
    /**
     * �������������Ϣ
     * @param blogType
     * @return
     */
    public Integer add(Link link);
    
    /**
     * �޸�����������Ϣ
     * @param blogType
     * @return
     */
    public Integer update(Link link);
    
    /**
     * ɾ������������Ϣ
     * @param id
     * @return
     */
    public Integer delete(Integer id);
}
