package Q17_23种设计模式.原型模式;

public interface Shape extends Cloneable {
    void draw();
    Shape clone() throws CloneNotSupportedException;
}


// 具体原型 - 圆形
class Circle implements Shape {
    private String color;
    private int radius;

    public Circle(String color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("绘制圆形 - 颜色: " + color + ", 半径: " + radius);
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        return (Shape) super.clone();
    }

    // 设置方法
    public void setColor(String color) {
        this.color = color;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
// 具体原型 - 矩形
class Rectangle implements Shape {
    private String color;
    private int width;
    private int height;

    public Rectangle(String color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("绘制矩形 - 颜色: " + color + ", 宽度: " + width + ", 高度: " + height);
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        return (Shape) super.clone();
    }

    // 设置方法
    public void setColor(String color) {
        this.color = color;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
