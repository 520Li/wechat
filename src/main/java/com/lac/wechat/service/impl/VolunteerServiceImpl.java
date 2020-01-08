package com.lac.wechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lac.wechat.dao.VolunteerMapper;
import com.lac.wechat.dao.VolunteerUserMapper;
import com.lac.wechat.domain.User;
import com.lac.wechat.domain.Volunteer;
import com.lac.wechat.domain.VolunteerUser;
import com.lac.wechat.service.VolunteerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    private VolunteerMapper volunteerMapper;
    @Autowired
    private VolunteerUserMapper volunteerUserMapper;
    @Autowired
    private HttpSession session;

    @Override
    public List<Volunteer> getVoList() {
        QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
        return volunteerMapper.selectList(wrapper);
    }

    @Override
    public Volunteer details(String voId) {
        User user = (User) session.getAttribute("login_user");
        return volunteerMapper.selectByVoId(voId, user.getUserId());
    }

    @Override
    public void joinVo(String voId) {
        User user = (User) session.getAttribute("login_user");
        VolunteerUser entity = VolunteerUser.builder()
                .volunteerId(voId)
                .userId(user.getUserId())
                .createTime(new Date())
                .build();
        volunteerUserMapper.insert(entity);
    }
}
