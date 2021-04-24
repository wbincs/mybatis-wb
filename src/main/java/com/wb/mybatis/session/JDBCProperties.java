package com.wb.mybatis.session;

/**
 * @Author wubin
 * @Date 2021/4/2 20:03
 * @Version 1.0
 * 数据库的配置项配置文件的字段。
 */
public class JDBCProperties {

    // 数据库url
    private String url;
    // 数据库驱动
    private String driver;
    // 数据库用户名
    private String username;
    // 数据库密码
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
