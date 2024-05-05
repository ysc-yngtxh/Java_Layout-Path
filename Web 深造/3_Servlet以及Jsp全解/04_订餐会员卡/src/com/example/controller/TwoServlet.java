package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TwoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int jiaozi_money = 30;
        int gaifan_money = 15;
        int miantiao_money = 20;
        int money = 0,xiaofei=0,balance=0;
        String food,userName = null;
        Cookie cookieArray[] = null;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Cookie newCard = null;
        //1、读取请求头参数信息，得到用户点餐食物类型
        food = request.getParameter("food");
        //2、读取请求中Cookie
        cookieArray = request.getCookies();
        //3、刷卡消费
        for(Cookie card:cookieArray){
            String key = card.getName();
            String value = card.getValue();
            if("userName".equals(key)){
                userName = value;
            } else if("money".equals(key)){
                money = Integer.valueOf(value);
                if("饺子".equals(food)){
                    if(jiaozi_money > money){
                        out.print("用户"+userName+"余额不足，请充值");
                    } else{
                        newCard = new Cookie("money",(money-jiaozi_money)+"");
                        xiaofei = jiaozi_money;
                        balance = money-jiaozi_money;
                    }
                } else if("面条".equals(food)){
                    if(miantiao_money > money){
                        out.print("用户"+userName+"余额不足，请充值");
                    } else{
                        newCard = new Cookie("money",(money-miantiao_money)+"");
                        xiaofei = miantiao_money;
                        balance = money-miantiao_money;
                    }
                } else if("盖饭".equals(food)){
                    if(gaifan_money > money){
                        out.print("用户"+userName+"余额不足，请充值");
                    } else{
                        newCard = new Cookie("money",(money-gaifan_money)+"");
                        xiaofei = gaifan_money;
                        balance = money-gaifan_money;
                    }
                }
            }
        }
        //4、将用户会员卡返还给用户
        response.addCookie(newCard);
        //5、将消费记录写入到响应
        out.print("用户"+userName+"本次消费"+xiaofei+"余额"+balance);
    }
}
