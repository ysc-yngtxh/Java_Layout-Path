package com.example.handler;

import com.example.exception.custom.AgeException;
import com.example.exception.custom.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /** 处理异常的方法和控制器方法的定义一样，可以有多个参数，可以有ModelAndView、String、void、对象类型的返回值
     *  形参：Exception，表示Controller中抛出的异常对象。通过形参可以获取发生的异常信息
     *  @ExceptionHandler(异常的class)：表示异常的类型，当发生此类型异常时，由当前方法处理
     */
    @ExceptionHandler(value= NameException.class)
    public ModelAndView doNameException(Exception exception) {
        /** 处理NameException的异常
         *  异常发生处理逻辑
         *    1、需要把异常记录下来，记录到数据库，日志文件。
         *      记录日志发生的事件，哪个方法发生的，异常错误内容
         *    2、发送通知，把异常的信息通过邮件，短信，微信发送给相关人员
         *    3、给用户友好的提示
         */
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "姓名必须是zs,其他用户不能访问");
        mv.addObject("e", exception);
        mv.setViewName("nameError");
        return mv;
    }

    @ExceptionHandler(value= AgeException.class)
    public ModelAndView doAgeException(Exception exception) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "您的年龄不能大于80");
        mv.addObject("e", exception);
        mv.setViewName("ageError");
        return mv;
    }

    // 处理其他异常（NameException、AgeException以外的不知类型的异常）
    // @ExceptionHandler
    // public ModelAndView doOtherException(Exception exception) {
    //     ModelAndView mv = new ModelAndView();
    //     mv.addObject("msg", "您的年龄不能大于80");
    //     mv.addObject("e", exception);
    //     mv.setViewName("defaultError");
    //     return mv;
    // }
}
