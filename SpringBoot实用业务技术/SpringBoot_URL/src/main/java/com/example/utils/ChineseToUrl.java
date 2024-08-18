package com.example.utils;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-19 00:17
 * @apiNote TODO 汉字转换成URL码
 */
public class ChineseToUrl {

    /**
     * @param s 需要转换的中文URL
     * @return 编译成功，返回URL码
     */
    public static String ChineseToUrls(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    // 指定需要的编码类型
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%").append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}