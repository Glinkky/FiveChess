package util;


import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DBUtil {
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;
    private static DruidDataSource druidDataSource = new DruidDataSource();
    private Connection connection;
    private PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    private int count;
    static {
        InputStream resourceAsStream = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driverClass = properties.getProperty("driverclass");
        url = properties.getProperty("url");
        username = properties.getProperty("userName");
        password = properties.getProperty("password");
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
    }

    protected Connection getConnection(){
        try {
            connection = druidDataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    protected PreparedStatement getStatement(String sql){
        try {
           preparedStatement = getConnection().prepareStatement(sql);
        } catch (SQLException throwables) {
        }
        return preparedStatement;
    }

    protected void getParam(List list){
        if(list!=null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                try {
                    preparedStatement.setObject(i+1,list.get(i));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    protected int update(String sql, List list){
        getStatement(sql);
        getParam(list);
        try {
            count = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    protected ResultSet query(String sql, List list){
        getStatement(sql);
        getParam(list);
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    protected void closeAll(){
        try {
            if (connection!=null){
                connection.close();
            }
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
