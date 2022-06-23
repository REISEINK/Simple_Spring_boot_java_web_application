package com.huadi.education.controller;

import com.huadi.education.entity.*;
import com.huadi.education.service.AddressService;
import com.huadi.education.service.OrgApplyService;
import com.huadi.education.service.UserService;
import com.huadi.education.utils.AddressResolutionUtil;
import com.huadi.education.utils.BaiduAPI;
import com.huadi.education.utils.ImportService;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.codehaus.groovy.util.ListHashMap;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrgApplyService orgApplyService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ImportService importService;

    //  管理员——展示平台管理员列表
    @GetMapping("/getUserList")
    public String UserList(Model model) {

        List<User> userList = userService.getUserList();
        List<User> orgUserList = userService.getOrgUserList();
        model.addAttribute("userList", userList);
        model.addAttribute("orgUserList", orgUserList);
        return "adminList";
    }

    //  管理员——跳转新增平台管理员页面
    @GetMapping("/toInsertUser")
    public String toInsertUser(Model model) {
        return "insertUser";
    }
    //  管理员——新增平台管理员
    @PostMapping("/InsertUser")
    public String InsertUser(User user, Model model) {

        if (null != user) {
            User checkUser = userService.getLoginUser(user.getTel(),2);
            System.out.println(checkUser);
            if(checkUser == null){
                int row = userService.insertUser(user);
                model.addAttribute("telFlag",true);
                if (row > 0) {
                    model.addAttribute("addAdminUserFlag", true);
                } else {
                    model.addAttribute("addAdminUserFlag", false);
                }
            }else {
                model.addAttribute("telFlag",false);
            }

        }
        return "insertUser";
    }

    //  管理员——删除管理员账户
    @GetMapping("/deleteUser")
    public String DeleteUser(Integer id, Model model) {

        int row = userService.deleteUserById(id);
        if (row > 0) {
            model.addAttribute("deleteAdminUserFlag", true);
        } else {
            model.addAttribute("deleteAdminUserFlag", false);
        }
        return "forward:/user/getUserList";
    }

    //  管理员——禁用、激活管理员状态
    @GetMapping("/modifyStatus")
    public String UserStatus(Integer id, boolean status, Model model) {

        int row = userService.modifyStatus(id, !status);
        if (row > 0) {
            if(status){
                model.addAttribute("openAdminStatusFlag", true);
            }else {
                model.addAttribute("closeAdminStatusFlag", true);
            }
        } else {
            if(status){
                model.addAttribute("openAdminStatusFlag", false);
            }else {
                model.addAttribute("closeAdminStatusFlag", false);
            }
        }
        return "forward:/user/getUserList";
    }

    //  机构账号审核——展示组织机构账户申请列表
    @GetMapping("/getOrgApplyList")
    public String OrgApplyList(Model model) {

        List<OrgApply> orgApplyList = orgApplyService.getOrgApplyList();
        model.addAttribute("orgApplyList", orgApplyList);
        return "applyList";
    }

    //  机构账号审核——通过机构账户申请
    @GetMapping("/verifyAccountApply")
    public String VerifyAccount(Integer id,Integer orgID, String name, String email, String tel, Model model) {

        int row = userService.verifyAccount(id);
        User user = new User();
        Org org = new Org();
        org.setOrgID(orgID);
        user.setOrg(org);
        user.setName(name);
        user.setTel(tel);
        user.setEmail(email);
        int insertRow = userService.insertOrgUser(user);
        if (row > 0 && insertRow > 0) {
            model.addAttribute("verifyAccountApplyFlag", true);
        } else {
            model.addAttribute("verifyAccountApplyFlag", false);
        }
        return "forward:/user/getOrgApplyList";
    }

    //  机构账号审核——驳回机构账号申请
    @GetMapping("/backAccountApply")
    public String BackAccount(Integer id, Model model) {

        int row = userService.backAccount(id);
        if (row > 0) {
            model.addAttribute("backAccountApplyFlag", true);
        } else {
            model.addAttribute("backAccountApplyFlag", false);
        }
        return "forward:/user/getOrgApplyList";
    }

    //  个人账户——展示个人信息
    @RequestMapping("/getUserInfo")
    public String UserInfo(Model model, HttpSession session) {

        int id = (int) session.getAttribute("userID");//获取session当前账号ID
        User userInfo = userService.findUserById(id);
        int access = userInfo.getAccess();
        if (access == 3) {
            userInfo = userService.findOrgUserById(id);
        }
        model.addAttribute("userInfo", userInfo);
        return "myAccount";
    }

    //  个人账户——修改个人信息
    @PostMapping("/updateUser")
    public String updateUser(User user, Model model) {

        if (null != user) {
            int row = userService.updateUser(user);
            if (row > 0) {
                model.addAttribute("modifyAdminInfoFlag", true);
            } else {
                model.addAttribute("modifyAdminInfoFlag", false);
            }
        }
        return "forward:/user/getUserInfo";
    }

    //  机构管理——展示组织机构列表页
    @GetMapping("/getOrgList")
    public String OrgList(Model model) {

        List<Org> orgList = userService.getOrgList();
        model.addAttribute("orgList", orgList);
        return "orgList";
    }

    //  机构管理——限制机构状态
    @GetMapping("/modifyLimitStatus")
    public String ModifyLimitStatus(Integer id, Integer status, Model model) {

        if (status != 2) {
            status = 2;
        } else {
            status = 1;
        }
        int row = userService.modifyOrgStatus(id, status);
        if (row > 0) {
            model.addAttribute("limitOrgStatusFlag", true);
        } else {
            model.addAttribute("limitOrgStatusFlag", false);
        }
        return "forward:/user/getOrgList";
    }

    //  机构管理——禁用机构状态
    @GetMapping("/modifyBanStatus")
    public String ModifyBanStatus(Integer id, Integer status, Model model) {

        if (status != 3) {
            status = 3;
        } else {
            status = 1;
        }
        int row = userService.modifyOrgStatus(id, status);
        if (row > 0) {
            model.addAttribute("banOrgStatusFlag", true);
        } else {
            model.addAttribute("banOrgStatusFlag", false);
        }
        return "forward:/user/getOrgList";
    }

    //  机构管理——删除机构
    @GetMapping("/deleteOrg")
    public String DeleteOrg(Integer id, Model model) {

        int row = userService.deleteOrgById(id);
        if (row > 0) {
            model.addAttribute("deleteOrgFlag", true);
        } else {
            model.addAttribute("deleteOrgFlag", false);
        }
        return "forward:/user/getOrgList";
    }

    //  机构信息修改审核——展示组织机构信息修改申请列表
    @GetMapping("/modifyApplyList")
    public String ModifyApplyList(Model model) {

        List<ModifyApply> modifyApplyList = userService.getModifyApplyList();
        model.addAttribute("modifyApplyList", modifyApplyList);
        return "modifyApplyList";
    }

    //  机构信息修改审核——通过机构信息修改申请
    @GetMapping("/verifyModifyApply")
    public String VerifyModify(Integer modID, Integer orgID, Integer status, String legalPerson, String principal, String publicTel, String address, String operationInfo, Model model) {

        int row = userService.verifyModify(modID, status);
        Org org = new Org();
        org.setOrgID(orgID);
        org.setLegalPerson(legalPerson);
        org.setPrincipal(principal);
        org.setPublicTel(publicTel);
        org.setAddress(address);
        org.setOperationInfo(operationInfo);
        int row2update = userService.updateOrgModify(org);
        if (row > 0 && row2update > 0) {
            model.addAttribute("verifyModifyApplyFlag", true);
        } else {
            model.addAttribute("verifyModifyApplyFlag", false);
        }
        return "forward:/user/modifyApplyList";
    }

    //  机构信息修改审核——驳回机构信息修改申请
    @GetMapping("/backModifyApply")
    public String BackModify(Integer modID, Integer modStatus, String feedback, Model model) {

        int row = userService.backModify(modID, modStatus);
        ModifyApply modifyApply = new ModifyApply();
        modifyApply.setModID(modID);
        modifyApply.setFeedback(feedback);
        int rowUpdate = userService.updateModifyApply(modifyApply);
        if (row > 0 && rowUpdate > 0) {
            model.addAttribute("backModifyApplyFlag", true);
        } else {
            model.addAttribute("backModifyApplyFlag", false);
        }
        return "forward:/user/modifyApplyList";
    }

    //  修改申请——删除修改申请记录
    @GetMapping("/deleteModifyApply")
    public String deleteModifyApply(Integer id, Model model) {

        int row = userService.deleteModifyApplyById(id);
        if (row > 0) {
            model.addAttribute("deleteModifyApplyFlag", true);
        } else {
            model.addAttribute("deleteModifyApplyFlag", false);
        }
        return "forward:/user/modifyApplyList";
    }

    //  资讯——跳转新增资讯页面
    @RequestMapping("/toInsertNews")
    public String toInsertNews() {

        return "insertNews";
    }

    //  资讯管理——展示辅助资讯列表页
    @GetMapping("/getNewsList")
    public String NewsList(Model model) {

        List<News> newsList = userService.getNewsList();
        model.addAttribute("newsList", newsList);
        return "newsList";
    }

    //  资讯管理——删除资讯
    @GetMapping("/deleteNews")
    public String DeleteNews(Integer id, Model model) {

        int row = userService.deleteNewsById(id);
        if (row > 0) {
            model.addAttribute("deleteNewsFlag", true);
        } else {
            model.addAttribute("deleteNewsFlag", false);
        }
        return "forward:/user/getNewsList";
    }

    //  资讯管理——设置资讯置顶
    @GetMapping("/setOnTop")
    public String SetOnTop(Integer id, boolean top, Model model) {

        int row = userService.setTopStatus(id, !top);
        if (row > 0) {
            model.addAttribute("setOnTopFlag", true);
        } else {
            model.addAttribute("setOnTopFlag", false);
        }
        return "forward:/user/getNewsList";
    }

    //  资讯——单条新增资讯
    @PostMapping("/insertSingleNews")
    public String insertSingleNews(News news, Model model) {

        if (null != news) {
            news.setTop(false);
            System.out.println(news);
            int row = userService.insertNews(news);
            if (row > 0) {
                model.addAttribute("addNewsFlag", true);
            } else {
                model.addAttribute("addNewsFlag", false);
            }
        }
        return "insertNews";
    }

    //  咨讯——下载新增咨讯Excel模板
    @GetMapping("/downloadNewsExcel")
    public void downloadNewsExcel(HttpServletResponse response) throws Exception {
        // 根据模板创建excel工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        // 获取创建的工作簿第一页
        XSSFSheet sheet = wb.createSheet();
        // 获取第一行
        XSSFRow row = sheet.createRow(0);
        XSSFCellStyle cellStyle = wb.createCellStyle();
        XSSFDataFormat format = wb.createDataFormat();
        // 先定义创建excel表头
        String[] title = {"咨询标题", "咨询链接", "咨询类别", "咨询内容"};
        XSSFCell cell = row.createCell(0);
        // 插入第一行数据的表头，用到上面的title数组
        for (int i = 0; i < title.length; i++) {
            // createCell(0)表示从左到右第一个空格哈，是依横向次插入的。
            cell = row.createCell(i);
            cellStyle.setDataFormat(format.getFormat("@"));
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 6000);
        }
        String[] list = {"通知公告", "政府法规"};

        CellRangeAddressList addressList = new CellRangeAddressList(1, 1000, 2, 2);

        DataValidationHelper helper = new XSSFDataValidationHelper((XSSFSheet) sheet);

        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) helper.createExplicitListConstraint(list);

        DataValidation dataValidation = helper.createValidation(dvConstraint, addressList);

        sheet.addValidationData(dataValidation);

        try {
            // 清空缓存
            response.reset();
            // 下载使用/未知类型
            response.setContentType("application/octet-stream");
            String fileName = "news_template";
//			 fileName = URLEncoder.encode(fileName, "utf-8");
//			 fileName = fileName.replace("+", "%20");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.flushBuffer();
            // 创建Excel文件的输入流对象
            OutputStream os = response.getOutputStream();
            // 这时候把创建好的excel写入到输出流
            wb.write(os);
            // 关闭流
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  咨讯——多条新增咨讯
    @ResponseBody
    @PostMapping("/insertNews")
    public Map<String, Object> insertMuchNews(@RequestParam(value = "file") MultipartFile file) throws Exception {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        System.out.println(file);
        if (file.isEmpty()) {
            System.out.println("文件不能为空");
            map.put("code", -1);
            map.put("msg", "上传失败");
            return map;
        }
        InputStream inputStream = file.getInputStream();
        List<List<String>> list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
        inputStream.close();

        int rowNum = 0;
        for (int i = 0; i < list.size(); i++) {
            List<String> lo = list.get(i);
            System.out.println(lo);
            boolean flag = true;
            for (int j = 0; j < lo.size(); j++) {
                if (lo.get(j) == null) {
                    flag = false;
                    map.put("code", -1);
                    map.put("msg", "上传失败");
                    return map;
                }
            }
            if (flag) {
                News news = new News();
                news.setTitle(lo.get(0));
                news.setFileSrc(lo.get(1));
                news.setCategory(lo.get(2));
                news.setEvent(lo.get(3));
                news.setTop(false);
                System.out.println(news);
                if (null != news) {
                    int row = userService.insertNews(news);
                    System.out.println(row);
                    rowNum += row;
                }
            }

        }
        map.put("code", 0);
        map.put("msg", "上传成功");
        map.put("count", rowNum);
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        mapData.put("src", "");
        map.put("data", mapData);
//        model.addAttribute("rowNum",rowNum);
        return map;
    }

    //  机构——跳转新增机构页面
    @RequestMapping("/toInsertOrg")
    public String toInsertOrg() {

        return "insertOrg";
    }

    //  机构——单条新增机构
    @PostMapping("/insertSingleOrg")
    public String insertSingleOrg(Org org, Model model) {

        if (null != org) {
            org.setComplainNum(0);
            org.setHaveAdm(false);
            System.out.println(org);
            String license = org.getLicenseKey();
            Org checkOrg = userService.findOrgByLicense(license);
            if (checkOrg==null){
                String address = org.getAddress();
                Double[] point = new BaiduAPI().AddressTolongitudea(address);
                if(point!=null){
                    int row = userService.insertOrg(org);
                    System.out.println(row);
                    if (row > 0) {
                        List<Map<String, String>> addressInfo = new AddressResolutionUtil().addressResolution(address);
                        Address addressObject = new Address();
                        addressObject.setOrg(org);
                        addressObject.setLongitude(BigDecimal.valueOf(point[0]));
                        addressObject.setLatitude(BigDecimal.valueOf(point[1]));
                        addressObject.setCity(addressInfo.get(0).get("city"));
                        addressObject.setArea(addressInfo.get(0).get("county"));
                        addressObject.setStreet(addressInfo.get(0).get("village"));
                        addressObject.setDetailInfo(addressInfo.get(0).get("getcyv"));
                        System.out.println(addressObject);
                        int addressRow = addressService.insertOrgAddress(addressObject);
                        if(addressRow>0){
                            model.addAttribute("addOrgFlag", true);
                        }else {
                            model.addAttribute("addOrgFlag", false);
                        }

                    }else {
                        model.addAttribute("addOrgFlag", false);
                    }
                }else {
                    model.addAttribute("addressFlag", false);
                }
            }else {
                model.addAttribute("haveOrgFlag",true);
            }

        }
        return "insertOrg";
    }

    //  机构——多条新增机构
    @ResponseBody
    @PostMapping("/insertOrg")
    public Map<String, Object> insertOrg(@RequestParam(value = "file") MultipartFile file) throws Exception {
        System.out.println(1111);
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject resObj = new JSONObject();

        System.out.println(file);
        if (file.isEmpty()) {
            System.out.println("文件不能为空");
            map.put("code", -1);
            map.put("msg", "上传失败");
            resObj = new JSONObject(map);
            return map;
        }
        InputStream inputStream = file.getInputStream();
        List<List<String>> list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
        inputStream.close();

        int rowNum = 0;
        boolean orgFlag = true;
        boolean addressFlag = true;
        boolean checkOrgFlag = true;
        for (int i = 0; i < list.size(); i++) {
            List<String> lo = list.get(i);
            System.out.println(lo);
            boolean flag = true;
            for (int j = 0; j < lo.size(); j++) {
                if (lo.get(j) == null) {
                    flag = false;
                    map.put("code", -1);
                    map.put("msg", "上传失败");
                    resObj = new JSONObject(map);
                    return map;
                }
            }
            if (flag) {
                Org org = new Org();
                org.setOrgName(lo.get(0));
                org.setLicenseKey(lo.get(1));
                org.setLicenseFile("0");
                org.setOrgType(lo.get(2));
                org.setLegalPerson(lo.get(3));
                org.setPrincipal(lo.get(4));
                ArrayList<String> title = new ArrayList<>();
                title.add("文理类");
                title.add("艺术类");
                title.add("语言类");
                title.add("科技类");
                title.add("其他");
                title.add("综合");
                for (int j = 0; j < title.size(); j++) {
                    String temp = lo.get(5);
                    if (title.get(j).equals(temp)) {
//                    System.out.println(j);
                        int num = j + 1;
                        org.setOperationType(num);
                    }
                }
                ArrayList<String> title2 = new ArrayList<>();
                title2.add("正常办学");
                title2.add("限期整改");
                title2.add("非法办学");
                for (int j = 0; j < title2.size(); j++) {
                    String temp = lo.get(9);
                    if (title2.get(j).equals(temp)) {
                        int num = j + 1;
                        org.setRegStatus(num);
                    }
                }
                org.setPublicTel(lo.get(6));
                org.setAddress(lo.get(7));
                org.setOperationInfo(lo.get(8));
                org.setComplainNum(0);
                org.setHaveAdm(false);
                System.out.println(org);
                if (null != org) {
                    String license = org.getLicenseKey();
                    Org checkOrg = userService.findOrgByLicense(license);
                    if(checkOrg==null){
                        String address = org.getAddress();
                        Double[] point = new BaiduAPI().AddressTolongitudea(address);
                        if(point!=null){
                            int row = userService.insertOrg(org);
                            System.out.println(row);
                            if(row>0){
                                rowNum += row;

                                List<Map<String, String>> addressInfo = new AddressResolutionUtil().addressResolution(address);
                                Address addressObject = new Address();
                                addressObject.setOrg(org);
                                addressObject.setLongitude(BigDecimal.valueOf(point[0]));
                                addressObject.setLatitude(BigDecimal.valueOf(point[1]));
                                addressObject.setCity(addressInfo.get(0).get("city"));
                                addressObject.setArea(addressInfo.get(0).get("county"));
                                addressObject.setStreet(addressInfo.get(0).get("village"));
                                addressObject.setDetailInfo(addressInfo.get(0).get("getcyv"));
                                System.out.println(addressObject);
                                int addressRow = addressService.insertOrgAddress(addressObject);
                                if(addressRow>0){

                                }else {
                                    System.out.println("插入address失败");
                                    addressFlag=false;
                                }
                            } else {
                                System.out.println("插入org失败");
                                orgFlag=false;
                            }
                        }else {
                            System.out.println("API错误");
                            addressFlag=false;
                        }
                    }else{
                        System.out.println("org已存在");
                        checkOrgFlag=false;
                    }

                }
            }

        }
        map.put("code", 0);
        if (checkOrgFlag==true){
            map.put("msg", "上传成功");
        }else {
            map.put("msg", "上传成功，但部分机构已存在！");
        }
        map.put("count", rowNum);
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        mapData.put("src", "");
        map.put("data", mapData);
        resObj = new JSONObject(map);
        System.out.println(resObj);
//        model.addAttribute("rowNum",rowNum);
        return map;
    }

    //  机构——下载新增机构Excel模板
    @GetMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) throws Exception {
        // 根据模板创建excel工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        // 获取创建的工作簿第一页
        XSSFSheet sheet = wb.createSheet();
        // 获取第一行
        XSSFRow row = sheet.createRow(0);
        XSSFCellStyle cellStyle = wb.createCellStyle();
        XSSFDataFormat format = wb.createDataFormat();
        // 先定义创建excel表头
        String[] title = {"组织机构全称", "许可证号", "组织机构类型", "法人", "校长", "办学类型", "公开电话", "学校地址", "办学内容", "办学状态"};
        XSSFCell cell = row.createCell(0);
        // 插入第一行数据的表头，用到上面的title数组
        for (int i = 0; i < title.length; i++) {
            // createCell(0)表示从左到右第一个空格哈，是依横向次插入的。
            cell = row.createCell(i);
            cellStyle.setDataFormat(format.getFormat("@"));
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 6000);
        }
        String[] list = {"文理类", "艺术类", "语言类", "科技类", "其他", "综合"};

        CellRangeAddressList addressList = new CellRangeAddressList(1, 500, 5, 5);

        DataValidationHelper helper = new XSSFDataValidationHelper((XSSFSheet) sheet);

        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) helper.createExplicitListConstraint(list);

        DataValidation dataValidation = helper.createValidation(dvConstraint, addressList);

        sheet.addValidationData(dataValidation);

        String[] list2 = {"正常办学", "限期整改", "非法办学"};

        CellRangeAddressList addressList2 = new CellRangeAddressList(1, 500, 9, 9);

        DataValidationHelper helper2 = new XSSFDataValidationHelper((XSSFSheet) sheet);

        XSSFDataValidationConstraint dvConstraint2 = (XSSFDataValidationConstraint) helper2.createExplicitListConstraint(list2);

        DataValidation dataValidation2 = helper.createValidation(dvConstraint2, addressList2);

        sheet.addValidationData(dataValidation2);

        try {
            // 清空缓存
            response.reset();
            // 下载使用/未知类型
            response.setContentType("application/octet-stream");
            String fileName = "template";
//			 fileName = URLEncoder.encode(fileName, "utf-8");
//			 fileName = fileName.replace("+", "%20");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.flushBuffer();
            // 创建Excel文件的输入流对象
            OutputStream os = response.getOutputStream();
            // 这时候把创建好的excel写入到输出流
            wb.write(os);
            // 关闭流
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  新增机构上传许可证文件
    @ResponseBody
    @PostMapping("/uploadOrgImg")
    public Map<String, Object> uploadOrgImg(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != file) {
            String fileOrigName = file.getOriginalFilename();// 文件原名称
            if (!fileOrigName.contains(".")) {
                throw new IllegalArgumentException("缺少后缀名");
            }
            //获取后缀名
            fileOrigName = fileOrigName.substring(fileOrigName.lastIndexOf("."));
            String newFileName = System.currentTimeMillis() + fileOrigName;
            String fileDirPath = new String("src/main/resources/" + "/static/upload/images");
            File fileDir = new File(fileDirPath);
            if (!fileDir.exists()) {
                // 递归生成文件夹
                fileDir.mkdirs();
            }
            System.out.println(fileDir.getAbsolutePath());

            File newFile = new File(fileDir.getAbsolutePath() + File.separator + newFileName);
//            if(!newFile.exists() && !newFile.isDirectory()){
//                System.out.println(newFile+"目录不存在，需要创建");
//                //创建目录
//                newFile.mkdirs();
//            }
            file.transferTo(newFile);
            map.put("code", 0);
            map.put("msg", "上传成功");
            Map<String, Object> data = new HashMap<String, Object>();
            String path = "/upload/images/" + newFileName;
            data.put("src", path);
            map.put("data", data);
            System.out.println(map);
        } else {
            map.put("code", -1);
            map.put("msg", "上传失败");
        }
        return map;
    }
}
