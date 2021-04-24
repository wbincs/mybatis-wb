package com.wb.mybatis.builder.xml;

import com.wb.mybatis.session.Configuration;
import com.wb.mybatis.session.JDBCProperties;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author wubin
 * @Date 2021/4/3 23:54
 * @Version 1.0
 */
public class XMLConfigBuilder {
    // 配置类
    private final Configuration configuration;
    // 配置文件是否被解析过
    private boolean parsed;
    // xml输入流
    private InputStream inputStream;
    // xml解析器
    private SAXReader reader;

    public XMLConfigBuilder(InputStream inputStream) {
        this.configuration = new Configuration();
        this.inputStream = inputStream;
        this.parsed = false;
        reader = new SAXReader();
    }

    public Configuration parse() {
        if (parsed) {
            throw new RuntimeException("Each XMLConfigBuilder can only be used once.");
        }
        parsed = true;
        // 先解析configuration
        parseConfiguration();
        return configuration;
    }

    private void parseConfiguration() {
        try {
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            //读取jdbc的配置文件，然后加载jdbc配置信息
            propertiesElement(root.element("properties"));
            //mapper的读取
            mapperElement(root.element("mappers"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载配置信息
     * @param propertiesEle
     */
    private void propertiesElement(Element propertiesEle) {
        String jdbcPropertiesPath = propertiesEle.attributeValue("resource");
        InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream(jdbcPropertiesPath);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JDBCProperties JDBCProperties = new JDBCProperties();
        JDBCProperties.setUrl(properties.getProperty("url"));
        JDBCProperties.setDriver(properties.getProperty("driver"));
        JDBCProperties.setPassword(properties.getProperty("password"));
        JDBCProperties.setUsername(properties.getProperty("username"));
        configuration.setJDBCProperties(JDBCProperties);
    }

    /**
     * 加载Mapper信息
     * @param mappersEle
     */
    private void mapperElement(Element mappersEle) {
        for(Element ele : mappersEle.elements("mapper")) {
            // mapperPath mapper接口的路径
            String resource = ele.attributeValue("resource");
            XMLMapperBuilder mapperParser = new XMLMapperBuilder(configuration, resource);
            mapperParser.parse();
        }
    }

}
