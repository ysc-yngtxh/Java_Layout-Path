package com.example.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-25 07:48
 * @apiNote TODO 类别表(Category)实体类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    private static final long serialVersionUID = 483640959410582653L;

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
