package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.StringTypeHandler;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
// @TableName参数：value映射表名；keepGlobalPrefix表示该映射表名是否保留在配置文件中设置的全局表名前缀
// autoResultMap 是否自动构建ResultMap （设置了resultMap则无效）
@TableName(value = "consumer", autoResultMap = true, keepGlobalPrefix = false)
public class Consumer implements Serializable {
    @Serial
    private static final long serialVersionUID = 692869694057194050L;

    // 主键Id
    // @TableId参数：value映射数据库column列主键字段；type设置新增数据主键Id规则,有自增、UUID等
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 上级Id
    private Integer superiorId;
    // 用户名
    private String username;
    // 密码
    private String password;
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
    // 菜单
    @TableField(value = "menu", typeHandler = FastjsonTypeHandler.class)
    private List<String> menu;
    // 逻辑删除
    private Integer deleteFlag;
    // 创建时间
    private Date createdDate;
    // 更新时间
    private Date updatedDate;
    // 更新时间
    private String json;
}

