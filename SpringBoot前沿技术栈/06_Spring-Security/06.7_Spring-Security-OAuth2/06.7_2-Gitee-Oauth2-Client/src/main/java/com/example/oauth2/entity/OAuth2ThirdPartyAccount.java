package com.example.oauth2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-29 12:41
 * @apiNote TODO OAuth2的映射信息类
 */
@Data
@TableName("oauth2_account")
public class OAuth2ThirdPartyAccount implements Serializable {
    @Serial
    private static final long serialVersionUID = 3304750105828784062L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Integer uniqueId;
    private String login;
    private String name;
    private String avatarUrl;
    private String registrationId;
    private String credentials;
    private Timestamp credentialsExpiresAt;
    private Timestamp createTime;
}
