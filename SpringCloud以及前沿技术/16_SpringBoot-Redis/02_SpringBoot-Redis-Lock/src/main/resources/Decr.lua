if redis.call("get", KEYS[1]) == ARGV[1] then
    return redis.call("del", KEYS[1])
else
    return 0
end


-- 这段代码是一个 Lua 脚本，它使用了 Redis 的命令来实现一个简单的删除操作。
-- 首先，它会通过 redis.call("get", KEYS) 来获取指定 key 的值，然后与 ARGV 进行比较。这里用于确定当前锁是否是当前进程的锁
-- 如果相等，就会执行 redis.call("del", KEYS) 来删除这个 key，并返回删除操作的结果。如果不相等，则返回 0。
-- KEYS[1]指的是 execute(RedisScript<T> script, List<K> keys, Object... args)方法里的集合keys的第一个元素，
-- ARGV[1]指的是 可变参数args的第一个元素
--
-- 你可以把这段代码用于 Redis 的 EVAL 命令来执行 Lua 脚本。
-- 你需要传递两个参数给 EVAL 命令，一个是 Lua 脚本代码，另一个是键和值的列表。
-- 在这个例子中，KEYS 表示要操作的 key，ARGV 表示要匹配的值。
--
-- 需要注意的是，Lua 脚本在 Redis 中是原子执行的，所以这段代码能够保证删除操作和比较操作的原子性。