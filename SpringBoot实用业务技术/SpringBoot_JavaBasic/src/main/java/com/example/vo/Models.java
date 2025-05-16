package com.example.vo;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2022-11-27 10:50:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Models {

	private ModelView modelView;
	private Optional<ModelView> modelViewOptional;

	@Override
	public String toString() {
		return "Models{" +
				"modelView=" + modelView +
				", modelViewOptional=" + modelViewOptional +
				'}';
	}
}
