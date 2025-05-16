package com.example.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-03-05 16:40:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produce {

	// 商品ID
	private String id;
	// 商品名称
	private String name;
	// 商品价格
	private String price;
	// 商品库存
	private String stock;
	// 商品描述
	private String desc;
	// 商品图片
	private String img;
	// 商品类型
	private String type;
	// 商品品牌地：注意 GeoPoint类 是ES提供的地理位置类型
	private GeoPoint brandLocation;
	// 商品规格
	private String spec;
	// 商品状态
	private String status;
	// 商品创建时间
	private Date createTime;
	// 商品更新时间
	private Date updateTime;
	// 商品创建人
	private String createUser;
	// 商品更新人
	private String updateUser;
	// 商品备注
	private String remark;
}
