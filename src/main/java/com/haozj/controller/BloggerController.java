
package com.haozj.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.haozj.entity.Blogger;
import com.haozj.service.BloggerService;
import com.haozj.util.CryptographyUtil;

/**
 * ����Controller��
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

    @Resource
    private BloggerService bloggerService;
    
    @RequestMapping("/login")
    public String login(Blogger blogger,HttpServletRequest request){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(blogger.getUserName(), CryptographyUtil.md5(blogger.getPassword(), "haozj"));
        try{
            subject.login(token); // ��¼��֤       
            return "redirect:/admin/main.jsp";
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("blogger", blogger);
            request.setAttribute("errorInfo", "�û��������������");
            return "login";
        }
    }
    @RequestMapping("/aboutMe")
    public ModelAndView aboutMe(){
        ModelAndView mView = new ModelAndView();
        mView.addObject("mainPage", "foreground/blogger/info.jsp");
        mView.addObject("pageTitle", "���ڲ���");
        mView.setViewName("template");
        return mView;
    }
}
