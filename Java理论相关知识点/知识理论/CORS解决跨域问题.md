
## 一、跨域，指的是浏览器不能执行其他网站的脚本。它是由浏览器的同源策略造成的，是浏览器施加的安全限制。
    所谓同源是指，域名，协议，端口均相同，只要有一个不同，就是跨域。不明白没关系，举个栗子：
        http://www.123.com/index.html 调用 http://www.123.com/server.php （非跨域）
        http://www.123.com/index.html 调用 http://www.456.com/server.php （主域名不同:123/456，跨域）
        http://abc.123.com/index.html 调用 http://def.123.com/server.php （子域名不同:abc/def，跨域）
        http://www.123.com:8080/index.html 调用 http://www.123.com:8081/server.php （端口不同:8080/8081，跨域）
        http://www.123.com/index.html 调用 https://www.123.com/server.php （协议不同:http/https，跨域）
        请注意：localhost和127.0.0.1虽然都指向本机，但也属于跨域。
        浏览器执行javascript脚本时，会检查这个脚本属于哪个页面，如果不是同源页面，就不会被执行。

## 二、跨域会阻止什么操作？
    浏览器是从两个方面去做这个同源策略的，一是针对接口的请求，二是针对Dom的查询
    1.阻止接口请求比较好理解，比如用ajax从http://192.168.100.150:8020/实验/jsonp.html页面
      向http://192.168.100.150:8081/zhxZone/webmana/dict/jsonp发起请求，由于两个url端口不同，所以属于跨域，
      在console打印台会报No 'Access-Control-Allow-Origin' header is present on the requested resource

     值得说的是虽然浏览器禁止用户对请求返回数据的显示和操作，但浏览器确实是去请求了，
     如果服务器没有做限制的话会返回数据的，在调试模式的network中可以看到返回状态为200，且可看到返回数据

    2.阻止dom获取和操作
         关于iframe中对象的获取方式请看：https://blog.csdn.net/lianzhang861/article/details/84870484
      比如a页面中嵌入了iframe，src为不同源的b页面，则在a中无法操作b中的dom，也没有办法改变b中dom中的css样式。
      而如果ab是同源的话是可以获取并操作的。改变了iframe中的元素，甚至是可以获取iframe中的cookie
          var i=document.getElementById("iframe");
          i.onload=function(){
          	console.log(i.contentDocument.cookie)
          }
      不用说也知道这是极为危险的，所以浏览器才会阻止非同源操作dom。浏览器的这个限制虽然不能保证完全安全，但是会增加攻击的困难性
      虽然安全机制挺好，可以抵御坏人入侵，但有时我们自己需要跨域请求接口数据或者操作自己的dom，也被浏览器阻止了，所以就需要跨域
      跨域的前提肯定是你和服务器是一伙的，你可以控制服务器返回的数据，否则跨域是无法完成的

## 三、CORS介绍
    1、CORS是一个W3C标准，全称是“跨域资源共享”。
       它允许浏览器想跨域服务器，发出XMLHttpRequest请求，从而克服了Ajax只能同源使用的限制。
       CORS需要浏览器和服务器同时支持。目前，所有浏览器都支持该功能，IE浏览器不能低于IE10
    2、举个栗子：我在http://manage.leyou.com浏览器上发起一个请求服务，但是由于我的前后端是分离的，又有各种微服务。
               所以我发起请求服务的地址的域名、端口都不相同。
               比如请求的是商品查询服务，那么请求路径就是http://api.leyou.com/api/category?pid=
               (这里的域名变为api.leyou.com是因为我在nginx中设置了对应端口10010--ZULL)
               域名和端口都不相同，就被浏览器当作是跨域请求，浏览器为了保护用户信息，也就不会执行Ajax请求。
    3、CORS具体是怎么操作的呢？
       ①、简单请求：(满足条件一、请求方法：HEAD,GET,POST  二、HTTP头信息不超出以下几种字段：Accept,Accept-Language,Content-Language,Last-Event-ID,Content-Type)
          比如说在http://manage.leyou.com中发起一个简单的Ajax请求，浏览器接收到了，
          它会在Request Headers中加入一个头信息Origin，在这个Origin中会指出当前请求属于哪个域（协议+域名+端口）--http://manage.leyou.com。
          服务器端的服务会根据这个值决定是否允许其跨域。如果服务器允许跨域，需要在返回的响应头中携带下面信息
          Access-Control-Allow-Origin: http://manage.leyou.com。可接受的域，是一个具体域名或者*
          Access-Control-Allow-Credentials: true  允许你访问Cookie
       ②、复杂请求：
          不符合简单请求的条件，会被浏览器判定为特殊请求，例如请求方式为PUT.
          特殊请求会在正式通信之前，增加一次HTTP查询请求，成为“预检”请求(preflight).
          浏览器先询问服务器，当前网页所在的域名是否在服务器的许可名单之中，以及可以使用那些HTTP动词和头信息字段。
          只有得到肯定答复，浏览器才会发出正式的XMLHttpRequest请求，否则就报错。
       ③、操作：
          想一想一个服务器要去判断Request Headers中头信息Origin的值，最好是在哪儿做判断？
          是不是应该在网关里？做一个过滤处理？你思考了吗？