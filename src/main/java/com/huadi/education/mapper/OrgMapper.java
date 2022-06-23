package com.huadi.education.mapper;

import com.huadi.education.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrgMapper {

    //机构黑名单
    @Select("<script>" +
            "SELECT * FROM org" +
            "<where>" +
            "<if test=\"orgname !=null and orgname!=''\"> orgName LIKE \"%\"#{orgname}\"%\"</if>" +
            "<if test=\"licensekey != null and licensekey !=''\">and licenseKey=#{licensekey} </if> "+
            "<if test=\"orgtype !=null and orgtype !='allType'\">and orgType=#{orgtype} </if>" + "and regStatus != 1" +
            "</where>" +
            "</script>"
    )
    List<Org> selectBlocklistsByQuery(@Param("orgname") String orgname, @Param("licensekey") String licensekey, @Param("orgtype") String orgtype);

    //机构白名单
    @Select("<script>" +
            "SELECT * FROM org" +
            "<where>" +
            "<if test=\"orgname !=null and orgname!=''\"> orgName LIKE \"%\"#{orgname}\"%\"</if>" +
            "<if test=\"licensekey != null and licensekey !=''\">and licenseKey=#{licensekey} </if> "+
            "<if test=\"orgtype !=null and orgtype !='allType'\">and orgType=#{orgtype} </if>" + "and regStatus = 1" +
            "</where>" +
            "</script>"
    )
    List<Org> selectWhitelistsByQuery(@Param("orgname") String orgname, @Param("licensekey") String licensekey, @Param("orgtype") String orgtype);

    //定位搜索
    @Select("<script>" +
            "SELECT * FROM org" +
            "<where>" +
            "<if test=\"orgname !=null and orgname!=''\"> orgName LIKE \"%\"#{orgname}\"%\"</if>" +
            "<if test=\"address !=null and address!=''\"> and address LIKE \"%\"#{address}\"%\"</if>" +
            "<if test=\"licensekey != null and licensekey !=''\">and licenseKey=#{licensekey} </if> "+
            "<if test=\"orgtype !=null and orgtype !='allType'\">and orgType=#{orgtype} </if>" +
            "<if test=\"operationtype !=null and operationtype !=0\">and operationType=#{operationtype} </if>" +
            "</where>" +
            "</script>"
    )
    List<Org> selectMaplist(@Param("orgname") String orgname, @Param("address") String address,
                            @Param("licensekey") String licensekey, @Param("orgtype") String orgtype,
                            @Param("operationtype") Integer operationType);

    //根据ID查找机构
    @Select("SELECT * FROM org WHERE orgID = #{orgid}")
    Org selectOrgByID(Integer orgid);

    //根据ID查找课程
    @Select("SELECT * FROM org_course WHERE orgID = #{orgid}")
    List<Course> selectOrgCourseInfoByID(Integer orgid);

    //根据ID查找教师
    @Select("SELECT * FROM org_lecturer WHERE orgID = #{orgid}")
    List<Teacher> selectOrgTeacherInfoByID(Integer orgid);

    //根据ID查找评论
    @Select("SELECT * FROM comment WHERE comType =1 AND commentOn = #{orgid}")
    List<Comment> selectOrgComment(Integer orgid);

    //根据ID查找评论的评论
    @Select("SELECT * FROM comment WHERE comType =4 AND commentOn = #{orgid}")
    List<Comment> selectCommentComment(Integer orgid);

    @Select("SELECT * FROM address WHERE orgID = #{orgid}")
    Address selectAddressByOrgID(Integer orgid);

    @Select("SELECT * FROM org_course ORDER BY cozScore DESC LIMIT 4")
    List<Course> selectBest4Course();

    @Select("SELECT * FROM org_course WHERE cozType = #{coztype} ORDER BY cozScore DESC LIMIT 1")
    Course selectBestCourseByType(Integer coztype);



}