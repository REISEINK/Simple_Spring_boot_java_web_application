package com.huadi.education.service;

import com.huadi.education.entity.*;

import java.util.List;

public interface OrgAdmService {
//1.机构申请修改机构基础信息
//2.老师管理（增删查改）
//3.课程管理（增删查改）

    int applyModifyApply(ModifyApply modifyApply);

    ModifyApply findModifyApplyByOrgId(Integer id);

    int insertOrg_Lecturer(Teacher teacher);

    int delectOrg_Lecturer(Integer id);

    Teacher findOrg_LecturerById(Integer id);

    List<Teacher> findOrg_LecturerByOrgId(Integer id);

    List<Teacher> findOrg_LecturerByOrgIdOrder(Integer id, String search, String orderby, Boolean order);

    int updateOrg_Lecturer(Teacher teacher);

    int insertOrg_course(Course course);

    int delectOrg_course(Integer id);

    Course findOrg_courseById(Integer id);

    List<Course> findOrg_courseByOrgId(Integer id);

    List<Course> findOrg_courseByOrgIdOrder(Integer id, String search, String orderby, Boolean order);

    int updateOrg_course(Course course);

}
