package com.example.字符及循环和正则;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-29 17:49
 * @apiNote TODO 正则详情
 */
public class CustomRegex {
    public static void main(String[] args) {

        // TODO Pattern在java.util.regex包中，是正则表达式的编译表示形式，此类的实例是不可变的，可供多个并发线程安全使用。
        //      Pattern的构造器被设计为私有，不允许通过new的方式创建Pattern。
        // 正则校验 ^开头 $结尾 (\+86|0026)表示匹配+86或0026 ?表示两部分的间隔符 (\s*|-)表示匹配若干个或者0个空格;或者-  d{11}表示11个数字
        String regex = "^(\\+86|0026)?(\\s*|-)?\\d{11}$";
        /** matches(String regex, CharSequence input)，对整个字符串进行匹配,只有整个字符串都匹配了才返回true。*/
        boolean matches1 = Pattern.matches(regex, "+86-12345678952");
        boolean matches2 = Pattern.matches(regex, "+86 12345678952");
        boolean matches3 = Pattern.matches(regex, "002612345678952");

        /** compile(String regex)，将给定的正则表达式编译为模式。*/
        Pattern p = Pattern.compile(regex);
        boolean matches = p.matcher("+8612345678952").matches();
        System.out.println(matches1 + "\n" + matches2 + "\n" + matches3 + "\n" + matches);

        /** String[] split(CharSequence input, int limit) limit参数控制应用模式的次数，从而影响结果数组的长度。*/
        Pattern p2 = Pattern.compile("\\d+");
        String[] str = p2.split("我的QQ是:456456我的电话是:0532214我的邮箱是:aaa@aaa.com");
        // 运行结果：str[0]="我的QQ是:"   str[1]="我的电话是:"   str[2]="我的邮箱是:aaa@aaa.com"
        System.out.println(Arrays.toString(str));
        // 运行结果：str[0]="我的QQ是:"   str[1]="我的电话是:0532214我的邮箱是:aaa@aaa.com"
        System.out.println(Arrays.toString(p2.split("我的QQ是:456456我的电话是:0532214我的邮箱是:aaa@aaa.com", 2)));



        // TODO Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查，此类的实例用于多个并发线程是不安全的。
        //      一个Matcher实例是被用来对目标字符串进行基于既有模式（也就是一个给定的Pattern所编译的正则表达式）进行匹配查找的，
        //      所有往Matcher的输入都是通过CharSequence接口提供的，这样做的目的在于可以支持对从多元化的数据源所提供的数据进行匹配工作。
        Pattern p3 = Pattern.compile("(\\w+)%(\\d+)");
        Matcher m3 = p3.matcher("ab%12-cd%34");
        /** 在正则表达式中，一个(...)定义了一个组
         *  (\w+)%(\d+)是第0组 group(0)， (\\w+)是第一组 group(1)， (\\d+)是第二组 group(2)。
         *  groupCount()返回此匹配器模式中的捕获分组数*/
        System.out.println("groupCount()3 -- " + m3.groupCount());    // 2

        /** regionStart()报告此匹配器区域的开始索引下标位置*/
        System.out.println("regionStart()3 -- " + m3.regionStart());  // 0

        /** regionEnd()报告此匹配器区域的结束索引下标位置*/
        System.out.println("regionEnd()3 -- " + m3.regionEnd());      // 11

        /** find()用于在目标字符串中查找与正则表达式模式匹配的子字符串，匹配上返回true，否则为false。再次调用会从该处匹配下一个。
         *  find(int start)重置此匹配器，然后尝试查找匹配该模式，从指定的位置开始查找下一个匹配的子串。
         *  如果上述重载方法匹配成功，则可以通过 start、end 和 group 方法获取更多信息。否则无法使用start、end 和 group 方法*/
        if (m3.find(1)) {  // b%12
            /** 在正则表达式中，一个(...)定义了一个组
             *  (\w+)%(\d+)是第0组 group(0)， (\\w+)是第一组 group(1)， (\\d+)是第二组 group(2)。
             *  group()返回当前查找而获得的与组匹配的所有子串内容
             *  group(int group)返回指定的分组子串内容 */
            System.out.println("group()3 -- " + m3.group(2));      // 12

            /** start()返回当前匹配子串的第一个字符在目标字符串中的索引下标位置*/
            System.out.println("start()3 -- " + m3.start());      // 0。开始索引的下标值

            /** 在正则表达式中，一个(...)定义了一个组
             *  (\w+)%(\d+)是第0组 group(0)， (\\w+)是第一组 group(1)， (\\d+)是第二组 group(2)。
             *  start(int group)返回当前匹配的指定组中的子串的第一个字符在目标字符串中的索引下标位置*/
            System.out.println("start(arg)3 -- " + m3.start(2));  // 3。匹配的第二个分组开始索引的下标值

            /** end()返回当前匹配的子串的最后一个字符的下一个位置在目标字符串中的索引下标位置*/
            System.out.println("end()3   -- " + m3.end());        // 5。最后索引下标值
        }
        while (m3.find()) {
            System.out.println("start()3.1 -- " + m3.start());    // 6。开始索引的下标值
            System.out.println("end()3.1   -- " + m3.end());      // 11。最后索引下标值
            System.out.println("group()3.1 -- " + m3.group());    // cd%34
        }
        // System.out.println("start()3 -- " + m3.start()); // 这里匹配位置已经挪移到最后，匹配不到任何字符，因此这里会报错



        Pattern p4 = Pattern.compile("(\\w+)%(\\d+)");
        Matcher m4 = p4.matcher("ab%12-cd%34");
        /** reset(CharSequence input) 使用新的输入序列重置此匹配器。*/
        m4.reset("ef%56-gh%78");
        while (m4.find()) {
            System.out.println("reset - group():" + m4.group()); // group():ef%56   group():gh%78
        }

        Pattern p5 = Pattern.compile("[A-Z]");
        Matcher m5 = p5.matcher("tabAdmin, tabProductStop");
        /** Matcher appendReplacement(StringBuffer sb, String replacement)将当前匹配子串替换为指定字符串，
          * 并将从上次匹配结束后到本次匹配结束后之间的字符①串添加到一个StringBuffer对象中，最后返回其字符串表示形式。*/
        StringBuffer sb = new StringBuffer();
        while (m5.find()){
            // 注意⚠️：在使用appendReplacement()方法前要使用find()方法查找目标字符串在正则模式下所匹配的子字符串是否存在。
            //        只有存在我们使用appendReplacement()方法才能进行去内容替换。
            m5.appendReplacement(sb, "_"+m5.group(0).toLowerCase());
            System.out.println("appendReplacement -- " + sb);
        }

        /** StringBuffer appendTail(StringBuffer sb)将最后一次匹配工作后剩余的字符串添加到一个StringBuffer对象里*/
        System.out.println("appendTail() -- " + m5.appendTail(sb));

        /** replaceAll(String replacement)替换所有符合正则的数据*/
        System.out.println("replaceAll() -- " + m5.replaceAll("_JAVA_"));

        /** replaceFirst(String replacement)替换第一个符合正则的数据*/
        System.out.println("replaceFirst() -- " + m5.replaceFirst("_JAVA_"));

        /** Matcher usePattern(Pattern newPattern)更改匹配器的匹配模式*/
        Matcher m6 = m5.usePattern(Pattern.compile("tab"));
        System.out.println("usePattern() -- " + m6.replaceAll(""));
    }
}
