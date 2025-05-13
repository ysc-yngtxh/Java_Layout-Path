package com.example.config;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpConfig {

    /**
     * OkHttp 客户端配置
     *
     * @return OkHttp 客户端配
     */
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory(), x509TrustManager()) // SSL证书信任管理器
                .hostnameVerifier(hostnameVerifier())                  // 主机名校验
                .retryOnConnectionFailure(false)  // 是否开启缓存
                .connectionPool(pool())                               // 连接池
                .connectTimeout(15L, TimeUnit.SECONDS)    // 连接超时时间
                .readTimeout(15L, TimeUnit.SECONDS)       // 读取超时时间
                .followRedirects(true)                   // 是否允许重定向
                .build();
    }

    /**
     * 忽略证书校验
     *
     * @return 证书信任管理器
     */
    @Bean
    public X509TrustManager x509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {}

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {}

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    /**
     * 信任所有 SSL 证书
     *
     * @return
     */
    @Bean
    public SSLSocketFactory sslSocketFactory() {
        try {
            TrustManager[] trustManagers = new TrustManager[]{x509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManagers, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 连接池配置
     *
     * @return 连接池
     */
    @Bean
    public ConnectionPool pool() {
        // 最大连接数、连接存活时间、存活时间单位（分钟）
        return new ConnectionPool(200, 5, TimeUnit.MINUTES);
    }

    /**
     * 信任所有主机名
     *
     * @return 主机名校验
     */
    @Bean
    public HostnameVerifier hostnameVerifier() {
        return (s, sslSession) -> true;
    }
}
