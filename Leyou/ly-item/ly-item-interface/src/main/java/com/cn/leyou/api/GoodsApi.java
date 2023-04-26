package com.cn.leyou.api;

import com.cn.leyou.pojo.Spu;
import com.cn.leyou.vo.PageResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface GoodsApi {

    @GetMapping("/spu/page")
    PageResult<Spu> querySpuByPage(
            @RequestParam(value="page",defaultValue = "1") Integer page,
            @RequestParam(value="rows",defaultValue = "5") Integer rows,
            @RequestParam(value="saleable",required = false) Boolean saleable,
            @RequestParam(value="key",required = false) String key);
}
