package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Tb2_Sku)实体类
 * @author 游家纨绔
 * @since 2023-08-31 19:37:00
 */
@Data
// @TableName 参数一：value 表示映射表名
//            参数二：schema 属性用来指定模式名称。如果你使用的是 mysql 数据库，则指定数据库名称。
//                  如果你使用的是 oracle，则为 schema，例如：schema="scott"，其中：scott 就是 oracle 中的 schema
//            参数三：resultMap  表示返回自定义的映射结果(xml文件的resultMap标签对应Id)
//            参数四：autoResultMap  表示是否自动构建ResultMap，适合实体字段中有使用typeHandler （设置了resultMap则无效）
//            参数五：keepGlobalPrefix  表示该映射表名是否保留在配置文件中设置的全局表名前缀(true表示保留)
//            参数六：excludeProperty 表示需要排除的属性字段。插入数据的时候会排除该字段数据
@TableName(value = "sku", resultMap = "TbSkuMap", keepGlobalPrefix = true, excludeProperty = {"context"})
public class Tb2_Sku implements Serializable {
    @Serial
    private static final long serialVersionUID = 436500863014955490L;

    // 主键Id。当我们的主键Id字段不是 Id 时，应该添加上@TableId注解进行人为映射主键
    // @TableId参数：value映射数据库column列主键字段；type设置新增数据主键Id规则,有自增(数据库自增)、UUID等
    // 并且这里作为局部的Id生成策略，会覆盖掉application.properties中的全局Id生成策略
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 图片
    private String image;
    // 价格
    private Double price;
    // 库存
    private Integer inventory;
    // 标题
    private String title;
    // 是否上架
    private Integer shelves;

    // 菜单
    // TODO 通过注解@TableName的属性resultMap映射xml文件，并且使用自定义数据类型处理器CustomHandler进行 String转 List<String>
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

    // 订单数据
    // TODO 通过注解@TableName的属性resultMap映射xml文件，并且使用自定义数据类型处理器TbOrderStringHandler进行 String转 Tb3_Order
    //  这里有一个坑点：我的 Json字符串数据 中有一个menu属性，但是在Tb3_Order实体类中对应的是menuList属性，
    //               因为我这个字段的返回类型是Tb3_Order，无法精准的映射字段属性，导致无法赋值。
    //  解决：方法一、将映射字段一一对应
    //       方法二、不使用实体类映射，使用 Map<String, Object>
    private Tb3_Order orderJson;

    /**
     * 使用 @TableField(exist = false) ，表示该字段在数据库中不存在 ，所以不会插入数据库中
     * 使用 transient 、 static 修饰属性也不会插入数据库中
     */
    @TableField(exist = false)
    private String content;
    private String context;
    public transient String text;
}

