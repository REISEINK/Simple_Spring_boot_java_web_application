package com.huadi.education;

import com.huadi.education.controller.OrgController;
import com.huadi.education.entity.Org;
import com.huadi.education.mapper.OrgMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class EducationApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private OrgController orgController;

//    @Resource
//    @Autowired：注意，这里使用@Autowired也是可以的；
//    但是由于idea工具检测不到CommentMapper的实现类，
//    因此会提示错误，但是程序运行正常
//    为解决这个错误有多种方式，其中之一是使用@Resource来代替Autowired
    @Resource
    private OrgMapper orgMapper;

//    @Test
//    public void orgMapperTest(){
//        Org org = orgMapper.selectByPrimaryKey(1);
//        System.out.println(org);
//    }

//    @Test
//    public void orgControllerTest() {
//        orgController.getOrgById(1);
//    }

}
