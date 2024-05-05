package com.example.dddmessage.domain.shared.specification;


import java.util.function.Consumer;

/**
 * 可交互的 specification
 *     扩展自 {@link Specification} , 新增 notSatisfiedHandleBy 当处理的结果不令人满意时调用，handle 处理
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface InteractiveSpecification<T,R> extends Specification<T>{
    /**
     * 当验证结果不满意时，调用 handle 进行处理
     * @param t
     * @param handle
     */
    void notSatisfiedHandleBy(T t, Consumer<R> handle);
}
