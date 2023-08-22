package E5String类.C3String方法;

public class substring方法12 {
    public static void main(String[] args) {

        // (掌握) String substring(int beginIndex)参数是起始下标
        // 截取字符串
        System.out.println("http://www.baidu.com".substring(7));        // www.baidu.com


        // (掌握) String substring(int beginIndex,int endIndex)
        // beginIndex起始位置（包括）
        // endIndex结束位置（不包括）
        System.out.println("http://www.baidu,com".substring(7,10));     // www
    }
}
