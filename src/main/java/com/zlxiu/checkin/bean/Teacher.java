package com.zlxiu.checkin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * 教师用户表
 */
@Data
@NoArgsConstructor
public class Teacher {
    @TableId(type = IdType.AUTO)
    private Integer teacherId;
//    private Integer adminId;
    private String teacherAccount;
    private String teacherPassword;
    private String teacherName;
    private Integer teacherSex;
    private String teacherPhone;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date teacherBirthday;
    private String teacherAvatar;

    //连表查询
    @TableField(exist = false)
    private List<Course> courses;

    public Teacher( String teacherAccount, String teacherPassword, String teacherName, Integer teacherSex, String teacherPhone, Date teacherBirthday, String teacherAvatar) {
//        this.adminId = adminId;
        this.teacherAccount = teacherAccount;
        this.teacherPassword = teacherPassword;
        this.teacherName = teacherName;
        this.teacherSex = teacherSex;
        this.teacherPhone = teacherPhone;
        this.teacherBirthday = teacherBirthday;
        this.teacherAvatar = teacherAvatar;
    }
}