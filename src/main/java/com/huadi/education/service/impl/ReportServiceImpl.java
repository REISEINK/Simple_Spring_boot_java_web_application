package com.huadi.education.service.impl;

import com.huadi.education.mapper.ReportMapper;
import com.huadi.education.service.ReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    ReportMapper reportMapper;

    @Override
    public int addReport(String orgname, String repotype, String repoevent, String filesrc, String whistlename, String whistleid, String whistletel) {
        return reportMapper.insertReport(orgname,repotype,repoevent,filesrc,whistlename,whistleid,whistletel);
    }
}
