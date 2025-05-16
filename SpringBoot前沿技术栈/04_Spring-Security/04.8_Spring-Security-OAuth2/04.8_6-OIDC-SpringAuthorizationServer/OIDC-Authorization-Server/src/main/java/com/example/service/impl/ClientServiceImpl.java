package com.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.entity.OAth2AuthorizationConsent;
import com.example.mapper.OAuth2AuthorizationConsentMapper;
import com.example.service.ClientService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-12-05 11:00
 * @apiNote TODO
 */
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private OAuth2AuthorizationConsentMapper consentMapper;

	@Autowired
	private RegisteredClientRepository clientRepository;

	@Autowired
	private OAuth2AuthorizationConsentService consentService;

	@Override
	public List<RegisteredClient> findRegisteredClientsByPrincipalName(String principalName) {
		// 根据 principalName 查询出 OAth2AuthorizationConsent
		List<OAth2AuthorizationConsent> oAth2AuthorizationConsents = consentMapper.selectList(
				Wrappers.<OAth2AuthorizationConsent>lambdaQuery().eq(OAth2AuthorizationConsent::getPrincipalName, principalName)
		                                                                                     );
		// 根据 oAth2AuthorizationConsents 查询所有的RegisteredClient对象，然后存入RegisteredClient集合
		List<RegisteredClient> arrayList = new ArrayList<>();
		oAth2AuthorizationConsents.forEach(oAth2AuthorizationConsent -> {
			arrayList.add(clientRepository.findById(oAth2AuthorizationConsent.getRegisteredClientId()));
		});
		// 返回已授权的客户端列表
		return arrayList;
	}

	@Override
	public void revokeAuthorization(String clientId, String principalName) {
		// 构建已同意授权信息
		OAuth2AuthorizationConsent consent = OAuth2AuthorizationConsent
				.withId(clientId, principalName)
				// TODO 这儿构建需要加上授权的范围，但实际上删除授权信息并不需要授权范围的数据，可实际上没有会报错
				// 写法一：
				// .authorities(auth ->
				//         auth.addAll(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
				// )
				// 写法二：
				// .authority(new SimpleGrantedAuthority("SCOPE_openid"))
				// .authority(new SimpleGrantedAuthority("SCOPE_profile"))
				// .authority(new SimpleGrantedAuthority("SCOPE_groups"))
				// .authority(new SimpleGrantedAuthority("SCOPE_emalls"))
				// .authority(new SimpleGrantedAuthority("SCOPE_pull_requests"))
				// 写法三：
				.scope("")
				.build();
		// 删除已同意的授权信息
		consentService.remove(consent);
	}
}
