package com.bjpowernode.dao;

import com.bjpowernode.domain.SysUser;
import org.springframework.stereotype.Repository;

@Repository("oracleDao")
public class OracleDaoImpl implements UserDao {
    @Override
    public void insertUser(SysUser user) {
        System.out.println("oracle 的insertUser");
    }
}
