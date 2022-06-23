package com.huadi.education.service.impl;

import com.huadi.education.entity.*;
import com.huadi.education.mapper.OrgMapper;
import com.huadi.education.service.OrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {

    @Resource
    private OrgMapper orgMapper;

//    @Override
//    public List<Org> getWhitelist() {
//        return orgMapper.selectWhitelists();
//    }
//
//    @Override
//    public List<Org> getBlocklist() {
//        return orgMapper.selectBlocklists();
//    }

    @Override
    public List<Org> getBlocklistByQuery(String orgname,String licensekey,String orgtype) {
        return orgMapper.selectBlocklistsByQuery(orgname,licensekey,orgtype);
    }

    @Override
    public List<Org> getWhitelistByQuery(String orgname, String licensekey, String orgtype) {
        return orgMapper.selectWhitelistsByQuery(orgname,licensekey,orgtype);
    }

    @Override
    public List<Org> getMaplist(String orgname, String address, String licensekey, String orgtype, Integer operationtype) {
        return orgMapper.selectMaplist(orgname,address,licensekey,orgtype,operationtype);
    }

    @Override
    public Org getOrgByID(Integer orgid) {
        return orgMapper.selectOrgByID(orgid);
    }

    @Override
    public List<Course> getOrgCourseInfo(Integer orgid) {
        return orgMapper.selectOrgCourseInfoByID(orgid);
    }

    @Override
    public List<Teacher> getOrgTeacherInfo(Integer orgid) {
        return orgMapper.selectOrgTeacherInfoByID(orgid);
    }

    @Override
    public List<Comment> getOrgComment(Integer orgid) {
        return orgMapper.selectOrgComment(orgid);
    }

    @Override
    public List<Comment> getCommentComment(Integer orgid) {
        return orgMapper.selectCommentComment(orgid);
    }

    @Override
    public Address getAddressByOrgID(Integer orgid) {
        return orgMapper.selectAddressByOrgID(orgid);
    }

    @Override
    public List<Course> getBest4Courses() {
        return orgMapper.selectBest4Course();
    }

    @Override
    public Course getBestCourseByType(Integer coztype) {
        return orgMapper.selectBestCourseByType(coztype);
    }
}
