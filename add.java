import java.sql.*;
import java.sql.DriverManager;
import java.util.*;  

class Add{
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
                System.out.print("Enter student name: ");  
                String name= sc.nextLine(); 
                System.out.print("Enter student id: ");  
                String student_id= sc.nextLine();
                System.out.print("Enter student branch: ");  
                String branch= sc.nextLine();

                String query = "insert into list(name,student_id,branch)"+"values(?,?,?)";
                PreparedStatement prepstatment = myCon.prepareStatement(query);
                prepstatment.setString(1,name);
                prepstatment.setString(2,student_id);
                prepstatment.setString(3,branch);

                prepstatment.executeUpdate();

                myCon.close();  
            }

		}
        catch(Exception e){
            System.out.println(e);
        }
       
	}
}
