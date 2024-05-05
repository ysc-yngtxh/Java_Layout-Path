## 1、IO流，什么是IO？
> #### &emsp;&emsp;&emsp;&emsp;I：Input  
> #### &emsp;&emsp;&emsp;&emsp;O：Output  
> #### &emsp;&emsp;&emsp;&emsp;通过IO可以完成硬盘文件的读和写
## 2、IO流的分类
      一种方式是按照流的方向进行分类：
          以内存作为参照物，
            往内存中去，叫做输入（Input）.或者叫做读（Read）.
            从内存中出来，叫做输出（Output）,或者叫做写（Write）

      另一种方式是按照读取数据方式不同进行分类：
          有的流是按照字节的方式读取数据，一次读取一个字节byte，等同于一次读取8个二进制位，这种流是万能的，什么类型的文件都可以读取。
          包括：文本文件，图片，声音文件，视频文件，等等

          有的流是按照字符的方式读取数据的，一次读取一个字符，这种流是为了方便读取普通文本文件而存在的，这种流不能读取：图片，声音，
          视频等文件。只能读取纯文本文件，连word文件都无法读取。
               假设文件file.txt,采用字符流的话是这样读的：
                    a中国bc张三fe
                    第一次读：'a'字符('a'字符在Windows系统中占用1个字节)
                    第二次读：'中'字符('中'字符在Windows系统中占用2个字节)
     综上所述：流的分类
          输入流，输出流
          字节流，字符流

## 3、Java中的IO流都已经写好了，我们程序员不需要关心，我们最主要是掌握，
    Java中所有的流都在：java.io.*,下
        Java中主要还是研究：
                        怎么new流对象
                        调用流对象的哪个方法是读，哪个方法是写
            
## 4、java IO流这块有四大家族：
> 四大家族的首领：  
> &emsp;&emsp;&emsp;&emsp;java.io.InputStream  字节输入流  
> &emsp;&emsp;&emsp;&emsp;java.io.OutputStream 字节输出流  
> &emsp;&emsp;&emsp;&emsp;java.io.Reader  字符输入流  
> &emsp;&emsp;&emsp;&emsp;java.io.Writer  字符输出流

        四大家族的首领都是抽象类。(abstract class)

        所有的流都实现了：
            java.io.Closeable接口，都是可关闭的，都有close()方法。
            流毕竟是一个管道，这个是内存和硬盘之间的通道，用完之后一定要关闭，
            不然会耗费/占用很多资源。养成好习惯，用完流一定要关闭
        所有的输出流都实现了：
             java.io.Flushable接口，都是可刷新的，都有flush()方法。
             养成一个好习惯，输出流在最终输出之后，一定要记得flush()
             刷新一下。这个刷新表示将通道/管道当中剩余未输出的数据
             强行输出完(清空管道！)刷新的作用就是清空管道
             注意：如果没有flush()可能会导致丢失数据

- ***注意：在java中只要"类名"以stream结尾的都是字节流，以"Reader/Writer" 结尾的都是字符流***

## 5、java.io包下需要掌握的流有16个

    文件专属：
    字节流：
    java.io.FileInputStream
    java.io.FileOutputSteam
    字符流：
    java.io.FileReader
    java.io.FileWriter
    
    转换流（将字节流转换成字符流）
    java.io.InputStreamReader
    java.io.OutputStreamWriter
    
    缓冲流专属:
    java.io.BufferedReader
    java.io.BufferedWriter
    java.io.BufferedInputStream
    java.io.BufferedOutputStream
    
    数据流专属：
    java.io.DataInputStream
    java.io.DataOutputStream
    
    标准输出流：
    java.io.PrintWriter
    java.io.PrintStream
    
    对象专属流：
    java.io.ObjectInputStream
    java.io.ObjectOutputStream