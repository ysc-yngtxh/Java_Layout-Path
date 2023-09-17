package com.example.springbootshirojwt.service.impl;


import com.example.springbootshirojwt.exceptionhanler.ExceptionEnum;
import com.example.springbootshirojwt.exceptionhanler.LyException;
import com.example.springbootshirojwt.mapper.SpecGroupMapper;
import com.example.springbootshirojwt.mapper.SpecParamMapper;
import com.example.springbootshirojwt.pojo.SpecGroup;
import com.example.springbootshirojwt.pojo.SpecParam;
import com.example.springbootshirojwt.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    public List<SpecGroup> queryGroupByCid(Long cid) {
        // 查询条件
        SpecGroup group = new SpecGroup();
        group.setCid(cid);
        // 查询
        List<SpecGroup> list = groupMapper.select(group);
        if(CollectionUtils.isEmpty(list)){
            // 没查到
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOND);
        }

        return list;
    }

    public List<SpecParam> queryParamByGid(Long gid, Long cid, Boolean searching) {
        SpecParam param = new SpecParam();
        param.setGroupid(gid);
        param.setId(cid);
        param.setSearching(searching);
        List<SpecParam> list = paramMapper.select(param);
        if(CollectionUtils.isEmpty(list)){
            // 没查到
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOND);
        }
        return list;
    }

    public List<SpecGroup> queryNameGid(String name) {
        SpecGroup group = new SpecGroup();
        group.setName(name);
        List<SpecGroup> list = groupMapper.select(group);
        if(CollectionUtils.isEmpty(list)){
            // 没查到
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOND);
        }
        return list;
    }


}
                                                                                        