package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * (Consumer)实体类
 * @author 游家纨绔
 * @since 2023-08-29 06:30:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"id", "superiorId", "userName", "passWord", "alias", "age", "sex", "phone", "address", "email", "deleteFlag", "createdDate", "updatedDate"})
// @TableName 参数一：value 表示映射表名
//            参数二：schema 属性用来指定模式名称。如果你使用的是 mysql 数据库，则指定数据库名称。
//                  如果你使用的是 oracle，则为 schema，例如：schema="scott"，其中：scott 就是 oracle 中的 schema
//            参数三：resultMap  表示返回自定义的映射结果(xml文件的resultMap标签对应Id)
//            参数四：autoResultMap  表示是否自动构建ResultMap，适合实体字段中有使用typeHandler （设置了resultMap则无效）
//            参数五：keepGlobalPrefix  表示该映射表名是否保留在配置文件中设置的全局表名前缀(true表示保留)
//            参数六：excludeProperty 表示需要排除的属性字段。插入数据的时候会排除该字段数据
@TableName(value = "consumer", keepGlobalPrefix = true)
public class Consumer implements Serializable {
    @Serial
    private static final long serialVersionUID = 692869694057194050L;

    // 主键Id。当我们的主键Id字段不是 Id 时，应该添加上@TableId注解进行人为映射主键。这里是 Id值恰好对上，则不需要
    private Integer id;
    // 上级Id
    private Integer superiorId; // 这里驼峰字段，Mybatis-plus 会自动转为字段superior_id
    // 用户名
    private String userName;
    // 密码
    private String passWord;
    // 别名
    private String alias;
    // 年龄
    private Integer age;
    // 性别
    private Integer sex;
    // 手机号
    private String phone;
    // 地址
    private String address;
    // 邮件
    private String email;
    // 逻辑删除
    // 在 application.yml文件中，已经设置了全局逻辑删除的实体字段名，所以每次查询都会带上delete_flag=0的 where 条件
    private Integer deleteFlag;
    // 创建时间
    private Date createdDate;
    // 更新时间
    private Date updatedDate;
}
