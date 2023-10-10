if( redis.call( 'exists', KEYS[1] ) == 1 ) then
    local stock = tonumber( redis.call('get', KEYS[1]) );
    if(stock > 0) then
        redis.call('incrby', KEYS[1], -1);
        return stock;
    end;
        return -1;
end;

-- 如果键 KEYS[1] 存在（存在返回值为1），则继续执行。
-- 获取键 KEYS[1] 的值，并将其转换为数字类型，保存在变量 stock 中。
-- 如果 stock 的值大于0，则将键 KEYS[1] 的值减1，并返回 stock 的值。(incrby设置自增，且自定义自增值)
-- 如果 stock 的值小于等于0，则返回-1。