package O15_泛型及练习题;

public class A一百以内的素数 {
    public static void main(String[] args) {
        for (int i = 2; i < 100; i++) {
            boolean sushu = true;
            for (int j = 2; j < i; j++) {
                if(i % j == 0){
                    sushu = false;
                }
            }

            if(sushu){
                System.out.println("i="+i);
            }
        }
    }
}

