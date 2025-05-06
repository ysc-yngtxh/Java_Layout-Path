/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-05-06 11:30:00
 */
/* JSON数据类型【MySQL 从 5.7.8 版本开始引入了 JSON 数据类型，为存储和处理 JSON 格式数据提供了原生支持】
 * 一、JSON 类型基本特性
 *     存储格式：
 *            内部以二进制格式存储（类似BLOB）
 *            自动验证输入的JSON数据有效性
 *            最大存储空间与LONGBLOB/LONGTEXT相同（约4GB）
 *     优势：
 *            比在字符串列中存储JSON更高效
 *            自动验证JSON文档有效性
 *            提供优化的存储格式，快速读取访问
 *            支持JSON路径表达式查询
 *
 * 二、创建JSON列
 *     CREATE TABLE products (
 *         id INT AUTO_INCREMENT PRIMARY KEY,
 *         name VARCHAR(100),
 *         attributes JSON,  -- JSON类型列
 *         price DECIMAL(10,2)
 *     );
 *
 * 三、插入JSON数据
 *     1. 直接插入JSON字符串
 *        INSERT INTO products (name, attributes, price)
 *                      VALUES ('iPhone 13', '{"color": "black", "memory": "128GB", "network": ["5G", "4G"]}', 5999.00);
 *     2. 使用JSON函数
 *        INSERT INTO products (name, attributes, price)
 *                      VALUES ('iPad Pro', JSON_OBJECT('color', 'silver',
 *                                                      'storage', '256GB',
 *                                                      'accessories', JSON_ARRAY('Pencil', 'Keyboard'))
 *                             , 6999.00);
 *
 * 四、查询JSON数据
 *     1. 提取JSON元素（-> 操作符）
 *        SELECT name, attributes->'$.color' AS color FROM products;    -- "silver"
 *     2. 提取并去除引号（->> 操作符）
 *        SELECT name, attributes->>'$.color' AS color FROM products;   -- silver
 *     3. 使用JSON_EXTRACT函数
 *        SELECT name, JSON_EXTRACT(attributes, '$.memory') AS memory FROM products;
 *     4. 条件查询
 *        -- 查找color为black的产品
 *        SELECT name FROM products WHERE attributes->>'$.color' = 'black';
 *        -- 查找network包含5G的产品
 *        SELECT name FROM products WHERE JSON_CONTAINS(attributes->'$.network', '"5G"');
 *
 * 五、更新JSON数据
 *     1. 完全替换
 *        UPDATE products SET attributes = '{"color": "blue", "memory": "256GB"}' WHERE id = 1;
 *     2. 部分更新（MySQL 8.0+）
 *        -- 修改特定属性
 *        UPDATE products SET attributes = JSON_SET(attributes, '$.color', 'red') WHERE id = 1;
 *        -- 添加新属性
 *        UPDATE products SET attributes = JSON_SET(attributes, '$.weight', '450g') WHERE id = 2;
 *        -- 删除属性
 *        UPDATE products SET attributes = JSON_REMOVE(attributes, '$.network') WHERE id = 1;
 *
 * 六、JSON函数大全
 *         函数	           描述
 *     JSON_OBJECT()	创建JSON对象
 *     JSON_ARRAY()	    创建JSON数组
 *     JSON_MERGE()	    合并JSON文档
 *     JSON_VALID()	    验证JSON有效性
 *     JSON_TYPE()	    返回JSON值类型
 *     JSON_KEYS()	    返回对象中的键
 *     JSON_LENGTH()	返回元素数量
 *     JSON_SEARCH()	查找值路径
 *
 * 七、索引与性能优化
 *    生成列+索引：
 *        ALTER TABLE products
 *        ADD color VARCHAR(20) AS (attributes->>'$.color'),
 *        ADD INDEX (color);
 *
 *    MySQL 8.0的多值索引：
 *        ALTER TABLE products
 *        ADD INDEX idx_network ((CAST(attributes->'$.network' AS CHAR(20) ARRAY)));
 */
public class V22_JSON数据类型 {}
