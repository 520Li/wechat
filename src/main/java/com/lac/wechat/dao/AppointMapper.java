package com.lac.wechat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lac.wechat.domain.Appoint;

import java.util.List;

/**
 * ClassName: AppointMapper <br/>
 *
 * @author lac
 * @version 1.0
 * @date 2020/1/4 0004 - 9:50
 */
public interface AppointMapper extends BaseMapper<Appoint> {
    List<Appoint> findAppointByUser(String userId);
}
