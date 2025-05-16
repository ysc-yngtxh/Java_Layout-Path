# 充血模型编码实践

本章我们将对通过《重构》一书中的案例，回顾贫血模型与充血模型，为后面的编码做知识储备，在DDD实践中，我们将大量用到充血模型的编码方式，如果你对贫血模型与充血模型已经了解了，可以跳过本章。

## 什么是贫血模型与充血模型？

回答这个问题，我们从《重构》一书中的一个影片租赁的案例，以及一个订单的开发场景，分别使用贫血模型与充血模型来实现，读者可以从中感受其差别理解它们的不同。

### 影片租赁场景

需要说明的是下面的代码基本与《重构》一书中的代码相同，但笔者省略了重构的各个代码优化环节，只展示了贫血模型与充血模型代码的不同。书中源代码，笔者也手写了一份实现，感兴趣可以通过以下链接点击查看。

https://gitee.com/izhengyin/some-code/tree/master/refactor/src/main/java/com/izhengyin/somecode/refactor/movierental/version0

#### 需求描述

> 根据顾客租聘的影片打印出顾客消费金额与积分

* 积分规则
	* 默认租聘积一分，如果是新片且租聘大于1天，在加一分
* 费用规则
	* 普通片 ，租聘起始价2元，如果租聘时间大于2天，每天增加1.5元
	* 新片 ，租聘价格等于租聘的天数
	* 儿童片 ，租聘起始价1.5元，如果租聘时间大于3天，每天增加1.5元

#### 基于贫血模型的实现

下面是影片 Movie 、租赁 Rental
两个贫血模型类，下面这样的代码在我们日常开发中是比较常见，简单来说它们就是只包含数据，不包含业务逻辑的类，从面向对象角度来说也违背了面向对象里面封装的设计原则。

> 面向对象封装：隐藏信息、保护数据，只暴露少量接口，提高代码的可维护性与易用性;

```
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String title;
    private Integer priceCode;

    public Movie(String title, Integer priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(Integer priceCode) {
        this.priceCode = priceCode;
    }
}
```

```
public class Rental {
    /**
     * 租的电影
     */
    private Movie movie;
    /**
     * 已租天数
     */
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }
}

```

接着是我们的Customer类,Customer类的问题是里面包含了原本应该是Movie与Reatal的业务逻辑，给人感觉很重，Customer可以类别我们日常开发的XxxService，想想我们是不是在Service层中不断的堆砌业务逻辑。

```
public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();
    public Customer(String name) {
        this.name = name;
    }
    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }
    public String getName() {
        return name;
    }

    /**
     * 根据顾客租聘的影片打印出顾客消费金额与积分
     * @return
     */
    public String statement(){
        double totalAmount = 0;
        String result = getName()+"的租聘记录 \n";
        for (Rental each : rentals){
            double thisAmount = getAmount(each);
            result += "\t" + each.getMovie().getTitle() + " \t" + thisAmount +" \n";
            totalAmount += thisAmount;
        }
        int frequentRenterPoints = getFrequentRenterPoints(rentals);
        result += "租聘总价 ： "+ totalAmount + "\n";
        result += "获得积分 ： "+ frequentRenterPoints;
        return result;
    }

    /**
     * 获取积分总额
     * @param rentals
     * @return
     */
    private int getFrequentRenterPoints(List<Rental> rentals){
        return rentals.stream()
                .mapToInt(rental -> {
                    //默认租聘积一分，如果是 Movie.NEW_RELEASE 且租聘大于1天，在加一分
                    int point = 1;
                    if(rental.getMovie().getPriceCode().equals(Movie.NEW_RELEASE) && rental.getDaysRented() > 1){
                        point ++;
                    }
                    return point;
                })
                .sum();
    }

    /**
     * 获取单个影片租聘的价格
     *  1. 普通片 ，租聘起始价2元，如果租聘时间大于2天，每天增加1.5元
     *  2. 新片 ，租聘价格等于租聘的天数
     *  3. 儿童片 ，租聘起始价1.5元，如果租聘时间大于3天，每天增加1.5元
     * @param rental
     * @return
     */
    private double getAmount(Rental rental){
        double thisAmount = 0;
        switch (rental.getMovie().getPriceCode()){
            case Movie.REGULAR:
                thisAmount += 2;
                if(rental.getDaysRented() > 2){
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented();
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if(rental.getDaysRented() > 3){
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                }
                break;
            default:
                //nothings todo
                break;
        }
        return thisAmount;
    }

}
```

