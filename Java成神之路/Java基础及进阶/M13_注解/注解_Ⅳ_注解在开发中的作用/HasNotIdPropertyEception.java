package M13_注解.注解_Ⅳ_注解在开发中的作用;

public class HasNotIdPropertyEception extends RuntimeException{
    public HasNotIdPropertyEception() {

    }

    public HasNotIdPropertyEception(String s) {
        super(s);
    }

}
