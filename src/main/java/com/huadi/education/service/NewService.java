package com.huadi.education.service;


import com.huadi.education.entity.News;

import java.util.List;

public interface NewService {

    List<News> getNewsList(String title);

    List<News> getPolicysList(String title);

    News getOrgByID(int newsid);

}
