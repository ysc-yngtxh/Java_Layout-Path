package com.example.movierental.mvc.service;

import com.example.movierental.mvc.entity.Movie;
import com.example.movierental.mvc.entity.Rental;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO 顾客租赁影片信息
 */
public class Customer {

    // 顾客名称
    private String name;
    // 顾客租赁影片信息
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }

    /**
     * 根据顾客租聘的影片打印出顾客消费金额与积分
     *
     * @return
     */
    public String statement() {
        double totalAmount = 0;
        String result = this.name + "的租聘记录 \n";
        for (Rental each : rentals) {
            double thisAmount = getAmount(each);
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
                .mapToInt(rental -> {
                    // 默认租聘积一分，如果是 Movie.NEW_RELEASE 且租聘大于1天，在加一分
                    int point = 1;
                    if (rental.getMovie().getPriceCode().equals(Movie.NEW_RELEASE) && rental.getDaysRented() > 1) {
                        point++;
                    }
                    return point;
                })
                .sum();
    }

    /**
     * 获取单个影片租聘的价格
     * 1. 普通片 ，租聘起始价2元，如果租聘时间大于2天，每天增加1.5元
     * 2. 新片 ，租聘价格等于租聘的天数
     * 3. 儿童片 ，租聘起始价1.5元，如果租聘时间大于3天，每天增加1.5元
     *
     * @param rental
     * @return
     */
    private double getAmount(Rental rental) {
        double thisAmount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2) {
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented();
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3) {
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                }
                break;
            default:
                // nothings TODO
                break;
        }
        return thisAmount;
    }

}