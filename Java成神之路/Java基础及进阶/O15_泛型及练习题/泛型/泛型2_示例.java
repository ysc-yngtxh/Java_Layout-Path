package O15_泛型及练习题.泛型;

public class 泛型2_示例 {
    public static void main(String[] args) {
        Double[] num = {1.11, 2.22, 3.33, 4.44, 5.55, 6.66};
        String[] str = {"Hello", "World", "你好", "世界"};

        Generic01 generic01 = new Generic01();
        generic01.toGeneric01(num);
        generic01.toGeneric01(str);


        Generic02<Double> generic02 = new Generic02<>();
        generic02.toGeneric02(num);

        Generic03 generic03 = new Generic03();
        generic03.toGeneric03(str);

        System.out.println(generic01 + "\n" + generic02 + "\n" + generic03);
    }


    static class Generic01 {
        // <T> T 表示这是一个泛型方法，且返回T类型数据
        public <T> T toGeneric01(T[] arr) {
            return arr[arr.length - 1];
        }
    }

    static class Generic02<T> {
        // 这里的 T 表示返回的是 T 类型的数据
        public T toGeneric02(T[] arr) {
            return arr[arr.length - 1];
        }
    }

    static class Generic03 {
        // <T> 表示这是一个泛型方法
        public <T> void toGeneric03(T[] arr) {
            T t = arr[arr.length - 1];
        }
    }
}