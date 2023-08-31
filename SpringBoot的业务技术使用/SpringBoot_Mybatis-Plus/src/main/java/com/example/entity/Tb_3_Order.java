package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单详情表(Tb_3_Order)实体类
 * @author 游家纨绔
 * @since 2023-08-31 19:36:39
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// @TableName 参数一：value 表示映射表名
//            参数二：schema 属性用来指定模式名称。如果你使用的是 mysql 数据库，则指定数据库名称。
//                  如果你使用的是 oracle，则为 schema，例如：schema="scott"，其中：scott 就是 oracle 中的 schema
//            参数三：resultMap  表示返回自定义的映射结果(xml文件的resultMap标签对应Id)
//            参数四：autoResultMap  表示是否自动构建ResultMap （设置了resultMap则无效）
//            参数五：keepGlobalPrefix  表示该映射表名是否保留在配置文件中设置的全局表名前缀(true表示保留)
//            参数六：excludeProperty 表示需要排除的属性字段。插入数据的时候会排除该字段数据
@TableName(value = "order", autoResultMap = true, keepGlobalPrefix = true)
public class Tb_3_Order implements Serializable {
    @Serial
    private static final long serialVersionUID = -53428860068885420L;

    // 订单id。当我们的主键Id字段不是 Id 时，应该添加上@TableId注解进行认为映射主键
    // @TableId参数：value映射数据库column列主键字段；type设置新增数据主键Id规则,有自增、UUID等
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;
    // sku商品id
    private Long skuId;
    // 购买数量
    private Integer num;
    // 购买价格
    private Double buyPrice;
    // 菜单
    // TODO @TableName(autoResultMap = true)开启映射注解,选择FastjsonTypeHandler内置处理器解析数据
    //  也可以选择JacksonTypeHandler内置处理器
    @TableField(value = "menu", typeHandler = FastjsonTypeHandler.class)
    private List<String> menuList;
    // 创建时间
    private Date createDate;
    // 更新时间
    private Date updatedDate;
    // 创建人
    private String createBy;
    // 更新人
    private String updatedBy;
    // 逻辑删除
    // @TableLogic注解是逻辑删除，加上这个注解在执行删除方法会变成修改。前端根据所加注解字段进行显隐即可达到逻辑删除效果。
    @TableLogic(value = "0", delval = "1")
    private Integer deleteFlag;
    // tb_consumer数据
    // TODO @TableName(autoResultMap = true)开启映射注解,选择FastjsonTypeHandler内置处理器解析数据
    //  也可以选择JacksonTypeHandler内置处理器
    @TableField(value = "consumer_json", typeHandler = JacksonTypeHandler.class)
    private Tb_1_Consumer consumerJson;
}

