package com.example.config;

import java.util.ArrayList;
import org.activiti.api.runtime.shared.identity.UserGroupManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class ActivitySecurityConfig {

	// 在 Activity 工作流引擎中，UserGroupManager 是一个重要的身份管理接口，负责处理用户、组（用户组）以及它们之间关系的管理。
	// @Bean
    // public UserGroupManager userGroupManager() {
    //     return new UserGroupManager() {
    //         @Override
    //         public List<String> getUserGroups(String username) {
    //             // 返回空列表或模拟数据
    //             return new ArrayList<>();
    //         }
    //
    //         @Override
    //         public List<String> getUserRoles(String username) {
    //             // 返回空列表或模拟数据
    //             return new ArrayList<>();
    //         }
    //
    //         @Override
    //         public List<String> getGroups() {
    //             return new ArrayList<>();
    //         }
    //
    //         @Override
    //         public List<String> getUsers() {
    //             return new ArrayList<>();
    //         }
    //     };
    // }

}
