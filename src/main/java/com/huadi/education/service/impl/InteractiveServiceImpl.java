package com.huadi.education.service.impl;

import com.huadi.education.mapper.InteractiveMapper;
import com.huadi.education.service.InteractiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InteractiveServiceImpl implements InteractiveService{
    @Resource
    InteractiveMapper interactiveMapper;

    @Override
    public int addComment(Integer userid, String username, Integer comtype, Integer commenton, String comments) {
        return interactiveMapper.insertComment(userid,username,comtype,commenton,comments);

    }
}
