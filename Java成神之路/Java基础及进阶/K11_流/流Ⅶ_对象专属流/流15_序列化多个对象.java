package K11_流.流Ⅶ_对象专属流;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* 一次序列化多个对象
 *    将对象放到集合当中，序列化集合
 *
 * 提示：
 *    参与序列化的ArrayList集合以及集合当中的元素User都需要实现 java.io.Serializable接口
 */
public class 流15_序列化多个对象 {

	public static void main(String[] args) throws Exception {
		List<User> u = new ArrayList<>();
		u.add(new User(1, "游家纨绔"));
		u.add(new User(2, "曹家千金"));
		u.add(new User(3, "我想你"));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/流Ⅶ_对象专属流/ObjectFile"));
		oos.writeObject(u);
		oos.close();
		oos.flush();

		// 反序列化集合
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/流Ⅶ_对象专属流/ObjectFile"));
		List<User> userList = (List<User>) ois.readObject();   // 是个List集合，强转成List集合
		for (User user : userList) {
			System.out.println(user);
		}
		ois.close();
	}

}

class User implements Serializable {

	private static final long serialVersionUID = 3797086858448566237L;
	private int no;
	private String name;

    public User() {}

	public User(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"no=" + no +
				", name='" + name + '\'' +
				'}';
	}

}
