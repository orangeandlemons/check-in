package com.zlxiu.checkin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zlxiu.checkin.bean.Leave;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LeaveMapper extends BaseMapper<Leave> {

    List<Leave> findLeavedStudent(Integer courseId, Timestamp startTime, Timestamp endTime);

    List<Leave> findAllLeave(Integer courseId);

    List<Leave> findAllLeaveWithStudent();
}
