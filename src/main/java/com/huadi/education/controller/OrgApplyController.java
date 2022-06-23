package com.huadi.education.controller;

import com.huadi.education.entity.Org;
import com.huadi.education.entity.OrgApply;
import com.huadi.education.service.OrgApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("orgApply")
public class OrgApplyController {

    @Autowired
    private OrgApplyService orgApplyService;

    @GetMapping("/toSubmit")
    public String toSubmitApply(){

        return "apply";
    }

    @PostMapping("/submit")
    public String SubmitApply(String license, OrgApply orgApply){
        System.out.println(license);
        if(license != null){
            Long orgID = orgApplyService.queryLicense(license);
            System.out.println(orgID);
            if(orgID!=null){
                System.out.println("存在机构");
                if(null != orgApply){
                    Org org = new Org();
                    org.setOrgID(Math.toIntExact(orgID));
                    orgApply.setOrg(org);
                    System.out.println(orgApply);
                    int row = orgApplyService.orgApplyCreate(orgApply);
                    System.out.println(row);
                    return row>0?"forward:/orgApply/successToApply":"forward:/orgApply/failToApply";
                }
            }else {
                System.out.println("不存在机构");
                return "forward:/orgApply/failToApply";
            }
        }
        return "apply";
    }

    @PostMapping("/failToApply")
    public String failToApply(Model model){
        boolean orgflag = false;
        model.addAttribute("orgflag",orgflag);
        return "apply";
    }

    @PostMapping("/successToApply")
    public String successToApply(Model model){
        boolean orgflag = true;
        model.addAttribute("orgflag",orgflag);
        return "apply";
    }


}
