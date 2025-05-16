package com.example.movierental.ddd.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		this.rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	/**
	 * 根据顾客租聘的影片打印出顾客消费金额与积分
	 *
	 * @return
	 */
	public String statement() {
		double totalAmount = 0;
		String result = getName() + "的租聘记录 \n";
		for (Rental each : rentals) {
			double thisAmount = each.getCharge();
			result += "\t" + each.getMovie().getTitle() + " \t" + thisAmount + " \n";
			totalAmount += thisAmount;
		}
		int frequentRenterPoints = getFrequentRenterPoints(rentals);
		result += "租聘总价 ： " + totalAmount + "\n";
		result += "获得积分 ： " + frequentRenterPoints;
		return result;
	}

	/**
	 * 获取积分总额
	 *
	 * @param rentals
	 * @return
	 */
	private int getFrequentRenterPoints(List<Rental> rentals) {
		return rentals.stream()
		              .mapToInt(Rental::getPoint)
		              .sum();
	}
}
