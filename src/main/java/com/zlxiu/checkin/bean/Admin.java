package com.zlxiu.checkin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员表
 */
@Data
@NoArgsConstructor
public class Admin {
    @TableId(type = IdType.AUTO)
    private Integer adminId;
    private String adminAccount;
    private String adminPassword;
}
