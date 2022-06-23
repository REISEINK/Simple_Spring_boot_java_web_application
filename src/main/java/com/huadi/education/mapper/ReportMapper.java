package com.huadi.education.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {
//    @Insert("INSERT INTO t_comment(content,author,a_id) " +
//            "values (#{content},#{author},#{aId})")
    @Insert("INSERT INTO report(orgName,repoType,repoEvent,filleSrc,whistleName,whistleID,whistleTel)" +
            "values(#{orgname},#{repotype},#{repoevent},#{filesrc},#{whistlename},#{whistleid},#{whistletel})")
    public int insertReport(String orgname,String repotype,String repoevent,String filesrc,String whistlename,String whistleid,String whistletel);
}
