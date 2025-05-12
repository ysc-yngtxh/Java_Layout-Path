package com.example.executor;

import com.example.annotation.AutoDiscoverClass;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * 类别表(Category)实体类
 *
 * @author 游家纨绔
 * @since 2024-02-27 22:00:00
 */
@Data
@ToString
@AutoDiscoverClass  // 使用自定义注解
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = -79781314987912932L;

    /**
     * 主键Id
     */
    private Long id;
    /**
     * 类别名称
     */
    private String categoryName;
    /**
     * 类别英文名称
     */
    private String categoryNameEn;
    /**
     * 逻辑删除：0存在,1删除
     */
    private Integer deleteFlag;
    /**
     * 备注
     */
    private String remark;
}
