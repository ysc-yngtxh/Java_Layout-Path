package M13_注解.F6注解在开发中的作用;

public class HasNotIdPropertyEception extends RuntimeException{
    public HasNotIdPropertyEception() {

    }

    public HasNotIdPropertyEception(String s) {
        super(s);
    }

}
