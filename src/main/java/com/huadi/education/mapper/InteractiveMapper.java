package com.huadi.education.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InteractiveMapper {

    @Insert("INSERT INTO comment(userID,userName,comType,commentOn,comments)" +
            "values(#{userid},#{username},#{comtype},#{commenton},#{comments})")
    public int insertComment(Integer userid,String username,Integer comtype,Integer commenton,String comments);

}
