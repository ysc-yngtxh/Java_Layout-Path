package Q17_23种设计模式.原型模式;

public class PrototypePatternDemo {
    public static void main(String[] args) {
        // 1. 直接克隆方式
        try {
            Circle originalCircle = new Circle("绿色", 15);
            Circle clonedCircle = (Circle) originalCircle.clone();
            
            System.out.println("原始圆形:");
            originalCircle.draw();
            
            System.out.println("克隆圆形:");
            clonedCircle.draw();
            
            // 修改克隆对象不影响原对象
            clonedCircle.setColor("黄色");
            clonedCircle.setRadius(20);
            
            System.out.println("\n修改后的克隆圆形:");
            clonedCircle.draw();
            
            System.out.println("原始圆形保持不变:");
            originalCircle.draw();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
        // 2. 使用原型管理器方式
        System.out.println("\n=== 使用原型管理器 ===");
        ShapeCache.loadCache();
        
        try {
            Shape clonedShape1 = ShapeCache.getShape("红色圆形");
            System.out.println("形状: " + clonedShape1.getClass().getSimpleName());
            clonedShape1.draw();
            
            Shape clonedShape2 = ShapeCache.getShape("蓝色矩形");
            System.out.println("形状: " + clonedShape2.getClass().getSimpleName());
            clonedShape2.draw();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
