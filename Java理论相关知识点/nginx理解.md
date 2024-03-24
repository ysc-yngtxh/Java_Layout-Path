
## 一、nginx作为Web服务器
       Web服务器分为两类：
          web应用服务器，如：
             tomcat
             resin
             jetty
          web服务器，如：
             Apache服务器
             Nginx
             IIS

       区分：1、WEB你可以简单理解为你所看到的HTML页面就是WEB的数据元素,处理这些数据元素的应用软件就叫WEB服务器,如apache、IIS。
               WEB服务器与客户端打交道，它要处理的主要信息有：session、request、response、HTML等
            2、应用服务器，如JSP，处理的是非常规性WEB页面（JSP文件），它动态生成WEB页面，生成的WEB页面在发送给客户端
             （实际上当应用服务器处理完一个JSP请求并完成JSP生成HTML后它的任务就结束了，其余的就是WEB处理的过程了）

       正向代理：我们平时需要访问国外的浏览器是不是很慢，比如我们要看推特，看GitHub等等。
                我们直接用国内的服务器无法访问国外的服务器，或者是访问很慢。
                所以我们需要在本地搭建一个服务器来帮助我们去访问。那这种就是正向代理。（浏览器中配置代理服务器）
       
       反向代理：那什么是反向代理呢。比如：我们访问淘宝的时候，淘宝内部肯定不是只有一台服务器，它的内部有很多台服务器，
                那我们进行访问的时候，因为服务器中间session不共享，那我们是不是在服务器之间访问需要频繁登录，
                那这个时候淘宝搭建一个过渡服务器，对我们是没有任何影响的，我们是登录一次，但是访问所有，这种情况就是 反向代理。
                对我们来说，客户端对代理是无感知的，客户端不需要任何配置就可以访问，我们只需要把请求发送给反向代理服务器，
                由反向代理服务器去选择目标服务器获取数据后，再返回给客户端，此时反向代理服务器和目标服务器对外就是一个服务器，
                暴露的是代理服务器地址，隐藏了真实服务器的地址。（在服务器中配置代理服务器）
 
       1、代理功能：
             通过客户机的配置，实现让一台服务器代理客户机，客户的所有请求都交给代理服务器处理
       2、反代理功能：
             用一台服务器，代理真实服务器，用户访问时，不再是访问真实服务器，而是代理服务器
       3、域名解析：
             我们在访问网站，就得去区分域名，用来准确无误地访问到我们所需要的网站地址。
             比如说，http://localhost:8080/api/item/id?pid=0 这里的 localhost:8080 (服务IP:端口号)就是我们的本机域名
             但是通常情况下，一个网站的域名是这样的 www.leyou.com 这么做是挺美观了，但是计算机却不管这么多，所以我们需要去解析这个域名。
             让计算机知道这个地址，从而能够成功访问到地址。
   
             像这种域名注册有两种请况：
                 1、本机注册：我这台电脑的域名映射IP是192.168.0.101，可以将我需要注册的域名和电脑映射ip进行绑定
                                    那么这样，就算不是在本机上访问，计算机也能够通过域名去锁定对应的电脑，进而访问。
                 2、运营商注册：原理类似，要钱。

## 二、nginx配置：
         conf文件-->nginx.conf中
             server{
                 listen       80;       # 监听的端口
                 server_name  manage.leyou.com;   # 监听的域名

                proxy_set_header X-Forwarded-Host $host;
                proxy_set_header X-Forwarded-Server $host;     # 三条都是头信息
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

                 #charset koi8-r;

                 #access_log  logs/host.access.log  main;

                 location / {   # 代表映射一切路径
                       proxy_pass http://192.168.0.101:9001;    # 主机地址下的9001端口
                       proxy_connect_timeout 600;       # 把监听到的请求转发到主机：192.168.0.101下的9001端口
                       proxy_read_timeout 600;
                 }
             }
             server{
                 listen       80;
                 server_name  api.leyou.com;

                 proxy_set_header X-Forwarded-Host $host;
                 proxy_set_header X-Forwarded-Server $host;
                 proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

                 #charset koi8-r;

                 #access_log  logs/host.access.log  main;

                 location / {
                       proxy_pass http://192.168.0.101:10010;    # 主机地址下的10010端口
                       proxy_connect_timeout 600;
                       proxy_read_timeout 600;
                 }
             }



