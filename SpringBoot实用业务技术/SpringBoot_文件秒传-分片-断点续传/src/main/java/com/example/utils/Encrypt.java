package com.example.utils;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-31 14:00
 * @apiNote TODO
 */
public class Encrypt {

	// 获取字节文件的 md5值
	public static String calculateHash(byte[] fileChunk) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(fileChunk);
		byte[] hash = md.digest();
		ByteBuffer byteBuffer = ByteBuffer.wrap(hash);
		StringBuilder hexString = new StringBuilder();
		while (byteBuffer.hasRemaining()) {
			hexString.append(String.format("%02x", byteBuffer.get()));
		}
		return hexString.toString();
	}

}
