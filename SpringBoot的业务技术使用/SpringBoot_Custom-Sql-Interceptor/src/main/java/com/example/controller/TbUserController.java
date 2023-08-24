package com.example.controller;

import com.example.entity.TbUser;
import com.example.service.TbUserService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表(TbUser)表控制层
 *
 * @author 游家纨绔
 * @since 2023-08-24 23:42:04
 */
@RestController
@RequestMapping("tbUser")
public class TbUserController {
    /**
     * 服务对象
     */
    @Resource
    private TbUserService tbUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TbUser> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.tbUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tbUser 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TbUser> add(TbUser tbUser) {
        return ResponseEntity.ok(this.tbUserService.insert(tbUser));
    }

    /**
     * 编辑数据
     *
     * @param tbUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TbUser> edit(TbUser tbUser) {
        return ResponseEntity.ok(this.tbUserService.update(tbUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.tbUserService.deleteById(id));
    }

}

