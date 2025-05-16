package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serial;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌表(Brand)表实体类
 *
 * @author 游家纨绔
 * @since 2023-08-18 23:15:50
 */
@Data
@TableName("brand")
@EqualsAndHashCode(callSuper = true)
public class Brand extends Model<Brand> {
	@Serial
	private static final long serialVersionUID = -5703040435111508073L;

	// 主键Id
	@TableId("id")
	private Long id;

	// 品牌名称
	private String brandName;

	// 是否上架:0为true即上架,1为false下架
	private Integer racking;

	// 逻辑删除：0存在,1删除
	private Integer deleteFlag;

	// 备注
	private String remark;

	// 不作为映射数据库字段
	@TableField(exist = false)
	private Courses courses;
}
