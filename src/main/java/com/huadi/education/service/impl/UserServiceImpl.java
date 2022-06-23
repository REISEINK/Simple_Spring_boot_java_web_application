package com.huadi.education.service.impl;

import com.huadi.education.entity.ModifyApply;
import com.huadi.education.entity.News;
import com.huadi.education.entity.Org;
import com.huadi.education.entity.User;
import com.huadi.education.mapper.UserMapper;
import com.huadi.education.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getLoginUser(String tel, Integer access) {
        return userMapper.selectUserByUserTelAndAccess(tel,access);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findOrgUserById(Integer id) {
        return userMapper.findOrgUserById(id);
    }

    @Override
    public List<User> getOrgUserList() {
        return userMapper.findAllOrgUser();
    }

    @Override
    public List<User> getUserList() {
        return userMapper.findAll();
    }

    @Override
    public List<Org> getOrgList() { return userMapper.findAllOrg(); }

    @Override
    public List<ModifyApply> getModifyApplyList() {
        return userMapper.showAllModifyApply();
    }

    @Override
    public List<News> getNewsList() {
        return userMapper.findAllNews();
    }

    @Override
    public Org findOrgByLicense(String licenseKey) {
        return userMapper.findOrgByLicense(licenseKey);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public int deleteOrgById(Integer id) {
        int user = userMapper.deleteUserByOrgId(id);
        int apply = userMapper.deleteApplyByOrgId(id);
        int modify = userMapper.deleteModifyByOrgId(id);
        int address = userMapper.deleteAddressByOrgId(id);
        return userMapper.deleteOrgById(id);
    }

    @Override
    public int insertOrgUser(User user) { return userMapper.insertOrgUser(user); }

    @Override
    public int insertOrg(Org org) {
        return userMapper.insertOrg(org);
    }

    @Override
    public int insertNews(News news) {
        return userMapper.insertNews(news);
    }

    @Override
    public int updateModifyApply(ModifyApply modifyApply) {
        return userMapper.updateModifyApply(modifyApply);
    }

    @Override
    public int deleteModifyApplyById(Integer id) {
        return userMapper.deleteModifyApplyById(id);
    }

    @Override
    public int deleteNewsById(Integer id) {
        return userMapper.deleteNewsById(id);
    }

    @Override
    public int setTopStatus(Integer id, boolean top) {
        return userMapper.setTopStatus(id, top);
    }

    @Override
    public int modifyStatus(Integer id,boolean status) {
        return userMapper.modifyStatus(id,status);
    }

    @Override
    public int modifyOrgStatus(Integer id, int status) {
        return userMapper.modifyOrgStatus(id, status);
    }

    @Override
    public int verifyAccount(Integer id) {
        return userMapper.verifyAccount(id);
    }

    @Override
    public int updateOrgModify(Org org) {
        return userMapper.updateOrgModify(org);
    }

    @Override
    public int backAccount(Integer id) {
        return userMapper.backAccount(id);
    }

    @Override
    public int verifyModify(Integer id, Integer status) {
        return userMapper.verifyModify(id, status);
    }

    @Override
    public int backModify(Integer id, Integer status) {
        return userMapper.backModify(id, status);
    }
}
