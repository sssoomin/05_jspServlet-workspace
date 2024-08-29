package com.br.web.common.template;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	

		public static Connection getConnection() {
			
			Properties prop = new Properties();
			
			// 읽어들이고자 하는 파일 : driver.properties (src/main/java 안 x, src/main/webapp/WEB-INF/classes 안 o)
			// 
			
			try {
				prop.load(new FileInputStream("")); 
			}catch(IOException e){
				e.printStackTrace();
			}
			
			String driver = prop.getProperty("driver"); 
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			
			
			Connection conn = null;
			try {
				Class.forName(driver); 
				conn = DriverManager.getConnection(url, username, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return conn;
		}
		
		
		public static void close(ResultSet rset) {
			try {
				if(rset != null && !rset.isClosed()) { 
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		public static void close(Statement stmt) {
			try {
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		public static void close(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		public static void commit(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		public static void rollback(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	

}
