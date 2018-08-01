package com.haozj.controller;
//所有和首页有关的请求

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.haozj.entity.Blog;
import com.haozj.entity.PageBean;
import com.haozj.service.BlogService;
import com.haozj.util.PageUtil;
import com.haozj.util.StringUtil;
import com.sun.jmx.snmp.SnmpStringFixed;

@Controller
@RequestMapping("/")
public class IndexController {
    @Resource
    private BlogService blogService;

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ModelAndView mView = new ModelAndView();
        int pageSize = 10;
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr);
        System.out.println("发布时间："+releaseDateStr);
        List<Blog> blogList = blogService.list(map);
        for (Blog blog : blogList) {
            List<String> imageList = blog.getImageList();
            String blogInfo = blog.getContent();
            Document document = Jsoup.parse(blogInfo);
            Elements elements = document.select("img[src~=.(png|jpe?g|jpg)]");
            for (int i = 0; i < elements.size(); i++) {
                Element jpg = elements.get(i);
                imageList.add(jpg.toString());
                if (i == 2) {
                    break;
                }
            }
        }
        StringBuffer param = new StringBuffer();
        if (StringUtil.isNotEmpty(typeId)) {
            param.append("typeId="+typeId);
        }
        if (StringUtil.isNotEmpty(releaseDateStr)) {
            param.append("releaseDateStr="+releaseDateStr);
        }
        mView.addObject("pageCode", PageUtil.genPagination(request.getContextPath() + "/index.html",
                blogService.getTotal(map), Integer.parseInt(page), pageSize, param.toString()));
        mView.addObject("blogList", blogList);
        mView.addObject("pageTitle", "haozj个人博客系统");
        mView.addObject("mainPage", "foreground/main/list.jsp");
        mView.setViewName("template");
        return mView;

    }
    
    @RequestMapping("/download")
    public ModelAndView aboutMe(){
        ModelAndView mView = new ModelAndView();
        mView.addObject("mainPage", "foreground/main/download.jsp");
        mView.addObject("pageTitle", "本站源码");
        mView.setViewName("template");
        return mView;
    }

}
