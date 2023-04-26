package com.system.service;

import com.system.pojo.Registry;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/7 15:55
 */
public interface RegistryService {
    Registry selectUserId(int id);

    Registry selectUser(String username);

    int insertUser(Registry registry);


}
