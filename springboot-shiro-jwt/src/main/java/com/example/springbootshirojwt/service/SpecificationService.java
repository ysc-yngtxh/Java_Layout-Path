package com.example.springbootshirojwt.service;

import com.example.springbootshirojwt.pojo.SpecGroup;
import com.example.springbootshirojwt.pojo.SpecParam;

import java.util.List;

public interface SpecificationService {

    List<SpecGroup> queryGroupByCid(Long cid);

    List<SpecParam> queryParamByGid(Long gid, Long cid, Boolean searching);

    List<SpecGroup> queryNameGid(String name);
}
