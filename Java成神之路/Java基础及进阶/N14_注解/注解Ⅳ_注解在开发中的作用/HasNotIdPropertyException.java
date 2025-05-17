package N14_注解.注解Ⅳ_注解在开发中的作用;

public class HasNotIdPropertyException extends RuntimeException {

	private static final long serialVersionUID = 7983837057378970583L;

    public HasNotIdPropertyException() {}

	public HasNotIdPropertyException(String s) {
		super(s);
	}
}
