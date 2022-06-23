package com.huadi.education.controller;

import com.huadi.education.entity.Course;
import com.huadi.education.entity.ModifyApply;
import com.huadi.education.entity.Org;
import com.huadi.education.entity.Teacher;
import com.huadi.education.service.OrgAdmService;
import com.huadi.education.service.OrgService;
import com.huadi.education.service.UserService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import java.util.List;


@Controller
public class OrgAdmController {

    @Autowired
    private OrgAdmService orgAdmService;

    @Autowired
    private OrgService orgService;

    @Autowired
    private UserService userService;

    private Integer orgId;
    private Integer userId;
    private String userName;

    //通过机构id获取课程列表
    @GetMapping("/getCourse")
    public String getCourseByOrgId(Integer id,Model model,HttpSession session){

        if(session.getAttribute("userID") == null){
            return "login";
        }else {
            this.userId = (Integer) session.getAttribute("userID");
            this.orgId = userService.findOrgUserById(userId).getOrg().getOrgID();
            this.userName = userService.findOrgUserById(userId).getName();
        }

        model.addAttribute("courseItem",orgAdmService.findOrg_courseByOrgId(orgId));
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        return "course";
    }

    //通过机构id获取教师列表
    @GetMapping("/getLecturer")
    public String getLecturerByOrgId(Integer id,Model model){
        if(this.userId == null){
            return "login";
        }
        model.addAttribute("lecturerItem",orgAdmService.findOrg_LecturerByOrgId(orgId));
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        return "lecturer";
    }

    //跳转到添加课程
    @GetMapping("/toAddCourse")
    public String toAddCourse(Model model){
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        return "add_course";
    }

    //添加课程
    @PostMapping("/addCourse")
    public String addCourse(Course course,Model model){
        float score = 0;
        course.setOrgid(orgId);
        course.setCozscore(score);
        orgAdmService.insertOrg_course(course);
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        return "redirect:/getCourse";
    }

    //跳转到编辑课程
    @GetMapping("/toModifyCourse/{id}")
    public String toModifyCourse(@PathVariable("id") Integer id,Model model){
        model.addAttribute("courseItem",orgAdmService.findOrg_courseById(id));
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        return  "modify_course";
    }

    //编辑课程
    @PutMapping("/modifyCourse")
    public String modifyCourse(Course course,Model model){
        course.setOrgid(orgId);
        orgAdmService.updateOrg_course(course);
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        return "redirect:/getCourse";
    }

    //搜索课程
    @PostMapping("/searchCourse")
    public String searchCourse(Course course, Model model){
        String search = course.getCozname();
        List<Course> course_searches = orgAdmService.findOrg_courseByOrgIdOrder(orgId,search,"cozid",true);
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        model.addAttribute("courseItem", course_searches);
        model.addAttribute("search",search);
        return "course";
    }

    //删除课程
    @DeleteMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable("id") Integer id){
        orgAdmService.delectOrg_course(id);
        return "redirect:/getCourse";
    }


    //跳转到添加教师
    @GetMapping("/toAddLecturer")
    public String toAddLecturer(Model model){
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        return "add_lecturer";
    }

    //添加教师
    @PostMapping("/addLecturer")
    public String addLecturer(Teacher teacher,Model model){
        teacher.setOrgid(orgId);
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        orgAdmService.insertOrg_Lecturer(teacher);
        return "redirect:/getLecturer";
    }

    //跳转到编辑教师
    @GetMapping("/toModifyLecturer/{id}")
    public String toModifyLecturer(@PathVariable("id") Integer id,Model model){
        model.addAttribute("lecturerItem",orgAdmService.findOrg_LecturerById(id));
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        return  "modify_lecturer";
    }

    //编辑教师
    @PutMapping("/modifyLecturer")
    public String modifyLecturer(Teacher teacher,Model model){
        teacher.setOrgid(orgId);
        orgAdmService.updateOrg_Lecturer(teacher);
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        return "redirect:/getLecturer";
    }

    //搜索课程
    @PostMapping("/searchLecturer")
    public String searchLecturer(Teacher teacher, Model model){
        String search = teacher.getTchname();
        System.out.println(search);
        List<Teacher> teacher_searches = orgAdmService.findOrg_LecturerByOrgIdOrder(orgId,search,"tchid",true);
        model.addAttribute("lecturerItem", teacher_searches);
        model.addAttribute("search",search);
        return "lecturer";
    }

    //删除教师
    @DeleteMapping("/deleteLecturer/{id}")
    public String deleteLecturer(@PathVariable("id") Integer id){
        orgAdmService.delectOrg_Lecturer(id);
        return "redirect:/getLecturer";
    }

    //获取当前机构基本信息
    @GetMapping("/getModifyApply")
    public String getModifyApply(Integer id,Model model){
        if(this.userId == null){
            return "login";
        }
        Org org = orgService.getOrgByID(orgId);
        model.addAttribute("_legal_person",org.getLegalPerson());
        model.addAttribute("_principal",org.getPrincipal());
        model.addAttribute("_public_tel",org.getPublicTel());
        model.addAttribute("_address",org.getAddress());
        model.addAttribute("_operation_info",org.getOperationInfo());
        model.addAttribute("orgName",orgService.getOrgByID(orgId).getOrgName());
        model.addAttribute("userName",this.userName);
        return "modify_apply";
    }

    //发送申请
    @PostMapping("/sendApply")
    public String sendApply(ModifyApply modifyApply){
        Org org = orgService.getOrgByID(orgId);
        if(modifyApply.getLegalPerson().equals("")){
            modifyApply.setLegalPerson(org.getLegalPerson());
        }
        if (modifyApply.getPrincipal().equals("")){
            modifyApply.setPrincipal(org.getPrincipal());
        }
        if(modifyApply.getPublicTel().equals("")){
            modifyApply.setPublicTel(org.getPublicTel());
        }
        if(modifyApply.getAddress().equals("")){
            modifyApply.setAddress(org.getAddress());
        }
        if(modifyApply.getOperationInfo().equals("")){
            modifyApply.setOperationInfo(org.getOperationInfo());
        }
        modifyApply.setOrg(org);
        orgAdmService.applyModifyApply(modifyApply);
        return "redirect:/getCurrentApply";
    }

    @GetMapping("/getCurrentApply")
    public String getCurrentApply(Model model){
        if(this.userId == null){
            return "login";
        }
       // Org org = orgService.findOrgById(orgId);
        Org org = orgService.getOrgByID(orgId);
        ModifyApply modifyApply = orgAdmService.findModifyApplyByOrgId(orgId);
        model.addAttribute("_legal_person", org.getLegalPerson());
        model.addAttribute("_principal",org.getPrincipal());
        model.addAttribute("_public_tel",org.getPublicTel());
        model.addAttribute("_address",org.getAddress());
        model.addAttribute("_operation_info",org.getOperationInfo());
        model.addAttribute("orgName",org.getOrgName());
        model.addAttribute("userName",this.userName);
        if (modifyApply==null){
            model.addAttribute("showApply",false);
        }else {
            model.addAttribute("showApply",true);
            model.addAttribute("modifyApplyItem",modifyApply);
        }
        return "current_apply";
    }
    
    @GetMapping("/logOut")
    public String logOut(HttpServletRequest request){
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        this.userId = null;
        this.userName = null;
        this.orgId = null;
        return "login";
    }
}

