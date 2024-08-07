
## 问题
    为什么需要分布式全局唯一ID以及分布式ID的业务需求?

    在复杂分布式系统中，往往需要对大量的数据和消息进行唯一标识，如在美团点评的金融、支付、餐饮、酒店
    猫眼电影等产品的系统中数据逐渐增长，对数据库分库分表后需要有一个唯一ID来标识一条数据或信息；
    特别Ian的订单、骑手、优惠券都需要有唯一ID做标识。此时一个能够生成全局唯一ID的系统是非常必要的

## UUID

    UUID.randomUUID(), UUID的标准型包含32个16进制数字，以连字号分为五段，形式为 8-4-4-4-12的36个字符，
    性能非常高，本地生成，没有网络消耗。

    存在问题
    1、入数据库性能差，因为UUID是无序的，无法预测他的生成顺序，不能生成递增有序的数字
    2、分布式id一般都会作为主键，但是按照mysql官方推荐主键尽量越短越好，UUID每一个都很长，所以不是很推荐。
    3、因为UUID是无序的，会造成每一次UUID数据的插入都会对主键的B+树进行很大的修改，这一点很不好，
      插入完全无序，不但会导致一些中间节点产生分裂，也会白白创造出很多不饱和的节点，这样大大降低了数据库插入的性能。

## 数据库自增主键

    在分布式情况下，并且并发量不多的情况，可以使用这种方案来解决，获得一个全局的唯一ID

    集群分布式集群
    那数据库自增ID机制适合做分布式ID吗？答案是不太适合
    系统水平扩展比较困难，比如定义好步长和机器台数之后，如果要添加机器该怎么办，假设现在有一台机器发号是：1,2,3,4,5,（步长是1），
    这个时候需要扩容机器一台，可以这样做：把第二台机器的初始值设置得比第一台超过很多，貌似还好，但是假设线上如果有100台机器，
    这个时候扩容要怎么做，简直是噩梦，所以系统水平扩展方案复杂难以实现。

    数据库压力还是很大，每次获取ID都得读写一次数据库，非常影响性能，不符合分布式ID里面的延迟低和高QPS的规则
    （在高并发下，如果都去数据库里面获取ID，那是非常影响性能的）

## 基于Redis生成全局ID策略

    单机版
    因为Redis是单线程，天生保证原子性，可以使用原子操作INCR和INCRBY来实现
    INCRBY：设置增长步长

    集群分布式
    注意：在Redis集群情况下，同样和MySQL一样需要设置不同的增长步长，同时key一定要设置有效期，可以使用Redis集群来获取更高的吞吐量。
    假设一个集群中有5台Redis，可以初始化每台Redis的值分别是 1,2,3,4,5 ， 然后设置步长都是5
    各个Redis生成的ID为：
    A：1 6 11 16 21
    B：2 7 12 17 22
    C：3 8 13 18 23
    D：4 9 14 19 24
    E：5 10 15 20 25
    但是存在的问题是，就是Redis集群的维护和保养比较麻烦，配置麻烦。因为要设置单点故障，哨兵值守

    但是主要是的问题就是，为了一个ID，却需要引入整个Redis集群，有种杀鸡焉用牛刀的感觉

## 雪花算法

    Twitter的分布式自增ID算法，经测试SnowFlake每秒可以产生26万个自增可排序的ID
      1、Twitter的SnowFlake生成的ID按时间趋势递增
      2、SnowFlake算法生成ID的结果是一个64Bit大小的整数，为一个Long型（转换成字符串后长度最多19）
      3、整个分布式系统内不会产生重复ID，因为有datacenterId 和 workerId来做区分

    分布式系统中，有一些需要全局唯一ID的场景，生成ID的基本要求
      1、在分布式环境下，必须全局唯一性
      2、一般都需要单调递增，因为一般唯一ID都会存在数据库，而InnoDB的特性就是将内容存储在主键索引上的叶子节点，而且是从左往右递增的，
         所有考虑到数据库性能，一般生成ID也最好是单调递增的。
      3、可能还会需要无规则，因为如果使用唯一ID作为订单号这种，为了不让别人知道一天的订单量多少，就需要这种规则

    优点
      1、毫秒数在高维，自增序列在低位，整个ID都是趋势递增的
      2、不依赖数据库等第三方系统，以服务的方式部署，稳定性更高，生成ID的性能也是非常高的
      3、可以根据自身业务特性分配bit位，非常灵活
    缺点
      1、依赖机器时钟，如果机器时钟回拨，会导致重复ID生成
      2、在单机上是递增的，但由于涉及到分布式环境，每台机器上的时钟不可能完全同步，有时候会出现不是全局递增的情况，此缺点可以认为无所谓，
         一般分布式ID只要求趋势递增，并不会严格要求递增，90%的需求只要求趋势递增。
    其它补充
      为了解决时钟回拨问题，导致ID重复，也有专门解决的方案
        1、百度开源的分布式唯一ID生成器 UidGenerator
        2、Leaf - 美团点评分布式ID生成系统