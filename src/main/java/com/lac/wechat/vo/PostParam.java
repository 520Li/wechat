package com.lac.wechat.vo;

import lombok.Data;

@Data
public class PostParam {
    /*
    signature:   [d7b6c53feba622373efc495da3ef78971ddcb7a5]
    timestamp:   [1577675730]
    nonce:   [1168738644]
    openid:   [omRjy1OC0-3_iFigWCNsKwsxAcAg]
     */

    private String signature;
    private String timestamp;
    private String nonce;
    private String openid;//微信用户id


}
