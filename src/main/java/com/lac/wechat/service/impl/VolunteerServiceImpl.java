package com.lac.wechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lac.wechat.dao.UserMapper;
import com.lac.wechat.dao.VolunteerMapper;
import com.lac.wechat.dao.VolunteerUserMapper;
import com.lac.wechat.domain.User;
import com.lac.wechat.domain.Volunteer;
import com.lac.wechat.domain.VolunteerUser;
import com.lac.wechat.service.VolunteerService;
import com.lac.wechat.vo.Result;
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
    private UserMapper userMapper;
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
    public Result joinVo(String voId) {
        User user = (User) session.getAttribute("login_user");
        if (user.getUserVolunteer().equals("02")) {
            return new Result(false, "你还不是志愿者,请先申请成为志愿者！");
        }
        VolunteerUser entity = VolunteerUser.builder()
                .volunteerId(voId)
                .userId(user.getUserId())
                .createTime(new Date())
                .build();
        volunteerUserMapper.insert(entity);
        user.setUserVolunteer("01");
        session.setAttribute("login_user", user);
        return new Result(true, "报名成功！");
    }
}
