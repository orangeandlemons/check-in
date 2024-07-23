package com.zlxiu.checkin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

/**
 * 学生用户表
 */
@Data
@NoArgsConstructor
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer studentId;
    private String studentAccount;
    private String studentPassword;
    private String studentName;
    private Integer studentSex;
    private String studentAvatar;
    private String studentClass;
    private String studentFace;
    private String studentPhone;
//    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date studentBirthday;

    @TableField(exist = false)
    private List<Record> records;
    @TableField(exist = false)
    private List<Leave> leaves;
    @TableField(exist = false)
    private List<CourseStudent> courseStudents;

    public Student(String studentAccount, String studentPassword, String studentName, Integer studentSex, String studentAvatar, String studentClass, String studentPhone, Date studentBirthday) {
        this.studentAccount = studentAccount;
        this.studentPassword = studentPassword;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.studentAvatar = studentAvatar;
        this.studentClass = studentClass;
        this.studentPhone = studentPhone;
        this.studentBirthday = studentBirthday;
    }
}
