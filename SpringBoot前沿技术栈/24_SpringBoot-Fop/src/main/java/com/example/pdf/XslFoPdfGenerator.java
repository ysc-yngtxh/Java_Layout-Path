package com.example.pdf;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FopConfParser;
import org.apache.fop.apps.FopFactoryBuilder;
import org.apache.fop.apps.MimeConstants;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.net.URI;

@Component
public class XslFoPdfGenerator {

    /**
     * 生成包含左图右文的PDF
     *
     * @param outputPath PDF输出路径（如：D:/demo.pdf）
     */
    public void generatePdf(String outputPath) throws Exception {
        // 1. 初始化FOP工厂（从 classpath 解压 fop.xconf 与 fonts 到临时目录，确保在可执行 jar 中也能找到字体）
        // FopFactory fopFactory = createFopFactoryFromClasspath();
        // URL url = XslFoPdfGenerator.class.getClassLoader().getResources("fop/fop.xml").nextElement();
        // FopFactory fopFactory = FopFactory.newInstance(url.toURI());

        URI uri = new ClassPathResource("").getURL().toURI();
        Path confTarget = new ClassPathResource("fop/fop.xml").getFilePath();
        FopFactory fopFactory = null;

        try (var confStream = Files.newInputStream(confTarget)) {
            FopConfParser parser = new FopConfParser(confStream, uri);
            FopFactoryBuilder builder = parser.getFopFactoryBuilder();
            fopFactory = builder.build();
        }

        // 2. 准备输出流
        try (OutputStream out = Files.newOutputStream(Paths.get(outputPath))) {
            // 3. 创建FOP实例（指定输出格式为PDF）
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

            // 4. 加载XSL模板并创建转换器
            StreamSource xsltSource = new StreamSource(
                    new ClassPathResource("templates/invoice-template.xsl").getInputStream()
            );
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(xsltSource);

            // 5. 加载XML数据源（示例用空XML，可替换为动态数据）
            StreamSource xmlSource = getXmlData();

            // 6. 执行转换生成PDF
            transformer.transform(xmlSource, new SAXResult(fop.getDefaultHandler()));
        }
    }

    public StreamSource getXmlData() throws Exception {
        // 直接从 classpath 输入流返回，避免在可执行 JAR 中使用 File API
        return new StreamSource(new ClassPathResource("data/empty.xml").getInputStream());
    }

}
