package com.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http请求
 *
 * @author 游家纨绔
 */
public class HttpUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * CloseableHttpClient
	 *
	 * @return
	 */
	public static CloseableHttpClient createDefault() {
		return HttpClientBuilder.create().build();
	}

	/**
	 * get请求
	 *
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		try (CloseableHttpClient client = HttpUtil.createDefault()) {
			HttpGet request = new HttpGet(url);
			CloseableHttpResponse response = client.execute(request);
			int code = response.getCode();
			logger.info("请求URL：" + url + ";code：" + code);
			if (code == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * key-value格式的参数
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, Map<String, Object> params) {
		BufferedReader in = null;
		try {
			HttpClient client = HttpUtil.createDefault();
			HttpPost request = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext(); ) {
				String key = (String) iter.next();
				String value = String.valueOf(params.get(key));
				nvps.add(new BasicNameValuePair(key, value));
			}
			request.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(request);
			int code = response.getCode();
			logger.info("请求URL：" + url + ";code：" + code);
			if (code == HttpStatus.SC_OK) {
				in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					sb.append(line).append(NL);
				}
				in.close();
				return sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 请求json格式的参数
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, String params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		StringEntity entity = new StringEntity(params, StandardCharsets.UTF_8);
		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			int code = response.getCode();
			logger.info("请求URL：" + url + ";code：" + code);
			if (code == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				String jsonString = EntityUtils.toString(responseEntity);
				return jsonString;
			}
			if (response != null) {
				response.close();
			}
			httpclient.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
