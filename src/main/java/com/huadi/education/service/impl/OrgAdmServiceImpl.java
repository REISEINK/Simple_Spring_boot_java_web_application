package com.huadi.education.service.impl;

import com.huadi.education.entity.Course;
import com.huadi.education.entity.Teacher;
import com.huadi.education.entity.ModifyApply;
import com.huadi.education.mapper.CourseMapper;
import com.huadi.education.mapper.TeacherMapper;
import com.huadi.education.mapper.ModifyApplyMapper;
import com.huadi.education.service.OrgAdmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrgAdmServiceImpl implements OrgAdmService {

    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private ModifyApplyMapper modifyApplyMapper;

    @Override
    public int applyModifyApply(ModifyApply modifyApply) {
        int rows;
        if(modifyApplyMapper.findModify(modifyApply.getOrg().getOrgID())!=null){
            rows= modifyApplyMapper.reApplyModify(modifyApply);
        }else{
            rows= modifyApplyMapper.applyModify(modifyApply);
        }
        return rows;
    }

    @Override
    public ModifyApply findModifyApplyByOrgId(Integer id) {
        return modifyApplyMapper.findModify(id);
    }

    @Override
    public int insertOrg_Lecturer(Teacher teacher) {
        return teacherMapper.insertTch(teacher);
    }

    @Override
    public int delectOrg_Lecturer(Integer id) {
        return teacherMapper.deleteTchByPrimaryKey(id);
    }

    @Override
    public Teacher findOrg_LecturerById(Integer id) {
        return teacherMapper.selectTchByPrimaryKey(id);
    }

    @Override
    public List<Teacher> findOrg_LecturerByOrgId(Integer id) {
        return teacherMapper.selectTchByForeignKey(id);
    }

    @Override
    public List<Teacher> findOrg_LecturerByOrgIdOrder(Integer id, String search, String orderby, Boolean order) {
        return teacherMapper.selectOrder(id,search,orderby,order);
    }

    @Override
    public int updateOrg_Lecturer(Teacher teacher) {
        return teacherMapper.updateTchByPrimaryKey(teacher);
    }

    @Override
    public int insertOrg_course(Course course) {
        return courseMapper.insert(course);
    }

    @Override
    public int delectOrg_course(Integer id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Course findOrg_courseById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Course> findOrg_courseByOrgId(Integer id) {
        return courseMapper.selectByForeignKey(id);
    }

    @Override
    public List<Course> findOrg_courseByOrgIdOrder(Integer id, String search, String orderby, Boolean order) {
        return courseMapper.selectOrder(id,search,orderby,order);
    }

    @Override
    public int updateOrg_course(Course course) {
        return courseMapper.updateByPrimaryKey(course);
    }
}
