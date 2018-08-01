package com.haozj.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.haozj.entity.Blog;
import com.haozj.entity.Comment;
import com.haozj.service.BlogService;
import com.haozj.service.CommentService;
import com.haozj.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * 评论controller层
 * 
 * @author haozj
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private BlogService blogService;
    
    
    //添加或者修改评论
    @RequestMapping("/save")
    public String save(Comment comment, @RequestParam("imageCode") String imageCode, HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws Exception {
        String sRand = (String) session.getAttribute("sRand");
        JSONObject result = new JSONObject();
        int resultTotal = 0;
        if (!imageCode.equals(sRand)) {
            result.put("success", false);
            result.put("errorInfo", "验证码错误");
        }
        else {
            String userIp = request.getRemoteAddr();
            comment.setUserIp(userIp);
            if (comment.getId() == null) {
                resultTotal = commentService.add(comment);
                //博客回复次数+1
                Blog blog = blogService.findById(comment.getBlog().getId());
                blog.setReplyHit(blog.getReplyHit()+1);
                blogService.update(blog);
            }
        }
        if (resultTotal  > 0) {
            result.put("success", true);
        }
        ResponseUtil.write(response, result);
        return null;
    }
}
