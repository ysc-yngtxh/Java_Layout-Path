package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/11/27 10:51
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
