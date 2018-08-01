package com.haozj.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.haozj.entity.Blog;
import com.haozj.lucene.BlogIndex;
import com.haozj.service.BlogService;
import com.haozj.service.CommentService;
import com.haozj.service.impl.BloggerServiceImpl;
import com.haozj.util.ResponseUtil;
import com.haozj.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 博客controller层
 * 
 * @author haozj
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService blogService;
    @Resource
    private CommentService commentService;

    private BlogIndex blogIndex = new BlogIndex();

    // 博客详情
    @RequestMapping("/articles/{id}")
    public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception {
        ModelAndView mView = new ModelAndView();
        Blog blog = blogService.findById(id);
        String keywords = blog.getKeyWord();
        if (StringUtil.isNotEmpty(keywords)) {
            String[] keyword = keywords.split(" ");
            mView.addObject("keyword", StringUtil.filterWhite(Arrays.asList(keyword)));
        } else {
            mView.addObject("keyword", null);
        }
        mView.addObject("blog", blog);
        blog.setClickHit(blog.getClickHit() + 1);
        blogService.update(blog);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("blogId", blog.getId());
        map.put("state", "1");
        mView.addObject("commentList", commentService.list(map));
        mView.addObject("pageTitle", blog.getTitle());
        mView.addObject("pageCode", this.getLastAndNextBlog(blogService.getLastBlog(id), blogService.getNextBlog(id),
                request.getServletContext().getContextPath()));
        mView.addObject("mainPage", "foreground/main/view.jsp");
        mView.setViewName("template");
        return mView;
    }

    // 博客上一篇下一篇
    private String getLastAndNextBlog(Blog lastBlog, Blog nextBlog, String pageContext) {
        StringBuffer pageCode = new StringBuffer();
        if (lastBlog == null || lastBlog.getId() == null) {
            pageCode.append("<p>上一篇：没有了</p>");
        } else {
            pageCode.append("<p>上一篇：<a href='" + pageContext + "/blog/articles/" + lastBlog.getId() + ".html'>"
                    + lastBlog.getTitle() + "</a></p>");
        }
        if (nextBlog == null || nextBlog.getId() == null) {
            pageCode.append("<p>下一篇：没有了</p>");
        } else {
            pageCode.append("<p>下一篇：<a href='" + pageContext + "/blog/articles/" + nextBlog.getId() + ".html'>"
                    + nextBlog.getTitle() + "</a></p>");
        }

        return pageCode.toString();
    }

    // 根据关键字查询博客信息
    @RequestMapping("/q")
    public ModelAndView search(@RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "page", required = false) String page,HttpServletRequest request) throws Exception {
        int pageSize = 2;
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        ModelAndView mView = new ModelAndView();
        List<Blog> blogList = blogIndex.searchBlog(q);
        mView.addObject("pageTitle", "搜索'" + q + "'的结果_个人博客系统");
        int toIndex = blogList.size() >= Integer.parseInt(page) * pageSize ? Integer.parseInt(page) * pageSize
                : blogList.size();
        mView.addObject("blogList", blogList.subList((Integer.parseInt(page) - 1) * pageSize, toIndex));
        mView.addObject("q", q);
        mView.addObject("pageCode",this.getnUpAndDownPageCode(Integer.parseInt(page), blogList.size(), q, pageSize, request.getServletContext().getContextPath()) );
        mView.addObject("resultTotal", blogList.size());
        mView.addObject("mainPage", "foreground/main/result.jsp");
        mView.setViewName("template");
        return mView;
    }
    //搜索分页
    @SuppressWarnings("unused")
    private String getnUpAndDownPageCode(Integer page, Integer totalNum, String q, Integer pageSize,
            String projectContext) {
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        StringBuffer pageCode = new StringBuffer();
        if (totalPage == 0) {
            return "";
        } else {
            pageCode.append("<nav>");
            pageCode.append("<ul class='pager'>");
            if (page > 1) {
                pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page - 1) + "&q=" + q
                        + "'>上一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }
            if (page < totalPage) {
                pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page + 1) + "&q=" + q
                        + "'>下一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            }
            pageCode.append("</ul>");
            pageCode.append("</nav>");
        }
        return pageCode.toString();
    } 

}
