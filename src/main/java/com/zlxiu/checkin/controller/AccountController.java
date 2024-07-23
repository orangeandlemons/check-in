package com.zlxiu.checkin.controller;

import com.zlxiu.checkin.bean.Student;
import com.zlxiu.checkin.bean.Teacher;
import com.zlxiu.checkin.common.ServiceResponse;
import com.zlxiu.checkin.service.AccountService;
import com.zlxiu.checkin.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Scanner;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    /**
     * 用户登录
     *
     * @param type     用户角色
     * @param account  账号
     * @param password 密码
     * @return 登录成功返回用户信息，失败返回code401
     */
    @PostMapping(value = "/login")
    public Object login(@RequestParam("type") Integer type,
                        @RequestParam("account") String account,
                        @RequestParam("password") String password) {

        return accountService.accountLogin(type, account, password);
    }

    /**
     * 注销用户
     *
     * @param type      被删除用户角色
     * @param accountId 被删用户的账户id
     * @return 执行删除操作的结果
     */
    @PostMapping("/deleteAccount")
    public Object deleteAccount(
            @RequestParam("type") Integer type,
            @RequestParam("accountId") Integer accountId
    ) {
        switch (type) {
            case 1:
                /**
                 * 删除教师用户
                 */
                return accountService.deleteTeacher(accountId);
            case 2:
                /**
                 * 删除学生用户
                 */
                return accountService.deleteStudent(accountId);
            default:
                return ServiceResponse.createFailResponse("操作不允许");
        }
    }

    /**
     * 添加学生和教师的两种方法
     */

    @PostMapping("/addTeacher")
    public Object addTeacher(
//            @RequestParam("adminId") Integer adminId,
            @RequestParam("account") String account,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("sex") Integer sex,
            @RequestParam("phone") String phone,
            @RequestParam("birthday") java.sql.Date birthday,
            @RequestParam("avatar") String avatar
    ) {
        ServiceResponse<Teacher> response;
        if (!Utils.isPhone(phone)) {
            response = ServiceResponse.createFailResponse("电话或邮箱填写有误");
        } else {
            Teacher teacher = new Teacher( account, password, name, sex, phone, birthday, avatar);
            response = accountService.addTeacher(teacher);
        }
        return response;
    }

    @PostMapping("/addStudent")
    public Object addStudent(
            @RequestParam("account") String account,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("sex") Integer sex,
            @RequestParam("major") String classname,
            @RequestParam("phone") String phone,
            @RequestParam("birthday") java.sql.Date birthday
    ) {

        ServiceResponse<Student> response;
        String avatar = "/image/avatars/user-default.png";
        if (!Utils.isPhone(phone)) {
            response = ServiceResponse.createFailResponse("电话填写有误");
        } else {
            Student student = new Student(account, password, name, sex, avatar, classname, phone, birthday);
            response = accountService.addStudent(student);
        }
        return response;
    }

    /**
     * 修改学生和教师的两种方法
     */
    @RequestMapping("/modifyTeacher")
    public Object modifyTeacher(
            @RequestParam("teacherId") Integer teacherId,
//            @RequestParam(value = "adminId", required = false) Integer adminId,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "sex", required = false) Integer sex,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "birthday", required = false) java.sql.Date birthday,
            @RequestParam(value = "avatar", required = false) String avatar
    ) {
        ServiceResponse<Teacher> response;
        Teacher teacher = new Teacher( account, password, name, sex, phone, birthday, avatar);
        teacher.setTeacherId(teacherId);
        response = accountService.modifyTeacher(teacher);
        return response;
    }

    @RequestMapping("/modifyStudent")
    public Object modifyStudent(
            @RequestParam("studentId") Integer studentId,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "sex", required = false) Integer sex,
            @RequestParam(value = "avatar", required = false) String avatar,
            @RequestParam(value = "major", required = false) String classname,
            @RequestParam(value = "face", required = false) String face,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "birthday", required = false) Date birthday
    ) {
        ServiceResponse<Student> response;
        Student student = new Student(account, password, name, sex, avatar, classname, phone, birthday);
        student.setStudentId(studentId);
        student.setStudentFace(face);
        response = accountService.modifyStudent(student);
        return response;
    }

    /**
     * 查询所有用户账号
     */
    @PostMapping("/findAccount")
    public Object findAccount(@RequestParam("type") Integer type) {
        switch (type) {
            case 1:
                return accountService.findAllTeacher();
            case 2:
                return accountService.findAllStudent();
            default:
                return ServiceResponse.createFailResponse("查询失败");
        }
    }

    /**
     * 通过模糊查询查找用户
     *
     * @return 返回是一个数组
     */
    @PostMapping("/findAccountByColumn")
    public Object findAccountByColumn(
            @RequestParam("type") Integer type,
            @RequestParam("column") String column,
            @RequestParam("value") Object value
    ) {

        switch (type) {
            case 1:
                return accountService.findTeacher(column, value);
            case 2:
                return accountService.findStudent(column, value);
            default:
                return ServiceResponse.createFailResponse("查询失败");
        }
    }

    /**
     * 验证账户是否存在
     */
    @PostMapping("/confirmAccount")
    public Object confirmAccount(
            @RequestParam("type") Integer type,
            @RequestParam("account") String account,
            @RequestParam(value = "phone", required = false) String phone
    ) {
        if (phone.isEmpty()) {
            return accountService.confirmAccount(type, account);
        } else {
            return accountService.confirmAccount(type, account, phone);
        }
    }

}
