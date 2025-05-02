package com.example.transactional.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.transactional.dao.StudentDao;
import com.example.transactional.entity.Student;
import com.example.transactional.service.StudentService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Student)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-11-11 17:32:05
 */
@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser() {
        try {
            Student student1 = new Student(10, "敏敏", "12345@11.com", 22);
            studentDao.updateById(student1);
            // 数据库中已经存在 Id=2 的数据，因此这条数据插入会失败，会出现运行异常
            // 因此，只需要判断上述更新语句Id=10的这条数据是否被修改。如果修改，事务失效；如果没有修改，说明事务有效执行
            Student student2 = new Student(2, "敏敏", "12387@11.com", 22);
            studentDao.insert(student2);
        } catch (Exception e) {
            System.out.println("insert()方法故意抛异常，制造回滚");
            // 注意：事务默认回滚的异常为【RuntimeException】
            // 如果使用@Transactional(rollbackFor = Exception.class),就不要使用try..catch。因为你捕获了异常，没办法进行回滚
            throw new RuntimeException();
        }
    }

    @Override
    public void saveSigUser() {
        System.out.println("执行saveSigUser()方法...");
        try {
            Student student = new Student(8, "敏敏", "12387@11.com", 22);
            studentDao.updateById(student);
        } catch (Exception e) {
            System.out.println("updateById()方法更新异常");
        }
        // 事务是通过动态代理实现，因此需要代理对象才能生效。这里调用saveUser()方法是this对象，即为直接对象，事务不生效
        this.saveUser();
    }

    @Override
    public void saveAllUser() {
        System.out.println("执行saveAllUser()方法...");
        try {
            Student student = new Student(9, "敏敏", "12387@11.com", 22);
            studentDao.updateById(student);
        } catch (Exception e) {
            System.out.println("updateById()方法更新异常");
        }
        // AopContext.currentProxy() 是 Spring 框架中的一个静态方法，用于获取当前正在执行的代理对象。
        // 这个方法是在组件内部调用的，目的是让当前组件能够访问自己的代理对象，从而在方法内部调用目标方法或其他切入点。
        // 将 currentProxy 强制转换为 proxyService 类型，即为代理对象。这个时候saveUser()方法的事务会生效执行
        StudentService currentProxy = (StudentService) AopContext.currentProxy();
        currentProxy.saveUser();
    }
}
