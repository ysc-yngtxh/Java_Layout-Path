package com.cn.leyou.api;

import com.cn.leyou.pojo.SpecGroup;
import com.cn.leyou.pojo.SpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SpecificationApi {

    @GetMapping("/spec/params")
    List<SpecParam> queryParamByGid(
            @RequestParam(value="gid",required = false) Long gid,
            @RequestParam(value="cid",required = false) Long cid,
            @RequestParam(value="searching",required = false) Boolean searching
    );
}
