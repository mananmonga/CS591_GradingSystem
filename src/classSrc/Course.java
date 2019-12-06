package classSrc;

import java.util.ArrayList;

public class Course {
    private String name;
    private String ID;
    private String description;
    private Curve curve;
    ArrayList<enrolledStudent> rankStudents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Curve getCurve() {
        return curve;
    }

    public void setCurve(Curve curve) {
        this.curve = curve;
    }

    public Course(String name, String ID, String description) {
        this.name = name;
        this.ID = ID;
        this.description = description;
    }
}
