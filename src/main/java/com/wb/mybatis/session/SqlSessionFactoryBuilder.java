package com.wb.mybatis.session;

import com.wb.mybatis.builder.xml.XMLConfigBuilder;
import com.wb.mybatis.mapping.MappedStatement;
import com.wb.mybatis.session.defaults.DefaultSqlSessionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @Author wubin
 * @Date 2021/4/2 20:02
 * @Version 1.0
 * SqlSessionFactoryBuilder主要是读取配置文件信息，进行构造SqlSessionFactory。
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) {
        try {
            XMLConfigBuilder parser = new XMLConfigBuilder(inputStream);
            return build(parser.parse());
        } catch (Exception e) {
            System.err.println("解析配置文件出错，请检查配置文件格式是否正确");
            throw new RuntimeException("Error building SqlSession.", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                // Intentionally ignore. Prefer previous error.
            }
        }
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
