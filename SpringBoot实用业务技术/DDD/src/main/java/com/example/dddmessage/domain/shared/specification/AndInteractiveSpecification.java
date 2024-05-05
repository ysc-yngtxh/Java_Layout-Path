package com.example.dddmessage.domain.shared.specification;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public class AndInteractiveSpecification<T,R> extends AbstractSpecification<T> implements InteractiveSpecification<T,R>{

    private final List<InteractiveSpecification<T,R>> specifications;

    public AndInteractiveSpecification(InteractiveSpecification<T,R> ...specs) {
        this.specifications = Arrays.asList(specs);
    }

    @Override
    public void notSatisfiedHandleBy(T t, Consumer<R> handle) {
        AtomicBoolean processed = new AtomicBoolean(false);
        for (InteractiveSpecification<T, R> specification : specifications){
            if(processed.get()){
                break;
            }
            specification.notSatisfiedHandleBy(t,r -> {
                handle.accept(r);
                processed.compareAndSet(false, true);
            });
        }
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        for (Specification<T> specification : specifications){
            if(!specification.isSatisfiedBy(t)){
                return false;
            }
        }
        return true;
    }
}
