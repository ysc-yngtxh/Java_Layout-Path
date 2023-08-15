package com.example;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.example.listener.DemoDataListener;
import com.example.pojo.Demo1;
import com.example.pojo.Demo2;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
class ReadExcelApplicationTests {

    private final Logger log = LoggerFactory.getLogger(ReadExcelApplicationTests.class);

    // TODO 默认一行行的读取excel，所以需要创建excel一行一行的回调监听器

    // 当 Excel 文件中的表头可以与实体类相对应时，直接读取。
    // 且此时读取一张表格----sheet()【默认为第一张表格】； 还可加上参数表示读取指定的表格数据----sheet(1)【表示读取第二张表格】
    @Test
    void readExcel1() {
        File file = new File("/Users/youshicheng/IDEA/java-layout-path/SpringCloud以及前沿技术/23_EasyExcel/工作簿demo1.xlsx");
        EasyExcel.read(
                file
                , Demo1.class
                // ExcelReadListener监听器 不能被spring管理，要每次读取excel都要new.为什么呢？仔细想想高并发的情况下
                // PageReadListener是官方提供的继承ReadListener的监听器，一般我们是在监听器中进行解析数据处理数据
                , new PageReadListener<Demo1>(dataList -> {
                    for (Demo1 demo1Data : dataList) {
                        log.info("读取到一条数据{}", JSON.toJSONString(demo1Data));
                    }
                })).sheet(1).doRead();
    }

    // 当 Excel 文件中的表头不与实体类中的字段属性相对应，则需要通过加在字段上的注解 @ExcelProperty 进行映射。
    // 且此时由于没有加上sheet()方法，表示读取全部表格数据
    @Test
    void readExcel2() {
        File file = new File("/Users/youshicheng/IDEA/java-layout-path/SpringCloud以及前沿技术/23_EasyExcel/工作簿demo2.xlsx");
        EasyExcel.read(
                file
                , Demo2.class
                , new PageReadListener<Demo2>(dataList -> {
                    for (Demo2 demo2Data : dataList) {
                        log.info("读取到一条数据{}", JSON.toJSONString(demo2Data));
                    }
                })).doReadAll();
        // TODO 这里打印结果你会发现数据重复了，并不是什么 bug，而是官方提供的监听器逻辑会使多表格 Excel 在每一张表格解析完都会去执行doAfterAllAnalysed()方法
    }

    // 使用匿名内部类监听器来对数据解析和读取，重写其方法逻辑。且此时由于没有加上sheet()方法，表示读取全部表格数据
    @Test
    void readExcel3() {
    	String fileString = "/Users/youshicheng/IDEA/java-layout-path/SpringCloud以及前沿技术/23_EasyExcel/工作簿demo2.xlsx";
    	EasyExcel.read(fileString, Demo2.class, new ReadListener<Demo2>(){
            // 单次缓存的数据量
            public static final int BATCH_COUNT = 100;
            // 临时存储
            private List<Demo2> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
            // 这个方法,每一条数据解析都会来调用
            @Override
            public void invoke(Demo2 data, AnalysisContext context) {
                log.info("正在读取sheet:[{}]的数据:{}", context.readSheetHolder().getSheetName(), JSON.toJSONString(data));
                cachedDataList.add(data);
                if (cachedDataList.size() <= BATCH_COUNT) {
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }
            // 这个方法,所有数据解析完成了 会来调用
            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                log.info("sheet:[{}]读取完成!", context.readSheetHolder().getSheetName());
            }
        }).doReadAll();
    }

    // 当存在多表格 Excel ，却只想解析需要的表格时(比如：sheet1、sheet2、sheet3；我只想解析sheet1、sheet2)
    @Test
    void readExcel4() {
        File fileName = new File("/Users/youshicheng/IDEA/java-layout-path/SpringCloud以及前沿技术/23_EasyExcel/工作簿demo2.xlsx");
        try ( ExcelReader excelReader = EasyExcel.read(fileName).build() ) {
            // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
            ReadSheet readSheet1 =
                    EasyExcel.readSheet(0).head(Demo2.class).registerReadListener(new DemoDataListener()).build();
            ReadSheet readSheet2 =
                    EasyExcel.readSheet(1).head(Demo2.class).registerReadListener(new DemoDataListener()).build();
            // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            excelReader.read(readSheet1, readSheet2);
        }
    }

    // 需要读取批注、合并单元格、超链接
    @Test
    public void extraRead() {
        File fileName = new File("/Users/youshicheng/IDEA/java-layout-path/SpringCloud以及前沿技术/23_EasyExcel/工作簿demo2.xlsx");
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(fileName, Demo2.class, new DemoDataListener())
                // 需要读取批注 默认不读取
                .extraRead(CellExtraTypeEnum.COMMENT)
                // 需要读取超链接 默认不读取
                .extraRead(CellExtraTypeEnum.HYPERLINK)
                // 需要读取合并单元格信息 默认不读取
                .extraRead(CellExtraTypeEnum.MERGE).sheet().doRead();
    }
}
