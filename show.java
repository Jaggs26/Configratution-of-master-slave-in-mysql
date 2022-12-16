import java.sql.*;
import java.lang.*;
import java.sql.DriverManager;
public class show {
	public static void main(String[] args) {
		Connection con;
		Statement st;
		ResultSet rs;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://192.168.64.4:3306/java?characterEncoding=utf-8","kumar","Jagadesh@26");
			st=con.createStatement();
			rs=st.executeQuery("select * from list");
			while(rs.next())
			{
				//System.out.println(rs.getInt(1));
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			}
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			//System.out.println(e);
	
			e.printStackTrace();
		}
		
	}

}
