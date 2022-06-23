package com.huadi.education.mapper;

import com.huadi.education.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewMapper {

    //    @Select("SELECT* FROM news WHERE category = '通知公告'")
    @Select("<script>" +
            "SELECT * FROM news" +
            "<where>" +
            "<if test=\"title !=null and title!=''\"> title LIKE \"%\"#{title}\"%\"</if>" + "and category = '通知公告'" +
            "ORDER BY top = 1 DESC,newsID DESC" +
            "</where>" +
            "</script>"
    )
    List<News> selectNewsList(String title);


    @Select("<script>" +
            "SELECT * FROM news" +
            "<where>" +
            "<if test=\"title !=null and title!=''\"> title LIKE \"%\"#{title}\"%\"</if>" + "and category = '政策法规'" +
            "</where>" +
            "</script>"
    )
    List<News> selectPolicysList(String title);

    @Select("SELECT * FROM news WHERE newsID = #{newsid}")
    News selectNewByID(Integer newsid);



}
