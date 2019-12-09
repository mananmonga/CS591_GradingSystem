package classSrc;

import java.util.ArrayList;

public class Teacher extends Person{
	
    private ArrayList<Course> courses;
    private ArrayList<CourseTemplate> templates;


    public Teacher(String username) {
    	super("Teacher", username); //teacher's ID is the username they signup/login with
    }

    public Teacher(String username, ArrayList<Course> courses, ArrayList<CourseTemplate> templates) {
    	super("Teacher", username);
        this.courses = courses;
        this.templates = templates;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<CourseTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(ArrayList<CourseTemplate> templates) {
        this.templates = templates;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getID() {
    	return this.ID;
    }
    
    public void AddNewCourse(Course c) {
    	courses.add(c);
    	//TODO: ADD NEW COURSE TO DATABASE
    }
    
    public void RemoveCourse(Course c) {
    	for(int i = 0; i < courses.size(); i += 1) {
    		if(courses.get(i).equals(c)) {
    			courses.remove(i);
    		}
    	}
    	//TODO: REMOVE COURSE FROM DATABASE
    }
    
    public void AddCourseTemplate(CourseTemplate template) {
    	templates.add(template);
    	//TODO: ADD NEW TEMPLATE TO DATABASE
    }
}
