package com.cn.leyou.service;

import com.cn.leyou.mapper.SpuDetailMapper;
import com.cn.leyou.mapper.SpuMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cn.leyou.enums.ExceptionEnum;
import com.cn.leyou.exception.LyException;
import com.cn.leyou.pojo.Category;
import com.cn.leyou.pojo.Spu;
import com.cn.leyou.vo.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper detailMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        //分页
        PageHelper.startPage(page,rows);
        //过滤
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //搜索字段过滤
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title","%" + key + "%");
        }
        //上下架过滤
        if(saleable != null){
            criteria.andEqualTo("saleable",saleable);
        }
        //默认排序
        example.setOrderByClause("last_update_time DESC");
        //查询
        List<Spu> spus = spuMapper.selectByExample(example);
        //判断
        if(CollectionUtils.isEmpty(spus)){
            throw new LyException(ExceptionEnum.GOODS_NOT_FOND);
        }
        //解析分类和品牌的名称
        loadCategoryAndBrandName(spus);

        //解析分页结果
        PageInfo<Spu> info = new PageInfo<>(spus);
        return new PageResult<>(info.getTotal(),spus);
    }

    private void loadCategoryAndBrandName(List<Spu> spus){

        for (Spu spu:spus
             ) {
            //处理分类名称
            List<String> names = categoryService.queryByIds( Arrays.asList(spu.getCid1(),spu.getCid2(),
                    spu.getCid3())).stream().map(Category::getName).collect(Collectors.toList());
            spu.setCname(StringUtils.join(names,"/"));

          /*
            Arrays.asList()方法可以将数组转化为集合，也可以往里面添加数据。
            stream().map(Category::getName).collect(Collectors.toList())
            首先是在流Stream()中创建一个管道，取出Category中的所有getName,然后到collect的List集合中进行遍历。

            stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
           对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，
           而是会产生一个不包含被过滤元素的新stream。
           stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，想要再次遍历必须重新生成。
          */
            //处理品牌名称
            spu.setBname(brandService.queryBrandById(spu.getBrandId()).getName());

        }
    }
}
