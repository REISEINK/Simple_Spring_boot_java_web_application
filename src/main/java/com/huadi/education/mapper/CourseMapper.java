package com.huadi.education.mapper;

import com.huadi.education.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<Course> selectByForeignKey(Integer orgid);

    List<Course> selectOrder(@Param("orgid")Integer orgid, @Param("search") String search, @Param("orderby")String orderby, @Param("order")Boolean order);

    int deleteByPrimaryKey(Integer cozid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer cozid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}
