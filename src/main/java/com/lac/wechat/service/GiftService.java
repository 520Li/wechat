package com.lac.wechat.service;


import com.lac.wechat.domain.Gift;
import com.lac.wechat.vo.Result;

import java.util.List;
import java.util.Map;

public interface GiftService {


    List<Gift> findGift();

    Result insertGiftLog(List<Map> gifts);
}
