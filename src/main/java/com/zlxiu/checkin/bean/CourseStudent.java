package com.zlxiu.checkin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 课程学生统计表
 */
@Data
@NoArgsConstructor
public class CourseStudent {
    private Integer courseId;
    private Integer studentId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp joinTime;

    @TableField(exist = false)
    private Student student;

    public CourseStudent(Integer courseId, Integer studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
