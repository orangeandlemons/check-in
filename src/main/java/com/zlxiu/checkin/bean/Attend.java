package com.zlxiu.checkin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * 考勤任务表
 */
@Data
@NoArgsConstructor
public class Attend {
    @TableId(type = IdType.AUTO)
    private Integer attendId;
    private Integer courseId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp attendStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp attendEnd;
    private Double attendLongitude;
    private Double attendLatitude;
    private String attendLocation;
    private Integer attendType;
    private String attendGesture;

    @TableField(exist = false)
    private List<Record> records;

    public Attend(Integer courseId, Timestamp attendStart, Timestamp attendEnd, Double attendLongitude, Double attendLatitude, String attendLocation, Integer attendType) {
        this.courseId = courseId;
        this.attendStart = attendStart;
        this.attendEnd = attendEnd;
        this.attendLongitude = attendLongitude;
        this.attendLatitude = attendLatitude;
        this.attendLocation = attendLocation;
        this.attendType = attendType;
    }
}
