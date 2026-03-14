<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:fox="http://xmlgraphics.apache.org/fop/extensions">

    <!-- 输出格式设置 -->
    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>

    <!-- ========================================= -->
    <!-- 动态参数声明                                -->
    <!-- 这些参数将从 Java 代码中传入                  -->
    <!-- ========================================= -->

    <!-- 文档标题 -->
    <xsl:param name="title" select="'默认发票'"/>

    <!-- 公司名称 -->
    <xsl:param name="companyName" select="data/companyName"/>

    <!-- Base64 编码的 Logo 图片数据 (不含 data:image/xxx;base64, 前缀) -->
    <xsl:param name="logoBase64" select="data/logoBase64"/>

    <!-- 是否显示 Logo -->
    <xsl:param name="showLogo" select="'true'"/>

    <!-- ========================================= -->
    <!-- 主模板                                      -->
    <!-- ========================================= -->
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format"
                 xmlns:fox="http://xmlgraphics.apache.org/fop/extensions"
                 font-family="SourceHanSans">

            <!-- 页面布局定义 -->
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4-portrait"
                                       page-height="29.7cm"
                                       page-width="21cm"
                                       margin-top="1.5cm"
                                       margin-bottom="1.5cm"
                                       margin-left="2cm"
                                       margin-right="2cm">
                    <fo:region-body margin-top="2.5cm" margin-bottom="1cm"/>
                    <fo:region-before extent="2cm" region-name="header"/>
                    <fo:region-after extent="1cm" region-name="footer"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <!-- 页面序列 -->
            <fo:page-sequence master-reference="A4-portrait"
                              initial-page-number="1"
                              format="1">

                <!-- 页眉 (显示公司名称和Logo) -->
                <fo:static-content flow-name="header">
                    <fo:block font-size="10pt"
                              font-family="SourceHanSans"
                              text-align="right"
                              border-bottom="1pt solid #999999"
                              padding-bottom="5pt">
                        <!-- 条件显示 Logo (使用动态参数) -->
                        <xsl:if test="$showLogo = 'true' and string-length($logoBase64) > 0">
                            <!-- 关键点: 使用 data URI 嵌入 Base64 图片 [citation:4][citation:7] -->
                            <fo:inline>
                                <fo:external-graphic
                                        src="{$logoBase64}"
                                        content-width="2cm"
                                        content-height="1cm"
                                        scaling="uniform"
                                        vertical-align="middle"
                                        space-before="52pt"/>
                            </fo:inline>
                        </xsl:if>
                        <xsl:if test="$showLogo != 'true' or string-length($logoBase64) = 0">
                            <fo:inline>
                                <xsl:value-of select="$companyName"/> -
                                <xsl:value-of select="$title"/>
                            </fo:inline>
                        </xsl:if>
                    </fo:block>
                </fo:static-content>

                <!-- 页脚 (显示页码) -->
                <fo:static-content flow-name="footer">
                    <fo:block text-align="center"
                              font-size="9pt"
                              font-family="SourceHanSans"
                              border-top="0.5pt solid #CCCCCC"
                              padding-top="3pt">
                        第
                        <fo:page-number/>
                        页
                    </fo:block>
                </fo:static-content>

                <!-- 页面主体内容 -->
                <fo:flow flow-name="xsl-region-body" border="1pt solid #000000">
                    <fo:block font-size="16pt"
                              font-weight="bold"
                              font-family="SourceHanSans"
                              text-align="center"
                              space-after="15pt">
                        <xsl:value-of select="$title"/>
                    </fo:block>

                    <!-- 从 XML 数据生成表格 (示例) -->
                    <fo:block font-size="12pt"
                              font-family="SourceHanSans"
                              space-after="10pt">
                        客户信息
                    </fo:block>

                    <fo:table table-layout="fixed" width="100%" border="1pt solid #000000">
                        <fo:table-column column-width="30%"/>
                        <fo:table-column column-width="70%"/>

                        <fo:table-body>
                            <xsl:for-each select="data/customer/*">
                                <fo:table-row>
                                    <fo:table-cell padding="3pt"
                                                   border="1pt solid #000000"
                                                   background-color="#F0F0F0">
                                        <fo:block font-weight="bold">
                                            <xsl:value-of select="local-name()"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="3pt" border="1pt solid #000000">
                                        <fo:block>
                                            <xsl:value-of select="."/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>

                    <!-- 商品明细表 -->
                    <fo:block font-size="12pt"
                              font-family="SourceHanSans"
                              space-before="20pt"
                              space-after="10pt">
                        商品明细
                    </fo:block>

                    <fo:table table-layout="fixed" width="100%" border="1pt solid #000000">
                        <fo:table-column column-width="40%"/>
                        <fo:table-column column-width="20%"/>
                        <fo:table-column column-width="20%"/>
                        <fo:table-column column-width="20%"/>

                        <!-- 表头 -->
                        <fo:table-header background-color="#E0E0E0">
                            <fo:table-row>
                                <fo:table-cell padding="5pt" border="1pt solid #000000">
                                    <fo:block font-weight="bold">商品名称</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="1pt solid #000000">
                                    <fo:block font-weight="bold">单价</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="1pt solid #000000">
                                    <fo:block font-weight="bold">数量</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="1pt solid #000000">
                                    <fo:block font-weight="bold">金额</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>

                        <!-- 数据行 -->
                        <fo:table-body>
                            <xsl:for-each select="data/items/item">
                                <fo:table-row>
                                    <fo:table-cell padding="3pt" border="1pt solid #000000">
                                        <fo:block>
                                            <xsl:value-of select="name"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="3pt" border="1pt solid #000000">
                                        <fo:block text-align="right">
                                            <xsl:value-of select="format-number(price, '¥ #,##0.00')"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="3pt" border="1pt solid #000000">
                                        <fo:block text-align="center">
                                            <xsl:value-of select="quantity"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="3pt" border="1pt solid #000000">
                                        <fo:block text-align="right">
                                            <xsl:value-of select="format-number(price * quantity, '¥ #,##0.00')"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>

                            <!-- 合计行 -->
                            <fo:table-row>
                                <fo:table-cell padding="5pt"
                                               border="1pt solid #000000"
                                               number-columns-spanned="3"
                                               text-align="right">
                                    <fo:block font-weight="bold">合计：</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="1pt solid #000000">
                                    <fo:block font-weight="bold" text-align="right">
                                        <xsl:variable name="total"
                                                      select="sum(/data/items/item/(number(price) * number(quantity)))"/>
                                        <xsl:value-of select="format-number($total, '¥ #,##0.00')"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <!-- 备注信息 (使用动态参数) -->
                    <fo:block font-size="10pt"
                              font-family="SourceHanSans"
                              font-style="italic"
                              space-before="20pt"
                              border-top="1pt dashed #CCCCCC"
                              padding-top="5pt">
                        备注: 本发票由 Spring Boot + FOP 生成
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
