package com.wb.mybatis.builder.xml;

import com.wb.mybatis.mapping.MappedStatement;
import com.wb.mybatis.mapping.SqlCommandType;
import com.wb.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Map;

/**
 * @Author wubin
 * @Date 2021/4/4 21:39
 * @Version 1.0
 */
public class XMLMapperBuilder {

    private final Configuration configuration;
    // mapper.xml地址
    private final String resource;
    private final SAXReader reader;

    public XMLMapperBuilder(Configuration configuration, String resource) {
        this.configuration = configuration;
        this.resource = resource;
        reader = new SAXReader();
    }

    public void parse() {
        configurationElement();
    }

    private void configurationElement() {
        try {
            InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream(resource);
            Document document = reader.read(inputStream);
            Element rootEle = document.getRootElement();
            //获取命名空间，也就是Mapper类的全类名
            String namespace = rootEle.attributeValue("namespace");

            for(Element ele : rootEle.elements()) {
                MappedStatement mappedStatement = new MappedStatement(configuration, resource);
                String eleName = ele.getName();
                if (SqlCommandType.SELECT.value().equals(eleName)) {
                    // select
                    mappedStatement.setResultType(ele.attributeValue("resultType"));
                    mappedStatement.setSqlCommandType(SqlCommandType.SELECT);
                } else if (SqlCommandType.UPDATE.value().equals(eleName)) {
                    // update
                    mappedStatement.setSqlCommandType(SqlCommandType.UPDATE);
                } else if (SqlCommandType.INSERT.value().equals(eleName)) {
                    // insert
                    mappedStatement.setSqlCommandType(SqlCommandType.INSERT);
                } else if (SqlCommandType.DELETE.value().equals(eleName)) {
                    // delete
                    mappedStatement.setSqlCommandType(SqlCommandType.DELETE);
                } else {
                    System.err.println("无法解析" + eleName + "标签");
                    mappedStatement.setSqlCommandType(SqlCommandType.UNKNOWN);
                }
                mappedStatement.setId(namespace + "." + ele.attributeValue("id"));
                mappedStatement.setSql(ele.getText().trim());

                configuration.addMappedStatement(mappedStatement);

                System.out.println(mappedStatement);
                //这里其实是在MapperRegistry中生产一个mapper对应的代理工厂
                configuration.addMapper(Class.forName(namespace));
            }
        } catch (DocumentException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
