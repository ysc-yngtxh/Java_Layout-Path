package com.example.controller;

import com.example.entity.Sku;
import com.example.service.SkuService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * (Sku)表控制层
 * @author 游家纨绔
 * @since 2023-08-31 20:00:00
 */
@RestController
@RequestMapping("tbSku")
@RequiredArgsConstructor
public class SkuController {

	private final SkuService tb2SkuService;

	@RequestMapping("/selectById")
	public Sku selectById(@RequestParam(required = false) Integer Id) {
		return tb2SkuService.selectById(Id);
	}

	@RequestMapping("/selectMaps")
	public List<Map<String, Object>> selectMaps() {
		return tb2SkuService.selectMaps();
	}

	@RequestMapping("/deleteLogic")
	public Sku deleteLogic(@RequestParam(required = false) Integer id) {
		tb2SkuService.deleteLogic();
		return tb2SkuService.selectById(id);
	}
}
