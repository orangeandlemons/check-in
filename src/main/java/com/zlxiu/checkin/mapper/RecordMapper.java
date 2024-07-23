package com.zlxiu.checkin.mapper;


import com.zlxiu.checkin.bean.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordMapper extends BaseMapper<Record> {

    List<Record> findRecordByTime(String recordTime);

//    查询某次考勤任务的所以签到记录
    List<Record> findAllRecord(Integer attendId);

    List<Record> findAllRecordWithAttend();
}
