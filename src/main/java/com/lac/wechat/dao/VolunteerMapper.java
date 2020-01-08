package com.lac.wechat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lac.wechat.domain.Volunteer;
import org.apache.ibatis.annotations.Param;

public interface VolunteerMapper extends BaseMapper<Volunteer> {

    Volunteer selectByVoId(@Param("voId") String voId, @Param("userId") String userId);

}
