package com.example.字符及循环和正则;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-29 17:50
 * @apiNote TODO 循环
 */
public class CustomCycle {

    public static void main(String[] args) {
        // TODO 区别迭代器、for循环、增强for循环、list.foreach()、stream.foreach()
        // 原生语法跳出当前循环使用continue
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            if (next == 3) {
                continue;
            }
            if (next == 5) {
                break;
            }
            System.out.println(next);
        }
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                continue; // 跳出本次循环
            } else if (i == 4) {
                break;    // 跳出整个循环
            }
            System.out.println(i);;
        }
        for (String k : Arrays.asList("123", "456", "789")) {
            if ("123".equals(k)) {
                continue; // 跳出本次循环
            } else if ("789".equals(k)){
                break;    // 跳出整个循环
            }
            System.out.println(k);
        }

        // 使用list.foreach或者stream.foreach跳出当前循环使用return(不支持跳出整个循环的关键字)
        // 怎么理解支持return呢？foreach()里是函数式Consumer消费者没有返回值，所以return只结束当前的循环的Consumer
        Arrays.asList("ysc","ysq","jack").forEach(a -> {
            if ("ysq".equals(a)) {
                return;
            }
            System.out.println(a + "--List");
        });
        Arrays.asList("ysc", "ysq", "jack").stream().forEach(a -> {
            if ("ysq".equals(a)) {
                return;
            }
            System.out.println(a + "--stream");
        });

        // 这种写法目前不清楚优势在哪儿(网上说不建议使用，那咱就当是见识)
        for (Iterator<String> itt = Arrays.asList("ysc", "ysq", "jack").iterator(); itt.hasNext();) {
            String next = itt.next();
            System.out.println(next);
        }

        // 在for循环前加上标识，可以在循环体内去break标识，跳出指定循环体
        case1 : for (int i = 0; i < 5; i++) {
            case2 : for (int j = 0; j < 5; j++) {
                case3 : for (int k = 0; k < 5; k++) {
                    if (k == 4) {
                        break case2;
                    }
                    System.out.println(i + "--" + j + "--" + k);
                }
            }
        }
    }
}
