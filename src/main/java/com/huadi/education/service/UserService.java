package com.huadi.education.service;


import com.huadi.education.entity.ModifyApply;
import com.huadi.education.entity.News;
import com.huadi.education.entity.Org;
import com.huadi.education.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getLoginUser(String tel, Integer access);
    User findUserById(Integer id);
    User findOrgUserById(Integer id);
    List<User> getOrgUserList();
    List<User> getUserList();
    List<Org> getOrgList();
    List<ModifyApply> getModifyApplyList();
    List<News> getNewsList();
    Org findOrgByLicense(String licenseKey);
    int insertUser(User user);
    int modifyStatus(Integer id, boolean status);
    int modifyOrgStatus(Integer id, int status);
    int updateUser(User user);
    int verifyAccount(Integer id);
    int updateOrgModify(Org org);
    int backAccount(Integer id);
    int verifyModify(Integer id, Integer status);
    int backModify(Integer id, Integer status);
    int deleteUserById(Integer id);
    int deleteOrgById(Integer id);
    int insertOrgUser(User user);
    int insertOrg(Org org);
    int insertNews(News news);
    int updateModifyApply(ModifyApply modifyApply);
    int deleteModifyApplyById(Integer id);
    int deleteNewsById(Integer id);
    int setTopStatus(Integer id, boolean top);
}
