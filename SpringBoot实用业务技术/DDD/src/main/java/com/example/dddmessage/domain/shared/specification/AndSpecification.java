package com.example.dddmessage.domain.shared.specification;

/**
 * AND specification, used to create a new specifcation that is the AND of two other specifications.
 *
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public class AndSpecification<T> extends AbstractSpecification<T> {

	private Specification<T> spec1;
	private Specification<T> spec2;

	/**
	 * Create a new AND specification based on two other spec.
	 *
	 * @param spec1 Specification one.
	 * @param spec2 Specification two.
	 */
	public AndSpecification(final Specification<T> spec1, final Specification<T> spec2) {
		this.spec1 = spec1;
		this.spec2 = spec2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(final T t) {
		return spec1.isSatisfiedBy(t) && spec2.isSatisfiedBy(t);
	}
}
