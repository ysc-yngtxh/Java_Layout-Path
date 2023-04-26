package com.example.springbootshirojwt.service.impl;

import com.example.springbootshirojwt.exceptionhanler.ExceptionEnum;
import com.example.springbootshirojwt.exceptionhanler.LyException;
import com.example.springbootshirojwt.mapper.BrandMapper;
import com.example.springbootshirojwt.pojo.Brand;
import com.example.springbootshirojwt.service.BrandService;
import com.example.springbootshirojwt.vo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //分页,我们加入的分页助手依赖中的方法
        PageHelper.startPage(page,rows);
        //过滤
        Example example = new Example(Brand.class);
        if(StringUtils.isNotBlank(key)){
            //过滤条件  WHERE 'name' LIKE "%X%" OR letter == 'x'
            example.createCriteria().orLike("name","%" + key + "%")
                    .orEqualTo("letter",key.toUpperCase());//key表示的是搜索字段，toUpperCase()方法忽略大小写
        }
        //排序
        if(StringUtils.isNotBlank(sortBy)){
            //ORDER BY id DESC
            String orderByClause = sortBy +(desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<Brand> list = brandMapper.selectByExample(example);
        //selectByExample表示加上条件where查询
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        //解析分页结果
        /*将返回得到的list数据作为Page类型返回，这样才会展示分页效果。
          Page info = (Page) list;
          但是这样强转类型太不优雅了，所以我们将返回数据list对象传给PageInfo类型，
          让他去做向上转型。毕竟，优雅永不过时！！！
        */
        PageInfo<Brand> info = new PageInfo<>(list);
        return new PageResult<>(info.getTotal(),list);
    }


    @Transactional
    public void saveBrand(Brand brand,List<Long> cids){
        //新增品牌
        brand.setId(null);
        int count = brandMapper.insert(brand);
        if(count != 1){
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
        //新增中间表
        for (Long cid: cids) {
            count = brandMapper.insertCategoryBrand(cid,brand.getId());
            if(count != 1){
                throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
            }
        }
    }

    public Brand queryBrandById(Long id){
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if(brand == null){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        return brand;
    }

    public List<Brand> queryBrandByCid(Long cid){

        List<Brand> list = brandMapper.queryByCategoryId(cid);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        return list;
    }
}
