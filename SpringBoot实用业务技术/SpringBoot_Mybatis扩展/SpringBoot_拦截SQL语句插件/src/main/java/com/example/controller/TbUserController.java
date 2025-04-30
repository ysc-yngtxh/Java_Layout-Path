package com.example.controller;

import com.example.entity.TbUser;
import com.example.service.TbUserService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * (TbUser)表控制层
 *
 * @author makejava
 * @since 2023-08-25 00:19:30
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
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/IgnoreTenant/{id}")
    public ResponseEntity<TbUser> queryByIdIgnoreTenant(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.tbUserService.queryByIdIgnoreTenant(id));
    }

    @GetMapping("/query")
    public ResponseEntity<Map<String, TbUser>> query() {
        return ResponseEntity.ok(this.tbUserService.query());
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
    @PutMapping("/update")
    public ResponseEntity<TbUser> edit(@RequestBody TbUser tbUser) {
        return ResponseEntity.ok(this.tbUserService.update(tbUser));
    }


    /**
     * 编辑数据
     *
     * @param tbUser 实体
     * @return 编辑结果
     */
    @PutMapping("/fullUpdate")
    public ResponseEntity<TbUser> editFull(@RequestBody TbUser tbUser) {
        return ResponseEntity.ok(this.tbUserService.fullTableUpdate(tbUser));
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
