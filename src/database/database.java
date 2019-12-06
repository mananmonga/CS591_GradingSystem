/**
 * @author Tian
 * @version 1.0.0
 * @ClassName database.jave
 * @Description connect database and database API
 * @Param
 * @updateTime 2019.12.1
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;




public class database {
	private String url = "jdbc:mysql://127.0.0.1:3306/mySql?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private String username = "root";
	private String password = "root";
	private Connection connect;
	private Statement stmt;
	private ResultSet res;
	private String sql;
	
	//connect the database
	public database() {
		try 
        {  
             Class.forName("com.mysql.jdbc.Driver");     //Load mysql jdbc driver
             connect = DriverManager.getConnection(this.url,this.username,this.password);
             stmt = connect.createStatement();
             System.out.println("Success loading Mysql Driver!");  
         }  
         catch (Exception e) 
        {  
              System.out.println("Error loading Mysql Driver!");  
              e.printStackTrace();  
        }  
	}
	
	//close the database
	public void close() {
		try {
			connect.close();
			stmt.close();
			System.out.println("close success");
		}
		catch(Exception e) {
			System.out.println("Error when close the database!");  
            e.printStackTrace();  
		}
	}
	
	/*Course operation*/
	
	//add course in database
	public void addCourse(String Name) {
		sql = "insert into gradingsystem.Course( CourseName ) values ('" + Name + "')" ;
		try {
			stmt.executeUpdate(sql);
			connect.close();
			stmt.close();
		}
		catch(Exception e) {
			System.out.println("add course fail");  
            e.printStackTrace();  
		}
	}
	
	//delete course in database
	public void deletCourse(int ID) {
		sql = "delete from gradingsystem.Course where CourseID = "+ID;
		try {
			stmt.executeUpdate(sql);
			connect.close();
			stmt.close();
		}
		catch(Exception e) {
			System.out.println("delete course fail");  
            e.printStackTrace();
		}
	}
	
	//search course in database
	public ResultSet showCourse() {
		sql = "select * from gradingsystem.course";
		try {
			res = stmt.executeQuery(sql);
		}
		catch(Exception e) {
			System.out.println("search course fail");  
            e.printStackTrace();
		}
		return res;
	}
	
	//add section for one course
	public void addSection(int SectionID, int CourseID) {
		sql = "insert into gradingsystem.Section( SectionID, CourseID) values ('" + SectionID+ "'," + "'" + CourseID +"')" ;
		try {
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			System.out.println("add section fail");  
            e.printStackTrace();
		}
	}
	
	//search all section for one course
	public ResultSet searchsection(int CourseID) {
		sql = "select * from gradingsystem.Section where CourseID = " + CourseID;
		try {
			res = stmt.executeQuery(sql);
		}
		catch(Exception e) {
			System.out.println("search sections for one course fail");  
            e.printStackTrace();
		}
		return res;
	}
	
	/*Student operation:*/
	
	//add student
	public void addStudent(String ID, String Name) {
		if(!studentExist(ID)) {
			sql = "insert into gradingsystem.Student( StudentID,Name ) values ('" + ID+"',"+ "'"+ Name + "')" ;
			try {
				int a = stmt.executeUpdate(sql);
				if(a == 1) {
					System.out.println("add student success");
				}
			}
			catch(Exception e) {
				System.out.println("add student fail");  
	            e.printStackTrace();  
			}
		}
	}
	
	//delete student
	public void deleteStudent(String ID) {
		sql = "delete from gradingsystem.Student where StudentID = " + "'"+ID+"'";
		try {
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			System.out.println("delete student fail");  
            e.printStackTrace();
		}
	}
	
	//search student
	public ResultSet searchStudent(String ID) {
		sql = "select * from gradingsystem.Student where StudentID =" + "'"+ID +"'";
		try {
			res = stmt.executeQuery(sql);
		}
		catch(Exception e) {
			System.out.println("show student fail");  
            e.printStackTrace();
		}
		return res;
	}
	
	//show all student
	public ResultSet showStudent() {
		sql = "select * from gradingsystem.Student";
		try {
			res = stmt.executeQuery(sql);
		}
		catch(Exception e) {
			System.out.println("show student fail");  
            e.printStackTrace();
		}
		return res;
		
	}
	
	//determine whether the student is in the database or not
	public boolean studentExist(String ID) {
		boolean Exist = false;
		res = searchStudent(ID);
		try {
			if(res.next()) {
				Exist = true;
			}
		}
		catch(Exception e) {
			System.out.println("determine student exit fail");  
            e.printStackTrace();
		}
		return Exist;
	}
	
	/* task operation */
	
	//add task
	public void addTask(int CourseID, String type, String name, int credits, int weight) {
		sql = "insert into gradingsystem.Task(CourseID, Name, FullCredit, Weight, Type)"
				+ "values ('"+CourseID+"',"+"'"+name+"',"+"'"+credits+"'," + "'" + weight + "'," + "'" + type + "'" +")";
		try {
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			System.out.println("add task fail");  
            e.printStackTrace();
		}
	}
	
	//update task's weight
	public void updateWeight(int CourseID, String name, int weight) {
		sql = "update gradingsystem.Task set Weight = " + weight + " where CourseID =" + CourseID + " and Name = " + "'" + name + "'";
		try {
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			System.out.println("update task fail");  
            e.printStackTrace();
		}
	}
	
	//update task's full credits
	public void updateCredit(int CourseID, String name, int credit) {
		sql = "update gradingsystem.Task set FullCredit = " + credit + " where CourseID =" + CourseID + " and Name = " + "'" + name + "'";
		try {
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			System.out.println("update task fail");  
            e.printStackTrace();
		}
	}
	
	//delete task 
	public void deleteTask(int CourseID, String Name) {
		sql = "delete from gradingsystem.Task where CourseID = "+ CourseID + " and Name = "+ "'" + Name +"'";
		try {
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			System.out.println("delete task fail");
			e.printStackTrace();
		}
	}
	
	//search all tasks for one class
	public ResultSet showTask(int CourseID) {
		sql = "select * from gradingsystem.Task where CourseID = "+CourseID;
		try {
			res = stmt.executeQuery(sql);
		}
		catch(Exception e) {
			System.out.println("search all tasks fail");
			e.printStackTrace();
		}
		return res;
	}
	
	/*grade operation*/
	
	//add grade
	public void addGrade(int CourseID, String TaskName, String StudentID, int score) {
		sql = "insert into gradingsystem.Grade(CourseID, TaskName, StudentID, Score)"
				+ "values ('" + CourseID + "'," + "'" + TaskName + "'," + "'" + StudentID + "'," + "'" + score + "')";
		try {
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			System.out.println("all grades fail");
			e.printStackTrace();
		}
	}
	
	//update grade
	public void updateGrade(int CourseID, String TaskName, String StudentID, int score) {
		sql = "update gradingsystem.Grade set Score =" + score + " where "
				+ "CourseID = " + CourseID + " and TaskName = " + "'" + TaskName + "'" + " and StudentID = " + "'" + StudentID + "'";
		try {
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			System.out.println("upgrade grade fail");
			e.printStackTrace();
		}
		
	}
	
	//delete grade
	public void deleteGrade(int CourseID, String TaskName, String StudentID) {
		sql = "delete from gradingsystem.Grade where "
				+ "CourseID = " + CourseID + " and TaskName = " + "'" + TaskName + "'" + " and StudentID = " + "'" + StudentID + "'";
		try {
			stmt.executeUpdate(sql);
		}
		catch(Exception e) {
			System.out.println("delete grade fail");
			e.printStackTrace();
		}
	}
	
	//search one student's grades in one Course
	public ResultSet studentGrade(int CourseID, String StudentID) {
		sql = "select * from gradingsystem.Grade where CourseID = " + CourseID + " and StudentID = " + "'" + StudentID + "'";
		try {
			res = stmt.executeQuery(sql);
		}
		catch(Exception e) {
			System.out.println("delete grade fail");
			e.printStackTrace();
		}
		return res;
	}
	
	//search all students' grades in one Course in one section
	public ResultSet courseGrade(int CourseID, int section) {
		sql = "select * from gradingsystem.Grade, gradingsystem.StudentCourse, gradingsystem.Student where " +
				"gradingsystem.Grade.StudentID = gradingsystem.StudentCourse.StudentID and gradingsystem.Grade.CourseID = gradingsystem.StudentCourse.CourseID and gradingsystem.Grade.StudentID = gradingsystem.Student.StudentID and " +
				"gradingsystem.Grade.CourseID = " + CourseID + " and Section = " + section;
		try {
			res = stmt.executeQuery(sql);
		}
		catch(Exception e) {
			System.out.println("search all grade fail");
			e.printStackTrace();
		}
		return res;
		
	}
	
	/*Student-Course tabel Operation*/
	
	//add a student in a course (need a int section)
	public void addStudent1(String studentID, int CourseID, int section) {
		sql = "insert into gradingsystem.StudentCourse (StudentID, CourseID, Section) " +
				"values ('" + studentID + "'," + "'"+ CourseID + "'," + "'"+ section + "')";
		try {
			stmt.execute(sql);
		}
		catch(Exception e) {
			System.out.println("insert student in one course fail");
			e.printStackTrace();
		}
	}
	
	//delete a student in a course
	public void deleteStudent1(String studentID, int CourseID) {
		sql = "delete from gradingsystem.StudentCourse where StudentID = " + "'" + studentID + "'" + " and CourseID = " + CourseID;
		try {
			stmt.execute(sql);
		}
		catch(Exception e) {
			System.out.println("delete student in one course fail");
			e.printStackTrace();
		}
	}
	

	
	
	
	
	
	
	
	
		
				
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
}
