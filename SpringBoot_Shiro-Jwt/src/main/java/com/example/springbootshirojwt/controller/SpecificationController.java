package com.example.springbootshirojwt.controller;

import com.example.springbootshirojwt.pojo.SpecGroup;
import com.example.springbootshirojwt.pojo.SpecParam;
import com.example.springbootshirojwt.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specService;

    // 根据分类id查询规格组
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid){
        return ResponseEntity.ok(specService.queryGroupByCid(cid));
    }
    // 根据组id查询参数
    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> queryParamByGid(
            @RequestParam(value="gid",required = false) Long gid,
            @RequestParam(value="cid",required = false) Long cid,
            @RequestParam(value="searching",required = false) Boolean searching
            ){
        return ResponseEntity.ok(specService.queryParamByGid(gid, cid, searching));
    }

    @GetMapping("/name")
    public ResponseEntity<List<SpecGroup>> queryNameGid(@RequestParam(value="name") String name){

        return ResponseEntity.ok(specService.queryNameGid(name));
    }
}
