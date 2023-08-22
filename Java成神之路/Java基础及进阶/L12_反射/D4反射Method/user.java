package L12_反射.D4反射Method;

/*
用户业务类
 */
public class user {

    /**
     * 登陆方法
     * name      用户名
     * password  密码
     * true表示登陆成功，false表示登陆失败
     */

    public boolean login(String name,String password){
        if("admin".equals(name) && "123".equals(password)){
            return true;
        }
        return false;
    }

    /**
     * 退出系统的方法
     */
    public void logout(){
        System.out.println("系统已经安全退出！");
    }
}
