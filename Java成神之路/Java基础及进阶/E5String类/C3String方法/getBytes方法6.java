package E5String类.C3String方法;

public class getBytes方法6 {
    public static void main(String[] args) {

        // (掌握) byte[] getBytes();
        // 将字符串对象转换成字节数组
        byte[] bytes ="ABCDEFG".getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);            // 65 66 67 68 69 70 71 72
        }
    }
}
