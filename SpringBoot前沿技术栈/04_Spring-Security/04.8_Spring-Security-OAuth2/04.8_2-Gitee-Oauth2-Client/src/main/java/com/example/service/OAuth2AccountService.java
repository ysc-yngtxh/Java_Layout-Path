package com.example.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mapper.OAuth2AccountMapper;
import com.example.oauth2.entity.OAuth2ThirdPartyAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-29 14:00
 * @apiNote TODO
 */
@Service
public class OAuth2AccountService {

	@Autowired
	private OAuth2AccountMapper oAuth2AccountMapper;

	public void save(OAuth2ThirdPartyAccount oAuth2ThirdPartyAccount) {
		oAuth2AccountMapper.insert(oAuth2ThirdPartyAccount);
	}

	public OAuth2ThirdPartyAccount getAccess(Integer uniqueId) {
		return oAuth2AccountMapper.selectOne(Wrappers.<OAuth2ThirdPartyAccount>lambdaQuery().eq(OAuth2ThirdPartyAccount::getUniqueId, uniqueId));
	}
}
