package com.huadi.education.service;

import com.huadi.education.entity.OrgApply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrgApplyService {
    List<OrgApply> getOrgApplyList();
    int orgApplyCreate(OrgApply orgApply);
    Long queryLicense(String license);
}
