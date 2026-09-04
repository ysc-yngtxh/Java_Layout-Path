package com.example.mapper;

import com.example.pojo.Student;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.cache.decorators.LruCache;

/**
 * @author 游家纨绔
 */
// @CacheNamespace 注解用于开启 MyBatis 二级缓存（只针对注解类型 Sql 才有效 -- @Select, @Update 等）
//     eviction      : 属性指定缓存的驱逐策略，这里使用 LRU（最近最少使用）策略。
//     flushInterval : 属性指定缓存刷新间隔时间（自动清空当前 Mapper 命名空间下的二级缓存），这里设置为 3600000 毫秒（即 1 小时）。
//     size          : 属性指定缓存的大小，这里设置为 512 条记录。
//     readWrite     : 属性指定缓存是否可读写，这里设置为 false，表示缓存是只读的。
@CacheNamespace(eviction = LruCache.class, flushInterval = 3600000, size = 512, readWrite = false)
public interface FindLevelOneMapper {

	Student selectByPrimaryKey(Integer id);



    @Select("""
             select
                 id,
                 name,
                 teacher_id AS teacherId,
                 email,
                 age,
                 department_id AS departmentId
             from db_student
             where id = #{id}
    """)
    Student selectByPrimaryKeyOnAnnotation(Integer id);


    @Update("""
             update db_student
             set email = #{email}
             where id = #{id}
    """)
    int updateByPrimaryKeySelective(Student record);
}
