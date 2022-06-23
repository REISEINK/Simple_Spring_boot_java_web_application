package com.huadi.education.service;

import com.huadi.education.entity.*;

import java.util.List;

public interface OrgService {

//    List<Org> getWhitelist();
//
//    List<Org> getBlocklist();

    //黑名单
    List<Org> getBlocklistByQuery(String orgname,String licensekey,String orgtype);

    //白名单
    List<Org> getWhitelistByQuery(String orgname,String licensekey,String orgtype);

    //定位搜索
    List<Org> getMaplist(String orgname,String address,String licensekey,String orgtype,Integer operationtype);

    //根据id查找机构信息
    Org getOrgByID(Integer orgid);

    //根据id查找机构对应的课程信息
    List<Course> getOrgCourseInfo(Integer orgid);

    //根据id查找机构对应的课程信息
    List<Teacher> getOrgTeacherInfo(Integer orgid);

    //根据id查找机构对应的评论信息
    List<Comment> getOrgComment(Integer orgid);

    //根据id查找评论对应的评论信息
    List<Comment> getCommentComment(Integer orgid);

//    int addComment(int userid,String username,int comtype,int commenton,String comments);

    Address getAddressByOrgID(Integer orgid);

    List<Course> getBest4Courses();

    Course getBestCourseByType(Integer coztype);

}
