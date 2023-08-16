package com.example;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.pojo.WriteDemo1;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-16 00:43
 * @apiNote TODO
 */
@SpringBootTest
public class WriteExcelApplicationTests {

    private final Logger log = LoggerFactory.getLogger(WriteExcelApplicationTests.class);

    private List<WriteDemo1> data() {
        List<WriteDemo1> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            WriteDemo1 data = new WriteDemo1();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    // 简单写入一个 Excel
    // 注意 在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入
    @Test
    void writeExcel1(){
        String fileName = "/Users/youshicheng/IDEA/java-layout-path/SpringCloud以及前沿技术/23_EasyExcel/工作簿WriteDemo4.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板1 然后文件流会自动关闭
        EasyExcel.write(fileName, WriteDemo1.class)
                .sheet("模板1")
                .doWrite(data());
    }

    // 重复多次写入(写到单个或者多个Sheet)
    @Test
    public void repeatedWrite() {
        // 方法1: 如果写到同一个sheet
        String fileName = "/Users/youshicheng/IDEA/java-layout-path/SpringCloud以及前沿技术/23_EasyExcel/工作簿WriteDemo4.xlsx";
        // 这里 需要指定写用哪个class去写
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, WriteDemo1.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 5; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<WriteDemo1> data = data();
                excelWriter.write(data, writeSheet);
            }
        }

        // 方法2: 如果写到不同的sheet 同一个对象
        fileName = "/Users/youshicheng/IDEA/java-layout-path/SpringCloud以及前沿技术/23_EasyExcel/工作簿WriteDemo4.xlsx";
        // 这里 指定文件
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, WriteDemo1.class).build()) {
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
            for (int i = 0; i < 5; i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<WriteDemo1> data = data();
                excelWriter.write(data, writeSheet);
            }
        }

        // 方法3 如果写到不同的sheet 不同的对象
        fileName = "/Users/youshicheng/IDEA/java-layout-path/SpringCloud以及前沿技术/23_EasyExcel/工作簿WriteDemo4.xlsx";
        // 这里 指定文件
        try (ExcelWriter excelWriter = EasyExcel.write(fileName).build()) {
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
            for (int i = 0; i < 5; i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class
                // 实际上可以一直变
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).head(WriteDemo1.class).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<WriteDemo1 > data = data();
                excelWriter.write(data, writeSheet);
            }
        }

    }

    // 导出指定的列【excludeColumnFieldNames()排除列 ｜｜ includeColumnFieldNames()包含列】
    @Test
    void writeExcel2(){
        String fileName = "/Users/youshicheng/IDEA/java-layout-path/SpringCloud以及前沿技术/23_EasyExcel/工作簿WriteDemo4.xlsx";

        EasyExcel.write(fileName, WriteDemo1.class)
                .excludeColumnFieldNames(Set.of("date"))
                .sheet("模板2")
                .doWrite(data());
    }
}
