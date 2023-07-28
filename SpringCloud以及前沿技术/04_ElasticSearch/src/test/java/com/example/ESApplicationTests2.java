package com.example;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 游家纨绔
 * @dateTime 2023-07-28 12:51
 * @apiNote TODO 测试类
 */
@SpringBootTest
public class ESApplicationTests2 {

    private final Logger log = LoggerFactory.getLogger(ESApplicationTests2.class);

    // TODO 从 ElasticSearch7.17 这个版本开始，原先的 Java 高级客户端. Java High Level REST Client 废弃了，不支持了。
    //  现在的客户端叫做 Elasticsearch Java API Client。

    //  TODO 必须要熟悉 ElasticSearch 的查询脚本，建议先在 Kibana 中把操作脚本写好，然后再翻译成 Java 代码，
    //   或者直接拷贝到 Java 代码中，非常不建议上来就整 Java 代码，那样很容易出错。

    /**
     * Elasticsearch Java API Client 是 Elasticsearch 的官方 Java API，
     * 这个客户端为所有 Elasticsearch APIs 提供强类型的请求和响应。
     * <p>
     * 这里跟大家解释下什么是强类型的请求和响应：
     * 因为所有的 Elasticsearch APIs 本质上都是一个 RESTFul 风格的 HTTP 请求，所以当我们调用这些 Elasticsearch APIs 的时候，
     * 可以就当成普通的 HTTP 接口来对待，例如使用 HttpUrlConnection 或者 RestTemplate 等工具来直接调用，如果使用这些工具直接调用，
     * 就需要我们自己组装 JSON 参数，然后自己解析服务端返回的 JSON。而强类型的请求和响应则是系统把请求参数封装成一个对象了，
     * 我们调用对象中的方法去设置就可以了，不需要自己手动拼接 JSON 参数了，请求的结果系统也会封装成一个对象，不需要自己手动去解析 JSON 参数了。
     */


    // 这就是一个普通的 HTTP 请求，请求参数就是查询的条件，这个条件是一个 JSON 字符串，需要我们自己组装，
    // 请求的返回值也是一个 JSON 字符串，这个 JSON 字符串也需要我们自己手动去解析，这种可以算是弱类型的请求和响应
    @Test
    void tst() throws IOException {
        URL url = new URL("http://localhost:9200/shop/_search?pretty");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("content-type", "application/json;charset=utf-8");
        // 允许输出流/允许参数
        con.setDoOutput(true);
        // 获取输出流，就是获取请求路径的一个输出
        OutputStream out = con.getOutputStream();
        String params = "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"title\": \"小米\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        out.write(params.getBytes());  // 输出的请求中携带的数据

        if (con.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String str = null;
            while ((str = br.readLine()) != null) {
                System.err.println(str);
            }
            br.close();
        }
    }

    @Test
    void createIndex() throws IOException {
        ElasticsearchClient elasticsearchClient = getElasticsearchClient();
        CreateIndexResponse response = elasticsearchClient.indices().create(c -> c.index("products"));
        // 响应状态
        boolean acknowledged = response.acknowledged();
        boolean shardsAcknowledged = response.shardsAcknowledged();
        String index = response.index();
        log.info("创建索引状态:{}", acknowledged);
        log.info("已确认的分片:{}", shardsAcknowledged);
        log.info("索引名称:{}", index);
    }

    private ElasticsearchClient getElasticsearchClient() {
        // 创建低级客户端
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "password"));

        RestClientBuilder builder = RestClient.builder( new HttpHost("localhost", 9200) )
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        RestClient restClient = builder.build();

        // 使用Jackson映射器创建传输
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }

}
