package com.example;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.StandardEnvironment;

// @SpringBootTest
class SpringBootJasyptApplicationTests {

	@Autowired
	private StringEncryptor jasyptStringEncryptor;

	// ================ ↓↓↓↓↓↓ 需加载spring容器方式 ↓↓↓↓↓↓ ================
	@Test
	public void encrypt() {
		System.out.println("加密： " + jasyptStringEncryptor.encrypt("root"));
	}

	@Test
	public void decrypt() {
		String psw = "ftPJhQj0Pv/g82VIXYV4ulzzCXgJoxXRQ5LA+a8fmIGew/Ppiyv6zQoDT0aCN3xq";
		System.out.println("解密： " + jasyptStringEncryptor.decrypt(psw));
	}

	// ================ ↓↓↓↓↓↓ 下面为无需加载spring容器方式 ↓↓↓↓↓↓ ================

	@Test
	public void test() {
		// 对应配置文件中配置的加密密钥
		System.setProperty("jasypt.encryptor.password", "yjwk");
		StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());
		System.out.println("加密： " + stringEncryptor.encrypt("131474"));
		String psw = "ftPJhQj0Pv/g82VIXYV4ulzzCXgJoxXRQ5LA+a8fmIGew/Ppiyv6zQoDT0aCN3xq";
		System.out.println("解密： " + stringEncryptor.decrypt(psw));
	}

}
