package N14练习题;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/11/04 22:44
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("--游诗成");
        User user1 = new User();
        user1.setDate("2022-11-01");
        User user2 = new User();
        user2.setDate("2022-11-02");
        User user3 = new User();
        user3.setDate("2022-11-03");
        User user4 = new User();
        user4.setDate("2022-11-02");
        List<User> users = Arrays.asList(user1, user2, user3, user4);

        Map<String, List<User>> collect = users.stream()
                .collect(groupingBy(User::getDate));


//        collect
//                .stream().map(obj -> {
//                            obj.setNewDate(obj.getDate());
//                            return obj;
//                        }
//                ).collect(Collectors.toList());

//        collect.forEach(user->
//        {
            System.out.println(user1);
//        });

    }

}
