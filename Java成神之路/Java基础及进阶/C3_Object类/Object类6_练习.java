package C3_Object类;

class User {

	String name;     // 用户名
	Address addr;    // 用户的地址

	public User() {}

	public User(String name, Address addr) {
		this.name = name;
		this.addr = addr;
	}

	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof User)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		User u = (User) obj;   // 能执行到这一步，说明obj肯定是User类型,所以不再需要强制类型转换符instanceof
		if (this.name.equals(u.name) && this.addr.equals(u.addr)) {  // this.name表示本类中的name。u.name表示的是形参中的name，即main函数中的name实参
			return true;
		}
		return false;
	}
}

class Address {

	String city, street, zipcode;  // 城市, 街道, 邮编

	public Address() {
	}

	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof Address)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Address a = (Address) obj;
		if (this.city.equals(a.city) && this.street.equals(a.street) && this.zipcode.equals(a.zipcode)) {
			return true;
		}
		return false;
	}

}

public class Object类6_练习 {

	public static void main(String[] args) {
		User u1 = new User("游家纨绔", new Address("武汉", "江夏区", "111111"));
		User u2 = new User("游家纨绔", new Address("武汉", "江夏区", "111111"));
		System.out.println(u1.equals(u2));   // true

		User u3 = new User("游家纨绔", new Address("武汉", "江岸区", "111222"));
		System.out.println(u1.equals(u3));   // false
	}

}
