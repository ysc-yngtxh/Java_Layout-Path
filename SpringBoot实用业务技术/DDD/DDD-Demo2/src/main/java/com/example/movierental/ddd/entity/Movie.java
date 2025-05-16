package com.example.movierental.ddd.entity;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	private String title;
	private Integer priceCode;

	public Movie(String title, Integer priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	}

	public Integer getPriceCode() {
		return priceCode;
	}


	/**
	 * 默认租聘积一分，如果是新片且租聘大于1天，在加一分
	 *
	 * @return
	 */
	public int getPoint(int daysRented) {
		int point = 1;
		if (this.priceCode.equals(Movie.NEW_RELEASE)
				&& daysRented > 1
		) {
			point++;
		}
		return point;
	}

	/**
	 * 获取单个影片租聘的价格
	 * 1. 普通片 ，租聘起始价2元，如果租聘时间大于2天，每天增加1.5元
	 * 2. 新片 ，租聘价格等于租聘的天数
	 * 3. 儿童片 ，租聘起始价1.5元，如果租聘时间大于3天，每天增加1.5元
	 *
	 * @param daysRented
	 * @return
	 */
	public double getCharge(int daysRented) {
		double thisAmount = 0;
		switch (this.priceCode) {
			case REGULAR:
				thisAmount += 2;
				if (daysRented > 2) {
					thisAmount += (daysRented - 2) * 1.5;
				}
				break;
			case NEW_RELEASE:
				thisAmount += daysRented;
				break;
			case CHILDRENS:
				thisAmount += 1.5;
				if (daysRented > 3) {
					thisAmount += (daysRented - 3) * 1.5;
				}
				break;
			default:
				// nothings todo
				break;
		}
		return thisAmount;
	}
}
