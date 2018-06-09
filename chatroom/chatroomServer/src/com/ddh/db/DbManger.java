package com.ddh.db;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
 
 

public class DbManger {

	 
	    private static final BasicDataSource dataSource=new BasicDataSource();

	    static {
	        Properties pro=new Properties();
	        try {
	            pro.load(DbManger.class.getClassLoader().getResourceAsStream("\\com\\ddh\\db\\jdbc.properties"));
	            String  driverName=pro.getProperty("driverName");
	            String url=pro.getProperty("url");
	            String username=pro.getProperty("username");
	            String password=pro.getProperty("password");
	            dataSource.setDriverClassName(driverName);
	            dataSource.setUrl(url);
	            dataSource.setUsername(username);
	            dataSource.setPassword(password);
	            dataSource.setInitialSize(10);
	            dataSource.setMaxActive(8);
	            dataSource.setMaxIdle(5);
	            dataSource.setMinIdle(1);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	 // 大家通过此方法 获得Connection连接对象
		public static Connection getConnection() throws SQLException {
			return dataSource.getConnection();
		}

	}

 
