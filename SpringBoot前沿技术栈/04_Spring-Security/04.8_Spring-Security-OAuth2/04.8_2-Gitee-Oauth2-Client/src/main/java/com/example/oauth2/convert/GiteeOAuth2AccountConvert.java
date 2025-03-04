package com.example.oauth2.convert;

import com.example.oauth2.entity.OAuth2ThirdPartyAccount;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-29 12:28
 * @apiNote TODO Gitee 的属性转换类
 */
public class GiteeOAuth2AccountConvert {

    public static OAuth2ThirdPartyAccount convert(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        // 创建本地应用的账户对象
        OAuth2ThirdPartyAccount account = new OAuth2ThirdPartyAccount();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        account.setUniqueId((Integer) attributes.get("id"));
        account.setLogin((String) attributes.get("login")); 
        account.setName((String) attributes.get("name"));
        account.setAvatarUrl((String) attributes.get("avatar_url"));
        account.setRegistrationId("gitee");
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        account.setCredentials(accessToken.getTokenValue());
        
        // expiresAt默认采用ISO 8601标准时间格式(使用零时区)
        ZonedDateTime zonedDateTime = accessToken.getExpiresAt().atZone(ZoneId.systemDefault());
        account.setCredentialsExpiresAt(Timestamp.from(zonedDateTime.toInstant()));
        account.setCreateTime(new Timestamp(new Date().getTime()));

        return account;
    }
}
