package com.lac.wechat.service;

import com.lac.wechat.vo.AccessToken;
import com.lac.wechat.vo.JsapiTicket;

public interface ApiService {

    /**
     * 获取access_token
     *
     * @return
     */
    public AccessToken fetchAccessToken();


    public JsapiTicket fetchJsapiTicket();


}
