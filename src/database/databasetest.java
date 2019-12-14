//package database;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class databasetest {
//	public static void main(String[] args) throws SQLException {
//		database db = new database();
//		//db.addCourse("OOD");
//		//db.deletCourse(2);
//		//db.deletCourse(3);
//		
//		//Test Course
//		System.out.println("All Course");
//
//		System.out.println("\n");
//		
//		//test add section
////		db.addSection(1, 1);
////		db.addSection(2, 1);
////		db.addSection(1, 2);
////		db.addSection(2, 2);
//		System.out.println("All section for one course");
////		ResultSet res = db.searchsection(2);
////		while(res.next()) {
////			String courseid = res.getString("CourseID");
////            String SectionID = res.getString("SectionID");
////            
////            System.out.println("course id:" + courseid +"     "+"Section ID: " + SectionID);
////            
////		}
////		System.out.println("\n");
//		
//		
//		
//		//Test Student
////		db.addStudent("U22", "Hou");
////		db.addStudent("U23", "You");
////		db.addStudent("U24", "Ding");
//////		db.addStudent("U25", "Tan");
////		db.deleteStudent("U21");
////		//查学生test成功
////		System.out.println("Search One Student");
////		res = db.searchStudent("U20");
////		while(res.next()) {
////			String Studentid = res.getString("studentID");
////            String Studentname = res.getString("Name");
////            
////            System.out.println("Student id:" + Studentid +"     "+"Student name: " + Studentname);
////            
////		}
////		System.out.println("\n");
////		
////		//显示所有学生test成功
////		System.out.println("Search All Student");
////		res = db.showStudent();
////		while(res.next()) {
////			String Studentid = res.getString("studentID");
////            String Studentname = res.getString("Name");
////            
////            System.out.println("Student id:" + Studentid +"     "+"Student name: " + Studentname);
////            
////            
////            
////		}
////		System.out.println("\n");
//		
//		//test add task, delete task
//		//db.addTask(2, "homework", "HW3", 100, 30);
//		//db.deleteTask(1, "HW1");
//		
//		System.out.println("Show One Course's All Task");
////		res = db.showTask(1);
//		while(res.next()) {
//            String CourseID = res.getString("CourseID");
//            String Name = res.getString("Name");
//            
//            System.out.println("CourseID: " + CourseID + " "+"Name :" + Name);
//            
//		}
//		System.out.println("\n");
//		
//		//test grade
//		System.out.println("Show one student's grade in one course");
//
////		res = db.studentGrade(2, "U20");
////		while(res.next()) {
////			String Courseid = res.getString("CourseID");
////			String TaskName = res.getString("TaskName");
////			String Studentid = res.getString("studentID");
////            String Score = res.getString("Score");
////            
////            System.out.println("CourseID :" + Courseid + "   " + "TaskName :" + TaskName + "   " + "Student id:" + Studentid +"     "+"Score: " + Score);
////            
////		}
////		System.out.println("\n");
//		
//		System.out.println("Show all grade in one course");
//		res = db.courseGrade(1, 1);
//		while(res.next()) {
//			String StudentName = res.getString("Name");
//			String TaskName = res.getString("TaskName");
//			String Studentid = res.getString("studentID");
//            String Score = res.getString("Score");
//            String CourseID = res.getString("CourseID");
//            String section = res.getString("Section");
//            
//            System.out.println("StudentName :" +StudentName + "   " + "TaskName :" + TaskName + "   " + "Student id:" + Studentid +
//            					"     "+"Score: " + Score + "  Section: " + section + "   CourseID: "+ CourseID);
//            
//		}
//		System.out.println("\n");
//		//student-course test
////		db.deleteStudent1("U20", 1);
//		
//		
////		//test StudentExit function:
////		if(db.studentExist("U19")) {
////			System.out.println("exit");
////		}
////		else {
////			System.out.println("not exit");
////		}
//	}	
//}
