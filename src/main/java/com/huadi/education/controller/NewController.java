package com.huadi.education.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huadi.education.entity.*;
import com.huadi.education.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class NewController {

    @Autowired
    private NewService newService;

    @GetMapping("/newslist")
    //通知公告展示
    public String showNewslist(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize,
                               @RequestParam(value = "title",required = false) String title,
                               Model model){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo newslistPageInfo = new PageInfo(newService.getNewsList(title));
        //System.out.println(newslistPageInfo);
        model.addAttribute("newslistPageInfo",newslistPageInfo);

        return "news-list";
    }

    @GetMapping("/policylist")
    //通知公告展示
    public String showPolicyslist(@RequestParam(defaultValue = "1") int pageNum,
                                  @RequestParam(defaultValue = "10") int pageSize,
                                  @RequestParam(value = "title",required = false) String title,
                                  Model model){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo policylistPageInfo = new PageInfo(newService.getPolicysList(title));
        //System.out.println(newslistPageInfo);
        model.addAttribute("policylistPageInfo",policylistPageInfo);

        return "policy-list";
    }

    @GetMapping("/newsDetail")
    public String showOrgDetail(@RequestParam("newsid") int newsid, Model model) {
        News news = newService.getOrgByID(newsid);

        model.addAttribute("newInfo", news);

        return "news-detail";

    }
}
