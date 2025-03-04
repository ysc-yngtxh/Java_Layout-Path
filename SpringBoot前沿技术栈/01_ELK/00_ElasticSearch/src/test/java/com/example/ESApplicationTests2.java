package com.example;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.LongTermsAggregate;
import co.elastic.clients.elasticsearch._types.aggregations.MaxAggregate;
import co.elastic.clients.elasticsearch._types.aggregations.MaxAggregation;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsAggregate;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.cat.IndicesResponse;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.SearchTemplateResponse;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexState;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.alibaba.fastjson.JSON;
import com.example.pojo.User;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.tomcat.util.codec.binary.Base64;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // TODO 必须要熟悉 ElasticSearch 的查询脚本，建议先在 Kibana 中把操作脚本写好，然后再翻译成 Java 代码，
    //  或者直接拷贝到 Java 代码中，非常不建议上来就整 Java 代码，那样很容易出错。

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
    void test() throws IOException {
        URL url = new URL("http://localhost:9200/shop/_search");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("content-type", "application/json;charset=utf-8");
        // 包装Basic信息
        String username = "elastic";
        String password = "Z*h-KkWj6FGdy2gYUQpC";
        String auth = username + ":" + password;  // elastic:Z*h-KkWj6FGdy2gYUQpC
        // 对其进行加密
        byte[] rel = Base64.encodeBase64(auth.getBytes());
        // 注意不要使用数组工具类的toString方法：Arrays.toString(rel)
        String res = new String(rel);
        // 设置Basic认证
        con.setRequestProperty("Authorization", "Basic " + res);
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
            String str;
            while ((str = br.readLine()) != null) {
                System.err.println(str);
            }
            br.close();
        }
    }


    // 获取ES操作对象。(可以把ElasticsearchClient对象注入到SpringBoot容器中，方便我们可以使用)
    private static ElasticsearchClient elasticsearchClient() {
        // 基本凭证提供者(用户名、密码)
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "Z*h-KkWj6FGdy2gYUQpC"));

        // 创建低级客户端
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200))
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

        RestClient restClient = builder.build();

        // 使用Jackson映射器创建传输
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }


    @Test
    void createIndex() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        CreateIndexResponse response = elasticsearchClient.indices().create(c -> c.index("products"));
        // 响应状态
        boolean acknowledged = response.acknowledged();
        boolean shardsAcknowledged = response.shardsAcknowledged();
        String index = response.index();
        log.warn("创建索引状态:{}", acknowledged);
        log.warn("已确认的分片:{}", shardsAcknowledged);
        log.warn("索引名称:{}", index);
    }

    /**
     * 获取索引
     */
    @Test
    void getIndex() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        // 查看指定索引
        GetIndexResponse getIndexResponse = elasticsearchClient.indices().get(s -> s.index("products"));
        Map<String, IndexState> result = getIndexResponse.result();
        result.forEach((k, v) -> log.info("key = {}, value = {}", k, v));

        // 查看全部索引
        IndicesResponse indicesResponse = elasticsearchClient.cat().indices();
        // 返回对象具体查看 co.elastic.clients.elasticsearch.cat.indices.IndicesRecord
        indicesResponse.valueBody().forEach(
                info -> log.info("health:{}\n status:{} \n uuid:{} \n ", info.health(), info.status(), info.uuid())
        );
    }

    /**
     * 删除索引
     */
    @Test
    void deleteIndex() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        DeleteIndexResponse deleteIndexResponse = elasticsearchClient.indices().delete(s -> s.index("products"));
        log.error("删除索引操作结果：{}", deleteIndexResponse.acknowledged());
    }

    /**
     * 文档操作测试
     */
    @Test
    void addOneDocument() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        // 1、流利地使用 DSL
        User user = new User(1, "王五", "28", 26, new Date());
        User user1 = new User(33, "钱六", "21", 27, new Date());
        IndexResponse indexResponse = elasticsearchClient.index(s -> s
                // 索引
                .index("users")
                // ID
                .id(user.getId().toString())
                // 文档
                .document(user).document(user1)
        );
        log.error("result: {}", indexResponse.result().jsonValue());


        // 2、您还可以将使用 DSL 创建的对象分配给变量。 Java API 客户端类为此有一个静态 of() 方法，该方法使用 DSL 语法创建一个对象。
        IndexRequest<User> request = IndexRequest.of(i -> i
                .index("users")
                .id(user1.getId().toString())
                .document(user1));
        IndexResponse response = elasticsearchClient.index(request);
        log.error("Indexed with version " + response.version());


        // 3、使用经典构建器
        IndexRequest.Builder<User> indexReqBuilder = new IndexRequest.Builder<>();
        indexReqBuilder.index("users");
        indexReqBuilder.id(user.getId().toString());
        indexReqBuilder.document(user);
        IndexResponse responseTwo = elasticsearchClient.index(indexReqBuilder.build());
        log.error("Indexed with version " + responseTwo.version());
    }

    /**
     * 获取文档
     */
    @Test
    void getDocument() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        GetResponse<User> getResponse = elasticsearchClient.get(s -> s.index("users").id("123456"), User.class);
        log.error("getResponse: {}", getResponse.source());

        // Reading a domain object
        if (getResponse.found()) {
            User user = getResponse.source();
            assert user != null;
            log.error("user name={}", user.getName());
        }

        // 判断文档是否存在
        BooleanResponse booleanResponse = elasticsearchClient.exists(s -> s.index("users").id("123456"));
        log.error("判断Document是否存在: {}", booleanResponse.value());
    }

    /**
     * 更新文档
     */
    @Test
    void updateDocument() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        // 构建需要修改的内容，这里使用了Map
        Map<String, Object> map = new HashMap<>();
        map.put("name", "LiuFeiEr");
        // 构建修改文档的请求
        UpdateResponse<Test> response = elasticsearchClient.update(e -> e
                        .index("users")
                        .id("33")
                        .doc(map),
                Test.class
        );
        // 打印请求结果
        log.info(String.valueOf(response.result()));
    }


    /**
     * 删除文档
     */
    @Test
    void deleteDocument() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        DeleteResponse deleteResponse = elasticsearchClient.delete(s -> s.index("users").id("33"));
        log.info("删除文档操作结果: {}", deleteResponse.result());
    }


    /**
     * 批量添加文档
     */
    @Test
    void batchAddDocument() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        // 1、使用 BulkOperation
        List<User> users = new ArrayList<>();
        users.add(new User(1, "赵四", "20", 26, new Date()));
        users.add(new User(2, "阿旺", "25", 26, new Date()));
        users.add(new User(3, "刘菲", "22", 26, new Date()));
        users.add(new User(4, "冬梅", "20", 26, new Date()));

        List<BulkOperation> bulkOperations = new ArrayList<>();
        users.forEach(u ->
                bulkOperations.add(BulkOperation.of(b ->
                        b.index(c ->
                                c.id(u.getId().toString()).document(u)
                        )))
        );
        BulkResponse bulkResponse = elasticsearchClient.bulk(s -> s
                .index("users")
                .operations(bulkOperations)
        );
        bulkResponse.items().forEach(i ->
                log.info("i = {}", i.result()));
        log.error("bulkResponse.errors() = {}", bulkResponse.errors());

        // 2、使用 BulkRequest
        BulkRequest.Builder br = new BulkRequest.Builder();
        for (User user : users) {
            br.operations(op -> op
                    .index(idx -> idx
                            .index("users")
                            .id(user.getId().toString())
                            .document(user)));
        }
        BulkResponse result = elasticsearchClient.bulk(br.build());

        // 记录错误(如果有的话)
        if (result.errors()) {
            log.error("Bulk had errors");
            for (BulkResponseItem item : result.items()) {
                if (item.error() != null) {
                    log.error(item.error().reason());
                }
            }
        }
    }


    /**
     * 批量删除文档
     */
    @Test
    void batchDeleteDocument() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        // 1、使用 BulkOperation
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        List<BulkOperation> bulkOperations = new ArrayList<>();
        list.forEach(a ->
                bulkOperations.add(BulkOperation.of(b ->
                        b.delete(c -> c.id(a))
                ))
        );
        BulkResponse bulkResponse = elasticsearchClient.bulk(a -> a.index("users").operations(bulkOperations));
        bulkResponse.items().forEach(a ->
                log.info("result = {}", a.result()));
        log.error("bulkResponse.errors() = {}", bulkResponse.errors());

        // 2、使用 BulkRequest
        BulkRequest.Builder br = new BulkRequest.Builder();
        for (String s : list) {
            br.operations(op -> op
                    .delete(c -> c.id(s)));
        }
        BulkResponse bulkResponseTwo = elasticsearchClient.bulk(br.build());
        bulkResponseTwo.items().forEach(a ->
                log.info("result = {}", a.result()));
        log.error("bulkResponse.errors() = {}", bulkResponseTwo.errors());
    }

    /**
     * 条件查询
     */
    @Test
    void searchOne() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        String searchText = "阿旺";
        SearchResponse<User> response = elasticsearchClient.search(s -> s
                        // 我们要搜索的索引的名称
                        .index("users")
                        // 搜索请求的查询部分（搜索请求也可以有其他组件，如聚合）
                        .query(q -> q
                                // 在众多可用的查询变体中选择一个。我们在这里选择匹配查询（全文搜索）
                                .match(t -> t
                                        // name配置匹配查询：我们在字段中搜索一个词
                                        .field("name")
                                        .query(searchText)
                                )
                        ),
                // 匹配文档的目标类
                User.class
        );
        TotalHits total = response.hits().total();
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        if (isExactResult) {
            log.info("There are " + total.value() + " results");
        } else {
            log.info("There are more than " + total.value() + " results");
        }

        List<Hit<User>> hits = response.hits().hits();
        for (Hit<User> hit : hits) {
            User user = hit.source();
            assert user != null;
            log.info("users " + user);
        }
    }


    /**
     * 嵌套搜索查询
     */
    @Test
    void searchTwo() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        String searchText = "LiuYiHu";
        int maxAge = 30;
        // byName、byMaxAge：分别为各个条件创建查询
        Query byName = MatchQuery.of(m -> m
                          .field("name")
                          .query(searchText)
                       )
                       // MatchQuery是一个查询变体，我们必须将其转换为 Query 联合类型
                       ._toQuery();
        Query byMaxAge = RangeQuery.of(m -> m
                             .field("age")
                             // Elasticsearch 范围查询接受大范围的值类型。我们在这里创建最高价格的 JSON 表示。
                             .gte(JsonData.of(maxAge))
                         )._toQuery();
        SearchResponse<User> response = elasticsearchClient.search(s -> s
                        .index("users")
                        .query(q -> q
                                .bool(b -> b
                                        // 搜索查询是结合了文本搜索和最高价格查询的布尔查询
                                        .must(byName)
                                        // .should(byMaxAge)
                                        .must(byMaxAge)
                                )
                        ),
                User.class
        );
        List<Hit<User>> hits = response.hits().hits();
        for (Hit<User> hit : hits) {
            User user = hit.source();
            assert user != null;
            log.info("Found userId " + user.getId() + ", name " + user.getName());
        }
    }


    /**
     * 模板化搜索
     * <p>
     * 模板化搜索是存储的搜索，可以使用不同的变量运行它。搜索模板让您无需修改应用程序代码即可更改搜索。
     * 在运行模板搜索之前，首先必须创建模板。这是一个返回搜索请求正文的存储脚本，通常定义为 Mustache 模板
     */
    @Test
    void templatedSearch() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        // 事先创建搜索模板
        elasticsearchClient.putScript(r -> r
                // 要创建的模板脚本的标识符
                .id("query-script")
                .script(s -> s
                        .lang("mustache")
                        .source("{\"query\":{\"match\":{\"{{field}}\":\"{{value}}\"}}}")
                ));
        // 开始使用模板搜索
        String field = "name";
        String value = "LiuYiHu";
        SearchTemplateResponse<User> response = elasticsearchClient.searchTemplate(r -> r
                        .index("users")
                        // 要使用的模板脚本的标识符
                        .id("query-script")
                        // 模板参数值
                        .params("field", JsonData.of(field))
                        .params("value", JsonData.of(value)),
                User.class
        );

        List<Hit<User>> hits = response.hits().hits();
        for (Hit<User> hit : hits) {
            User user = hit.source();
            assert user != null;
            log.info("Found userId " + user.getId() + ", name " + user.getName());
        }
    }


    /**
     * 分页+排序条件搜索
     */
    @Test
    void paginationSearch() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        int maxAge = 20;
        Query byMaxAge = RangeQuery.of(m -> m
                .field("age")
                .gte(JsonData.of(maxAge))
        )._toQuery();
        SearchResponse<User> response = elasticsearchClient.search(s -> s
                        .index("users")
                        // .query(q -> q
                        //         .matchAll( m -> m)
                        // )
                        .query(q -> q
                                .bool(b -> b
                                        .must(byMaxAge)
                                )
                        )
                        // 分页查询，从第0页开始查询4个document
                        .from(0)
                        .size(4)
                        // 按age降序排序
                        .sort(f -> f.field(o -> o.field("age")
                                .order(SortOrder.Desc))),
                User.class
        );
        List<Hit<User>> hits = response.hits().hits();
        for (Hit<User> hit : hits) {
            User user = hit.source();
            assert user != null;
            log.info("Found userId " + user.getId() + ", name " + user.getName());
        }
    }


    /**
     * 过滤字段
     */
    @Test
    void filterFieldSearch() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        SearchResponse<User> response = elasticsearchClient.search(s -> s
                        .index("users")
                        .query(q -> q
                                .matchAll(m -> m)
                        )
                        .sort(f -> f
                                .field(o -> o
                                        .field("age")
                                        .order(SortOrder.Desc)
                                )
                        )
                        .source(source -> source
                                .filter(f -> f
                                        .includes("name", "id")
                                        .excludes(""))),
                User.class
        );
        List<Hit<User>> hits = response.hits().hits();
        List<User> userList = new ArrayList<>(hits.size());
        for (Hit<User> hit : hits) {
            User user = hit.source();
            userList.add(user);
        }
        log.info("过滤字段后：{}", JSON.toJSONString(userList));
    }


    /**
     * 模糊查询
     */
    @Test
    void fuzzyQuerySearch() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        SearchResponse<User> response = elasticsearchClient.search(s -> s
                        .index("users")
                        .query(q -> q
                                // 模糊查询
                                .fuzzy(f -> f
                                        // 需要判断的字段名称
                                        .field("name")
                                        // 需要模糊查询的关键词
                                        // 目前文档中没有LiuYi这个用户名
                                        .value("LiuYi")
                                        // fuzziness代表可以与关键词有误差的字数，可选值为0、1、2这三项
                                        .fuzziness("2")
                                )
                        )
                        .source(source -> source
                                .filter(f -> f
                                        .includes("name", "id")
                                        .excludes(""))),
                User.class
        );
        List<Hit<User>> hits = response.hits().hits();
        List<User> userList = new ArrayList<>(hits.size());
        for (Hit<User> hit : hits) {
            User user = hit.source();
            userList.add(user);
        }
        log.info("过滤字段后：{}", JSON.toJSONString(userList));
    }


    /**
     * 高亮查询
     */
    @Test
    void highlightQueryQuery() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        SearchResponse<User> response = elasticsearchClient.search(s -> s
                        .index("users")
                        .query(q -> q
                                .term(t -> t
                                        .field("name")
                                        .value("ZhaoSi"))
                        )
                        .highlight(h -> h
                                .fields("name", f -> f
                                        .preTags("<font color='red'>")
                                        .postTags("</font>")))
                        .source(source -> source
                                .filter(f -> f
                                        .includes("name", "id")
                                        .excludes(""))),
                User.class
        );
        List<Hit<User>> hits = response.hits().hits();
        List<User> userList = new ArrayList<>(hits.size());
        for (Hit<User> hit : hits) {
            User user = hit.source();
            userList.add(user);
            for (Map.Entry<String, List<String>> entry : hit.highlight().entrySet()) {
                System.out.println("Key = " + entry.getKey());
                entry.getValue().forEach(System.out::println);
            }
        }
        log.info("模糊查询结果：{}", JSON.toJSONString(userList));
    }


    /**
     * 聚合查询：获取最大年龄用户
     */
    @Test
    void getMaxAgeUserTest() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        SearchResponse<Void> response = elasticsearchClient.search(b -> b
                        .index("users")
                        .size(0)
                        .aggregations("maxAge", a -> a
                                .max(MaxAggregation.of(s -> s
                                        .field("age"))
                                )
                        ),
                Void.class
        );
        MaxAggregate maxAge = response.aggregations()
                .get("maxAge")
                .max();
        log.info("maxAge.value:{}", maxAge.value());
    }


    /**
     * 聚合查询：年龄分组
     */
    @Test
    void groupingTest() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        SearchResponse<Void> response = elasticsearchClient.search(b -> b
                        .index("users")
                        .size(0)
                        .aggregations("groupName", a -> a
                                .terms(TermsAggregation.of(s -> s
                                        .field("age")))
                        ),
                Void.class
        );
        LongTermsAggregate longTermsAggregate = response.aggregations()
                .get("groupName")
                .lterms();
        log.info("multiTermsAggregate: {}", longTermsAggregate.buckets());
    }


    /**
     * 聚合查询：性别分组
     */
    @Test
    void groupBySexTest() throws IOException {
        ElasticsearchClient elasticsearchClient = elasticsearchClient();
        SearchResponse<Void> response = elasticsearchClient.search(b -> b
                        .index("users")
                        .size(0)
                        .aggregations("groupSex", a -> a
                                .terms(TermsAggregation.of(s -> s
                                        // ⚠️特别注意这一块，我们加上了.keyword，下面会说明
                                        .field("sex.keyword")))
                        ),
                Void.class
        );
        StringTermsAggregate stringTermsAggregate = response.aggregations()
                .get("groupSex")
                .sterms();
        log.info("stringTermsAggregate:{}", stringTermsAggregate.buckets());
    }
    // TODO 问题
    // 我们根据性别分组，按理来说，field直接写sex就行，但是我们发现，如果不加.keyword，运行测试用例会报错

    // TODO 原因分析：
    // 这个错误的原因是因为我的分组聚合查询的字符串sex类型是text(可分词)类型。当使用到 term 查询的时候，
    // 由于是精准匹配，所以查询的关键字在es上的类型，必须是keyword而不能是text。
    // 所以想要分组查询，指定根据分组字段的keyword属性就可以了。
}
