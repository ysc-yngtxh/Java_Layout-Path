package com.example.controller;

import com.example.pojo.Brand;
import com.example.service.impl.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 游家纨绔
 * @dateTime 2025-09-29 00:00
 * @apiNote TODO
 */
@Controller("/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@RequestMapping("/test1")
	public Brand findBrandById() {
		System.out.println("BrandController.test1()");
		return brandService.findAllById(1);
	}
}
