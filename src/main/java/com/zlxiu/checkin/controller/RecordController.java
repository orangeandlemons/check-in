package com.zlxiu.checkin.controller;

import com.zlxiu.checkin.service.RecordService;
import com.zlxiu.checkin.utils.Utils;
import com.zlxiu.checkin.bean.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService recordService;

    /**
     * 进行签到操作，如果数据库没有该学生记录，则调用addRecord；若已经存在，则调用modifyRecord进行修改
     */
    @PostMapping("/doRecord")
    public Object doRecord(
            @RequestParam("attendId") Integer attendId,
            @RequestParam("studentId") Integer studentId,
            @RequestParam(value = "time") String time,
            @RequestParam(value = "location") String location
    ){
        /**
         * 从数据库查看当前学生的人脸信息位置，并与签到上传的图片进行比对识别，返回true或false
         * 先默认始终为真
         */
        String path2 = "src/main/resources/static/check/" + studentId + "_" + attendId + ".png";
        String path1 = "src/main/resources/static/face/" + studentId + ".png";
        String path = "/image/check/" + studentId + "_" + attendId + ".png";
        Integer result = Utils.face(path1, path2);
        System.out.println("confidence = " + result);
        Record record = new Record(attendId,studentId, Timestamp.valueOf(time),location,path);
        record.setRecordResult(result);
        return recordService.modifyRecord(record);
    }

    @PostMapping("/modifyRecord")
    public Object modifyRecord(
            @RequestParam("attendId") Integer attendId,
            @RequestParam("studentId") Integer studentId,
            @RequestParam("result") Integer result,
            @RequestParam(value = "time",required = false) Timestamp time,
            @RequestParam(value = "location",required = false) String location
    ){
        Record record = new Record();
        record.setAttendId(attendId);
        record.setStudentId(studentId);
        record.setRecordResult(result);
        record.setRecordTime(time);
        record.setRecordLocation(location);
        return recordService.modifyRecord(record);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteRecord")
    public Object deleteRecord(
            @RequestParam("attendId") Integer attendId,
            @RequestParam("studentId") Integer studentId
    ){
        return recordService.deleteRecord(attendId,studentId);
    }

    /**
     * 查找
     */
    @PostMapping("/findRecordByColumn")
    public Object findRecordByColumn(String column, Object value){
        return recordService.findRecordByColumn(column,value);
    }

    @PostMapping("/findAllRecord")
    public Object findRecordByColumn(@RequestParam("attendId") Integer attendId){
        return recordService.findAllRecord(attendId);
    }

    @PostMapping("/findRecordByMap")
    public Object findRecordByMap(@RequestParam Map<String, Object> map){
        return recordService.findRecordByMap(map);
    }

    @PostMapping("/findRecordByTime")
    public Object findRecordByTime(@RequestParam("time") String time){
        return recordService.findRecordByTime(time);
    }

    @PostMapping("/findAllStudentRecord")
    public Object findAllStudentRecord(@RequestParam("courseId") Integer courseId){
        return recordService.findAllStudentRecord(courseId);
    }

    @PostMapping("/findAllRecordWithAttend")
    public Object findAllRecordWithAttend(){
        return recordService.findAllRecordWithAttend();
    }

}
