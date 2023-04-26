package com.cn.leyou.service;

import com.cn.leyou.client.BrandClient;
import com.cn.leyou.client.CategoryClient;
import com.cn.leyou.client.GoodsClient;
import com.cn.leyou.client.SpecificationClient;
import com.cn.leyou.enums.ExceptionEnum;
import com.cn.leyou.pojo.*;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cn.leyou.exception.LyException;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private BrandClient brandClient;

    /*@Autowired
    private GoodsClient goodsClient;
    @Autowired
    private SpecificationClient specificationClient;*/

    public Goods buildGoods(Spu spu){

        //查询分类
        List<Category> categories = categoryClient.queryCategoryByIds(
                Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        if(CollectionUtils.isEmpty(categories)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        List<String> names = categories.stream().map(Category::getName).collect(Collectors.toList());
        //查询品牌
        Brand brand = brandClient.queryBrandById(spu.getBrandId());
        if (brand == null) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        //搜索字段
        String all = spu.getTitle() + StringUtils.join(names,"") + brand.getName();

        //查询sku //查询规格参数
        /*List<Sku> skuList = goodsClient.querySkuById(spu.getId());
        if(CollectionUtils.isEmpty(skuList)){
            throw new LyException(ExceptionEnum.GOODS_NOT_FOND);
        }
        Set<Long> priceList = skuList.stream().map(Sku::getPrice).collect(Collectors.toSet());
        ......
        */


        //构建goods对象
        Goods goods = new Goods();
        goods.setBrandId(spu.getBrandId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setId(spu.getId());
        goods.setAll(all);   //搜索字段，包含标题，分类，品牌，规格等
        /*goods.setPrice(null); 所有sku的价格集合
        goods.setSkus(null);  所有sku的集合的json格式
        goods.setSpecs(null); 所有的可搜索的规格参数
        goods.setSubTitle(spu.getSubTitle());*/
        return goods;
    }

    /*private String chooseSegment(String value, SpecParam p){
        double val = NumberUtils.toDouble(value);
        String result = "其他";
        //保存数值段
        for (String segment:p.getSegments().split(",")){
            String[] segs = segment.split("-");
            //获取数值范围
            double begin = NumberUtils.toDouble(segs[0]);
            double end = Double.MAX_VALUE;
            if (segs.length==2){
                end=NumberUtils.toDouble(segs[1]);
            }
            //判断是否在范围内
            if (val>=begin && val<end){
                if (segs.length==1){
                    result = segs[0]+p.getUnit()+"以上";
                }else if (begin==0){
                    result = segs[1]+p.getUnit()+"以下";
                }else{
                    result = segment+p.getUnit();
                }
                break;
            }
        }
        return result;
    }*/
}
