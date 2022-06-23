package com.huadi.education.service.impl;

import com.huadi.education.entity.OrgApply;
import com.huadi.education.mapper.OrgApplyMapper;
import com.huadi.education.service.OrgApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrgApplyServiceImpl implements OrgApplyService {

    @Resource
    private OrgApplyMapper orgApplyMapper;

    @Override
    public List<OrgApply> getOrgApplyList() {
        return orgApplyMapper.findAllOrgApply();
    }

    @Override
    public int orgApplyCreate(OrgApply orgApply) {
        return orgApplyMapper.orgApplyCreate(orgApply);
    }

    @Override
    public Long queryLicense(String license) {
        return orgApplyMapper.queryLicense(license);
    }
}