最后我们运行主程序类，进行输出，得到下面结果,记住这个结果，我们会通过重新模型重构后，保证同样的输出。

```
张三的租聘记录 
	儿童片 	1.5 
	普通片 	3.5 
	新片 	5.0 
租聘总价 ： 10.0
获得积分 ： 4
```

> 主程序类

```
public class Main {
    public static void main(String[] args) {
        Movie movie1 = new Movie("儿童片", Movie.CHILDRENS);
        Movie movie2 = new Movie("普通片", Movie.REGULAR);
        Movie movie3 = new Movie("新片", Movie.NEW_RELEASE);
        Customer customer = new Customer("张三");
        customer.addRental(new Rental(movie1,1));
        customer.addRental(new Rental(movie2,3));
        customer.addRental(new Rental(movie3,5));
        System.out.println(customer.statement())
    }
}
```

#### 基于充血模型的实现

我们的类没有变化，只是类里面的实现发生了变化，接下来就逐一看看类的实现都发生了那些改变。

> 重构后影片 Movie 类

1. 删除了不必要setXXX方法
2. 增加了 getCharge 获取费用电影费用的方法，将原本 Customer 的逻辑交由Movie类实现。

注：Movie类还有优化空间，但不是本文的重点，读者感兴趣可以查看此链接
https://gitee.com/izhengyin/some-code/tree/master/refactor/src/main/java/com/izhengyin/somecode/refactor/movierental/version2

```
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String title;
    private Integer priceCode;

    public Movie(String title, Integer priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPriceCode() {
        return priceCode;
    }

    /**
     *获取单个影片租聘的价格
     *  1. 普通片 ，租聘起始价2元，如果租聘时间大于2天，每天增加1.5元
     *  2. 新片 ，租聘价格等于租聘的天数
     *  3. 儿童片 ，租聘起始价1.5元，如果租聘时间大于3天，每天增加1.5元
     * @param daysRented
     * @return
     */
    public double getCharge(int daysRented){
        double thisAmount = 0;
        switch (this.priceCode){
            case REGULAR:
                thisAmount += 2;
                if(daysRented > 2){
                    thisAmount += (daysRented - 2) * 1.5;
                }
                break;
            case NEW_RELEASE:
                thisAmount += daysRented;
                break;
            case CHILDRENS:
                thisAmount += 1.5;
                if(daysRented > 3){
                    thisAmount += (daysRented - 3) * 1.5;
                }
                break;
            default:
                //nothings todo
                break;
        }
        return thisAmount;
    }
}
```

> 重构后租赁 Rental 类

1. 移除了部分不必要的 get / set 方法
2. 增加一个 getPoint 方法，计算租赁积分,将原本 Customer
   的逻辑交由获取积分的业务交由getPoint实现，但总积分的计算还是在Customer。
3. 增加一个 getCharge 方法，具体调用Movie::getCharge传入租赁天数得到租赁的费用，因为在这个需求中主体是租赁

```
public class Rental {

    /**
     * 租的电影
     */
    private Movie movie;

    /**
     * 已租天数
     */
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    /**
     * 默认租聘积一分，如果是新片且租聘大于1天，在加一分
     * @return
     */
    public int getPoint(){
        int point = 1;
        if(this.movie.getPriceCode().equals(Movie.NEW_RELEASE) && this.daysRented > 1){
            point ++;
        }
        return point;
    }
    /**
     * 获取费用
     * @return
     */
    public double getCharge(){
        return this.movie.getCharge(this.daysRented);
    }
}
```

> 瘦身后的Customer

```
public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();
    public Customer(String name) {
        this.name = name;
    }
    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }
    public String getName() {
        return name;
    }

    /**
     * 根据顾客租聘的影片打印出顾客消费金额与积分
     * @return
     */
    public String statement(){
        double totalAmount = 0;
        String result = getName()+"的租聘记录 \n";
        for (Rental each : rentals){
            double thisAmount = each.getCharge();
            result += "\t" + each.getMovie().getTitle() + " \t" + thisAmount +" \n";
            totalAmount += thisAmount;
        }
        int frequentRenterPoints = getFrequentRenterPoints(rentals);
        result += "租聘总价 ： "+ totalAmount + "\n";
        result += "获得积分 ： "+ frequentRenterPoints;
        return result;
    }

    /**
     * 获取积分总额
     * @param rentals
     * @return
     */
    private int getFrequentRenterPoints(List<Rental> rentals){
        return rentals.stream()
                .mapToInt(Rental::getPoint)
                .sum();
    }
}
```

