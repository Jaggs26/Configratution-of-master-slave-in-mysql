import java.sql.*;
import java.sql.DriverManager;
import java.util.*;  

class Delete{
	public static void main(String args[]){
		try{
            Class.forName("com.mysql.jdbc.Driver");
            String dburl="jdbc:mysql://192.168.64.4:3306/java?characterEncoding=utf-8";
            String username = "kumar";
            String password = "Jagadesh@26";

            Connection myCon = DriverManager.getConnection(dburl,username,password);
            if(myCon==null){

                System.out.println("cant connect");
            }
            else{
                Scanner sc = new Scanner(System.in);

                System.out.println("Enter id to delete:");
               
                int id = sc.nextInt();

                
                    String query = "delete from list where item_id=?";
                    PreparedStatement prepstatment = myCon.prepareStatement(query);
                    
                    prepstatment.setInt(1,id);
                    prepstatment.executeUpdate();
                
               
                

                myCon.close();  
            }

		}
        catch(Exception e){
            System.out.println(e);
        }
       
	}
}
