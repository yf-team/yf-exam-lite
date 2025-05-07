package com.yf.exam.config.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

import java.io.IOException;

/**
 * XSS过滤器，但保留一些常用的HTML标签
 * @author van
 */
public class XssEscapeJsonDeserializer extends JsonDeserializer<String> {

    private static final PolicyFactory POLICY = new HtmlPolicyBuilder()
            // 允许的标签
            .allowElements("a", "p", "div", "span", "font")
            // 允许a标签的href和title
            .allowAttributes("href", "title").onElements("a")
            // 允许font标签的color属性
            .allowAttributes("color").onElements("font")
            // 允许的URL协议
            .allowUrlProtocols("http", "https")
            // 自动添加 rel="nofollow" 增强安全
            .requireRelNofollowOnLinks()
            .toFactory();

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // 获取原始字符串
        String value = p.getText();
        // 转义HTML特殊字符
        return POLICY.sanitize(value);
    }
}