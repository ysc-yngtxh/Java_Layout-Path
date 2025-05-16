package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单详情表(Order)实体类
 *
 * @author 游家纨绔
 * @since 2023-08-31 19:40:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"orderId", "skuId", "num", "buyPrice", "menuList", "createDate", "updatedDate", "createBy", "updatedBy", "deleteFlag", "consumerJson"})
// @TableName 参数一：value 表示映射表名
//            参数二：schema 属性用来指定模式名称。如果你使用的是 mysql 数据库，则指定数据库名称。
//                  如果你使用的是 oracle，则为 schema，例如：schema="scott"，其中：scott 就是 oracle 中的 schema
//            参数三：resultMap  表示返回自定义的映射结果(xml文件的resultMap标签对应Id)
//            参数四：autoResultMap  表示是否自动构建ResultMap，适合实体字段中有使用typeHandler（设置了resultMap则无效）
//            参数五：keepGlobalPrefix  表示该映射表名是否保留在配置文件中设置的全局表名前缀(true表示保留)
//            参数六：excludeProperty 表示需要排除的属性字段。插入数据的时候会排除该字段数据
@TableName(value = "order", autoResultMap = true, keepGlobalPrefix = true)
public class Order implements Serializable {
	@Serial
	private static final long serialVersionUID = -53428860068885420L;

	// 订单id。当我们的主键Id字段不是 Id 时，应该添加上@TableId注解进行人为映射主键
	// @TableId参数：value映射数据库column列主键字段；type设置新增数据主键Id规则,有自增(数据库自增)、UUID等
	// 并且这里作为局部的Id生成策略，会覆盖掉application.properties中的全局Id生成策略
	@TableId(value = "order_id", type = IdType.AUTO)
	private Long orderId;

	// sku商品id
	private Long skuId;
	// 购买数量
	private Integer num;
	// 购买价格
	private Double buyPrice;

	// 菜单
	// TODO @TableName(autoResultMap = true)开启映射注解,选择 FastjsonTypeHandler处理器(fastjson依赖)解析数据
	//      也可以选择 JacksonTypeHandler(jackson-core依赖)处理器 或者 GsonTypeHandler内置处理器
	@TableField(value = "menu", typeHandler = FastjsonTypeHandler.class)
	private List<String> menuList;

	// 创建时间
	// 属性 fill 是用来做自动填充功能的，有四种生成策略：默认、插入、更新、插入更新.
	// 这里设置的是在做更新操作时，通过实现MetaObjectHandler类，重写updateFill(MetaObject metaObject)方法
	// 会在执行 update 语句的 set 里填充 create_date=LocalDateTime.now()
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createDate;

	// 更新时间
	// 属性 fill 是用来做自动填充功能的，有四种生成策略：默认、插入、更新、插入更新.
	// 这里设置的是在做更新操作时，通过实现MetaObjectHandler类，重写updateFill(MetaObject metaObject)方法
	// 会在执行 update 语句的 set 里填充 updated_date=new Date()
	@TableField(fill = FieldFill.UPDATE)
	private Date updatedDate;

	// 创建人
	private String createBy;
	// 更新人
	private String updatedBy;

	// 逻辑删除
	// @TableLogic注解是逻辑删除，加上这个注解在执行删除方法会变成修改。前端根据所加注解字段进行显隐即可达到逻辑删除效果。
	@TableLogic(value = "0", delval = "1")
	private Integer deleteFlag;

	// 版本号
	// @Version注解是用来标注该字段是乐观锁字段。乐观锁：读操作不会上锁，更新操作会通过版本号来进行上锁
	// 乐观锁实现方式：取出记录时，获取当前 version
	//              更新时，带上这个 version
	//              执行更新时， set version = newVersion where version = oldVersion
	//              如果 version 不对，就更新失败
	// 支持的数据类型只有:int、Integer、long、Long、Date、Timestamp、LocalDateTime
	// 整数类型下 newVersion = oldVersion + 1。newVersion 会回写到 entity 中
	// 仅支持 updateById(id) 与 update(entity, wrapper) 方法
	@Version
	private Integer version;

	// sku数据
	// TODO @TableName(autoResultMap = true)开启映射注解,选择 FastjsonTypeHandler处理器(fastjson依赖)解析数据
	//  也可以选择 JacksonTypeHandler(jackson-core依赖)处理器 或者 GsonTypeHandler内置处理器
	@TableField(value = "sku_json", typeHandler = JacksonTypeHandler.class)
	private Map<String, Object> skuJson;
}
