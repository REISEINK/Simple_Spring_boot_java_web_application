package com.huadi.education.service.impl;

import com.huadi.education.entity.News;
import com.huadi.education.mapper.NewMapper;
import com.huadi.education.service.NewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewServiceImpl implements NewService {

    @Resource
    private NewMapper newMapper;

    @Override
    public List<News> getNewsList(String title) {
        return newMapper.selectNewsList(title);
    }

    @Override
    public List<News> getPolicysList(String title) {
        return newMapper.selectPolicysList(title);
    }

    @Override
    public News getOrgByID(int newsid) {
        return newMapper.selectNewByID(newsid);
    }
}
