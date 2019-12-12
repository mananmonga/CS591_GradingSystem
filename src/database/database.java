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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import classSrc.Assignment;
import classSrc.Course;
import classSrc.EnrolledStudent;
import classSrc.Student;
import classSrc.Teacher;




public class database {
	private String url = "jdbc:mysql://127.0.0.1:3306/mySql?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private String username = "root";
	private String password = "root";
	private Connection connect;
	private Statement stmt;
	private ResultSet res;
	private String sql;
	private boolean success;
	private ArrayList<Course> courses;
	private ArrayList<Student> students;
	private ArrayList<Assignment> assignments;
	private Student searchStudent;
	
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
	public boolean addCourse(Course course) {
		try {
			sql = "insert into gradingsystem.Course( CourseName, CourseID, CourseCode, Description, CreateDate ) values ('" +course.getName() + "'," 
					+ "'" + course.getUID() + "'," + "'" + course.getCode() + "'," + "'" + course.getDescription() + "',"
					+ "'" + course.getCreateDate() + "')";
			stmt.executeUpdate(sql);
			success = true;
		}
		catch(Exception e) {
			success = false;
			System.out.println("add course fail");  
            e.printStackTrace();  
		}
		return success;
	}
	
	//delete course in database
	public boolean deletCourse(Course course) {
		System.out.println(course.getUID());
		sql = "delete from gradingsystem.Course where CourseID = "+ "'" + course.getUID() + "'";
		try {
			stmt.executeUpdate(sql);
			success = true;
		}
		catch(Exception e) {
			success = false;
			System.out.println("delete course fail");  
            e.printStackTrace();
		}
		return success;
	}
	
	//update course
	public boolean updateCourse(Course course) {
		sql = "update gradingsystem.Course set CourseName = " + "'" + course.getName() + "'," 
				+ "CourseCode = " + "'" + course.getCode() + "'," 
				+ "Description = " + "'" + course.getDescription() + "'"
				+ "where CourseID = " + "'" + course.getUID() + "'";
		try {
			stmt.execute(sql);
			success = true;
		}
		catch(Exception e) {
			success = false;
			System.out.println("update course fail");  
            e.printStackTrace();
		}
		return success;
	}
	
