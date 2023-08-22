package N14_练习题;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/11/04 22:24
 */
public class User {

    private String id;
    private String user;
    private String name;
    private String age;
    private String date;
    private String newDate;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", date='" + date + '\'' +
                ", newDate='" + newDate + '\'' +
                ", no=" + no +
                '}';
    }

    private int no;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }
}