最后我们运行主程序类，得到同样的输出。

源码地址： https://gitee.com/izhengyin/ddd-message/tree/master/src/main/java/democode/movierental

### 订单的场景

#### 需求描述

1. 创建订单
2. 设置订单优惠

#### 订单场景贫血模型实现

> Order 类 , 只包含了属性的Getter，Setter方法

```
@Data
public class Order {
    private long orderId;
    private int buyerId;
    private int sellerId;
    private BigDecimal amount;
    private BigDecimal shippingFee;
    private BigDecimal discountAmount;
    private BigDecimal payAmount;
    private String address;
}
```

> OrderService ，根据订单创建中的业务逻辑，组装order数据对象，最后进行持久化

```
    /**
     * 创建订单
     * @param buyerId
     * @param sellerId
     * @param orderItems
     */
    public void createOrder(int buyerId,int sellerId,List<OrderItem> orderItems){
        //新建一个Order数据对象
        Order orderDDD = new Order();
        orderDDD.setOrderId(1L);
        //算订单总金额
        BigDecimal amount = orderItems.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        orderDDD.setAmount(amount);
        //运费
        orderDDD.setShippingFee(BigDecimal.TEN);
        //优惠金额
        orderDDD.setDiscountAmount(BigDecimal.ZERO);
        //支付总额 = 订单总额 + 运费 - 优惠金额
        BigDecimal payAmount = orderDDD.getAmount().add(orderDDD.getShippingFee()).subtract(orderDDD.getDiscountAmount());
        orderDDD.setPayAmount(payAmount);
        //设置买卖家
        orderDDD.setBuyerId(buyerId);
        orderDDD.setSellerId(sellerId);
        //设置收获地址
        orderDDD.setAddress(JSON.toJSONString(new Address()));
        //写库
        orderDDDDao.insert(orderDDD);
        orderItems.forEach(orderItemDDDDao::insert);
    }
```

在此种方式下，核心业务逻辑散落在OrderService中，比如获取订单总额与订单可支付金额是非常重要的业务逻辑，同时对象数据逻辑一同混编，在此种模式下，代码不能够直接反映业务，也违背了面向对象的SRP原则。

> 设置优惠

``` 
 /**
     * 设置优惠
     * @param orderId
     * @param discountAmount
     */
    public void setDiscount(long orderId, BigDecimal discountAmount){
        Order orderDDD = orderDDDDao.find(orderId);
        orderDDD.setDiscountAmount(discountAmount);
        //从新计算支付金额
        BigDecimal payAmount = orderDDD.getAmount().add(orderDDD.getShippingFee()).subtract(discountAmount);
        orderDDD.setPayAmount(payAmount);
        //orderDDDDao => 通过主键更新订单信息
        orderDDDDao.updateByPrimaryKey(orderDDD);
    }
```

贫血模型在设置折扣时因为需要考虑到折扣引发的支付总额的变化，因此还需要在从新的有意识的计算支付总额，因为面向数据开发需要时刻考虑数据的联动关系，在这种模式下忘记了修改某项关联数据的情况可能是时有发生的。

#### 订单场景充血模型实现

> Order 类，包含了业务关键属于以及行为，同时具有良好的封装性

