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
public class test {
    public static void main(String[] args) {
        System.out.println("--游诗成");
        USER user1 = new USER();
        user1.setDate("2022-11-01");
        USER user2 = new USER();
        user2.setDate("2022-11-02");
        USER user3 = new USER();
        user3.setDate("2022-11-03");
        USER user4 = new USER();
        user4.setDate("2022-11-02");
        List<USER> users = Arrays.asList(user1, user2, user3, user4);

        Map<String, List<USER>> collect = users.stream()
                .collect(groupingBy(USER::getDate));


//        collect
//                .stream().map(obj -> {
//                            obj.setNewDate(obj.getDate());
//                            return obj;
//                        }
//                ).collect(Collectors.toList());

//        collect.forEach(user->
//        {
            System.out.println(user1.toString());
//        });

    }

}
