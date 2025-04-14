package com.example.service;

import com.example.mapper.CanalMapper;
import com.example.pojo.CanalPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (CanalPO)表服务实现类
 *
 * @author makejava
 * @since 2022-03-05 11:30:32
 */
@Service
public class CanalService {

    @Autowired
    private CanalMapper canalMapper;

   public CanalPO query(Integer id) {
       return canalMapper.selectByPrimaryKey(id);
   }

   public List<CanalPO> queryList() {
       return canalMapper.selectAll();
   }

   public int save(CanalPO testysc) {
       return canalMapper.insert(testysc);
   }

   public int update(CanalPO testysc) {
       int i = canalMapper.updateByPrimaryKey(testysc);
       return i;
   }

    public boolean remove(Integer id) {
        int i = canalMapper.deleteByPrimaryKey(id);
        return String.valueOf(i).isEmpty();
    }
}
