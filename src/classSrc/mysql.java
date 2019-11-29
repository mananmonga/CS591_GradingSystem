package classSrc;

import java.sql.*;


public class mysql 
{
    public static void main(String[] args) 
    {
        try 
        {  
              Class.forName("com.mysql.jdbc.Driver");     //Load mysql jdbc driver      
             System.out.println("Success loading Mysql Driver!");  
         }  
         catch (Exception e) 
        {  
              System.out.print("Error loading Mysql Driver!");  
              e.printStackTrace();  
        }  
        try 
        {  		//connect the database use url, usernmae and password.
        		String url = "jdbc:mysql://127.0.0.1:3306/mySql?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        		String username = "root";
        		String password = "root";
               	System.out.println("start connecting");
                Connection connect = DriverManager.getConnection(url,username,password);  
                    
          
               System.out.println("Success connect Mysql server!");  
               /* executeQuery(String sql) send search SQL statements to the database
                * executeUpdate(String sql) insert/update/delete statements to the database
                * execute(String sql) any statement is ok
                * */
               
               //The Statement object in the Jdbc program is used to send SQL statements to the database
               Statement stmt = connect.createStatement();  
               String insertsql = "insert into gradingsystem.course( CourseName ) values ('Algorithm')";
               //String deletesql = "delete from gradingsystem.course where CourseID=3";
               stmt.executeUpdate(insertsql);
               
               //Resultset is the search result from the database
               ResultSet rs = stmt.executeQuery("select * from gradingsystem.course");  
                                                                      //user 为你表的名称  
               
               
               while (rs.next()) 
               {  
                   
                   String courseid = rs.getString("CourseID");
                   String coursename = rs.getString("CourseName");
                  
                   
                   System.out.println("course id:" + courseid +"     "+"course name: " + coursename);
               }   
               rs.close(); 
               connect.close(); // is necessary to close the connect and result set  
         }  
         catch (Exception e) 
         {  
              System.out.print("get data error!");  
              e.printStackTrace();  
          }  
    }  
}