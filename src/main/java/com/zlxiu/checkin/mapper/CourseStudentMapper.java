package com.zlxiu.checkin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zlxiu.checkin.bean.CourseStudent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStudentMapper extends BaseMapper<CourseStudent> {
    List<CourseStudent> findStudentByCourseId(Integer courseId);
}
