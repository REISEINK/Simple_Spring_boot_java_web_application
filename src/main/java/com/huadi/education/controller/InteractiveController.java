package com.huadi.education.controller;

import com.huadi.education.service.InteractiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@Controller
public class InteractiveController {

    @Autowired
    InteractiveService interactiveService;

    @PostMapping("/submitComment")
    @ResponseBody
    public Map submitComment(Integer comID, Integer comType, String comments,HttpSession session){
        Map<String, Integer> map = new HashMap<>();

        String username = (String) session.getAttribute("loginUser");
        Integer userid = (Integer) session.getAttribute("userID");
//        System.out.println(username);
//        System.out.println(userid);
//
//        System.out.println(comID);
//        System.out.println(comType);
//        System.out.println(comments);

        if(username ==null) {
            String username_temp = "Unkonwn";
            if(interactiveService.addComment(null,username_temp,comType,comID,comments) == 1) {
                map.put("msg", 1);
            }else{
                map.put("msg", 0);
            }
        }
        else{
            if(interactiveService.addComment(userid,username,comType,comID,comments) == 1) {
                map.put("msg", 1);
            }else{
                map.put("msg", 0);
            }
        }

        return map;
    }

    @PostMapping("/submitScore")
    @ResponseBody
    public Map submitScore(Integer cozID, Integer score){
        Map<String, Integer> map = new HashMap<>();
//        System.out.println(cozID);
//        System.out.println(score);
        map.put("msg", 1);
        return map;
    }
}
