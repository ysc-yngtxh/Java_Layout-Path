package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

/**
 * (TbUser)实体类
 *
 * @author 游家纨绔
 * @since 2023-08-25 00:19:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TbUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 882909710663298133L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 租户Id
     */
    private Integer tenantId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 商品
     */
    private String commodity;
    /**
     * 颜色
     */
    private String color;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 价格
     */
    private Double price;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updatedDate;
    /**
     * 逻辑删除
     */
    private Integer deleteFlag;
    /**
     * 版本
     */
    private Integer version;
}
