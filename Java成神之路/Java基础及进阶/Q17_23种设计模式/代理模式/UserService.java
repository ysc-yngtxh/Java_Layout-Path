package Q17_23种设计模式.代理模式;

public interface UserService {
    void save(String username);
}

class UserServiceImpl implements UserService {
    @Override
    public void save(String username) {
        System.out.println("保存用户: " + username);
    }
}

class UserServiceProxy implements UserService {
    private final UserService target; // 目标对象

    public UserServiceProxy(UserService target) {
        this.target = target;
    }

    @Override
    public void save(String username) {
        System.out.println("【代理】前置处理");
        target.save(username); // 调用目标方法
        System.out.println("【代理】后置处理");
    }
}
