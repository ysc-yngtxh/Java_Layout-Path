package Q17_23种设计模式.装饰者模式;

public class StarbuzzCoffee {
    public static void main(String[] args) {
        // 1. 点一杯浓缩咖啡，不加调料
        Beverage beverage1 = new Espresso();
        System.out.println(beverage1.getDescription() + " $" + beverage1.cost());
        
        // 2. 点一杯深焙咖啡，加双份摩卡和奶泡
        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
        
        // 3. 点一杯低咖啡因咖啡，加牛奶、摩卡和豆浆
        Beverage beverage3 = new Decaf();
        beverage3 = new Milk(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Soy(beverage3);
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
    }
}
