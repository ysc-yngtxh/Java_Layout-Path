package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.utils.ApprenticeUtil;
import com.example.vo.ResponseUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 核心公共controller类
 */
public class BaseController<S extends IService<E>, E> {

	@Autowired
	protected S baseService;

	// "增"
	@PostMapping("/insert")
	public ResponseUtils<String> insert(@RequestBody E entity) {
		baseService.save(entity);
		return ResponseUtils.success("添加成功");
	}

	// "删"
	@PostMapping("/deleteById")
	public ResponseUtils<String> delete(@RequestBody List<Integer> ids) {
		baseService.removeByIds(ids);
		return ResponseUtils.success("添加成功");
	}

	// "改"
	@PostMapping("/updateById")
	public ResponseUtils<String> updateById(@RequestBody E entity) {
		baseService.updateById(entity);
		return ResponseUtils.success("添加成功");
	}

	// "查"
	@GetMapping("/getById")
	public ResponseUtils<E> getById(@RequestParam Integer id) {

		return ResponseUtils.success(baseService.getById(id));
	}

	// "存"
	@PostMapping("/save")
	public ResponseUtils<String> save(@RequestBody E entity) {
		baseService.saveOrUpdate(entity);
		return ResponseUtils.success("添加成功");
	}

	// "list查"
	@PostMapping("/list")
	public ResponseUtils<List<E>> list(@RequestBody E entity) {
		QueryWrapper<E> queryWrapper = ApprenticeUtil.getQueryWrapper(entity);
		List<E> list = baseService.list(queryWrapper);
		return ResponseUtils.success(list);
	}

	// "page查"
	// @PostMapping("/page")
	// public ResponseUtils page(@RequestBody PageParamDto<E> pageParamDto) {
	//     // 限制条件
	//     if (pageParamDto.getPage() < 1) {
	//         pageParamDto.setPage(1);
	//     }
	//
	//     if (pageParamDto.getSize() > 100) {
	//         pageParamDto.setSize(100);
	//     }
	//     Page<E> page = new Page<>(pageParamDto.getPage(), pageParamDto.getSize());
	//     QueryWrapper<E> queryWrapper = new QueryWrapper<>();
	//     // 升序
	//     String asc = pageParamDto.getAsc();
	//     if (!StrUtil.isEmpty(asc) && !"null".equals(asc)) {
	//         String[] split = asc.split(",");
	//         queryWrapper.orderByAsc(split);
	//     }
	//     // 降序
	//     String desc = pageParamDto.getDesc();
	//     if (!StrUtil.isEmpty(desc) && !"null".equals(desc)) {
	//         String[] split = desc.split(",");
	//         queryWrapper.orderByDesc(split);
	//     }
	//     Page<E> ePage = baseService.page(page, queryWrapper);
	//     return ResponseUtils.success(ePage);
	// }

	// "获取数量"
	@PostMapping("/count")
	public ResponseUtils count(@RequestBody E entity) {
		QueryWrapper<E> queryWrapper = ApprenticeUtil.getQueryWrapper(entity);
		long count = baseService.count(queryWrapper);
		return ResponseUtils.success(count);
	}
}
