package com.example.encoding;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-30 20:30
 * @apiNote TODO 自定义的明文密码编码器
 */
public class PlainTextPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		// 不对密码进行任何处理直接返回
		return Objects.requireNonNull(rawPassword).toString();
	}

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("自定义编码器被调用了！");
        // 比较原始密码和存储的密码是否相等
        return Objects.requireNonNull(rawPassword).toString().equals(encodedPassword);
    }
}
