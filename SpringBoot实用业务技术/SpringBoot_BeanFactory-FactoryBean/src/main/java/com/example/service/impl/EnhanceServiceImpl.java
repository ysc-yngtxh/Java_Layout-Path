package com.example.service.impl;

import com.example.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EnhanceServiceImpl implements TestService {

	private final static Logger log = LoggerFactory.getLogger(EnhanceServiceImpl.class);

	@Override
	public void doService() {
		// todo 埋点
		log.info("我是增强版 但稳定性需要在线上锤一锤...");
	}
}
