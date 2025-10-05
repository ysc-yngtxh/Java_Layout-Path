package com.example.repository;

import com.example.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository 用于简单查询，传入泛型，第一个参数为要操作的实体类，第二个参数为该实体类的主键类型
// JpaSpecificationExecutor 可用于动态生成query，提供了一个高级的入口可以使用底层Jpa的Criteria的所有方法，用以满足所有业务场景
public interface TeacherRepository extends JpaRepository<Teacher, Integer>, JpaSpecificationExecutor<Teacher> {}
