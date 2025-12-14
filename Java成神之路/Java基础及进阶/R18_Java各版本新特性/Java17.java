package R18_Java各版本新特性;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2025-12-14 08:00
 * @apiNote TODO
 */
public class Java17 {
	static void main() {
		// TODO 增强switch表达式：
		//      1、使用箭头表达式（无需担心break的穿透效应）
		//      2、使用 yield 作为返回值
		//      3、一个case处理多个值
		String status = "PENDING APPROVED";
		String result = switch (status) {
			case "SAVED" -> {
				System.out.println("处理保存状态");
				yield "保存中";  // yield 关键字用于从 switch 表达式的一个分支中返回一个值
			}
			case "SUBMITTED" -> {
				System.out.println("处理提交状态");
				yield "提交中";  // yield 关键字用于从 switch 表达式的一个分支中返回一个值
			}
			case "PENDING SUBMITTED", "PENDING APPROVED" -> {
				System.out.println("处理待定状态");
				yield "处理中";  // yield 关键字用于从 switch 表达式的一个分支中返回一个值
			}
			case "APPROVED" -> {
				System.out.println("处理已批准状态");
				yield "已完成";  // yield 关键字用于从 switch 表达式的一个分支中返回一个值
			}
			case "REJECTED" -> {
				System.out.println("处理已拒绝状态");
				yield "已拒绝";  // yield 关键字用于从 switch 表达式的一个分支中返回一个值
			}
			default -> "未知状态";
		};
		System.out.println("处理结果：" + result); // 输出：处理中



		// TODO Java Record
		Person1 person1 = new Person1("张三", 30);
		System.out.println(person1.name()); // 输出：张三
		System.out.println(person1);        // 输出：Person[name=张三, age=30]

		Person2 person2 = new Person2("张三", 20, "");
		System.out.println("Person2的阶段是：" + person2.era);  // 输出：少年

		List<String> list = new ArrayList<>();
		list.add("张三");
		Person3 person3 = new Person3(list);
		list.add("李四");
		System.out.println("Record的字段如果是可变对象（如List），虽然引用不可变，但对象内容可被修改。" +
				                   "Person3的成员数量是：" + person3.members().size()); // 输出：2

		// 解决可变对象作为字段的问题：方案一：在构造器中进行防御性拷贝
		List<String> list2 = new ArrayList<>();
		list2.add("张三");
		Person4 person4 = new Person4(list2);
		list2.add("李四");
		System.out.println("Person4的成员数量是：" + person4.members().size()); // 输出：1

		// 解决可变对象作为字段的问题：方案二：使用不可变集合（如List.copyOf()、Arrays.asList()）
		List<String> list3 = Arrays.asList("张三", "李四");
		Person3 person3_1 = new Person3(list3);
		list3.add("李四");
		System.out.println("由于定义的是不可变集合，因此在修改字段时，会通过报错方式终止操作！"); // 输出：1
	}

	// Java Record是Java 14引入的“语法糖”，专为简化数据类而生。自动生成构造方法、字段访问器（getter方法）、equals()、hashCode()和toString()，彻底告别样板代码！
	// Record 可以让我们更方便地定义类，它简化了代码，并提供了类似于数据传输对象（DTO）和值对象（VO）的功能
	record Person1(String name, int age) {
		// TODO 使用record关键字声明的类具有以下特性：
		//      1、不可变性：所有字段默认final，实例化后不可修改，这也意味着 Record 类不能有 setter 方法，线程安全无忧。
		//      2、简洁性：声明即定义，无需手动编写冗余代码。
		//      3、透明数据模型：自动生成的toString()和字段访问器（如user.name()）让数据一目了然。
	}

	record Person2(String name, int age, String era) {
		Person2 {
			if (age > 0 && age <= 10) {
				era = "小孩";
			} else if (age > 10 && age <= 20) {
				era = "少年";
			} else if (age > 20 && age <= 30) {
				era = "青年";
			} else {
				era = "中年";
			}
		}
	}

	// 可变对象作为字段
	record Person3(List<String> members) {
	}

	// 解决可变对象作为字段的问题：在构造器中进行防御性拷贝
	record Person4(List<String> members) {
		public Person4(List<String> members) {
			this.members = List.copyOf(members); // 构造时拷贝
		}
	}

}
