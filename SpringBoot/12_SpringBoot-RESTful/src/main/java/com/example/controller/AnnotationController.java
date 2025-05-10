package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 */
// @Controller
@RestController   // 这个注解相当于@Controller和@ResponseBody
public class AnnotationController {

    @RequestMapping(value="/student",method={RequestMethod.GET,RequestMethod.POST})
    // @ResponseBody
    public Object query() {
        return "啊。有意思~";
    }

    // @RequestMapping(value="/student1", method=RequestMethod.GET)
    @GetMapping(value="/student1")  // 相当于上面一句话，是查Get请求
    public Object query1() {
        return "这小妮子，以后削她";
    }

    // @RequestMapping(value="/student2", method=RequestMethod.POST)
    @PostMapping(value="/student2")  // 相当于上面一句话，是增Post请求
    public Object query2() {
        return "他妈的，气死我了。削，狠狠的削！";
    }

    /* RESTful 是一种基于 HTTP 协议 的软件架构风格，全称是 Representational State Transfer（表述性状态转移）。
     * 它强调以 资源（Resource） 为核心，通过 HTTP 方法（GET、POST、PUT、DELETE 等）对资源进行操作，并使用 统一资源标识符（URI） 定位资源。
     *
     * HTTP动作：
     *     GET(SELECT):    从服务器取出资源（一项或者多项）
     *     POST(CREATE):   在服务器新建一个资源
     *     PUT(UPDATE):    在服务器更新资源（客户端提供改变后的完整资源）。PUT是更新整个对象。
     *     PATCH(UPDATE):  在服务器更新资源（客户端提供改变的属性[补丁]）。PATCH更新对象中的个别属性。
     *     DELETE(DELETE): 从服务器删除资源。
     *     HEAD:           获得一个资源的元数据，如某个资源的hash值或者最后修改日期
     *     OPTIONS:        获得客户端对一个资源能够实施的操作，即获取该资源的api能够对资源做什么操作的描述。
     *
     * 访问的HTTP状态码：
     *     1xx 相关信息
     *     2xx 操作成功
     *     3xx 重定向
     *     4xx 客户端错误
     *     5xx 服务器错误
     */
}
