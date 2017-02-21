package Information;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import Access.FormLogin;

public class DbConnect {
	static Connection con = null;
	public Connection getCon(){
		Properties properties = new Properties();
		try{
			properties.load(this.getClass().getResourceAsStream("/db.properties"));
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			try{
				Class.forName(driver);
			}catch(ClassNotFoundException e){
					e.printStackTrace();
			}
			try{
				con = DriverManager.getConnection(url,user,password);
				System.out.println("连接成功");
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return con;
	}
	public ResultSet querySql(String sql) throws SQLException{
		Statement SQL;
		ResultSet rs;
		SQL = con.createStatement();
		rs = SQL.executeQuery(sql);
		return rs;
	}
	public void Insert(String sql)throws SQLException{
		Statement SQL;
		SQL = con.createStatement();
		SQL.execute(sql);
	}
	public void Save(String account)throws SQLException{
		Statement SQL;
		SQL = con.createStatement();
		String sql = "delete from Member where account = '"+account+"'";
		SQL.execute(sql);
		sql = "insert into Member values('"+FormLogin.member.getAccount()+"','"+FormLogin.member.getPassword()+"','"+FormLogin.member.getIcon()+"','"+FormLogin.member.getName()+"','"+FormLogin.member.getScore()+"','"+FormLogin.member.getSex()+"','"+FormLogin.member.getMyNative()+"')";
		SQL.execute(sql);
	}
	
	public void close() throws SQLException{
		if(!con.isClosed()){
			con.close();
		}
	}
}
