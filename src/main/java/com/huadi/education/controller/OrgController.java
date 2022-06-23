package com.huadi.education.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huadi.education.entity.*;
import com.huadi.education.service.OrgService;
//import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;


@Controller
public class OrgController {

    @Autowired
    private OrgService orgService;

    //组织机构（民办学校）黑名单
    @GetMapping("/blocklists")
    public String showBlocklist(@RequestParam(value = "licenseKey", required = false) String licensekey,
                                @RequestParam(value = "orgName", required = false) String orgname,
                                @RequestParam(value = "orgType", required = false) String orgtype,
                                @RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "6") int pageSize,
                                Model model) {
        PageHelper.startPage(pageNum, pageSize);
//        PageInfo blocklistPageInfo = new PageInfo(orgService.getBlocklist());
//        System.out.println(orgService.getBlocklistByQuery(orgname,licensekey,orgtype));

        PageInfo blocklistPageInfo = new PageInfo(orgService.getBlocklistByQuery(orgname, licensekey, orgtype));
        System.out.println(blocklistPageInfo);
        model.addAttribute("blocklistPageInfo", blocklistPageInfo);
        return "expose-black";
    }

    //组织机构白名单
    @GetMapping("/whitelists")
    public String showWhitelist(@RequestParam(value = "licenseKey", required = false) String licensekey,
                                @RequestParam(value = "orgName", required = false) String orgname,
                                @RequestParam(value = "orgType", required = false) String orgtype,
                                @RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "6") int pageSize,
                                Model model) {
        PageHelper.startPage(pageNum, pageSize);

        PageInfo whitelistPageInfo = new PageInfo(orgService.getWhitelistByQuery(orgname, licensekey, orgtype));

        model.addAttribute("whitelistPageInfo", whitelistPageInfo);
        return "expose-white";
    }

    @GetMapping("/orgDetail")
    public String showOrgDetail(@RequestParam("orgid") int orgid, Model model) {
        Org org = orgService.getOrgByID(orgid);
        List<Course> course = orgService.getOrgCourseInfo(orgid);
        List<Teacher> teacher = orgService.getOrgTeacherInfo(orgid);
        List<Comment> comment1 = orgService.getOrgComment(orgid);
        List<Comment> comment = new LinkedList<>();
        List<Course> courseR = new LinkedList<>();
        for(int i=0;i<course.size();i++)
        {
            Course course_temp = orgService.getBestCourseByType(course.get(i).getCoztype());
            Org org_temp = orgService.getOrgByID(course.get(i).getOrgid());
            if(course !=null ){
                courseR.add(course_temp);
                courseR.get(i).setTeacher(org.getOrgName());
            }
        }

        for(int i=0;i<courseR.size()-1;i++)
        {
            for(int j = courseR.size()-1; j >i ;j --) {
                if(courseR.get(j).getCozname().equals(courseR.get(i).getCozname()))
                    courseR.remove(j);
            }
        }


        if (comment1 != null) {
            for (int i = 0; i < comment1.size(); i++) {
                comment.add(comment1.get(i));
                List<Comment> comment2 = orgService.getCommentComment(comment1.get(i).getComID());
                if (comment2 != null) {
                    for (int j = 0; j < comment2.size(); j++) {
                        comment.add(comment2.get(j));
                    }
                }
            }
        }

        model.addAttribute("orgInfo", org);
        model.addAttribute("orgCourseInfo", course);
        model.addAttribute("orgTeacherInfo", teacher);
        model.addAttribute("orgComments", comment);
        model.addAttribute("orgCourseRecommendation", courseR);
        return "org-details";

    }


    @RequestMapping("/searchlist")
    public String showMaplist(@RequestParam(value = "licenseKey", required = false) String licensekey,
                              @RequestParam(value = "orgName", required = false) String orgname,
                              @RequestParam(value = "orgType", required = false) String orgtype,
                              @RequestParam(value = "operationType", required = false) Integer operationtype,
                              @RequestParam(value = "address", required = false) String address,
                              Model model) {
        List<Org> maplist = orgService.getMaplist(orgname,address,licensekey,orgtype,operationtype);

        model.addAttribute("maplist", maplist);

        return "search-list";
    }

    @RequestMapping("/map")
    public String toMap(Model model){

        List<Org> maplist = orgService.getMaplist(null,null,null,null,null);

        List<Integer> id = new ArrayList<Integer>();
        List<String> name = new ArrayList<String>();
        List<String> address = new ArrayList<String>();
        List<String> orgType = new ArrayList<String>();
        List<Integer> operationType = new ArrayList<Integer>();
        List<String> point = new ArrayList<String>();
        List<Integer> isOpen = new ArrayList<Integer>();

        for(int i=0;i<maplist.size();i++) {
            id.add(maplist.get(i).getOrgID());
            name.add(maplist.get(i).getOrgName());
            address.add(maplist.get(i).getAddress());
            orgType.add(maplist.get(i).getOrgType());
            operationType.add(maplist.get(i).getOperationType());
            isOpen.add(0);
            Address address_temp = orgService.getAddressByOrgID(maplist.get(i).getOrgID());

            String point_temp = address_temp.getLongitude().setScale(6, BigDecimal.ROUND_HALF_UP).toString() + "|"
                                + address_temp.getLatitude().setScale(6, BigDecimal.ROUND_HALF_UP).toString();
            point.add(point_temp);
        }

        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("address",address);
        model.addAttribute("orgType",orgType);
        model.addAttribute("operationType",operationType);
        model.addAttribute("point",point);
        model.addAttribute("isOpen",isOpen);

        return "baidu-map";
    }

    @RequestMapping("/mapShowById")
    public String mapShowById(Model model, Integer id){

        List<Org> maplist = orgService.getMaplist(null,null,null,null,null);

        List<Integer> ids = new ArrayList<Integer>();
        List<String> name = new ArrayList<String>();
        List<String> address = new ArrayList<String>();
        List<String> orgType = new ArrayList<String>();
        List<Integer> operationType = new ArrayList<Integer>();
        List<String> point = new ArrayList<String>();
        List<Integer> isOpen = new ArrayList<Integer>();

        Double mainPointX = null;
        Double mainPointY = null;

        for(int i=0;i<maplist.size();i++) {

            ids.add(maplist.get(i).getOrgID());
            name.add(maplist.get(i).getOrgName());
            address.add(maplist.get(i).getAddress());
            orgType.add(maplist.get(i).getOrgType());
            operationType.add(maplist.get(i).getOperationType());

            Address address_temp = orgService.getAddressByOrgID(maplist.get(i).getOrgID());
            String point_temp = address_temp.getLongitude().setScale(6, BigDecimal.ROUND_HALF_UP).toString() + "|"
                    + address_temp.getLatitude().setScale(6, BigDecimal.ROUND_HALF_UP).toString();
            point.add(point_temp);

            if(maplist.get(i).getOrgID().equals(id)) {
                isOpen.add(1);
                mainPointX = address_temp.getLongitude().doubleValue();
                mainPointY = address_temp.getLatitude().doubleValue();
            }
            else {
                isOpen.add(0);
            }
        }

//        isOpen.set(id, 1);  // 设置该标签的信息框打开

        // 所有机构的经纬度，最后根据id将选中的机构经纬度设置为地图的中心点
//        Double[] mainPointX = {103.888768, 103.924707};
//        Double[] mainPointY = {30.473268, 30.473268};

        model.addAttribute("id", ids);
        model.addAttribute("name", name);
        model.addAttribute("address",address);
        model.addAttribute("orgType",orgType);
        model.addAttribute("operationType",operationType);
        model.addAttribute("point",point);
        model.addAttribute("isOpen",isOpen);

        model.addAttribute("mainPointX",mainPointX);
        model.addAttribute("mainPointY",mainPointY);
        model.addAttribute("isSelect",1);   // 表示用户选择了某个机构
        model.addAttribute("mapid",id);

        return "baidu-map";
    }

    @GetMapping("/index")
    public String showWhitelist(Model model) {
        List<Course> course = orgService.getBest4Courses();
        model.addAttribute("courseRecommend1", course);
        return "index";
    }

}
