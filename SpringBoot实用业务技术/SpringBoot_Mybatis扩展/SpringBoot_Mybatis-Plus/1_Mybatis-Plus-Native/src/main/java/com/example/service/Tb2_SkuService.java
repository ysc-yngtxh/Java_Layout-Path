package com.example.service;

import com.example.entity.Tb2_Sku;

import java.util.List;
import java.util.Map;

/**
 * (Tb2_Sku)表服务接口
 * @author 游家纨绔
 * @since 2023-08-31 19:37:00
 */
public interface Tb2_SkuService {
    Tb2_Sku selectById(Integer Id);
    List<Map<String, Object>> selectMaps();
    void deleteLogic();
}
