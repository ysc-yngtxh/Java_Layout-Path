package M13_反射.反射Ⅰ_获取Class实例化对象;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class 反射4_以流的形式直接返回 {
    public static void main(String[] args) throws URISyntaxException {
        // 获取一个文件的绝对路径
        String path = Thread.currentThread()
                .getContextClassLoader()
                .getResource("classInfo.properties")
                .toURI()
                .getPath();
        FileInputStream reader = null;
        // FileReader reader = null;
        try{
            reader = new FileInputStream(path);
            // reader = new FileReader(path);
            Properties pro = new Properties();
            pro.load(reader);
            String value1 = pro.getProperty("className1");
            System.out.println("使用字节流当然会乱码啊！改用字符流即可！ -- " + value1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("=========================================================================================");

        // 通过流来返回，其实就是将获取路径和创建流对象两行代码合并
        InputStream in = null;
        try{
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream("classInfo.properties");
            // 通过InputStreamReader将字节流转换为字符流
            InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
            Properties pro1 = new Properties();
            pro1.load(inputStreamReader);
            String value2 = pro1.getProperty("className3");
            System.out.println(value2);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}