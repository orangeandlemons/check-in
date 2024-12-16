## 介绍

人脸识别签到APP服务端代码

## 软件架构

本系统基于 Spring Boot 和 MyBatis-Plus ，严谨的MVC结构：

- **实体类:** `bean`
- **持久层:** `mapper`
- **业务层:** `service`
- **控制层:** `controller`

系统主要有教师和学生两个用户角色，其对应功能具体介绍如下。

## 学生模块

学生模块的功能包括：登录、注册、找回密码、退出登录、加入课程、签到、请假、个人信息修改。

- **加入课程:** 学生点击加入课程按钮，通过课程码查询对应课程，并验证是否是自己的课程。验证通过后，后台将学生添加到课程中。
- **退出课程:** 学生通过点击课程详情界面的删除课程按钮，实现退出课程。
- **签到:** 学生在课程详情的签到界面点击签到。有三种签到方式：定位签到、人脸识别签到和手势识别签到。这三种签到都需要定位验证，满足条件后即可完成签到。
- **请假:** 学生在课程详情的请假界面点击申请请假，弹出输入框，输入请假开始时间、天数和理由，向后台提交请假申请，并显示请假审批状态。
- **个人信息修改:** 学生可以在个人信息页面查看并修改详细信息。可以通过输入新信息或选择新的头像或人脸图像，替换已有信息。

## 教师模块

教师模块的功能包括：登录、注册、找回密码、退出登录、课程管理、发布签到、学员管理、考勤统计、审批请假、个人信息修改。

- **课程管理:** 教师可以创建课程，输入课程信息和选取课程头像，创建课程并获取课程码。教师可以在课程详情界面修改课程名和简介，或删除课程。
- **发布签到:** 教师在签到界面创建签到，根据提示选择签到方式、开始时间和结束时间，发布签到。
- **学员管理:** 教师可以在课程中查看学员信息，并可以删除学员。
- **考勤统计:** 教师可以在课程统计详情中查看所有学员的签到情况，并在对应签到中查看学生的具体签到情况。
- **审批请假:** 教师在课程请假界面查看学员请假信息，并在审核界面填写审核意见和审批结果，提交至服务器，更新请假状态。
- **个人信息修改:** 教师可以在个人信息页面修改个人信息。

## 签到模块

签到模块的功能包括：人脸识别签到、定位签到和手势签到。

- **人脸识别签到:** 通过采集学生图像上传至服务器，进行人脸识别比对，获取签到结果。
- **定位签到:** 通过定位学生位置上传位置信息，判断是否在签到范围内，决定签到结果。
- **手势签到:** 学生输入签到手势，手势正确则签到成功。

## 持久层

持久层包含多个Mapper，用于增删改查操作：

- `TeacherMapper`：教师相关操作
- `StudentMapper`：学生相关操作
- `CourseMapper`：课程相关操作
- `LeaveMapper`：请假相关操作
- `RecordMapper`：签到记录相关操作
- `AttendMapper`：签到相关操作
- `CourseStudentMapper`：根据课程查找学生

## 业务层

业务层负责业务模块的实现，调用持久层接口。具体包括：

- 账户注册、登录、修改
- 签到的增删改查
- 签到记录统计
- 考勤记录查询更改
- 请假的增删改查
- 课程的添加和修改

## 控制层

控制层负责业务模块流程控制，获取APP发送的参数，通过调用业务层接口控制业务流程。包括登录、注册、添加课程、添加签到等。


<img width="488" alt="image" src="https://github.com/user-attachments/assets/31db7005-459a-4414-831d-867ccc768e3b">

<img width="772" alt="image" src="https://github.com/user-attachments/assets/3c7e5c66-eb70-4e8a-895d-a8aeb04f4e96">

<img width="442" alt="image" src="https://github.com/user-attachments/assets/dab0b51a-81ac-495d-9f84-1807aafc0e3b">


## Introduction

This is the backend code for the Facial Recognition Attendance App.

## Software Architecture

This system is based on Spring Boot and MyBatis-Plus, with strict MVC structure:

- **Entity Layer:** `bean`
- **Persistence Layer:** `mapper`
- **Service Layer:** `service`
- **Controller Layer:** `controller`

The system primarily serves two user roles: Teachers and Students. The functionalities for each role are detailed below.

## Student Module

The student module includes the following features: Login, Registration, Password Recovery, Logout, Join Course, Check-in, Leave Request, and Personal Information Update.

- **Join Course:** Students can join a course by clicking the join course button, entering the course code, and verifying if the course belongs to them. The backend adds the student to the course upon verification.
- **Leave Course:** Students can leave a course by accessing the course details and selecting the delete course option from the course information interface.
- **Check-in:** Students can check in by navigating to the check-in interface within the course details. There are three check-in methods: Location Check-in, Facial Recognition Check-in, and Gesture Recognition Check-in. Each method requires location verification. Upon meeting the criteria, the check-in is completed.
- **Leave Request:** Students can request leave by clicking on the leave request option within the course details. A pop-up window will appear where they can enter the start time, duration, and reason for the leave. The leave request is then sent to the backend, and the approval status is displayed in the leave request interface.
- **Personal Information Update:** Students can view and edit their detailed information on the personal information page. They can update text information by entering new details in the corresponding fields. They can also change their avatar or facial image by selecting a new photo from the album or taking a new picture, which will replace the existing information.

## Teacher Module

The teacher module includes the following features: Login, Registration, Password Recovery, Logout, Course Management, Publish Check-in, Student Management, Attendance Statistics, Leave Approval, and Personal Information Update.

- **Course Management:** Teachers can create courses by entering course information and selecting a course avatar. They will receive a course code upon creation. Teachers can also modify the course name and description or delete the course in the course details interface.
- **Publish Check-in:** Teachers can create check-ins in the check-in interface by selecting the check-in method, start time, and end time based on the pop-up prompts and then publish the check-in.
- **Student Management:** Teachers can view student information within a course and remove students if necessary.
- **Attendance Statistics:** Teachers can view the attendance status of all students in the course statistics details. They can also check individual check-in details for each student.
- **Leave Approval:** Teachers can view student leave requests in the course leave interface. They can enter the approval opinion and result in the approval interface, sending the request to the server, which updates the leave status for students to view.
- **Personal Information Update:** Teachers can update their personal information on the personal information page.

## Check-in Module

The check-in module includes the following methods: Facial Recognition Check-in, Location Check-in, and Gesture Recognition Check-in.

- **Facial Recognition Check-in:** Students' images are captured and uploaded to the server for facial recognition comparison to obtain the check-in result.
- **Location Check-in:** Students' locations are tracked and uploaded. The check-in result is determined by verifying if the location is within the check-in range.
- **Gesture Check-in:** Students enter a check-in gesture, and if the gesture is correct, the check-in is successful.

## Persistence Layer

The persistence layer is divided into several mappers for CRUD operations:

- `TeacherMapper` for teacher-related operations
- `StudentMapper` for student-related operations
- `CourseMapper` for course-related operations
- `LeaveMapper` for leave request operations
- `RecordMapper` for check-in record operations
- `AttendMapper` for check-in operations
- `CourseStudentMapper` for finding students by course

## Service Layer

The service layer handles the implementation of business modules, invoking persistence layer interfaces. It includes:

- Account registration, login, and modification
- Check-in CRUD operations
- Check-in record statistics
- Attendance record query and modification
- Leave request CRUD operations
- Course addition and modification

## Controller Layer

The controller layer manages the business module workflows, retrieving parameters sent by the app, and controlling business processes by invoking service layer interfaces. This includes login, registration, course addition, and check-in addition.







