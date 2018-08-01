package com.haozj.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.haozj.entity.Blog;
import com.haozj.entity.PageBean;
import com.haozj.lucene.BlogIndex;
import com.haozj.service.BlogService;
import com.haozj.util.ResponseUtil;
import com.haozj.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 管理员博客controller
 * 
 * @author haozj
 *
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {
    @Resource
    private BlogService blogService;
    private BlogIndex blogIndex = new BlogIndex();

    // 添加或者修改博客信息
    @RequestMapping("/save")
    public String save(Blog blog, HttpServletResponse response) throws Exception {
        int total = 0;
        if (blog.getId() == null) {
            total = blogService.addBlog(blog);
            blogIndex.addIndex(blog);
        } else {
            total = blogService.update(blog);
            blogIndex.updateIndex(blog);
        }
        JSONObject result = new JSONObject();
        if (total > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }
    //分页查询博客信息
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows, Blog blog, HttpServletResponse response) {
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map  = new HashMap<String,Object>();
        map.put("title", StringUtil.formatLike(blog.getTitle()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Blog> blogList =  blogService.list(map);
        long total = blogService.getTotal(map);
        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(blogList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        try {
            ResponseUtil.write(response, result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids", required = false) String ids,HttpServletResponse response) throws Exception{
        String [] id = ids.split(",");
        for (int i = 0; i < id.length; i++) {
            blogService.deleteBlog(Integer.parseInt(id[i]));
            blogIndex.deleteIndex(id[i]);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("success", true);
            ResponseUtil.write(response, jsonObject);
        }
        return null;
    }
    //通过id查找对应记录显示在页面上用于更改
    @RequestMapping("/findById")
    public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
        Blog blog=blogService.findById(Integer.parseInt(id));
        JSONObject result=JSONObject.fromObject(blog);
        ResponseUtil.write(response, result);
        return null;
    }
}
