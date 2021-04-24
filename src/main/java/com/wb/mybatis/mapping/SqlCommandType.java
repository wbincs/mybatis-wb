package com.wb.mybatis.mapping;

/**
 * @Author wubin
 * @Date 2021/4/23 18:25
 * @Version 1.0
 * mapper的每条语句的类型
 */
public enum SqlCommandType {
    UNKNOWN("unknown"),
    INSERT("insert"),
    UPDATE("update"),
    DELETE("delete"),
    SELECT("select");

    private String value;

    private SqlCommandType(String value)
    {
        this.value = value;
    }

    public String value()
    {
        return this.value;
    }
}
