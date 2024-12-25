/**
 * @author 游家纨绔
 * @dateTime 2024-12-17 00:34
 * @apiNote TODO
 */
public class D4_常见函数 {}
/*
一、数学函数
    1、绝对值函数 ABS(x) 和返回圆周率函数 PI()
       ①、求2，-3.3和-33的绝对值，输入语句如下：
           SELECT ABS(2), ABS(-3.3), ABS(-33);
       ②、返回圆周率值，输入语句如下：
           SELECT pi();
    2、平方根函数 SQRT(x) 和求余函数 MOD(x,y)
       ①、求9，40和-49的二次平方根，输入语句如下：
           SELECT SQRT(9), SQRT(40);
       ②、对MOD(31,8)，MOD(234, 10)，MOD(45.5,6)进行求余运算，输入语句如下：
           SELECT MOD(31,8),MOD(234, 10),MOD(45.5,6);
    3、获取整数的函数 CEIL(x)、CEILING(x)、FLOOR(x)
       ①、使用CEIL和CEILING函数返回最小整数，输入语句如下：
           SELECT  CEIL(-3.35),CEILING(3.35);
       ②、FLOOR(x)返回不大于x的最大整数值，返回值转化为一个BIGINT。使用FLOOR函数返回最大整数，输入语句如下：
           SELECT FLOOR(-3.35), FLOOR(3.35);
    4、四舍五入函数ROUND(x)和ROUND(x,y)
       ①、使用ROUND(x)函数对操作数进行四舍五入操作，输入语句如下：
           SELECT ROUND(-1.14),ROUND(-1.67), ROUND(1.14),ROUND(1.66);
       ②、使用ROUND(x,y)函数对操作数进行四舍五入操作，结果保留小数点后面指定y位，输入语句如下：
           SELECT ROUND(1.38, 1), ROUND(1.38, 0), ROUND(232.38, -1), ROUND (232.38,-2);
    5、符号函数SIGN(x)
       ①、使用SIGN函数返回参数的符号，输入语句如下：
           SELECT SIGN(-21),SIGN(0), SIGN(21);
    6、幂运算函数POW(x,y)、POWER(x,y)和EXP(x)
       ①、使用POW和POWER函数进行乘方运算，输入语句如下：
           SELECT POW(2,2), POWER(2,2),POW(2,-2), POWER(2,-2);
       ②、使用EXP函数计算e的乘方，输入语句如下：
           SELECT EXP(3),EXP(-3),EXP(0);
    7、对数运算函数LOG(x)
       ①、使用LOG(x)函数计算自然对数，输入语句如下：
           SELECT LOG(3);
    8、角度与弧度相互转换的函数RADIANS(x)和DEGREES(x)
       ①、使用RADIANS将角度转换为弧度，输入语句如下：
           SELECT RADIANS(90),RADIANS(180);
       ②、使用DEGREES将弧度转换为角度，输入语句如下：
           SELECT DEGREES(PI()), DEGREES(PI() / 2);
    9、正弦函数SIN(x)和反正弦函数ASIN(x)
       ①、使用SIN函数计算正弦值，输入语句如下：
           SELECT SIN(1), ROUND(SIN(PI()));
       ②、使用ASIN函数计算反正弦值，输入语句如下：
           SELECT ASIN(0.8414709848078965);
    10、余弦函数COS(x)反余弦函数ACOS(x)
        ①、使用COS函数计算余弦值，输入语句如下：
            SELECT COS(0),COS(PI()),COS(1);
        ②、使用ACOS计算反余弦值，输入语句如下：
            SELECT ACOS(1),ACOS(0), ROUND(ACOS(0.5403023058681398));
    11、正切函数、反正切函数和余切函数
        ①、使用TAN函数计算正切值，输入语句如下：
            SELECT TAN(0.3), ROUND(TAN(PI()/4));
        ②、使用ATAN函数计算反正切值，输入语句如下：
            SELECT ATAN(0.30933624960962325), ATAN(1);
        ③、使用COT()函数计算正切值，输入语句如下，
            SELECT COT(0.3), 1/TAN(0.3),COT(PI() / 4);

二、字符串函数
    1、计算字符数的函数 char_length(str) 和字符串长度 length() 的函数
       ①、使用CHAR_LENGTH函数计算字符串字符个数，输入语句如下：
           SELECT CHAR_LENGTH('date'), CHAR_LENGTH('egg');
       ②、使用LENGTH函数计算字符串长度，输入语句如下：
           SELECT LENGTH('date'), LENGTH('egg');
    2、合并字符串 CONCAT(s1,s2,…),CONCAT_WS(x,s1,s2,…)
       ①、使用CONCAT函数连接字符串，输入语句如下：
           SELECT CONCAT('PostgreSQL', '9.15'), CONCAT('Postgre',NULL,'SQL');
       ②、使用CONCAT_WS函数连接带分隔符的字符串，输入语句如下：
           SELECT CONCAT_WS('-', '1st','2nd', '3rd'), CONCAT_WS('*', '1st', NULL, '3rd');
    3、获取指定长度的字符串的函数 LEFT(s,n) 和 RIGHT(s,n)
       ①、使用LEFT函数返回字符串中左边的字符，输入语句如下：
           SELECT LEFT('football', 5);
       ②、使用RIGHT函数返回字符串中右边的字符，输入语句如下：
           SELECT RIGHT('football', 4);
    4、填充字符串 LPAD(s1,len,s2) 和 RPAD(s1,len,s2)
       ①、使用LPAD函数对字符串进行填充操作，输入语句如下：
           SELECT LPAD('hello',4,'??'), LPAD('hello',10,'??');
       ②、使用LPAD函数对字符串进行填充操作，输入语句如下：
           SELECT RPAD('hello',4,'?'), RPAD('hello',10,'?');
    5、删除空格的函数 LTRIM(s)、RTRIM(s)和TRIM(s)
       ①、使用LTRIM函数删除字符串左边的空格，输入语句如下：
           SELECT CONCAT('(',LTRIM('  book  '),')');
       ②、使用LTRIM函数删除字符串右边的空格，输入语句如下：
           SELECT CONCAT('(', RTRIM ('  book  '),')');
       ③、使用TRIM函数删除指定字符串两端的空格，输入语句如下：
           SELECT CONCAT('(', TRIM('  book  '),')');
    6、删除指定字符串的函数TRIM(s2 from s)
       ①、使用TRIM(s1 FROM s)函数删除字符串中两端指定的字符，输入语句如下：
           SELECT TRIM('xy' FROM 'xyboxyokxyxy') ;
    7、重复生成字符串的函数 REPEAT(s,n)
       ①、使用REPEAT函数重复生成相同的字符串，输入语句如下：
           SELECT REPEAT('PostgreSQL', 3);
    8、替换函数 REPLATE(s,s1,s2)
       ①、使用REPLACE函数进行字符串替代操作，输入语句如下：
           SELECT REPLACE('xxx.PostgreSQL.com', 'x', 'w');
    9、获取指定子串的函数 SUBSTRING(s,n,len)
       ①、使用SUBSTRING函数获取指定位置处的子字符串，输入语句如下：
           SELECT SUBSTRING('breakfast', 5) AS col1,     # 从第5个位置开始截取，截取到最后一个字符。
                  SUBSTRING('breakfast', 5, 3) AS col2,  # 从第5个位置开始截取，截取3个字符。
                  SUBSTRING('lunch', -3) AS col3,        # 不支持负索引，但也不会报错，会显示出完整的字符
                  SUBSTRING ('PostgreSQL', 'gre') col4;  # 正则表达式截取，截取 'gre' 字符串
    10、匹配子串开始位置的函数 POSITION(str1 in str)
        ①、使用POSITION函数查找字符串中指定子字符串的开始位置，输入语句如下：
            SELECT POSITION('ball' IN 'football');
    11、字符串逆序的函数 REVERSE(s)
        ①、使用REVERSE函数反转字符串，输入语句如下：
            SELECT REVERSE('abc');

三、类型转换相关函数
    1、to_timestamp() 字符串转时间
       select * from t_user where create_time >= to_timestamp('2023-01-01 00:00:00', 'yyyy-mm-dd hh24:MI:SS');
    2、to_char 时间转字符串
       select to_char(create_time, 'yyyy-mm-dd hh24:MI:SS') from t_user;
    3、to_date(text, text) 字符串转换为日期
       select to_date('2024-12-25', 'YYYY-MM-DD');
    4、to_number(text, text) 转换字符串为数字
       select to_number('1,234,567.89', '999G999G999D99');  返回值：1234567.89

四、日期和时间函数
    1、获取当前日期的函数和当前时间的函数
       ①、使用日期函数获取系统当期日期，输入语句如下：
           SELECT CURRENT_DATE;
       ②、使用时间函数获取系统当期日期(返回时带时区)，输入语句如下：
           SELECT CURRENT_TIME;
       ③、使用时间函数获取系统当期日期(返回时不带时区)，输入语句如下：
           SELECT LOCALTIME;
    2、返回当前日期和时间的函数
       ①、使用日期时间函数获取当前系统日期和时间，输入语句如下：
           SELECT CURRENT_TIMESTAMP,LOCALTIMESTAMP,NOW();
    3、获取指定值的日期
       ①、使用EXTRACT函数从月份中提取日期，输入语句如下：
           SELECT EXTRACT(DAY FROM TIMESTAMP '2012-09-10 10:18:40');
       ②、使用EXTRACT函数从月份中提取月份，输入语句如下：
           SELECT EXTRACT(MONTH FROM TIMESTAMP '2012-09-10 10:18:40');
       ③、使用EXTRACT函数从月份中提取年份，输入语句如下：
           SELECT EXTRACT(YEAR FROM TIMESTAMP '2012-09-10 10:18:40');
       ④、使用EXTRACT函数查询指定日期是一年中的第几天，输入语句如下：
           SELECT EXTRACT(DOY FROM TIMESTAMP '2012-09-10 10:18:40');
       ⑤、使用EXTRACT函数查询指定日期是一周中的星期几，输入语句如下：
           SELECT EXTRACT(DOW FROM TIMESTAMP '2012-09-10 10:18:40');
       ⑥、使用EXTRACT函数查询指定日期是该年的第几季度(1-4)，输入语句如下：
           SELECT EXTRACT(QUARTER FROM TIMESTAMP '2012-09-10 10:18:40');
    4、日期和时间的运算操作
       ①、计算指定日期加上间隔天数后的结果，输入语句如下：
           SELECT DATE '2019-09-28' + integer '10';
       ②、计算指定日期加上间隔小时后的结果，输入语句如下：
           SELECT DATE '2012-09-28' + interval '3 hour';
       ③、计算指定日期加上指定时间后的结果，输入语句如下：
           SELECT DATE '2012-09-28' + time '06:00';
       ④、计算指定日期和时间加上间隔时间后的结果，输入语句如下：
           SELECT TIMESTAMP '2012-09-28 02:00:00' + interval '10 hours';
       ⑤、计算指定日期之间的间隔天数，输入语句如下：
           SELECT date '2012-11-01' - date '2012-09-10';
       ⑥、计算指定日期减去间隔天数后的结果，输入语句如下：
           SELECT DATE '2012-09-28' - integer '10';
       ⑦、计算整数与天数相乘的结果，输入语句如下：
           SELECT 15 * interval '2 day';
       ⑧、计算整数与秒数相乘的结果，输入语句如下：
           SELECT 50 * interval '2 second';
       ⑨、计算小时数与整数相乘的结果，输入语句如下：
           SELECT interval '1 hour' / integer  '2';
       ⑩、当前时间加一天
           SELECT NOW()::TIMESTAMP + '1 day';
           SELECT NOW() + INTERVAL '1 DAY';
           SELECT now()::timestamp + ('1' || ' day')::interval
       ⑪、当前时间减一天
           SELECT NOW()::TIMESTAMP + '-1 day';
           SELECT NOW() - INTERVAL '1 DAY';
           SELECT now()::timestamp - ('1' || ' day')::interval
       ⑫、加1年1月1天1时1分1秒
           SELECT NOW()::timestamp + '1 year 1 month 1 day 1 hour 1 min 1 sec';

四、条件判断函数
    1、CASE expr WHEN v1 THEN r1 [WHEN v2 THEN r2][ELSE rn] END;
       意思是如果expr的值等于某个vn，则返回对应位置THEN后面的结果，如果与所有值都不相等，则返回ELSE后面的rn。
       使用CASE value WHEN语句执行分支操作，输入语句如下：
           SELECT CASE 2 WHEN 1 THEN 'one' WHEN 2 THEN 'two' ELSE 'more' END;
    2、CASE WHEN v1 THEN r1 [WHEN v2 THEN r2][ELSE rn] END;
       使用CASE WHEN语句执行分支操作，输入语句如下：
           SELECT CASE WHEN 1<0 THEN 'true' ELSE 'false' END;
       
五、系统信息函数
    1、获取PostgreSQL版本号
       ①、查看当前PostgreSQL版本号，输入语句如下：
           SELECT VERSION();
    2、获取用户名的函数
       ①、获取当前登陆用户名称，输入语句如下：
           SELECT USER, CURRENT_USER;
    
六、加密函数
    1、加密函数MD5(str)
       ①、使用MD5函数加密字符串，输入语句如下：
           SELECT MD5 ('mypwd');
    2、加密函数ENCODE(str,pswd_str)
       ①、使用ENCODE加密字符串，输入语句如下：
           SELECT ENCODE('secret','hex'), LENGTH(ENCODE('secret','hex'));
    3、解密函数DECODE(cypt_str,pswd_str)
       ①、使用DECODE函数解密被ENCODE加密的字符串，输入语句如下：
           SELECT DECODE(ENCODE('secret','hex'),'hex');

七、改变数据类型的函数
    1、CAST(x, AS type)函数将一个类型的值转换为另一个类型的值。
       ①、使用CAST函数进行数据类型的转换，将整数类型100转成两位字符串的类型10，SQL语句如下：
           SELECT CAST(100 AS CHAR(2));
    
八、常见问题及解答
    疑问1：如何从日期时间值中获取年、月、日等部分日期或时间值？
          在PostgreSQL中，日期和时间是以字符串形式存储在数据表中，可以截取字符串，
          如dt = ‘2020-03-01 12:30:30’,则可以输入left(dt, 4)截取年份，输入mid(dt, 6, 2)截取月份，其他同理。
    疑问2：如何计算年龄？
          年龄通过当前年龄减去出生年份来计算的。例如extract(year form m_birth)返回的结果是出生的年份，
          然后通过extract(year from current_date)获取当前年份，两者相减就是年龄。

九、工作学习函数：
    1、函数：nullIf()、coalesce(t.lead_status, 1)
         position()函数返回一个整数：该整数表示子字符串在字符串中的位置。如果在字符串中未找到字符串，则position()函数将返回零(0)，否则返回子字符串在字符串中的下标。
                                    例如：select position('handler' in 'warning,handler,handler,cases,caseinfo');运行后返回值为9
	     cast()函数操作数据类型转换：例如 cast('100' AS INTEGER) String类型转换Integer类型
		 
    2、当磁盘满当会锁住表为只读，需要清空些表数据：set session default_transaction_read_only=off;  ---临时会话临时解除只读
                                               truncate TABLE 表名;（同上个脚本一起执行）
 */