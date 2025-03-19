package M13_反射.反射Ⅲ_反射Method;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/*
 * 用户业务类
 */
public class User {
    /**
     * 登陆方法
     * name      用户名
     * password  密码
     * true表示登陆成功，false表示登陆失败
     */
    public boolean login(String name, String password) {
        if("admin".equals(name) && "123".equals(password)) {
            return true;
        }
        return false;
    }

    /**
     * 获取所有用户名
     */
    public Map<String, List<Integer>> getUsers() {
        return Collections.singletonMap("姓名", Collections.singletonList(28));
    }

    /**
     * 退出系统的方法
     */
    public void logout() {
        System.out.println("系统已经安全退出！");
    }
}
