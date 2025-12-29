<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <!-- 根模板：定义PDF文档结构 -->
    <xsl:template match="/">
        <fo:root>
            <!-- 页面布局配置（A4纸） -->
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4"
                                       page-width="210mm"
                                       page-height="297mm"
                                       margin="20mm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <!-- 页面内容 -->
            <fo:page-sequence master-reference="A4">
                <fo:flow flow-name="xsl-region-body">
                    <!-- 核心：表格实现左图右文 -->
                    <fo:table table-layout="fixed" width="100%" border="0">
                        <!-- 第一列（图片）：固定宽度50mm -->
                        <fo:table-column column-width="50mm"/>
                        <!-- 第二列（文字）：自适应剩余宽度 -->
                        <fo:table-column column-width="proportional-column-width(1)"/>

                        <fo:table-body>
                            <fo:table-row>
                                <!-- 图片单元格：垂直居中 -->
                                <fo:table-cell vertical-align="middle" text-align="center">
                                    <fo:block>
                                        <!-- 引入图片：支持classpath/绝对路径 -->
                                        <fo:external-graphic
                                                src="url('classpath:static/logo.png')"
                                                content-type="image/png"
                                                width="40mm" height="30mm"
                                                /> <!-- 保持图片比例 -->
                                    </fo:block>
                                </fo:table-cell>

                                <!-- 文字单元格：垂直居中 + 左内边距 -->
                                <!-- ✅ 核心：配置中文字体 font-family + 保留原有样式 -->
                                <fo:table-cell vertical-align="middle" padding-left="10mm" padding="0">
                                    <fo:block font-size="16pt" font-weight="bold"
                                              font-family="SimSun,Microsoft YaHei,STSong,Helvetica-Bold">
                                        企业名称：XX科技有限公司
                                    </fo:block>
                                    <fo:block font-size="12pt" color="#666" margin-top="5mm"
                                              font-family="SimSun,Microsoft YaHei,STSong,Helvetica">
                                        地址：北京市海淀区中关村科技园 | 电话：010-12345678
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>
