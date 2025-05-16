package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (User)实体类
 *
 * @author 游家纨绔
 * @since 2023-07-10 23:50:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("db_user")
public class User implements Serializable {
	private static final long serialVersionUID = -25637857384418168L;

	/**
	 * 主键Id
	 */
	@TableId
	private Integer id;
	/**
	 * 上级Id
	 */
	private Integer superiorId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String passWord;
	/**
	 * 别名
	 */
	private String alias;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮件
	 */
	private String email;
	/**
	 * 逻辑删除
	 */
	private Integer deleteFlag;
	/**
	 * 创建时间
	 */
	private Date createdDate;
	/**
	 * 更新时间
	 */
	private Date updatedDate;
}
