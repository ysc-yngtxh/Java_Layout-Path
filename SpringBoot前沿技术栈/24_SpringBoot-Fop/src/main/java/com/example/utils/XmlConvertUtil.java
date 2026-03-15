package com.example.utils;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;

/**
 * 作者 游家纨绔
 * 日期 2026-03-12 17:00
 * 说明 将 fastjson2 的 JSONObject/JSONArray 转为 XML 字符串（可用于 FOP 的 XMLContent）
 */
public class XmlConvertUtil {

    /**
     * 将 JSONObject 转为 XML 字符串
     * @param json 原始 JSONObject
     * @param rootName 根节点名称，若为 null 或空则使用 "root"
     * @return 格式化的 XML 字符串（UTF-8）
     * @throws Exception 解析或转换异常向上抛出
     */
    public static String toXml(JSONObject json, String rootName) throws Exception {
        if (json == null) {
            throw new IllegalArgumentException("json must not be null");
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        String root = (rootName == null || rootName.isEmpty()) ? "root" : rootName;
        Element rootElement = doc.createElement(root);
        doc.appendChild(rootElement);

        appendJsonToElement(json, doc, rootElement);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // 可选：设置缩进量（部分 Transformer 实现支持）
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        StringWriter sw = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(sw));
        return sw.toString();
    }

    private static void appendJsonToElement(Object node, Document doc, Element parent) {
        if (node instanceof JSONObject) {
            JSONObject json = (JSONObject) node;
            for (String key : json.keySet()) {
                Object value = json.get(key);
                if (value == null) {
                    Element child = doc.createElement(key);
                    parent.appendChild(child);
                } else if (value instanceof JSONObject) {
                    Element child = doc.createElement(key);
                    parent.appendChild(child);
                    appendJsonToElement(value, doc, child);
                } else if (value instanceof JSONArray) {
                    JSONArray arr = (JSONArray) value;
                    for (int i = 0; i < arr.size(); i++) {
                        Object item = arr.get(i);
                        Element child = doc.createElement(key);
                        parent.appendChild(child);
                        if (item instanceof JSONObject || item instanceof JSONArray) {
                            appendJsonToElement(item, doc, child);
                        } else if (item != null) {
                            child.setTextContent(String.valueOf(item));
                        }
                    }
                } else {
                    Element child = doc.createElement(key);
                    child.setTextContent(String.valueOf(value));
                    parent.appendChild(child);
                }
            }
        } else if (node instanceof JSONArray) {
            JSONArray arr = (JSONArray) node;
            for (int i = 0; i < arr.size(); i++) {
                Object item = arr.get(i);
                Element child = doc.createElement("item");
                parent.appendChild(child);
                if (item instanceof JSONObject || item instanceof JSONArray) {
                    appendJsonToElement(item, doc, child);
                } else if (item != null) {
                    child.setTextContent(String.valueOf(item));
                }
            }
        } else if (node != null) {
            parent.setTextContent(String.valueOf(node));
        }

    }
}
