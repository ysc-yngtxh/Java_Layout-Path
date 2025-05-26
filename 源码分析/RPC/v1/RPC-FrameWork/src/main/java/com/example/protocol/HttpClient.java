package com.example.protocol;

import com.example.common.Invocation;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-07 下午8:30:00
 * @apiNote TODO
 */
public class HttpClient {

	public String sendRequest(String hostName, Integer port, Invocation invocation) {
		try {
			// 用户的配置
			URL url = new URL("http", hostName, port, "/");
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();

			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoOutput(true);

			// 配置
			OutputStream outputStream = httpUrlConnection.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);

			oos.writeObject(invocation);
			oos.flush();
			oos.close();

			InputStream inputStream = httpUrlConnection.getInputStream();
			String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

			return result;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
