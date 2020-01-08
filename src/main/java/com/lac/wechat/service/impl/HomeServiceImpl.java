package com.lac.wechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lac.wechat.dao.HomeMapper;
import com.lac.wechat.domain.Home;
import com.lac.wechat.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeMapper homeMapper;


    @Override
    public List<Home> getHomeList() {
        QueryWrapper<Home> wrapper = new QueryWrapper<>();
        return homeMapper.selectList(wrapper);
    }
}
