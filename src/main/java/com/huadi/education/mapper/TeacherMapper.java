package com.huadi.education.mapper;

import com.huadi.education.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherMapper {

    List<Teacher> selectTchByForeignKey(Integer orgid);

    List<Teacher> selectOrder(@Param("orgid")Integer orgid, @Param("search") String search, @Param("orderby")String orderby, @Param("order")Boolean order);

    int deleteTchByPrimaryKey(Integer tchid);

    int insertTch(Teacher record);

    int insertTchSelective(Teacher record);

    Teacher selectTchByPrimaryKey(Integer tchid);

    int updateTchByPrimaryKeySelective(Teacher record);

    int updateTchByPrimaryKey(Teacher record);
}
