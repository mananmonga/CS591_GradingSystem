package classSrc;

import java.util.ArrayList;

public class Teacher {
    private ArrayList<Course> courses;
    private ArrayList<AssignmentTemplate> templates;
    private ArrayList<enrolledStudent> enrolledStudents;


    public Teacher() {
    }

    public Teacher(ArrayList<Course> courses, ArrayList<AssignmentTemplate> templates, ArrayList<enrolledStudent> enrolledStudents) {
        this.courses = courses;
        this.templates = templates;
        this.enrolledStudents = enrolledStudents;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<AssignmentTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(ArrayList<AssignmentTemplate> templates) {
        this.templates = templates;
    }

    public ArrayList<enrolledStudent> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(ArrayList<enrolledStudent> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
}
