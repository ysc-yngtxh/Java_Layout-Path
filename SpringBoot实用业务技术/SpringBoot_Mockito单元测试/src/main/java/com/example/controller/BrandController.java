package com.example.controller;

import com.example.pojo.Brand;
import com.example.service.impl.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2025-09-29 00:00
 * @apiNote TODO
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;


	@GetMapping("/test")
	public String greet(@RequestParam(required = false, defaultValue = "World") String name) {
		System.out.println("BrandController.test()");
		return "Hello, " + name + "!";
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ResponseEntity<Brand> findBrandById(@RequestParam(value = "id", defaultValue = "1") Integer id) {
		System.out.println("BrandController.findBrandById()");
		return ResponseEntity.status(HttpStatus.CREATED)
		                     .body(brandService.findAllById(id));
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Brand> addBrand(@RequestBody Brand brand) {
		System.out.println("BrandController.addBrand()");
		return ResponseEntity.status(HttpStatus.CREATED)
		                     .body(brandService.add(brand));
	}
}
