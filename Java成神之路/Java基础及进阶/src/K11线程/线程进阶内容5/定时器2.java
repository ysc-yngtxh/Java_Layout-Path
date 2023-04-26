package K11线程.线程进阶内容5;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
定时器：
    定时器作用：
        间隔特定的时间，执行特定的程序。
        每周要进行银行账户的总帐操作。
        每天要进行数据的备份操作。
        在实际的开发中，每隔多久执行一段特定的程序，这种需求是很常见的，那么在Java中其实可以采用多种方式实现：
            可以使用sleep方法，睡眠，设置睡眠时间，每到这个时间点醒来，执行任务。这种方式是最原始的定时器。（比较low）

            在Java的类库中已经写好了一个定时器：java.util.Timer,可以直接拿来用。
            不过，这种方式在目前的开发中也很少见，因为现在有很多高级框架都是支持定时任务的。

            在实际的开发中，目前使用较多的是Spring框架中提供的SpringTask框架，这个框架只要进行简单的配置，就可以完成定时器的任务。
 */
public class 定时器2 {
    public static void main(String[] args) throws ParseException {

        //创建定时器对象
        Timer timer = new Timer();
        //Timer timer = new Timer(true);  //守护线程的方式

        //指定定时任务
        //timer.schedule(定时任务,开始时间,间隔多久执行时间);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS");
        Date firstTime = sdf.parse("2021-7-08 16:57:00 00");//使用parse方法将日期字符串String,转换成Date类型.
        timer.schedule(new LongTimeTask(),firstTime,1000*10);
    }
}

//编写一个定时任务类
class LongTimeTask extends TimerTask {


    //一个定时的日志
    @Override
    public void run() {
        try{
            PrintStream out = new PrintStream(new FileOutputStream("log2",true));
            //改变输出方向，不再是控制台输出
            System.setOut(out);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS");
            String strTime = sdf.format(new Date());    //使用format方法将Date类型转换成日期字符串String
            System.out.println(strTime + ":成功完成了一次数据备份！");
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}