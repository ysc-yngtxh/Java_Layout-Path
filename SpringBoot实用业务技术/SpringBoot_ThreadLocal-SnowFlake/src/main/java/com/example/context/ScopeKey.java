package com.example.context;

import java.util.function.Supplier;

// 定义上下文中的数据状态
public final class ScopeKey<T> {

    // 初始化器，参考 ThreadLocal 的 withInitial()
    private final Supplier<T> initializer;

    public ScopeKey() {
        this(null);
    }

    public ScopeKey(Supplier<T> initializer) {
        this.initializer = initializer;
    }

    // 统一初始化所有线程的 ScopeKey 对应的值，参考 ThreadLocal 的 withInitial()
    public static <T> ScopeKey<T> withInitial(Supplier<T> initializer) {
        return new ScopeKey<>(initializer);
    }

    public Supplier<T> initializer() {
        return this.initializer;
    }

    // 获取当前上下文中 ScopeKey 对应的变量
    public T get() {
        Scope currentScope = Scope.getCurrentScope();
        return currentScope.get(this);
    }

    // 设置当前上下文中 ScopeKey 对应的变量
    public boolean set(T value) {
        Scope currentScope = Scope.getCurrentScope();
        if (currentScope != null) {
            currentScope.set(this, value);
            return true;
        } else {
            return false;
        }
    }
}