	//search course in database
	public ArrayList<Course> showCourse() {
		courses = new ArrayList<>();
		sql = "select * from gradingsystem.course";
		try {
			res = stmt.executeQuery(sql);
			while ( res.next()) {
				
				Course c = new Course(res.getString("CourseName"), res.getString("CourseCode"), res.getString("CourseID"), res.getString("Description"), res.getString("CreateDate"));
				c.setAssignments(showTask(c));
				courses.add(c);
			}
		}
		catch(Exception e) {
			System.out.println("search course fail");  
            e.printStackTrace();
		}
		return courses;
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
	public boolean addStudent(ArrayList<Student> student) {	
		try {
			for(int i = 0; i<student.size(); i++) {
				if(studentExist(student.get(i))) {
					sql = "insert into gradingsystem.Student( StudentID,Name ) values ('" + student.get(i).getID()+"',"
					+ "'"+ student.get(i).getName() + "')" ;
					stmt.executeUpdate(sql);	
				}
			}
			success = true;
		}
		catch(Exception e) {
			success = false;
			System.out.println("add student fail");  
	        e.printStackTrace();  
		}
		return success;
	}
	
	//delete student
	public boolean deleteStudent(Student student) {
		sql = "delete from gradingsystem.Student where StudentID = " + "'"+student.getID()+"'";
		try {
			stmt.executeUpdate(sql);
			success = true;
		}
		catch(Exception e) {
			success = false;
			System.out.println("delete student fail");  
            e.printStackTrace();
		}
		return success;
	}
	
	//search student
	public Student searchStudent(Student student) {
		sql = "select * from gradingsystem.Student where StudentID =" + "'"+student.getID() +"'";
		try {
			res = stmt.executeQuery(sql);
			searchStudent = new Student(res.getString("Name"), res.getString("StudentID"));
		}
		catch(Exception e) {
			System.out.println("show student fail");  
            e.printStackTrace();
		}
		return searchStudent;
	}
	
	//show all student
	public ArrayList<Student> showStudent() {
		sql = "select * from gradingsystem.Student";
		try {
			res = stmt.executeQuery(sql);
			while(res.next()) {
				Student student = new Student(res.getString("Name"), res.getString("StudentID"));
				students.add(student);
			}
		}
		catch(Exception e) {
			System.out.println("show student fail");  
            e.printStackTrace();
		}
		return students;
		
	}
	
	//determine whether the student is in the database or not
	public boolean studentExist(Student student) {
		boolean Exist = false;
		searchStudent = searchStudent(student);
		try {
			if(searchStudent != null) {
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
	public boolean addTask(ArrayList<Assignment> assignments, Course course) {
		try {
			for(int i = 0; i<assignments.size(); i++){
				sql = "insert into gradingsystem.Task(CourseID, Name, FullCredit, Weight, Type)"
						+ "values ('"+course.getUID()+"',"+"'"+assignments.get(i).getName()+"',"
						+"'"+assignments.get(i).getFullCredit()+"'," + "'" + assignments.get(i).getWeight() + "'," + "'" 
						+ assignments.get(i).getType() + "'" +")";
				stmt.executeUpdate(sql);
			}
			stmt.executeUpdate(sql);
			success = true;
		}
		catch(Exception e) {
			success = false;
			System.out.println("add task fail");  
            e.printStackTrace();
		}
		return success;
	}
	
	//update task's weight  做到这里！！
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
	
	//update task
	public boolean updateTask(ArrayList<Assignment> assignments, Course course) {
		try {
			StringBuilder set = new StringBuilder("(");
			for(Assignment a : assignments) {
				set.append("'" + a.getID()+"',");
				sql = "select * from gradingsystem.task where CourseID = " + "'" + course.getUID() +
						"' and TaskID = " + "'" + a.getID() + "'";
				res = stmt.executeQuery(sql);
				if(res.next()) {
					sql = "update gradingsystem.Task set Name = " + "'" + a.getName() + "',"
							+ "FullCredit = " + a.getFullCredit() + ","
							+ "Weight = " + a.getWeight() + ","
							+ "type = " + "'" + a.getType() + "',"
							+ "Description = " + "'" + a.getDescription() + "'"
							+ "where CourseID = " + "'" + course.getUID() 
							+ "' and TaskID = " + "'" + a.getID() + "'";
					stmt.executeUpdate(sql);
 				}
				else {
					sql = "insert into gradingsystem.task(CourseID, Name, FullCredit, Weight, type, Description, TaskID)"
							+ "values ('"
							+ course.getUID()+ "',"
							+ "'" + a.getName() + "',"
							+ a.getFullCredit() + ","
							+ a.getWeight() + ","
							+ "'" + a.getType() + "',"
							+ "'" + a.getDescription() + "',"
							+ "'" + a.getID() + "')";
					stmt.executeUpdate(sql);
							
				}
			}
			set.deleteCharAt(set.length() - 1);
			set.append(")");
			System.out.println(set);
			sql = "delete from gradingsystem.task where CourseID = " + "'" + course.getUID() + "'" + " and TaskID not in " + set;
			System.out.println(sql);
			stmt.executeUpdate(sql);
			success = true;
		}
		catch(Exception e) {
			success = false;
			System.out.println("update task fail");  
            e.printStackTrace();
		}
		return success;
	}
	
	//search all tasks for one class
	public ArrayList<Assignment> showTask(Course course) {
		assignments = new ArrayList<>();
		sql = "select * from gradingsystem.Task where CourseID = "+ "'" + course.getUID() + "'";
		try {
			res = stmt.executeQuery(sql);
			while(res.next()) {
				Assignment ass = new Assignment(res.getString("TaskID"), res.getString("type"),res.getString("Name"), res.getString("Description"),
									res.getDouble("FullCredit"), res.getDouble("Weight"));
				assignments.add(ass);
			}
		}
		catch(Exception e) {
			System.out.println("search all tasks fail");
			e.printStackTrace();
		}
		return assignments;
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
