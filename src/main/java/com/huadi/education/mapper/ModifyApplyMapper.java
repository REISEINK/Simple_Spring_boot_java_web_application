package com.huadi.education.mapper;


import com.huadi.education.entity.ModifyApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModifyApplyMapper {
    // List<ModifyApply> selectAllModifyApply();//系统管理员显示所有申请

    // List<ModifyApply> selectModifyApplyById(Integer orgid);//查找自己机构的申请

     int applyModify(ModifyApply modifyApply);//添加申请

     ModifyApply findModify(Integer orgID);//查找现在是否有待审核的申请

     int reApplyModify(ModifyApply modifyApply);//替换原有的申请

  //   int feedbackApply(@Param("modid")Integer modid,@Param("feedback")String feedback);//拒绝申请并回复，设置状态为2

 //    int AgreeApply(Integer modid);//同意申请并设置状态为1，之后还需添加修改机构信息的功能
}
