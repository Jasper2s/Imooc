package com.javamail.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * 利用单例模式实现JDBC连接数据库
 * @author qiuzhiwen
 *
 */
public class JDBCUtil {
	//连接数据所需参数
	private static String url=null;
	private static String user=null;
	private static String password=null;
	public static Connection conn=null;
	
	//1.声明单例对象
	private static JDBCUtil instance=null;
	//2.私有化构造函数
	private JDBCUtil(){
		
	}
	//3.创建获取单例对象的方法
	public static JDBCUtil getInstance(){
		if(instance==null){
			synchronized (JDBCUtil.class) {//考虑并发情况
				if(instance==null){
					instance=new JDBCUtil();
				}
			}
		}
		return instance;
	}
	
	//通过静态代码块注册数据库驱动，保证注册只执行一次
	static{
		try {
			//加载数据库配置文件
			Properties prop=new Properties();
			/**
			* 使用类路径的读取方式
			* / : 斜杠表示classpath的根目录
			* 在java项目下，classpath的根目录从bin目录开始
			* 在web项目下，classpath的根目录从WEB-INF/classes目录开始
			*/
			prop.load(JDBCUtil.class.getResourceAsStream("/db.properties"));
			//加载数据库驱动
			Class.forName(prop.getProperty("dataSource.driverClass"));
			url=prop.getProperty("dataSource.url");
			user=prop.getProperty("dataSource.user");
			password=prop.getProperty("dataSource.password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取数据库连接对象
	public Connection getConnection(){
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭资源
	public void close(ResultSet rs,Statement st,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(st!=null){
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						if(conn!=null){
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			JDBCUtil jdbcUtil=JDBCUtil.getInstance();
			conn=jdbcUtil.getConnection();
			String sql="select * from regist";
			st=conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs!=null&&rs.next()){
				String username=rs.getString("username");
				String nickname=rs.getString("nickname");
				System.out.println(username+"--"+nickname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
