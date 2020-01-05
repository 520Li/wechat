package com.lac.wechat.service;

import com.lac.wechat.domain.Appoint;
import com.lac.wechat.domain.Event;
import com.lac.wechat.domain.User;
import com.lac.wechat.vo.Result;

import java.util.List;

/**
 * ClassName: UserService <br/>
 *
 * @author lac
 * @version 1.0
 * @date 2020/1/3 0003 - 0:21
 */
public interface UserService {

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    public User getUserById(String id);

    /**
     * 实名注册用户
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * 校验手机号是否重复
     *
     * @param user
     * @return
     */
    boolean checkIphone(User user);

    /**
     * 保存新预约
     */
    void saveAppoint(Appoint appoint);

    /**
     * 查询预约
     */
    List<Appoint> findAppointByUser();

    /**
     * 审批新预约
     */
    void applyAppoint();

    /**
     * 查询活动
     */
    List<Event> getEventList(String query);

    /**
     * 查看活动详情
     */
    Event getEventDis(String eventId);

    /**
     * 报名活动
     */
    Result reportEvent(String eventId);
}
