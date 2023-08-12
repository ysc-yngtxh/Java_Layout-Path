/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : joolun_config

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 12/08/2023 17:24:19
*/
DROP DATABASE IF EXISTS joolun_config;
CREATE DATABASE joolun_config;
USE joolun_config;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=700 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (1, 'dynamic_routes', 'DEFAULT_GROUP', 'routes:\n# joolun-auth\n- id: joolun-auth\n  predicates:\n  - name: Path\n    args: \n      _genkey_0: /auth/**\n  filters:\n  - name: ValidateCodeGatewayFilter\n    args: {}\n  - name: PasswordDecoderFilter\n    args: {}\n  uri: lb://joolun-auth\n  order: 0\n# joolun-upms-admin\n- id: joolun-upms-admin\n  predicates:\n  - name: Path\n    args: \n      _genkey_0: /upms/**\n  filters: \n  - name: RequestRateLimiter\n    args: \n      # 限流策略\n      key-resolver: \'#{@remoteAddrKeyResolver}\'\n      # 令牌桶每秒填充率\n      redis-rate-limiter.burstCapacity: 20\n      # 令牌桶容量\n      redis-rate-limiter.replenishRate: 20\n  uri: lb://joolun-upms-admin\n  order: 0\n# joolun-codegen\n- id: joolun-codegen\n  predicates:\n  - name: Path\n    args: \n      _genkey_0: /gen/**\n  filters: []\n  uri: lb://joolun-codegen\n  order: 0\n# joolun-weixin-admin\n- id: joolun-weixin-admin\n  predicates:\n  - name: Path\n    args: \n      _genkey_0: /weixin/**\n  filters: []\n  uri: lb://joolun-weixin-admin\n  order: 0\n# joolun-mall-admin\n- id: joolun-mall-admin\n  predicates:\n  - name: Path\n    args: \n      _genkey_0: /mall/**\n  filters: []\n  uri: lb://joolun-mall-admin\n  order: 0\n# joolun-mall-api\n- id: joolun-mall-api\n  predicates:\n  - name: Path\n    args: \n      _genkey_0: /mallapi/**\n  filters: []\n  uri: lb://joolun-mall-api\n  order: 0\n# joolun-pay-api\n- id: joolun-pay-api\n  predicates:\n  - name: Path\n    args: \n      _genkey_0: /payapi/**\n  filters: []\n  uri: lb://joolun-pay-api\n  order: 0', 'd9d20e1325ffa6d84c78e74e34efeceb', '2019-07-30 14:26:08', '2021-09-03 08:03:02', NULL, '127.0.0.1', '', '', '动态路由配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (2, 'application-dev.yml', 'DEFAULT_GROUP', '# 加解密根密码\njasypt:\n  encryptor:\n    #根密码，改完密码要把joolun_upms.sys_datasource数据库表清空，否则代码生成器无法启动\n    password: joolun\nspring:\n  servlet:\n    multipart:\n      location: /home\n  http:\n    multipart:\n      location: /home\n  # redis 相关\n  redis:\n    host: joolun-redis\n    port: 6379\n    password: \n    database: 2\n# logging日志\nlogging:\n  level:\n    com.alibaba.nacos.client.naming: error\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n# feign 配置\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n#请求处理的超时时间\nribbon:\n  ReadTimeout: 10000\n  ConnectTimeout: 10000\n# mybaits-plus配置\nmybatis-plus:\n  # MyBatis Mapper所对应的XML文件位置\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  # 自定义TypeHandler\n  type-handlers-package: com.joolun.cloud.common.core.mybatis.typehandler\n  global-config:\n    sql-parser-cache: true\n    # 关闭MP3.0自带的banner\n    banner: false\n    db-config:\n      # 主键类型\n      id-type: auto\n#swagger公共信息\nswagger:\n  title: JooLun API\n  description: JooLun\n  license: Powered By joolun\n  licenseUrl: http://www.joolun.com/\n  terms-of-service-url: http://www.joolun.com/\n  authorization:\n    name: OAuth\n    auth-regex: ^.*$\n    authorization-scope-list:\n      - scope: server\n        description: server all\n    token-url-list:\n      - http://joolun-gateway:9999/auth/oauth/token\n## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      # 无需token访问的url,如果子模块重写这里的配置就会被覆盖\n      release-urls:\n        - /actuator/**\n        - /v2/api-docs\n    resource:\n      loadBalanced: true\n      token-info-uri: http://joolun-auth/oauth/check_token\n#第三方登录配置\nthirdparty:\n  #微信登录\n  wx: \n    appId: wx5251cca540acece5\n    appSecret: c45d8b740babcde766ba1eebcb617b68\n  #QQ登录\n  qq:\n    appId: 101888362\n    appKey: 17494f217014cc949a8f5a52c4c6aff2\n     \n## 文件存放目录配置（用来存放微信支付证书）\nhome-dir:\n  windows: C:/joolun-file/\n  linux: /mnt/install/joolun-file/\n\nbase:\n  #商城相关配置\n  mall:\n    #支付、物流回调地址，即网关joolun-gateway服务的外网地址，要保证外网能访问\n    notifyHost: http://test.joolun.com\n    #快递100授权key\n    logisticsKey: xxxxxxxxxxxxxxxxxxxxx\n    #用户默认头像\n    userDefaultAvatar: http://minio.joolun.com/joolun/1/material/32f19366-3c43-4002-9a82-c984a2d20bbf.png\n    #物流公司配置（编码，名称，开关）编码需和快递100官网对应\n    logistics:\n      - code: tiantian\n        name: 天天快递\n        enable: \'1\'\n      - code: huitongkuaidi\n        name: 百世快递\n        enable: \'1\'\n      - code: yunda\n        name: 韵达快递\n        enable: \'1\'\n      - code: yuantong\n        name: 圆通速递\n        enable: \'1\'\n      - code: debangwuliu\n        name: 德邦\n        enable: \'1\'\n      - code: ems\n        name: EMS\n        enable: \'1\'\n      - code: shunfeng\n        name: 顺丰速运\n        enable: \'1\'\n      - code: zhongtong\n        name: 中通快递\n        enable: \'1\'\n      - code: shentong\n        name: 申通快递\n        enable: \'1\'\n#阿里短信配置\nsms:\n  regionId: cn-hangzhou\n  accessKeyId: xxxxxxxxxxxxxxxxxxxxxxxx\n  accessKeySecret: xxxxxxxxxxxxxxxxxxxxxxxxxx\n  #模板\n  templates:\n    #登录模板\n    signName1: JooLun商城\n    templateCode1: SMS_198927314\n    #绑定模板\n    signName2: JooLun商城\n    templateCode2: SMS_198927314\n    #解绑模板\n    signName3: JooLun商城\n    templateCode3: SMS_198927314\n#极光配置\njiguang:\n  appKey: e9a656ca1cfcd7c2247203a5\n  secret: 8126bf41c507d0453a2a2818\n  flag: 1', '4795b6a3941ff38cf020139ffbbff7d6', '2019-07-28 23:14:26', '2021-11-01 10:42:27', 'nacos', '127.0.0.1', '', '', '主配置文件', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (3, 'joolun-gateway-dev.yml', 'DEFAULT_GROUP', 'security:\n  encode:\n    # 前端密码密钥，必须16位，和joolun-plus-ui、joolun-plus-app配置文件\\src\\config\\env.js中的securityKey相对应\n    key: \'1234567891234567\'\n# 不校验验证码终端\nignore:\n  clients:\n    - swagger\n    - api\n  swagger-providers:\n    - joolun-auth', '4bbbc1e300706a8dbc9e126c4d5dfd65', '2019-07-28 23:14:26', '2020-05-22 21:34:28', NULL, '127.0.0.1', '', '', '网关配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (4, 'joolun-auth-dev.yml', 'DEFAULT_GROUP', '# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: root\n      password: root\n      url: jdbc:mysql://joolun-mysql:3306/joolun_upms?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  freemarker:\n    allow-request-override: false\n    allow-session-override: false\n    cache: true\n    charset: UTF-8\n    check-template-location: true\n    content-type: text/html\n    enabled: true\n    expose-request-attributes: false\n    expose-session-attributes: false\n    expose-spring-macro-helpers: true\n    prefer-file-system-access: true\n    suffix: .ftl\n    template-loader-path: classpath:/templates/', '8063283524ab6b229451b330b8c3d77f', '2019-07-28 23:14:26', '2020-06-22 14:18:27', NULL, '127.0.0.1', '', '', '认证授权配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (5, 'joolun-upms-admin-dev.yml', 'DEFAULT_GROUP', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: admin\n      client-secret: admin\n      scope: server\n      # 无需token访问的url\n      release-urls:\n        - /actuator/**\n        - /v2/api-docs\n        - /user/register\n        - /druid/**\n        - /user/count\n        - /tenant/outside/**\n# 数据源\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: root\n      password: root\n      url: jdbc:mysql://joolun-mysql:3306/joolun_upms?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n      web-stat-filter: \n        enabled: true\n      stat-view-servlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        deny:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username: admin\n        login-password: 123456\n      filter:\n        stat:\n            enabled: true\n            # 慢SQL记录\n            log-slow-sql: true\n            slow-sql-millis: 1000\n            merge-sql: true\n        wall:\n            config:\n                multi-statement-allow: true\n# Logger Config sql日志\nlogging:\n  level:\n    com.joolun.cloud.upms.admin.mapper: debug\nbase:\n  # 租户表维护\n  tenant:\n    column: tenant_id\n    tables:\n      - sys_user\n      - sys_role\n      - sys_organ\n      - sys_log\n      - sys_log_login\n      - sys_config_storage\n      - sys_config_editor\n      - sys_organ_relation\n      - sys_role_menu\n      - sys_user_role\n  #店铺数据权限控制\n  shop:\n    datascope:\n      mappers:\n        - column: shop_id\n          value: ShopUserMapper\n  #数据权限配置\n  datascope:\n    column: organ_id\n    mapperIds:\n      - com.joolun.cloud.upms.admin.mapper.SysUserMapper.getUserVosPage \n#邮箱配置\nemail:\n  mailSmtpHost: smtpdm.aliyun.com\n  mailSmtpUsername: xxxxxxxxx\n  mailSmtpPassword: xxxxxxxxxxx\n  siteName: JooLun', '37dfae93c127678eb0600d19cc137035', '2019-07-28 23:14:26', '2021-01-04 10:27:33', NULL, '127.0.0.1', '', '', '用户权限管理配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (6, 'joolun-codegen-dev.yml', 'DEFAULT_GROUP', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: gen\n      client-secret: gen\n      scope: server\n# 数据源配置\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: root\n      password: root\n      url: jdbc:mysql://joolun-mysql:3306/joolun_upms?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/\n# Logger config sql日志\nlogging:\n  level:\n    com.joolun.cloud.codegen.mapper: debug\nbase:\n  tenant:\n    column: tenant_id\n    tables:\n      - sys_datasource', '3a0e3dda5fd0a6c1d8f8b98ef33d1d53', '2019-07-28 23:14:26', '2020-05-22 21:34:58', NULL, '127.0.0.1', '', '', '代码生成配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (7, 'joolun-weixin-admin-dev.yml', 'DEFAULT_GROUP', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: weixin\n      client-secret: weixin\n      scope: server\n      # 无需token访问的url\n      release-urls:\n        - /actuator/**\n        - /v2/api-docs\n        - /portal/**\n        - /ws/**\n        - /open/notify/**\n        - /open/auth/**\n        - /api/**\n# 数据源配置\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: root\n      password: root\n      url: jdbc:mysql://joolun-mysql:3306/joolun_wx?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/\n# Logger Config sql日志\nlogging:\n  level:\n    com.joolun.cloud.weixin.admin.mapper: debug    \n# 租户表维护\nbase:\n  tenant:\n    column: tenant_id\n    tables:\n      - wx_app\n      - wx_menu\n      - wx_menu_rule\n      - wx_user\n      - wx_auto_reply\n      - wx_msg\n      - wx_mass_msg\n      - wx_template_msg\n      - wxma_code_audit\n  #数据权限配置\n  datascope:\n    column: organ_id\n    mapperIds:\n      - com.joolun.cloud.weixin.admin.mapper.WxAppMapper.selectPage\n      - com.joolun.cloud.weixin.admin.mapper.WxAppMapper.selectList\n# 微信第三方平台配置，请自行申请(https://open.weixin.qq.com/)\nwx:\n  component:\n    appId: xxxxxxxxxxxxxxxxxxx\n    appSecret: xxxxxxxxxxxxxxxxxxxxx\n    token: xxxxxxxxxxxxxxxxxxxxx\n    aesKey: xxxxxxxxxxxxxxxxxxxxx', '387da36a412788f09ea3d778971a5097', '2019-07-28 23:14:26', '2021-09-03 08:31:22', NULL, '127.0.0.1', '', '', '微信公众号配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (8, 'joolun-mall-admin-dev.yml', 'DEFAULT_GROUP', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: weixin\n      client-secret: weixin\n      scope: server\n      # 无需token访问的url\n      release-urls:\n        - /actuator/**\n        - /v2/api-docs\n        - /api/**\n        - /shopapply/addedit\n        - /shopinfo/count\n        - /shopapply/one\n        - /orderrefunds/notify-refunds\n# 数据源配置\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: root\n      password: root\n      url: jdbc:mysql://joolun-mysql:3306/joolun_mall?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/\n# Logger Config sql日志\nlogging:\n  level:\n    com.joolun.cloud.mall.admin.mapper: debug    \nbase:\n  # 租户表维护\n  tenant:\n    column: tenant_id\n    tables:\n      - goods_category\n      - goods_spu\n      - goods_spu_spec\n      - goods_sku\n      - goods_sku_spec_value\n      - goods_spec\n      - goods_spec_value\n      - goods_appraises\n      - shopping_cart\n      - order_info\n      - order_item\n      - order_logistics\n      - order_logistics_detail\n      - user_address\n      - user_collect\n      - material\n      - material_group\n      - order_refunds\n      - user_info\n      - points_record\n      - points_config\n      - coupon_info\n      - coupon_goods\n      - coupon_user\n      - freight_templat\n      - bargain_info\n      - bargain_user\n      - bargain_cut\n      - ensure\n      - ensure_goods\n      - groupon_info\n      - groupon_user\n      - shop_info\n      - theme_mobile\n      - seckill_hall\n      - seckill_hall_info\n      - seckill_info\n      - goods_category_shop\n      - page_devise\n      - shop_apply\n      - article_category\n      - article_info\n      - user_footprint\n      - sign_config\n      - sign_record\n      - user_shop\n      - distribution_config\n      - user_record\n      - distribution_user\n      - distribution_order\n      - distribution_order_item\n      - user_withdraw_record\n  #店铺数据权限控制\n  shop:\n    datascope:\n      mappers:\n        - column: id\n          value: ShopInfoMapper\n        - column: shop_id\n          value: GoodsSpuMapper\n        - column: shop_id\n          value: OrderInfoMapper\n        - column: shop_id\n          value: GoodsAppraisesMapper\n        - column: shop_id\n          value: OrderRefundsMapper\n        - column: shop_id\n          value: CouponInfoMapper\n        - column: shop_id\n          value: CouponUserMapper\n        - column: shop_id\n          value: FreightTemplatMapper\n        - column: shop_id\n          value: BargainInfoMapper\n        - column: shop_id\n          value: BargainUserMapper\n        - column: shop_id\n          value: BargainCutMapper\n        - column: shop_id\n          value: GrouponInfoMapper\n        - column: shop_id\n          value: GrouponUserMapper\n        - column: shop_id\n          value: PointsRecordMapper\n        - column: shop_id\n          value: MaterialGroupMapper\n        - column: shop_id\n          value: MaterialMapper\n        - column: shop_id\n          value: SeckillInfoMapper\n        - column: shop_id\n          value: GoodsCategoryShopMapper\n        - column: shop_id\n          value: PageDeviseMapper\n        - column: shop_id\n          value: UserShopMapper', 'a70a491bc37bedfa134d3690ce33220b', '2019-08-12 12:03:16', '2021-10-06 12:54:09', 'nacos', '127.0.0.1', '', '', '商城管理配置', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (9, 'joolun-mall-api-dev.yml', 'DEFAULT_GROUP', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: api\n      client-secret: api\n      scope: server\n      # 无需token访问的url\n      release-urls:\n        - /**\n# 数据源配置\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: root\n      password: root\n      url: jdbc:mysql://joolun-mysql:3306/joolun_mall?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n  resources:\n    static-locations: classpath:/static/,classpath:/views/\n# Logger Config sql日志\nlogging:\n  level:\n    com.joolun.cloud.mall.api.mapper: debug    \n# 租户表维护\nbase:\n  tenant:\n    column: tenant_id\n    tables:\n      - goods_category\n      - goods_spu\n      - goods_spu_spec\n      - goods_sku\n      - goods_sku_spec_value\n      - goods_spec\n      - goods_spec_value\n      - goods_appraises\n      - shopping_cart\n      - order_info\n      - order_item\n      - order_logistics\n      - order_logistics_detail\n      - user_address\n      - user_collect\n      - material\n      - material_group\n      - order_refunds\n      - user_info\n      - points_record\n      - points_config\n      - coupon_info\n      - coupon_goods\n      - coupon_user\n      - freight_templat\n      - bargain_info\n      - bargain_user\n      - bargain_cut\n      - ensure\n      - ensure_goods\n      - groupon_info\n      - groupon_user\n      - shop_info\n      - theme_mobile\n      - seckill_hall\n      - seckill_hall_info\n      - seckill_info\n      - goods_category_shop\n      - page_devise\n      - article_category\n      - article_info\n      - user_footprint\n      - sign_config\n      - sign_record\n      - user_shop\n      - distribution_config\n      - user_record\n      - distribution_user\n      - distribution_order\n      - distribution_order_item\n      - user_withdraw_record\nxxl:\n  job:\n    # 开关\n    enabled: false\n    admin:\n      # xxl_job后台访问地址\n      addresses: http://127.0.0.1:8080/xxl-job-admin\n    executor:\n      appname: joolun-mall-api', '1803a0129fdf81f6589ebbb6c10f9620', '2020-05-03 20:14:42', '2021-06-03 17:46:47', NULL, '127.0.0.1', '', '', '商城api模块', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (10, 'joolun-pay-api-dev.yml', 'DEFAULT_GROUP', '## spring security 配置\nsecurity:\n  oauth2:\n    client:\n      client-id: admin\n      client-secret: admin\n      scope: server\n      # 无需token访问的url\n      release-urls:\n        - /actuator/**\n        - /v2/api-docs\n        - /aliopenauth/redirect\n# 数据源配置\nspring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      username: root\n      password: root\n      url: jdbc:mysql://joolun-mysql:3306/joolun_mall?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true\n# Logger Config sql日志\nlogging:\n  level:\n    com.joolun.cloud.pay.api.mapper: debug    \n# 租户表维护\nbase:\n  tenant:\n    column: tenant_id\n    tables:\n      - pay_config\n      - pay_apply_form\n  #店铺数据权限控制\n  shop:\n    datascope:\n      mappers:\n        - column: shop_id\n          value: PayApplyFormMapper', '83ceaa702bbd4726cbe1949440a96fd8', '2020-05-28 17:06:27', '2021-10-03 07:23:09', 'nacos', '127.0.0.1', '', '', '支付模块', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (11, 'joolun-monitor-dev.yml', 'DEFAULT_GROUP', 'spring:\n  # 安全配置\n  security:\n    user:\n      name: joolun\n      password: joolun\n  boot:\n    admin:\n      ui:\n        title: \'JooLun服务状态监控\'', '49cac710202cc7d9763270aa78f5d746', '2021-01-17 19:33:20', '2021-03-11 18:18:10', NULL, '127.0.0.1', '', '', '监控中心', '', '', 'yaml', '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (20, 'sentinal-joolun', 'SENTINEL_GROUP', '[\n    {\n        \"resource\": \"joolun-auth\",\n        \"count\": 500,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    },{\n        \"resource\": \"joolun-upms-admin\",\n        \"count\": 1000,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    },{\n        \"resource\": \"joolun-weixin-admin\",\n        \"count\": 1000,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    },{\n        \"resource\": \"joolun-mall-admin\",\n        \"count\": 1000,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    },{\n        \"resource\": \"joolun-mall-api\",\n        \"count\": 1000,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    },{\n        \"resource\": \"joolun-pay-api\",\n        \"count\": 1000,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    }\n]', '3201726bc9f4ab1c2c3e46ea62edb6d9', '2021-01-17 17:38:30', '2021-01-17 17:43:30', NULL, '127.0.0.1', '', '', 'sentinal流控规则', '', '', 'json', '');
COMMIT;

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='增加租户字段';

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_info_beta';

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_info_tag';

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Records of group_capacity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL,
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin,
  `src_ip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='多租户改造';

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(512) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of permissions
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` (`username`, `role`) VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='tenant_info';

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
