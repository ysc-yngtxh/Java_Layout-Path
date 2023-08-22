package K11_线程.线程_Ⅲ_线程安全.线程_11_ThreadLocal;


public class User {

    private int id;
    private String message;

    public User(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public User() {
    }

    public int getId(int i) {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage(String adasdf) {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
