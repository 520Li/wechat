package com.lac.wechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lac.wechat.dao.BusBuildingInfoMapper;
import com.lac.wechat.domain.BusBuildingInfo;
import com.lac.wechat.service.BuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;


@Service
public class BuildServiceImpl implements BuildService {

    @Autowired
    private BusBuildingInfoMapper busBuildingInfoMapper;

    @Override
    public List<BusBuildingInfo> getBuList() {
        QueryWrapper<BusBuildingInfo> wrapper = new QueryWrapper<>();
        List<BusBuildingInfo> infos = busBuildingInfoMapper.selectList(wrapper);
        return infos;
    }

    @Override
    public List<BusBuildingInfo> getEnergyList() {
        QueryWrapper<BusBuildingInfo> wrapper = new QueryWrapper<>();
        List<BusBuildingInfo> infos = busBuildingInfoMapper.selectList(wrapper);
        return infos;
    }
}
