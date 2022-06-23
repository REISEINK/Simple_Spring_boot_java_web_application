package com.huadi.education.mapper;

import com.huadi.education.entity.ModifyApply;
import com.huadi.education.entity.News;
import com.huadi.education.entity.Org;
import com.huadi.education.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE tel=#{tel} AND access = #{access}")
    User selectUserByUserTelAndAccess(String tel, Integer access);
    User findUserById(Integer id);
    User findOrgUserById(Integer id);
    List<User> findAllOrgUser();
    List<User> findAll();
    List<Org> findAllOrg();
    List<ModifyApply> showAllModifyApply();
    List<News> findAllNews();
    Org findOrgByLicense(String licenseKey);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUserById(Integer id);
    int deleteModifyByOrgId(Integer id);
    int deleteUserByOrgId(Integer id);
    int deleteApplyByOrgId(Integer id);
    int deleteAddressByOrgId(Integer id);
    int deleteOrgById(Integer id);
    int modifyStatus(Integer id, boolean status);
    int modifyOrgStatus(Integer id, int status);
    int verifyAccount(Integer id);
    int updateOrgModify(Org org);
    int backAccount(Integer id);
    int verifyModify(Integer id, Integer status);
    int backModify(Integer id, Integer status);
    int insertOrgUser(User user);
    int insertOrg(Org org);
    int insertNews(News news);
    int updateModifyApply(ModifyApply modifyApply);
    int deleteModifyApplyById(Integer id);
    int deleteNewsById(Integer id);
    int setTopStatus(Integer id, boolean top);
}