``` 
/**
 * @author zhengyin
 * Created on 2021/10/18
 */
@Getter
public class Order {
    private long orderId;
    private int buyerId;
    private int sellerId;
    private BigDecimal shippingFee;
    private BigDecimal discountAmount;
    private Address address;
    private Set<OrderItem> orderItems;

    //空构造，只是为了方便演示
    public Order(){}

    public Order(long orderId,int buyerId ,int sellerId,Address address, Set<OrderItem> orderItems){
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.address = address;
        this.orderItems = orderItems;
    }

    /**
     * 更新收货地址
     * @param address
     */
    public void updateAddress(Address address){
        this.address = address;
    }
    /**
     * 支付总额等于订单总额 + 运费 - 优惠金额
     * @return
     */
    public BigDecimal getPayAmount(){
        BigDecimal amount = getAmount();
        BigDecimal payAmount = amount.add(shippingFee);
        if(Objects.nonNull(this.discountAmount)){
            payAmount = payAmount.subtract(discountAmount);
        }
        return payAmount;
    }

    /**
     * 订单总价 = 订单商品的价格之和
     *    amount 可否设置为一个实体属性？
     */
    public BigDecimal getAmount(){
        return orderItems.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }


    /**
     * 运费不能为负
     * @param shippingFee
     */
    public void setShippingFee(BigDecimal shippingFee){
        Preconditions.checkArgument(shippingFee.compareTo(BigDecimal.ZERO) >= 0, "运费不能为负");
        this.shippingFee = shippingFee;
    }

    /**
     * 设置优惠
     * @param discountAmount
     */
    public void setDiscount(BigDecimal discountAmount){
        Preconditions.checkArgument(discountAmount.compareTo(BigDecimal.ZERO) >= 0, "折扣金额不能为负");
        this.discountAmount = discountAmount;
    }

    /**
     * 原则上，返回给外部的引用，都应该防止间接被修改
     * @return
     */
    public Set<OrderItem> getOrderItems() {
        return Collections.unmodifiableSet(orderItems);
    }
}

```

> OrderService , 仅仅负责流程的调度

```
    /**
     * 创建订单
     * @param buyerId
     * @param sellerId
     * @param orderItems
     */
    public void createOrder(int buyerId, int sellerId, Set<OrderItem> orderItems){
        Order orderDDD = new Order(1L,buyerId,sellerId,new Address(),orderItems);
        //运费不随订单其它信息一同构造，因为运费可能在后期会进行修改，因此提供一个设置运费的方法
        orderDDD.setShippingFee(BigDecimal.TEN);
        orderRepository.save(orderDDD);
    }
```

在此种模式下，Order类完成了业务逻辑的封装，OrderService仅负责业务逻辑与存储之间的流程编排，并不参与任何的业务逻辑，各模块间职责更明确。


> 设置优惠

```  

    /**
     * 设置优惠
     * @param orderId
     * @param discountAmount
     */
     public void setDiscount(long orderId, BigDecimal discountAmount){
        Order orderDDD = orderRepository.find(orderId);
        orderDDD.setDiscount(discountAmount);
        orderRepository.save(orderDDD);
    }
```

在充血模型的模式下，只需设置具体的优惠金额，因为在Order类中已经封装了相关的计算逻辑，比如获取支付总额时，是实时通过优惠金额来计算的。

```
   /**
     * 支付总额等于订单总额 + 运费 - 优惠金额
     * @return
     */
    public BigDecimal getPayAmount(){
        BigDecimal amount = getAmount();
        BigDecimal payAmount = amount.add(shippingFee);
        if(Objects.nonNull(this.discountAmount)){
            payAmount = payAmount.subtract(discountAmount);
        }
        return payAmount;
    }
```

写到这里，可能读者会有疑问，文章都在讲充血模型的业务，那数据怎么进行持久化？

数据持久化时我们通过封装的 OrderRepository
来进行持久化操作，根据存储方式的不同提供不同的实现，以数据库举例，那么我们需要将Order转换为PO对象，也就是持久化对象，这时的持久化对象就是面向数据表的贫血模型对象。

比如下面的伪代码

```  
public class OrderRepository {
    private final OrderDao orderDDDDao;
    private final OrderItemDao orderItemDDDDao;


    public OrderRepository(OrderDao orderDDDDao, OrderItemDao orderItemDDDDao) {
        this.orderDDDDao = orderDDDDao;
        this.orderItemDDDDao = orderItemDDDDao;
    }

    public void save(Order orderDDD){
        // 在此处通过Order实体,创建数据对象 new OrderPO() ; new OrderItemPO();
        // orderDDDDao => 存储订单数据
        // orderItemDDDDao => 存储订单商品数据

    }

    public Order find(long orderId){
        //找到数据对象,OrderPO
        //找到数据对象,OrderItemPO
        //组合返回，order实体
        return new Order();
    }
}
```

源码地址： https://gitee.com/izhengyin/ddd-message/tree/master/src/main/java/democode/orderDDD

通过上面两种实现方式的对比，相信读者对两种模型已经有了明确的认识了，在贫血模型中，数据和业务逻辑是割裂的，而在充血模型中数据和业务是内聚的。
