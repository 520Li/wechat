package com.lac.wechat.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: User <br/>
 *
 * @author lac
 * @version 1.0
 * @date 2020/1/2 0002 - 20:28
 */
@TableName("zly_user")
@Data
public class User {

    @TableId(type = IdType.UUID)
    private String userId; //微信用户 openid

    private String userName;
    private String userType;
    private String userCard;
    private String userIphone;
    private String userPath;
    private Integer userCore;
    private Integer userSum;

    private Date createTime;
    private Date updateTime;

}
