package com.example.service;

import com.example.mapper.StudentMapper;
import com.example.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    // 这里要想实现Mybatis的一级缓存，就必须加上事务注解@Transactional
    // 因为执行的studentMapper对象是通过依赖注入的方式获取的，即studentMapper实例是由 Spring 容器管理的
    // 而 studentMapper 的实例化过程中会创建一个新的 SqlSession。因此，每次查询都是在不同的 SqlSession 中进行的
    // 并且每次查询完成后，都会自动关闭sqlSession连接。所以，第二个查询同样会创建一个新的 SqlSession用以连接数据库。
    // 综上：第一个查询sqlSession关闭后自动清除掉一级缓存，且第二个查询的sqlSession与第一个查询不是同一个
    //      所以，这里第二个查询语句是无法从Mybatis的一级缓存里获取数据
    // 在Mybatis源码中SqlSessionUtils.getSqlSession()时候执行一个sessionHolder()方法，在这个方法里判断是否执行的方法是事务性的，
    // 如果加了 @Transactional，则会把sqlSession暂存在ThreadLocal中，则当第二次执行相同的mapper、sql、参数的时候就会去ThreadLocal中去取有没有，
    // 如果没有，那么直接返回SqlSession为null，那么当第二次执行相同的mapper就会新建一个新的SqlSession
    @Override
    @Transactional
    public Student queryStudentByIdCacheL1(Integer id) {
        System.out.println("============第一次查询============");
        Student student = studentMapper.selectByPrimaryKey(id);
        System.out.println(student);

        System.out.println("============第二次查询============");
        Student student1 = studentMapper.selectByPrimaryKey(id);
        System.out.println(student1);
        return student;
    }

    // 使用replace into方式新增或更新
    @Override
    public int insertReplaceInto(Student student) {
        return studentMapper.insertReplaceInto(student);
    }

    // 使用insert ignore into，存在就忽略新增
    @Override
    public int insertIgnoreInto(Student student) {
        return studentMapper.insertIgnoreInto(student);
    }

    // 使用on duplicate key update方式新增或更新
    @Override
    public int insertDuplicateKeyUpdate(Student student) {
        return studentMapper.insertDuplicateKeyUpdate(student);
    }

    // 使用on duplicate key update方式批量新增或更新
    @Override
    public int insertDuplicateKeyUpdateBatch(List<Student> list) {
        return studentMapper.insertDuplicateKeyUpdateBatch(list);
    }

    @Override
    public int insertOrUpdateOneUserInfo(Student student) {
        return studentMapper.insertOrUpdateOneUserInfo(student);
    }
}
