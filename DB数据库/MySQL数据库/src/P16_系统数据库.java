/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-04-30 11:30:50
 */
/* MySql自带的4个系统数据库：information_schema、mysql、performance_schema、sys；
 *     1、information_schema
 *        这个数据库保存了MySql服务器所有数据库的信息。
 *        比如数据库的名、数据库的表、访问权限、数据库表的数据类型，数据库索引的信息等等。
 *     2、performance_schema
 *        主要用于收集数据库服务器性能参数，可用于监控服务器在一个较低级别的运行过程中的资源消耗、资源等待等情况。
 *     3、sys
 *        库中所有的数据源来自：performance_schema。
 *        目标是把performance_schema的把复杂度降低，让DBA能更好的阅读这个库里的内容。让DBA更快的了解DB的运行情况。
 *     4、mysql
 *        mysql的核心数据库，类似于sql server中的master表，主要负责存储数据库的用户、权限设置、关键字等mysql自己需要使用的控制和管理信息。
 */
public class P16_系统数据库 {}
