
/**
 * 指定到目录下     cd /目录名
 * 建包命令        mkdir 包名
 * 修改目录包权限   chmod 777 包名
 * 拷贝包名        cp 原包名 新包名
 * 查看目录下的文件 ls
 * 修改配置文件    vim redis.conf
 * 搜索命令行      按Esc-->:/搜索单词
 * 退出配置文件    按Esc-->:wq
 *
 * 一、在Linux系统里安装redis
 *     1、将redis压缩包通过Xftp传到Linux系统文件里，比如：/opt/mysoftwares包下
 *        在Linux系统里执行终端 输入：cd /opt/mysoftwares  目的是在mysoftwares包下执行命令
 *     2、然后在mysoftwares包下执行命令 输入：tar -zxvf redei-5.0.9.tar.gz
 *     3、可以发现redis压缩包已经解压在 /opt/mysoftwares包下
 *     4、然后我们还需要gcc jar包，可以在终端命令行里下载 输入：yum -y install gcc
 *     5、可以顺带的查看一下gcc版本号 输入：gcc -v
 *     6、然后执行命令 ：cd redis-5.0.9  目的是进入redis解压包目录下
 *     7、终端输入 ：make 命令进行编译
 *     8、如果出现提示 输入：make install
 *
 * 二、启动redis服务：
 *     1、前台启动：在任何目录下执行 redis-server
 *     2、后台启动：在任何目录下执行 redis-server &
 *     3、启动redis服务时，指定配置文件：redis-server redis.conf &
 *
 * 三、关闭redis服务：
 *     1、通过kill命令：（这种关闭命令容易丢失数据）
 *        ps -ef | grep redis 查看pid(第一排root后的第一个数字)
 *        kill -9 'pid'
 *     2、通过redis-cli命令关闭：
 *        redis-cli shutdown
 *
 * 四、redis客户端：用来连接redis服务，向redis服务端发送命令，并且显示redis服务处理结果。
 *     1、简单的来说。例如：navicat就是面向MySQL的第三方可视化的客户端
 *                      MySQL自带的客户端命令：mysql -uroot -p
 *     2、redis-cli:是redis自带客户端，使用命令redis-cli就可以启动redis的客户端程序。
 *        redis-cli:默认连接127.0.0.1(本机)的6379端口上的redis服务
 *
 *     3、当主机上有多个redis服务时，需要指定redis的端口号，用来连接需要的redis
 *         redis-cli -p 端口号：连接127.0.0.1(本机)的指定端口上的redis服务
 *         redis-cli -h ip地址 -p 端口：连接指定ip主机上的指定端口的redis服务
 *
 * 五、退出客户端：在客户端执行命令：exit或者quit
 *
 * 六、redis的基本知识：
 *        1、测试redis服务的性能：
 *           redis-benchmark
 *        2、查看redis服务是否正常运行
 *           ping  [主机地址(域名不包含端口)] 如果正常---pong
 *        3、查看redis服务器的统计信息：
 *           info  查看redis服务的所有统计信息
 *           info  [信息段]查看redis服务器的指定的统计信息，如：Replication[集群]
 *        4、redis的数据库实例：作用类似于mysql的数据库实例，redis中的数据库实例只能由redis服务来创建和维护，开发人员不能修改和自行创建数据库实例；
 *                            默认情况下，redis会自动创建16个数据库实例，并且给这些数据库实例进行编号.从0开始，一直到15，使用时通过编号来使用数据库；
 *                            可以通过配置文件，指定redis自动创建的数据库个数；redis的每一个数据实例本身占用的存储空间是很少的，所以也不造成存储空间的太多浪费
 *               默认情况下，redis客户端连接的是编号是0的数据库实例；可以使用select index切换数据库实例
 *        5、redis数据库是采用键值对的方式存储数据的，比如我要存一条数据 输入：set k1 v1   查询数据 输入：get k1
 *        6、查看当前数据库实例中所有key的数量：dbsize
 *        7、查看当前数据库实例中所有的key:keys *
 *        8、清空当前数据库实例：flushdb
 *        9、清空所有的数据库实例：flushall
 *        10、查看redis中所有的配置信息：config get *
 *            查看redis中的指定的配置信息：config get parameter
 *
 * 七、redis的五种数据结构
 *     程序是用来处理数据的，redis是用来存储数据的；程序处理完的数据要存储到redis中，不同特点的数据要存储在redis中不同类型的数据结构中
 *         1、string 单key:单value
 *         2、list   单key:多有序value   和Java中的LinkedList比较类似。增删效率高。
 *         3、set    单key:多无序value   相当于java中的HashSet 用在一些去重的场景：例如每个用户只能参与一次活动、一个用户只能中奖一次等等
 *         4、hash   单key:对象(属性：值) 相当于hashmap
 *         5、zset   单key:多有序value   一方面它是一个 set，保证了内部 value 的唯一性，另一方面它可以给每个 value 赋予一个 score，代表这个 value 的排序权重。
 *
 * 八、redis中的操作命令：
 *     1、redis中有关key的操作命令
 *          a、查看数据库中的key: keys 命令句
 *                              *：匹配0个或者多个字符
 *                              ?：匹配1个字符
 *                              []：匹配[]里边的任意一个字符
 *              keys *：查看数据库中所有的key
 *              keys k*：查看数据库中所有以k开头的key
 *              keys h*o：查看数据库中所有以h开头、以o结尾的key
 *              keys h?o：查看数据库中所有以h开头、以o结尾、并且中间只有一个字符的key
 *              keys h[abc]llo：查看数据库中所有以h开头、以llo结尾、并且h后边只能取abc中的一个字符的key
 *          b、判断key在数据库中是否存在：
 *                exists key 如果存在，则返回1；如果不存在，则返回0
 *                exists key [key key...] 返回值是存在的key的数量
 *          c、移动指定key到指定的数据库实例：move key index
 *                例如：move k1 1
 *          d、查看指定key的剩余生存时间：ttl key
 *                                    如果key没有设置生存时间，返回-1
 *                                    如果key不存在，返回-2
 *          e、设置key的最大生存时间：expire key seconds
 *                                例如：；expire k1 20  (时间单位是秒)
 *          f、查看指定key的数据类型：type key
 *          g、重命名key：rename key1 key2
 *          h、删除指定的key：del key [key key...]
 *                       返回值是实际删除的key的数量
 *     2、redis中有关string类型数据的操作命令(单key:单value)
 *          a、将string类型的数据设置到redis中：set 键 值
 *             如果key已经存在，则后来的value会把以前的value覆盖掉
 *          b、从redis中获取string类型的数据：get 键
 *          c、追加字符串：append key value
 *                       返回追加之后的字符串长度
 *                       如果key不存在，则新创建一个key，并且把value值设置为value
 *          d、获取字符串数据的长度:string key
 *          e、将字符串数值进行加1运算：incr key
 *                       返回加1运算之后的数据
 *                       如果key不存在，首先设置一个key，值初始化为0，然后进行incr运算
 *                       要求key所表示value必须是数值，否则，报错
 *          f、将字符串数值进行减1运算：decr key
 *                       返回减1运算之后的数据
 *                       如果key不存在，首先设置一个key，值初始化为0，然后进行decr运算
 *                       要求key所表示value必须是数值，否则，报错
 *          g、将字符串数值进行加任意数值运算：incrby key offset
 *                       返回加任意数值运算之后的数据
 *                       如果key不存在，首先设置一个key，值初始化为0，然后进行incrby运算
 *                       要求key所表示value必须是数值，否则，报错
 *          h、将字符串数值进行减任意数值运算：decrby key offset
 *                       返回减任意数值运算之后的数据
 *                       如果key不存在，首先设置一个key，值初始化为0，然后进行decrby运算
 *                       要求key所表示value必须是数值，否则，报错
 *          i、截取字符串：
 *                   例如：getrange key 1 5
 *                   下标自左向右，从0开始
 *                   下标自右向左，从-1开始
 *          j、用value覆盖从下标为*开始的字符串，能覆盖几个字符就覆盖几个字符:setrange key 下标 value
 *                    例如：setrange zsname 5 xiaosan
 *          k、设置字符串数据的同时，设置他最大生命周期：setex key 时间 value
 *          l、批量将string类型的数据设置到redis中：mset 键1 值1 键2 值2
 *                    mset k1 v1 k2 v2...
 *          m、批量从redis中获取string类型的数据：mget 键1 值1 键2 值2
 *                    mget k1 v1 k2 v2...
 *     3、redis中有关list类型数据的操作命令(单key:多有序value)
 *          a、将一个或者多个只一次插入到列表的表头(左侧)：lpush key value [value value...]
 *              **存储的数据在读取时候，遵循栈内存规律：先进后出**
 *                lpush list01 1 2 3   结果：3 2 1
 *                lpush list01 4 5     结果：5 4 3 2 1
 *          b、获取指定列表中指定下标区间的元素：lrange key startIndex endIndex
 *                lrange list01 1 3  结果：4 3 2
 *                lrange list01 1 -2 结果：4 3 2
 *                lrange list01 0 -1 结果：5 4 3 2 1
 *          c、将一个或者多个值依次插入到列表的表尾(右侧)：rpush key value [value value...]
 *                rpush list02 a b c 结果：a b c
 *                rpush list02 d e   结果：a b c d e
 *                lpush list02 m n   结果：n m a b c d e
 *          d、从指定列表中移除并且返回表头元素：lpop key
 *                lpop list02
 *          e、从指定列表中移除并且返回表尾元素：rpop key
 *                rpop list02
 *          f、获取指定列表中指定下标的元素：lindex key index
 *                lindex list01 2  结果：3
 *          g、获取指定列表的长度：llen key
 *                llen list01
 *          h、根据counut值移除指定列表中跟value相等的数据：lrem key count value
 *                count>0:从列表的左侧移除count个跟value相等的数据
 *                count<0:从列表的右侧移除count个跟value相等的数据
 *                lpush lilst03 a a b c a d e a b b  结果：b b a e d a c b a a
 *                lrem list03 2 a   结果：b b e d c b a a
 *                lrem list03 -1 a  结果：b b e d c b a
 *                lrem list03 0 a   结果：b b e d c b
 *          i、截取指定列表中指定下标区间的元素组成新的列表，并且赋值给key：ltrim key startIndex endIndex
 *                lpush list04 1 2 3 4 5  结果：5 4 3 2 1
 *                ltrim list04 1 3
 *                lrange list04 0 -1      结果：4 3 2
 *          j、将指定列表中指定下标的元素设置为指定值：lset key index value
 *                lset list04 1 10
 *          k、将value插入到指定列表中位于pivot元素之前/之后的位置：linsert key before/after pivot value
 *                linsert list04 before 10 50
 *                linsert list04 after 10 60
 *     4、redis中有关set类型数据的操作命令(单key:多无序value)
 *          a、将一个或者多个元素添加到指定的集合中：：sadd key value [value value...]
 *                如果元素已经存在，则会忽略
 *                返回成功加入的元素的个数
 *                sadd set01 a b c d  结果:a b c
 *                sadd set01 b d e
 *          b、获取指定集合中所有的元素：smembers key
 *                smembers set01
 *          c、判断指定元素在指定集合中是否存在：sismember key member
 *                存在，返回1
 *                不存在，返回0
 *                sismember set01 f
 *                sismember set01 a
 *          d、获取指定集合的长度：scard key
 *                scard set01
 *          e、移除指定集合中一个或者多个元素：srem key memeber [memeber ...]
 *                不存在的的元素会被忽略
 *                返回成功移除的个数
 *                srem set01 b d m
 *          f、随即获取指定集合中的一个或者多个元素：srandmember key [cound]
 *                count>0:随机获取的多个元素之间不能重复
 *                count<0:随机获取的多个元素之间可能重复
 *                sadd set02 1 2 3 4 5 6 7
 *                srandmember set02
 *                srandmember set02 3
 *                srandmember set02 -3
 *          g、从指定集合中随机移除一个或者多个元素：spop key [count]
 *                spop set02
 *          h、将指定集合中的指定元素移动到另一个元素：smove source dest member
 *                smove set01 set02 a
 *          i、获取第一个集合中有、但是其他集合中都没有的元素组成的新集合：sdiff key key [key key..]
 *                sdiff set01 set02 set03
 *          j、获取所有指定集合中都有的元素组成的新集合：sinter key key [key key...]
 *                sinter set01 set02 set03
 *          k、获取所有指定集合中所有元素组成的大集合：sunion key key[key key...]
 *                sunion set01 set02 set03
 *     5、redis中有关hash类型数据的操作命令( 单key:对象(属性：值) )
 *          a、将一个或者多个field-value对设置到哈希表中：hset key field1 value1
 *                hset stu1001 id 1001
 *                hset stu1001 name zhangsan age 20
 *          b、获取指定哈希表中指定field的值：hget key field
 *                hget stu1001 id
 *                hget stu1001 name
 *          c、批量将多个field-value对设置到哈希表中：hmset key field1 value1
 *                hmset stu1002 id 1002 name lisi age 20
 *          d、批量获取指定哈希表中的field的值：hmget key field
 *                hmget stu1001 id name age
 *          e、获取指定哈希表中所有的field和value：hgetall key
 *                hgetall stu1002
 *          f、从指定哈希表中删除一个或者多个field:hdel key field1
 *                hdel stu1002 name age
 *          g、获取指定哈希表中所有的field个数：hlen key
 *                hlen stu1001
 *                hlen stu1002
 *          h、判断指定哈希表中是否存在某一个field：hexists key field
 *                hexists stu1001 name
 *                hexists stu1002 name
 *          i、获取指定哈希表中所有的field列表：hkeys key
 *                hkeys stu1001
 *                hkeys stu1002
 *          j、获取指定哈希表中所有的value列表：hvals key
 *                hvals stu1001
 *                hvals stu1002
 *          k、对指定哈希表中指定field值进行整数加法运算：hincrby key field int
 *                hincrby stu1001 age 5
 *          l、对指定哈希表中指定field值进行浮点数加法运算：hincerbyfloat key field float
 *                hset stu1001 score 80.5
 *                hincrby stu1001 score 505
 *          m、将一个field-value对设置到哈希表中，当key-field已经存在时，则放弃设置；否则设置file-value:hsetnx key field value
 *                hsetnx stu1001 age 30
 *     6、redis中有关zset类型数据的操作命令(单key:多有序value)
 *          a、将一个或者多个member及其score值加入有序集合：zadd key score member [score member...]
 *                如果元素已经存在，则把分数覆盖
 *                zadd zset01 20 z1 30 z2 50 z3 40 z4
 *                zadd zset01 60 z2
 *          b、获取指定有序集合中指定下标区间的元素：zrange key startIndex endIndex [withscores]
 *                zrange zset01 0 -1
 *                zrange zset01 0 -1 withscores
 *          c、获取指定有序集合中指定分数区间的元素：zrangebyscore key min max [withscores]
 *                zrangebyscore zset01 30 50 withscores
 *          d、删除指定有序集合中一个或者多个元素：zrem key member [member...]
 *                zrem zset01 z3 z4
 *          e、获取指定有序集合中所有元素的个数：zcard key
 *                zcard zset01
 *          f、获取指定有序集合中分数在指定区间内元素的个数：zcount key min max
 *                zcount zset01 20 50
 *          g、获取指定有序集合中指定元素的排名(排名从0开始)：zrank key member
 *                zscore zset01 z4  ==>2
 *          h、获取指定有序集合中指定元素的分数：zscore key member
 *                zscore zset01 z4
 *          i、获取指定有序集合中指定元素的排名(按照分数从大到小的排名)：zrevrank key member
 *                zrevrank zset01 z4  ==>1
 *
 * 九、redis的配置信息
 *     1、常规配置
 *          loglevel:配置日志级别，开发阶段配置debug，上线阶段配置notice或者warning
 *          logfile:指定日志文件。redis在运行过程中，会输出一些日志信息；默认情况下，这些日志会输出到控制台；
 *                  我们可以使用logfile配置日志文件，使redis把日志信息输出到指定文件中
 *          databases:配置redis服务默认创建的的数据库实例个数，默认值是16
 *     2、安全配置
 *          requirepass:设置访问redis服务时所使用的密码；默认不使用。
 *                      此参数必须在protected-mode=yes时才起作用。
 *                      一旦设置了密码验证，客户端连接redis服务时，必须使用密码连接：redis-cli -h ip port -a pwd
 *     3、redis的持久化
 *          redis数据库的数据是存储在内存中的，像Mysql则是存储在磁盘里的。相对而言，存储在内存中运行速度快，读写方便，但是一旦断电，数据丢失。
 *          redis提供持久化策略，在适当的时机采用适当手段把内存中的数据持久化到磁盘中，每次redis服务启动时都可以把磁盘上的数据再次加载内存中使用。
 *
 *          a、RDB策略：（原理是将Redis在内存中的数据快照（snapshot）定时dump到磁盘的持久化）
 *                核心机制
 *                    快照生成：RDB通过生成某一时间点的数据快照（二进制文件）进行持久化。
 *                触发条件：
 *                    手动触发：通过SAVE（阻塞主进程）或BGSAVE（后台子进程）命令。
 *                    自动触发：在配置文件中设置save <seconds> <changes>，例如save 900 1表示若900秒内有至少1次写操作，则触发快照。
 *                优点
 *                    紧凑高效：二进制文件体积小，恢复速度快。
 *                    适合备份：定期生成快照便于灾难恢复。
 *                    性能影响小：子进程生成快照，主进程继续处理请求。
 *                缺点
 *                    数据丢失风险：若故障发生在两次快照之间，最后一次快照后的数据会丢失。
 *                    大数据量时延迟：BGSAVE的fork操作可能在大数据集时耗时较长，导致短暂延迟。
 *                conf文件配置示例
 *                    # 自动触发规则（300秒内如果超过1个key被修改则生成 RDB）【save <seconds> <changes>:配置持久化策略】
 *                    save 300 1
 *                    # RDB文件名（配置redis RDB持久化数据存储的文件）
 *                    dbfilename dump.rdb
 *                    # 存储路径（配置redis RDB持久化文件所在目录）
 *                    dir /var/lib/redis
 *          b、AOF策略：（原理是记录服务器接收的每个写操作，这些操作将在服务器启动时再次执行，以重建原始数据集）
 *                核心机制
 *                    日志记录：AOF以文本格式记录所有写操作命令（如SET, DEL），通过重放命令恢复数据。
 *                同步策略：
 *                    appendfsync always：每次写操作后同步到磁盘（最安全，性能最低）。
 *                    appendfsync everysec：每秒同步一次（平衡安全与性能，默认选项）。
 *                    appendfsync no：由操作系统决定同步时机（性能最高，数据风险最大）。
 *                优点
 *                    数据安全性高：最多丢失1秒数据（默认配置）。
 *                    可读性强：AOF文件为文本格式，便于人工检查或修复。
 *                    自动重写优化：通过BGREWRITEAOF压缩冗余命令，减少文件体积。
 *                缺点
 *                    文件体积大：长期运行的AOF文件可能远大于RDB。
 *                    恢复速度慢：需逐条执行命令，耗时长于RDB的直接加载。
 *                conf文件配置示例
 *                    # 启用AOF
 *                    appendonly yes
 *                    # AOF文件名
 *                    appendfilename "appendonly.aof"
 *                    # 同步策略（每秒同步）
 *                    appendfsync everysec
 *                    # 自动重写触发条件（AOF文件大小增长100%时触发重写）
 *                    auto-aof-rewrite-percentage 100
 *                    auto-aof-rewrite-min-size 64mb
 *          c、混合使用RDB与AOF
 *                 推荐配置
 *                     同时启用：在Redis 4.0+中，可同时开启RDB和AOF，结合两者的优势：
 *                 conf文件配置示例
 *                     appendonly yes # 启用AOF
 *                     save 300 1     # 保留RDB触发条件
 *                 恢复优先级：Redis重启时优先加载AOF文件（数据更完整），若AOF禁用或不存在，则加载RDB。
 *                 协调机制
 *                     快照期间AOF处理：执行BGSAVE时，AOF日志继续记录，保证数据连续性。
 *                 AOF重写优化：重写过程中，Redis会缓存新写入的命令到内存缓冲区，重写完成后追加到新AOF文件，避免阻塞。
 *          d. 优化建议
 *                 RDB优化：
 *                     避免频繁保存：根据数据变化频率调整save配置。
 *                     监控fork耗时：使用info stats查看latest_fork_usec（最近fork操作的微秒数）。
 *                 AOF优化：
 *                     启用自动重写：通过auto-aof-rewrite-percentage和auto-aof-rewrite-min-size控制文件大小。
 *                     使用高速磁盘：AOF同步依赖磁盘IO性能，建议使用SSD。
 *          e. 总结（根据数据的特点决定开启哪种持久化策略，一般情况下，开启RDB足够了）
 *                 RDB：适用于定期备份、快速恢复且能容忍少量数据丢失的场景。
 *                 AOF：适用于对数据安全性要求极高、需实时持久化的场景。
 *                 混合模式：结合两者优势，推荐在生产环境中同时启用，并通过合理配置平衡性能与数据安全。
 *
 * 十、redis的事务
 *      事务：把一组数据库命令放在一起执行，保证操作原子性，要么同时成功，要么同时失败。
 *      redis的事务：允许把一组redis命令放在一起，把命令进行序列化，然后一起执行，保证部分原子性
 *          1、multi:用来标记一个事务的开始
 *          2、exec:用来执行事务队列中所有的命令
 *          3、discard:清除所有压入队列中的命令，并且结束整个事务
 *          4、watch:监控某个键，当事务在执行过程中，此键代码的值发生变化，则本事务放弃执行；否则正常执行
 *          5、redis的事务只能保证部分原子性
 *               命令队列的原子性：事务中的命令队列会作为一个整体执行，不会被其他客户端命令插入。
 *               无回滚机制：若某个命令执行失败（如语法错误），其他命令仍会继续执行，不会自动回滚。
 *
 *               比如付款的时候，避免两个人在同一账户掐同一时间点付款。我们会监控某一个键，来进行监控事务正常执行。
 *               id balance version
 *               update table set balance=balance-dept,version=version+1
 *               where id=xxx and version=100
 *
 *               set balance 100
 *               set balance2 1000
 *               set version 1
 *               watch version
 *
 *               multi
 *               decrby balance 50
 *               incrby balance2 50
 *               incr version
 *               exec
 *          6、unwatch:放弃监控所有的键
 *               watch version
 *               unwach
 *               multi
 *               decrby balance 50
 *               incrby balance2 50
 *               exec
 *
 *          事务小结：
 *            1、单独的隔离操作：事务中的所有命令都会序列化，顺序的执行。事务在执行过程中，不会被其他客户端发来的命令请求所打断，除非是用watch命令监控某些键
 *            2、不保证事务的原子性：redis同一个事务中如果一条命令执行失败，其后的命令仍然可能会被执行，redis的事务没有回滚。
 *
 * 十一、redis消息的发布与订阅
 *         redis客户端订阅频道，消息的发布者往频道上发布消息，所有订阅此频道的客户端都能够接收到消息。
 *         1、subscribe：订阅一个或者多个频道的消息
 *               subscribe ch1 ch2 ch3
 *         2、publish:将消息发布到指定频道
 *               publish ch1 hello
 *         3、psubcribe:订阅一个或者多个频道的消息，频道名支持通配符
 *               psubcribe news.*
 *
 * 十二、redis的主从复制：主少从多，主写从读，读写分离，主写同步复制到从
 *          搭配一主二从redis集群
 *         1、搭建三台redis服务：使用一个redis模拟三台redis服务
 *            提供三份redis配置文件：redis6379.conf\redis6380.conf\redis6381.conf
 *               修改三份配置文件：以redis6379.conf为例
 *                   port6379
 *                   pidfile /var/run/redis_6379.pid
 *                   logfile "6379.log"
 *                   dbfilename dump6379.rdb
 *               分别使用三个redis配置文件，启动三个redis服务：
 *                   redis-server redis6379.conf &
 *                   redis-server redis6380.conf &
 *                   redis-server redis6381.conf &
 *         2、通过redis客户端分别连接三台redis服务
 *                   redis-cli -h 127.0.0.1 -p 6379
 *                   redis-cli -h 127.0.0.1 -p 6380
 *                   redis-cli -h 127.0.0.1 -p 6381
 *         3、查看三台redis服务在集群中的主从角色
 *                   info replication
 *                   默认情况下，所有的redis服务都是主机，既能写和读，但是都还没有从机。
 *         4、设置主从关系：设从不设主
 *                在6380上执行：slaveof 127.0.0.1 6379
 *                在6381上执行：slaveof 127.0.0.1 6379
 *         5、全量复制：一旦主从关系确定，会自动把主机上已有的数据同步复制到从库。
 *                在6380和6381上执行：keys *
 *         6、增量复制：主库写数据会自动同步到从库
 *                在6379上执行：set k2 v2
 *                在6380和6381上执行：keys *
 *         7、主写从读，读写分离
 *                在6380和6381上执行：set k3 v3  ==>报错
 *         8、主机宕机：从机原地待命
 *               关闭6379服务：redis-cli -h 127.0.0.1 -p 6379 shutdown
 *               查看6380和6381服务的的主从角色：info replication
 *         9、主机恢复：一切恢复正常
 *                重启6379服务：redis-server redis6379.conf &
 *                redis-cli -h 127.0.0.1 -p 6379
 *         10、从机宕机：主机少一个从机，其他从机不变
 *                关闭6380服务：redis-cli -h 127.0.0.1 -p 6380 shutdown
 *                查看6379和6381服务的的主从角色：info replication
 *         11、从机恢复：需要重新设置主从关系
 *                重启6380服务：redis-server redis6380.conf &
 *                客户端连接6380：redis-cli -h 127.0.0.1 -p 6380
 *                在6380上执行：slaveof 12.0.0.1 6379
 *         12、从机上位
 *             a、主机宕机，从机原地待命
 *                  关闭6379服务：redis-cli -h 127.0.0.1 -p 6379 shutdown
 *                  查看6380和6381服务的的主从角色：info replication
 *             b、从机断开原来主从关系
 *                  在6380上执行：slaveof no one
 *                  查看6380服务的主从角色:info replication
 *             c、重现设置主从关系
 *                  在6381上执行：slaveof 127.0.0.1 6380
 *             d、之前主机恢复，变成孤家寡人
 *                  重启6379服务：redis-server redis6379.conf &
 *                  客户端连接6379：redis-cli -h 127.0.0.1 -p 6379
 *             e、天堂变地狱
 *                  在6379上执行：slaveof 127.0.0.1 6381
 *                  在6381上执行：info replication   既是主机又是从机
 *
 *      小结：一台主机配置多台从机，一台从机又可以配置多台从机，从未形成一个庞大的集群结构。
 *           减轻一台主机的压力，但是增加了服务间的延迟时间。
 *
 * 十三、redis哨兵模式：主机宕机，从机上位的自动版
 *         1、搭建一主二从集群架构
 *         2、提供哨兵配置文件：
 *               在redis安装目录下创建配置文件：touch redis_sentinel.conf
 *               sentinel monitor dc-redis 127.0.0.1 6379 1
 *               (表示：哨兵名，指定监控主机的ip地址，port端口，得到哨兵的投票数[当哨兵投票数大于或者等于此数时切换主从关系])
 *         3、启动哨兵服务：
 *               redis-sentinel redis_sentinel.conf
 *         4、主机宕机：
 *               关闭6379服务：redis_cli -h 127.0.0.1 -p 6379 shutdown
 *               哨兵程序自动选择从机上位
 *         5、之前主机恢复：自动从属于新的主机
 *               重启6379服务：redis-server redis6379.conf &
 *               客户端连接6379：redis-cli -h 127.0.0.1 -p 6379
 *
 * 十四、集群模式
 *
 * 十五、
 *     缓存穿透：key对应的数据在数据源并不存在，每次针对此key的请求从缓存获取不到，请求都会到数据源，从而可能压垮数据源。
 *             比如用一个不存在的用户id获取用户信息，不论缓存还是数据库都没有，若黑客利用此漏洞进行攻击可能压垮数据库。
 *        解决方案：如果一个查询返回的数据为空（不管是数据不存在，还是系统故障），我们仍然把这个空结果进行缓存，但它的过期时间会很短，最长不超过五分钟。
 *
 *     缓存击穿：key对应的数据存在，但在redis中过期，此时若有大量并发请求过来，这些请求发现缓存过期一般都会从后端DB加载数据并回设到缓存，
 *             这个时候大并发的请求可能会瞬间把后端DB压垮。
 *        解决方案：大多数公司很难出现对数据库的压力，所以可以对一些‘爆款’设置缓存永不过期就好了。mutex key互斥锁真心用不上
 *
 *     缓存雪崩：当缓存服务器重启或者大量缓存集中在某一个时间段失效，这样在失效的时候，也会给后端系统(比如DB)带来很大压力。
 *        解决方案：尽量将缓存信息的过期时间相错开，在过期时间上加一个随机值即可。
 *
 * 十六、Redis在SpringBoot中的命令
 *      在String类型中的操作：
 *           1、将String类型存入到Redis当中：redisTemplate.opsForValue().set(key, value, long, TimeUnit.SECONDS);
 *           2、从redis当中取出String类型的数据：redisTemplate.opsForValue().get(key);
 *      在List类型中的操作：
 *           1、从当前的数据 向右添加：redisTemplate.opsForList().rightPush(key, integer);
 *           2、从当前的数据 向左添加：redisTemplate.opsForList().leftPush(key, integer);
 *           3、从redis中取出list数据：redisTemplate.opsForList().range(key, 0, 10);
 *           4、获取redis中list的长度：redisTemplate.opsForList().size(key);
 *      在Hash类型中的操作：
 *           1、将hash存放到redis当中：redisTemplate.opsForHash().put(key, hashkey, Value);这里的key表示存入redis的key,hashKey表示存入的Map集合中的Key
 *           2、检测 是否 存在该键：redisTemplate.opsForHash().hasKey(key, key1);
 *           3、将hash从redis当中取出来 根据具体的key取出具体的值：redisTemplate.opsForHash().get(key, key1);
 *           4、将key中所有的k-v值都取出来：redisTemplate.opsForHash().entries(key);
 *           5、根据具体的key移除具体的值：redisTemplate.opsForHash().delete(key, key1);
 *      在Set类型中的操作：
 *           1、set数据存入redis中：redisTemplate.opsForSet().add(key, object);
 *           2、从redis当中将set数据取出来：redisTemplate.opsForSet().members(key);
 *      在ZSet类型中的操作：
 *           1、zset数据存入redis中：redisTemplate.opsForZSet().add(key, value, score); 区别于Set类型多一个score
 *           2、从redis当中将set数据取出来：redisTemplate.opsForSet().members(key); 与Set类型没区别
 *
 *      五种数据类型中通用操作：
 *           自增：redisTemplate.opsForValue().increment(key);
 *           自减：redisTemplate.opsForValue().decrement(key);
 *           移除key值 则key里面的所有值都被移除：redisTemplate.delete(key);
 *           设置reids key的过期时间：redisTemplate.expire(key, time, TimeUnit.SECONDS);
 *           检测 是否 存在该键：redisTemplate.hasKey(key);
 */
public class Redis概述 {}
