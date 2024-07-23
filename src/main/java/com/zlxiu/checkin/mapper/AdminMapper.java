package com.zlxiu.checkin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlxiu.checkin.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<Admin> {

}
