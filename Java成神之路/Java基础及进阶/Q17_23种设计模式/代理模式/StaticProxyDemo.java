package Q17_23种设计模式.代理模式;

public class StaticProxyDemo {

    public static void main(String[] args) {
        UserService target = new UserServiceImpl();
        UserService proxy = new UserServiceProxy(target);
        proxy.save("张三");
    }

}
