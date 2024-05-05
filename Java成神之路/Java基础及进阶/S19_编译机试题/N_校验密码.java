package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-29 12:49
 * @apiNote TODO 密码要求:
 *                      1.长度超过8位
 *                      2.包括大小写字母.数字.其他符号,以上四种至少三种
 *                      3.不能有长度大于2的不含公共元素的子串重复 （注：其他符号不含空格或换行）
 *                      4.数据范围：输入的字符串长度满足 1<=n<=100
 *                        例如：021Abc9000    YES
 *                             021Abc9Abc1   NO
 *                             021ABC9000    NO
 *                             021$bc9000    YES
 */
public class N_校验密码 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String split = scanner.nextLine();

        // 验证密码长度是否符合要求
        if (split.length()<= 8 || split.length()>100){
            System.out.println("密码长度不符合要求");
            return;
        }

        // 根据要求：子串可以重复，但重复的子串长度不能超过2
        int upper=0, lower=0, num=0, other=0;
        for (int i = 0; i < split.length(); i++) {
            if ('A'<=split.charAt(i) && split.charAt(i)<='Z'){
                upper++;
            } else if ('a'<=split.charAt(i) && split.charAt(i)<='z') {
                lower++;
            } else if ('0'<=split.charAt(i) && split.charAt(i)<='9') {
                num++;
            } else {
                other++;
            }
        }
        if (upper+lower+num+other < 3) {
            System.out.println("密码要求不符合要求");
        }

        // 验证是否有长度大于2的子串重复
        for (int i = 0; i < split.length(); i++) {
            for (int j = i+3; j < split.length(); j++) {
                String tmp = split.substring(i, j);
                // 重复长度可能 3，4，5...
                if (split.substring(j).contains(tmp)) {
                    System.out.println("出现长度大于2的字符串重复");
                }
            }
        }
    }
}
