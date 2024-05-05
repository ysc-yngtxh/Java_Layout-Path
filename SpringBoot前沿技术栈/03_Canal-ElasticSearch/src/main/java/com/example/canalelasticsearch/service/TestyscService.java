package com.example.canalelasticsearch.service;

import com.example.canalelasticsearch.mapper.TestyscMapper;
import com.example.canalelasticsearch.pojo.Testysc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Testysc)表服务实现类
 *
 * @author makejava
 * @since 2022-03-05 11:30:32
 */
@Service
public class TestyscService {

    @Autowired
    private TestyscMapper testyscMapper;

   public Testysc query(Integer id){
       return testyscMapper.selectByPrimaryKey(id);
   }

   public List<Testysc> queryList(){
       return testyscMapper.selectAll();
   }

   public int save(Testysc testysc){
       return testyscMapper.insert(testysc);
   }

   public int update(Testysc testysc){
       int i = testyscMapper.updateByPrimaryKey(testysc);
       return i;
   }

    public boolean remove(Integer id){
        int i = testyscMapper.deleteByPrimaryKey(id);
        return String.valueOf(i).isEmpty();
    }
}
