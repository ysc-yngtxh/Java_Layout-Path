package com.example.service;

import com.example.entity.Sku;
import java.util.List;
import java.util.Map;

/**
 * (Sku)表服务接口
 * @author 游家纨绔
 * @since 2023-08-31 19:40:00
 */
public interface SkuService {

	Sku selectById(Integer Id);

	List<Map<String, Object>> selectMaps();

	void deleteLogic();
}
