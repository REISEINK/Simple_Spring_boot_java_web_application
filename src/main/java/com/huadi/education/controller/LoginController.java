package com.huadi.education.controller;

import com.huadi.education.entity.User;
import com.huadi.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login() {
        return "login";
    }


    @ResponseBody
    @RequestMapping(value="/login_ht",method=RequestMethod.POST)
    public Map login(@RequestParam("tel") String tel,
                     @RequestParam("password") String password,
                     @RequestParam("access") Integer access,
                     HttpSession session) {
        Map<String,Integer> map = new HashMap<>();
        User user = new User();
        if(access==3){
            user = userService.getLoginUser(tel,access);
        }else {
            user = userService.getLoginUser(tel,access);
            if(user==null){
                user = userService.getLoginUser(tel,2);
            }
        }
        //用户名不存在
        if(user == null) {
            map.put("msg",1);
        }
        else {
            String password2 = user.getPasswd();
            if(! password2.equals(password)) {
                map.put("msg", 2);
            }else {
                map.put("msg", 0);
                map.put("accessLevel", user.getAccess());
                session.setAttribute("loginUser",user);
                session.setAttribute("accessLevel",user.getAccess());
                session.setAttribute("userID",user.getUserID());
            }

        }
        return map;
    }



}
