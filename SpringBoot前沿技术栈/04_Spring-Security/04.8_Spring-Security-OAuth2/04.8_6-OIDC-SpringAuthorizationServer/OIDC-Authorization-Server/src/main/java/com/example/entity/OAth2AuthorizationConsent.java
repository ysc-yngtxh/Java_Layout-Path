package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2024-12-05 12:30
 * @apiNote TODO
 */
@Data
@TableName("oauth2_authorization_consent")
public class OAth2AuthorizationConsent implements Serializable {

	@Serial
	private static final long serialVersionUID = 5329336918776209265L;

	private String registeredClientId;
	private String principalName;
	private String authorities;
}
