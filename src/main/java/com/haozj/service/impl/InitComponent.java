package com.haozj.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.haozj.entity.Blog;
import com.haozj.entity.BlogType;
import com.haozj.entity.Blogger;
import com.haozj.entity.Link;
import com.haozj.service.BlogService;
import com.haozj.service.BlogTypeService;
import com.haozj.service.BloggerService;
import com.haozj.service.LinkService;


@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware{

    
    private static ApplicationContext applicationContext;
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService");
        Blogger blogger = bloggerService.find();
        blogger.setPassword(null);
        application.setAttribute("blogger", blogger);
        
        LinkService linkService = (LinkService) applicationContext.getBean("linkService");
        //查询友情链接
        List<Link> linkList = linkService.find(null);
        application.setAttribute("linkList", linkList);
        
        BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
        //根据博客种类显示各个种类数量
        List<BlogType> blogTypeCountList = blogTypeService.countList();
        application.setAttribute("blogTypeCountList", blogTypeCountList);
        
        BlogService blogService = (BlogService) applicationContext.getBean("blogService");
        //根据时间显示博客数量
        List<Blog> blogCountList = blogService.countList();
        application.setAttribute("blogCountList", blogCountList);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
        
    }

}
