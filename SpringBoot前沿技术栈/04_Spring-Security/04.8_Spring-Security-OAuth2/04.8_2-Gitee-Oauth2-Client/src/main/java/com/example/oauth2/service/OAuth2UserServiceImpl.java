package com.example.oauth2.service;

import com.example.oauth2.convert.GiteeOAuth2AccountConvert;
import com.example.oauth2.entity.OAuth2ThirdPartyAccount;
import com.example.service.OAuth2AccountService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-29 12:40
 * @apiNote TODO OAuth2UserService 默认实现类是 DefaultOAuth2UserService，
 *               因此自定义OAuth2用户服务类继承 DefaultOAuth2UserService
 */
@Service
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

	@Autowired
	private OAuth2AccountService oAuth2AccountService;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// OAuth2 用户类
		OAuth2User oAuth2User = super.loadUser(userRequest);

		// 判断并保存用户
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		OAuth2ThirdPartyAccount oAuth2ThirdPartyAccount;
		if ("gitee".equals(registrationId)) {
			oAuth2ThirdPartyAccount = GiteeOAuth2AccountConvert.convert(userRequest, oAuth2User);
			System.out.println("oAuth2ThirdPartyAccount = " + oAuth2ThirdPartyAccount);
			OAuth2ThirdPartyAccount accessAccount = oAuth2AccountService.getAccess(oAuth2ThirdPartyAccount.getUniqueId());
			if (Objects.isNull(accessAccount)) {
				oAuth2AccountService.save(oAuth2ThirdPartyAccount);
			}
		}

		return oAuth2User;
	}
}
