package com.lac.wechat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lac.wechat.domain.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: EventMapper <br/>
 *
 * @author lac
 * @version 1.0
 * @date 2020/1/5 0005 - 1:02
 */
public interface EventMapper extends BaseMapper<Event> {

    public List<Event> getEventByQuery(@Param("query") String query, @Param("userId") String userId);

    Event selectByEventId(@Param("eventId") String eventId, @Param("userId") String userId);
}
