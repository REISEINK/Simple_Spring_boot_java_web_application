package com.huadi.education.controller;

import com.huadi.education.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    ReportService reportService;

    @ResponseBody
    @PostMapping("/submitReport")
    public Map sumbitReport(@RequestParam("orgName") String orgname,
                            @RequestParam("repoType") String repotype,
                            @RequestParam("repoEvent") String repoevent,
                            @RequestParam("whistleName") String whistlename,
                            @RequestParam("whistleID") String whistleID,
                            @RequestParam("whistleTel") String whistletel,
                            @RequestParam("fileNames") String filenames) {
        System.out.println(orgname);
        System.out.println(repotype);
        System.out.println(repoevent);
        Map<String,Integer> map = new HashMap<>();
        if(reportService.addReport(orgname,repotype,repoevent,filenames,whistlename,whistleID,whistletel) == 1) {
            map.put("msg",1);
        }
        else {
            map.put("msg",0);
        }
        return map;

    }


    @PostMapping("/uploadReportFiles")
    @ResponseBody
    public Map UploadReportFiles(@RequestParam("file") MultipartFile file) {
        Map<String, String> map = new HashMap<>();
//        System.out.println(file);
        String pathString = null;
        if(file != null) {
            //获取上传的文件名称
            String filename = file.getOriginalFilename();
            //文件上传时，chrome与IE/Edge对于originalFilename处理方式不同
            //chrome会获取到该文件的直接文件名称，IE/Edge会获取到文件上传时完整路径/文件名
            //Check for Unix-style path
            int unixSep = filename.lastIndexOf('/');
            //Check for Windows-style path
            int winSep = filename.lastIndexOf('\\');
            //cut off at latest possible point
            int pos = (winSep > unixSep ? winSep:unixSep);
            if (pos != -1)
                filename = filename.substring(pos + 1);
            String dirPath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\";
            pathString = dirPath + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" +filename;//上传到本地
        }
        try {
            File files=new File(pathString);//在内存中创建File文件映射对象
            //打印查看上传路径
            System.out.println(pathString);
            if(!files.getParentFile().exists()){//判断映射文件的父文件是否真实存在
                files.getParentFile().mkdirs();//创建所有父文件夹
            }
            file.transferTo(files);//采用file.transferTo 来保存上传的文件
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            map.put("code","1");
            map.put("msg","fail");
            return map;
        }
        map.put("code","0");
        map.put("msg","success");
        map.put("fileUrl",pathString);
        map.put("fileName",pathString);
        return map;
    }
}
