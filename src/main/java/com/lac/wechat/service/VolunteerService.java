package com.lac.wechat.service;

import com.lac.wechat.domain.Volunteer;

import java.util.List;

public interface VolunteerService {

    /**
     * 活动列表
     * @return
     */
    List<Volunteer> getVoList();

    /**
     * 活动详情
     * @param voId
     * @return
     */
    Volunteer details(String voId);

    /**
     * 参加活动报名
     * @param voId
     */
    void joinVo(String voId);
}
