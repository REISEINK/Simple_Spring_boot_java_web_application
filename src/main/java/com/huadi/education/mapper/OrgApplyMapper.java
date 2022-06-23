package com.huadi.education.mapper;


import com.huadi.education.entity.OrgApply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrgApplyMapper {

    List<OrgApply> findAllOrgApply();
    int orgApplyCreate(OrgApply orgApply);
    Long queryLicense(String license);
}
