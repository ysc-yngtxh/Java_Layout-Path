package com.example.service.impl;

import com.example.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StableServiceImpl implements TestService {

	private final static Logger log = LoggerFactory.getLogger(StableServiceImpl.class);

	@Override
	public void doService() {
		log.info("我是稳定版  纵天崩地裂 我依旧稳如狗....");
	}
}
