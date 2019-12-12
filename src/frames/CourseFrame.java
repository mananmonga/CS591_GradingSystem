package frames;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import classSrc.*;
public class CourseFrame extends JFrame implements ActionListener
{
	JPanel introPage = new JPanel(new BorderLayout());
	StudentPage studentPage;
	AssignmentPage assignmentPage;
	StudentStatsPage studentStatsPage;
	CourseStatsPage courseStatsPage;
	GradingPage gradingPage;
	CurvePage curvePage;
	JMenuItem grading, assignment, student, curve, studentInfo, courseInfo;
	JMenuBar menuBar;
	JMenu main, setting, view;
	JPanel cardpanel = new JPanel();
	CardLayout cardLayout = new CardLayout();
	String frameTitle;
	
	Course course;
	
	public CourseFrame(Course course_) {
		this.course = course_;
		this.frameTitle = course.getCode() + " " + course_.getName();
		this.gradingPage = new GradingPage(course, this);
		this.assignmentPage = new AssignmentPage(course);
		this.studentPage = new StudentPage(course);
		this.studentStatsPage = new StudentStatsPage(course,null);
		this.curvePage = new CurvePage(course);
		this.courseStatsPage = new CourseStatsPage(course);
		this.assignmentPage.addListener("CurvePage",this.curvePage);
		this.assignmentPage.addListener("GradingPage",this.gradingPage);
		this.studentPage.addListener("GradingPage",this.gradingPage);
		initFrame();
		initMenubar();
		arrangePanel();
		course = course_;
	} 
	
	public void initMenubar() {
		setting = new JMenu("Setting");
        view = new JMenu("View");
        main = new JMenu("Main");
        
        grading = new JMenuItem("Grading");
        grading.addActionListener(this);
        main.add(grading);
        
        assignment = new JMenuItem("Assignment");  
        assignment.addActionListener(this);
        student = new JMenuItem("Student"); 
        student.addActionListener(this);
        curve = new JMenuItem("Curve"); 
        curve.addActionListener(this);
        setting.add(assignment);
        setting.add(student);
        setting.add(curve);
        
        studentInfo = new JMenuItem("Student Statistics");
        courseInfo = new JMenuItem("Course Statistics");
        studentInfo.addActionListener(this);
        courseInfo.addActionListener(this);
        view.add(studentInfo);
        view.add(courseInfo);
        
        menuBar = new JMenuBar();
        menuBar.add(main);  
        menuBar.add(setting); 
        menuBar.add(view); 
        this.setJMenuBar(menuBar);
	}
	
	public void arrangePanel() {
		cardpanel.setLayout(cardLayout);
		this.add(cardpanel,BorderLayout.CENTER);
		cardpanel.add("introPage",introPage);	
        cardpanel.add("studentPage",studentPage);
        cardpanel.add("assignmentPage",assignmentPage);
        cardpanel.add("curvePage",curvePage);
        cardpanel.add("studentStatsPage",studentStatsPage);  
        cardpanel.add("courseStatsPage",courseStatsPage);  
        cardpanel.add("gradingPage",gradingPage);
        setIntroPage();
	}
	
	public void initFrame() {
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setTitle(frameTitle);
		setBounds(80, 10, 900, 650); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
	}
	
	public void setIntroPage() {
		JLabel label = new JLabel("Thank you for using Fancy Grading System!", SwingConstants.CENTER);
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		introPage.add(label, BorderLayout.CENTER);
	}
	
	public void viewStudentStatsPage(EnrolledStudent student) {
		this.studentStatsPage.refreshStudentStatsList(student);
		cardLayout.show(cardpanel,"studentStatsPage");
	};

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==grading){
			cardLayout.show(cardpanel,"gradingPage");
        }
		else if (e.getSource()==student){
			cardLayout.show(cardpanel,"studentPage");
        }
		else if (e.getSource()==assignment){
			cardLayout.show(cardpanel,"assignmentPage");
        }
		else if (e.getSource()==curve){
			cardLayout.show(cardpanel,"curvePage");
        }
		else if (e.getSource()==studentInfo){
			this.studentStatsPage.refreshStudentStatsList(null);
			cardLayout.show(cardpanel,"studentStatsPage");
        }
		else if (e.getSource()==courseInfo){
			cardLayout.show(cardpanel,"courseStatsPage");
        }
	}
}
