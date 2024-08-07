package com.zlxiu.checkin.controller;


import com.zlxiu.checkin.bean.Course;
import com.zlxiu.checkin.common.ServiceResponse;
import com.zlxiu.checkin.service.AccountService;
import com.zlxiu.checkin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService  courseService;

    @Autowired
    AccountService accountService;

    @PostMapping("/addCourse")
    public Object addCourse(
            @RequestParam("teacherId") Integer teacherId,
            @RequestParam("name") String name,
            @RequestParam("avatar") String avatar,
            @RequestParam("introduce") String introduce
    ){
        Course course = new Course(teacherId,name,avatar,introduce);
        Map<String, Object> map = new HashMap<>();
        map.put("teacher_id",teacherId);
        map.put("course_name",name);
        if (courseService.findCourseByMap(map).getData().size() != 0){
            return ServiceResponse.createEmptyResponse("创建课程已存在");
        }
        return courseService.addCourse(course);
    }

    /**
     * 通过模糊查询课程表
     * @return 返回数组
     */
    @PostMapping("/findCourseByColumn")
    public Object findCourseByColumn(
            @RequestParam("column") String column,
            @RequestParam("value") Object value
    ){
        return courseService.findCourseByColumn(column,value);
    }

    /**
     * 通过模糊查询课程表
     * @return 返回数组
     */
    @PostMapping("/findCourseByMap")
    public Object findCourseByMap(@RequestParam Map<String, Object> map){
        return courseService.findCourseByMap(map);
    }

    @PostMapping("/findCourseByStudentId")
    public Object findCourseByStudentId(@RequestParam("studentId") Integer studentId){
        return courseService.findCourseByStudentId(studentId);
    }

    @PostMapping("/findCourseByStudentIdWithName")
    public Object findCourseByStudentIdWithName(@RequestParam("studentId") Integer studentId,
                                                @RequestParam("name") String name){
        return courseService.findCourseByStudentIdWithName(studentId,name);
    }

    @PostMapping("/findCourseByCode")
    public Object findCourseByCode(@RequestParam("code") String code){
        return courseService.findCourseByCode(code);
    }

    @PostMapping("/findCourseByTeacherId")
    public Object findCourseByTeacherId(@RequestParam("teacherId") Integer teacherId){
        return courseService.findCourseByTeacherId(teacherId);
    }

    @PostMapping("/findCourseByTeacherIdWithName")
    public Object findCourseByTeacherIdWithName(@RequestParam("teacherId") Integer teacherId,
                                                @RequestParam("name") String name){
        return courseService.findCourseByTeacherIdWithName(teacherId,name);
    }

    @PostMapping("/findAllCourse")
    public Object findAllCourse(){
        return courseService.findAllCourse();
    }

    /**
     * 修改课程，如果修改后的名称在数据库重复，取消修改操作
     */
    @PostMapping("/modifyCourse")
    public Object modifyCourse(
            @RequestParam("courseId") Integer courseId,
            @RequestParam(value = "courseName",required = false) String courseName,
            @RequestParam(value = "courseAvatar",required = false) String courseAvatar,
            @RequestParam(value = "courseIntroduce", required = false) String courseIntroduce
    ){
        Map<String, Object> map = new HashMap<>();
        map.put("course_id",courseId);
        Course courseOld = courseService.findCourseByMap(map).getData().get(0);
        map.clear();
        if (courseName != null && !courseName.equals(courseOld.getCourseName())){
            map.put("teacher_id",courseOld.getTeacherId());
            map.put("course_name",courseName);
            if (courseService.findCourseByMap(map).getData().size() != 0){
                return ServiceResponse.createEmptyResponse("修改课程内容重复");
            }
        }
        Course course = new Course(courseOld.getTeacherId(), courseName, courseAvatar, courseIntroduce);
        course.setCourseId(courseId);
        return courseService.modifyCourse(course);
    }

    /**
     * 删除课程
     * @param courseId 课程的id
     */
    @PostMapping("/deleteCourse")
    public Object deleteCourse(
            @RequestParam("id") Integer courseId
    ){
        return courseService.deleteCourse(courseId);
    }
}
