package com.example.pdf;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class XslFoPdfGenerator {

    /**
     * 生成包含左图右文的PDF
     * @param outputPath PDF输出路径（如：D:/demo.pdf）
     */
    public void generatePdf(String outputPath) throws Exception {
        // 1. 初始化FOP工厂
        FopFactory fopFactory = FopFactory.newInstance(
            new ClassPathResource("/").getFile().toURI()
        );

        // 2. 准备输出流
        try (OutputStream out = Files.newOutputStream(Paths.get(outputPath))) {
            // 3. 创建FOP实例（指定输出格式为PDF）
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

            // 4. 加载XSL模板并创建转换器
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            StreamSource xsltSource = new StreamSource(
                new ClassPathResource("templates/left-img-right-text.xsl").getFile()
            );
            Transformer transformer = transformerFactory.newTransformer(xsltSource);

            // 5. 加载XML数据源（示例用空XML，可替换为动态数据）
            StreamSource xmlSource = new StreamSource(
                new ClassPathResource("data/empty.xml").getFile()
            );

            // 6. 执行转换生成PDF
            transformer.transform(xmlSource, new SAXResult(fop.getDefaultHandler()));
        }
    }
}
