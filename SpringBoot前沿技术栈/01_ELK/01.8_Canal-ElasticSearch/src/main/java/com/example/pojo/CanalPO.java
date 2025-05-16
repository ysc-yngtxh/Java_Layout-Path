package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * (CanalPO)实体类
 *
 * @author 游家纨绔
 * @since 2022-03-05 11:30:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "testysc")
public class CanalPO {

	@Id
	private Integer id;

	private String name;

	private String address;

	@Override
	public String toString() {
		return "CanalPO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
