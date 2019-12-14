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
import classSrc.Grade;
import classSrc.Student;
import classSrc.Teacher;




public class database {
	private String url = "jdbc:mysql://127.0.0.1:3306/mySql?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private String username = "root";
	private String password = "root";
	private Connection connect;
	private Statement stmt;
	private Statement stmt1;
	private Statement stmt2;
	private Statement stmt3;
	private ResultSet res;
	private ResultSet res1;
	private ResultSet res2;
	private ResultSet res3;
	private String sql;
	private boolean success;
	private ArrayList<Course> courses;
	private ArrayList<EnrolledStudent> students;
	private ArrayList<Assignment> assignments;
	
	//connect the database
	public database() {
		try 
        {  
             Class.forName("com.mysql.jdbc.Driver");     //Load mysql jdbc driver
             connect = DriverManager.getConnection(this.url,this.username,this.password);
             stmt = connect.createStatement();
             stmt1 = connect.createStatement();
             stmt2 = connect.createStatement();
             stmt3 = connect.createStatement();
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
			sql = "insert into gradingsystem.Course( CourseName, CourseID, CourseCode, Description, CreateDate, Curve, CurveValue ) values ('" +course.getName() + "'," 
					+ "'" + course.getUID() + "'," + "'" + course.getCode() + "'," + "'" + course.getDescription() + "',"
					+ "'" + course.getCreateDate() + "'," + "'" + course.getCurve().getCurveString() + "'," + course.getCurve().getAmount() + ")";
			System.out.println(sql);
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
				Course c = new Course(res.getString("CourseName"), res.getString("CourseCode"), res.getString("CourseID"), res.getString("Description"), res.getString("CreateDate"), res.getString("Curve"), res.getDouble("CurveValue"));
				c.setAssignments(showTask(c));
				c.setEnrollStudent(showStudent(c));
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
	
	//update student
	public boolean updateStudent(ArrayList<EnrolledStudent> enrolledStudents, Course course) {
		System.out.println(enrolledStudents.size());
		try {
			StringBuilder set = new StringBuilder("(");
			for(EnrolledStudent s : enrolledStudents) {
				set.append("'" + s.getID()+"',");
				if(!studentExist(s)) {
					sql = "insert into gradingsystem.Student(StudentID, Name) values ('" + s.getID() + "'," + "'"+ s.getName() + "')";
					stmt.executeUpdate(sql);
					sql = "insert into gradingsystem.StudentCourse(StudentID, CourseID, Section, Bonus, Comment) values ('" + s.getID() + "'," + "'" 
							+ course.getUID() + "'," + 1 + "," + s.getBonus() + ",'" + s.getComment() + "')";
					stmt.executeUpdate(sql);
				}
				else {
					sql = "select * from gradingsystem.StudentCourse where CourseID = " + "'" + course.getUID() + "'"
					+ "and StudentID = " + "'" + s.getID() + "'";
					res = stmt.executeQuery(sql);
					if(res.next()) {
						sql = "update gradingsystem.Student set Name = " + "'" + s.getName() + "',"
								+ "StudentID = " + "'" + s.getID() + "'"
								+ "where StudentID = " + "'" + s.getID() + "'";
						stmt.executeUpdate(sql);
					}
					else {
						sql = "insert into gradingsystem.StudentCourse(StudentID, CourseID, Section, Bonus, Comment) values ('" + s.getID() + "'," + "'" 
								+ course.getUID() + "'," + 1 + "," + s.getBonus() + ",'" + s.getComment() + "')";
						System.out.println(sql);
						stmt.executeUpdate(sql);
					}
				}
			}
			System.out.println("this is set" + set);
			set.deleteCharAt(set.length() - 1);
			set.append(")");
			System.out.println(set);
			sql = "delete from gradingsystem.StudentCourse where CourseID = " + "'" + course.getUID() + "'" + " and StudentID not in " + set;
			System.out.println(sql);
			stmt.executeUpdate(sql);
			success = true;
		}
		catch(Exception e) {
			success = false;
			System.out.println("update student fail");  
            e.printStackTrace();
		}
		return success;
	}

	
	//search student
	public Student searchStudent(Student student) {
		sql = "select * from gradingsystem.Student where StudentID =" + "'"+student.getID() +"'";
		Student searchStudent = null;
		try {
			res = stmt.executeQuery(sql);
			while(res.next()) {
				searchStudent = new Student(res.getString("Name"), res.getString("StudentID"));
				break;
			}
		}
		catch(Exception e) {
			System.out.println("show student fail");  
            e.printStackTrace();
		}
		return searchStudent;
	}
	
	//show all student in one course
	public ArrayList<EnrolledStudent> showStudent(Course course) {
		students = new ArrayList<EnrolledStudent>();
		sql = "select * from gradingsystem.StudentCourse, gradingsystem.Student where CourseID = " + "'" + course.getUID() 
			+ "' and gradingsystem.Student.StudentID = gradingsystem.StudentCourse.StudentID";
		try {
			res2 = stmt2.executeQuery(sql);
			while( res2.next()) {
				EnrolledStudent student = new EnrolledStudent(res2.getString("Name"), res2.getString("StudentID"), null, res2.getDouble("Bonus"), res2.getString("Comment"));
				students.add(student);
			}
			for(EnrolledStudent s : students) {
				ArrayList<Grade> grades = new ArrayList<Grade>();
				for(Assignment a : course.getAssignments()) {
					grades.add(studentGrade(course.getUID(),s.getID(),a.getID(),a));
				}
				s.setGrades(grades);
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
		Student searchStudent = searchStudent(student);
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
			res1 = stmt1.executeQuery(sql);
			while(res1.next()) {
				Assignment ass = new Assignment(res1.getString("TaskID"), res1.getString("type"),res1.getString("Name"), res1.getString("Description"),
									res1.getDouble("FullCredit"), res1.getDouble("Weight"), res1.getString("Curve"), res1.getDouble("CurveValue"));
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
	public void updateGrade(Course course) {
		try {
			sql = "delete from gradingsystem.Grade where CourseId = " + "'" + course.getUID() + "'";
			stmt.executeUpdate(sql);
			for(EnrolledStudent s :course.getEnrollStudent()) {
				for(Grade g : s.getGrades()) {
					sql = "insert into gradingsystem.Grade (CourseId, TaskID, StudentID, Score, Comment, GradeID)"
							+ "values ('"+ course.getUID() + "'," + "'" + g.getAssignment().getID() + "',"
							+ "'" + s.getID() + "'," + g.getCredit() + ",'" + g.getComment() + "'," + "'" + g.getID() + "')";
					stmt.executeUpdate(sql);
				}
			}
		}
		catch(Exception e) {
			System.out.println("upgrade grade fail");
			e.printStackTrace();
		}
		
	}	
	//search one student's grades in one Course
	public Grade studentGrade(String CourseID, String StudentID, String TaskID, Assignment a) {
		sql = "select * from gradingsystem.Grade where CourseID = " + "'" +CourseID + "'" 
				+ " and StudentID = " + "'" + StudentID + "'"
				+ " and TaskID = " + "'" + TaskID + "'";
		
		Grade grade = null;
		try {
			res3 = stmt3.executeQuery(sql);
			while(res3.next()) {
				grade = new Grade(res3.getString("GradeID"),a, res3.getDouble("Score"), res3.getString("Comment"));
				break;
			}
			return grade;
		}
		catch(Exception e) {
			System.out.println("delete grade fail");
			e.printStackTrace();
		}
		return grade;
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
	
	public boolean updateStudentComments(EnrolledStudent student, Course course) {
		try {
			sql = "update gradingsystem.StudentCourse set Comment =" + "'" + student.getComment() + "'" + " where "
					    + "CourseID = " + "'" + course.getUID() + "'" + " and StudentID = " + "'" + student.getID() + "'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			for(Grade g :student.getGrades()) {
				sql = "update gradingsystem.Grade set Comment =" + "'" + g.getComment() + "'"+ " where "
				     + "CourseID = " + "'" + course.getUID() + "'" + " and StudentID = " + "'" + student.getID() + "'"
				     + "and TaskId = " + "'" +g.getAssignment().getID() + "'";
				System.out.println(sql);
				stmt.executeUpdate(sql);
			}
			success = true;
		}
		catch(Exception e) {
			success = false;
			System.out.println("update comment fail");  
		    e.printStackTrace();
		}
		return success;
	}
	
	public boolean updateCurve(Course course) {
		try {
			sql = "update gradingsystem.Course set Curve =" + "'" + course.getCurve().getCurveString() + "'," 
					+ "CurveValue = " + course.getCurve().getAmount()	
					+ " where "  + "CourseID = " + "'" + course.getUID() + "'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			for(Assignment a :course.getAssignments()) {
				sql = "update gradingsystem.task set Curve =" + "'" + a.getCurve().getCurveString() + "',"
					+ "CurveValue = " + a.getCurve().getAmount()
					+ " where "  + "CourseID = " + "'" + course.getUID() + "'"
				    + "and TaskId = " + "'" +a.getID() + "'";
				System.out.println(sql);
				stmt.executeUpdate(sql);
			}
			success = true;
		}
		catch(Exception e) {
			success = false;
			System.out.println("update curve fail");  
		    e.printStackTrace();
		}
		return success;
	}

	
	
	
	
	
	
	
	
		
				
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
}
