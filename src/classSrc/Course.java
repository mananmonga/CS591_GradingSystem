package classSrc;

import java.util.*;

public class Course {
    private String name;
    private String UID;
    private String Code;
    private String description = "";
    private Date createDate;
    private Curve curve = null;
    private ArrayList<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
    private ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    
    public Course() {
    	this.UID = UUIDGenerator.getUUID();
    	this.Code = "CS12345";
    	this.name = "Empty Course";
    	this.createDate = new Date();
    }

    public Course(String name, String ID) {
        this.name = name;
        this.UID = UUIDGenerator.getUUID();
        this.Code = ID;
        this.createDate = new Date();
    }
    
    public String getCreateDate() {
		return createDate.toString();
	}
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getUID() {
        return UID;
    }
    
    public void setCode(String Code) {
        this.Code = Code;
    }
    
    public String getCode() {
        return this.Code;
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
    
    public boolean hasCurve() {
    	return this.curve != null;
    }
    
    public ArrayList<Assignment> getAssignments(){
    	return this.assignments;
    }
    
    public void setAssignments(ArrayList<Assignment> ass){
    	this.assignments = ass;
    }
    
    public ArrayList<EnrolledStudent> getEnrollStudent(){
    	return this.enrolledStudents;
    }
    
    public void setEnrollStudent(ArrayList<EnrolledStudent> stu){
    	this.enrolledStudents = stu;
    }
    /*    
    public void EnrollStudent(enrolledStudent es) {
    	enrolledStudents.add(es);
    	//TODO: ADD ENROLLEDSTUDENT TO DATABASE
    }
    
    public void RemoveStudent(enrolledStudent es) {
    	for(int i = 0; i < enrolledStudents.size(); i += 1) {
    		if(enrolledStudents.get(i).equals(es)) {
    			enrolledStudents.remove(i);
    		}
    	}
    	//TODO: REMOVE ENROLLED STUDENT FROM DATABASE
    }
    
    public void AddNewAssignment(Assignment a) {
    	assignments.add(a);
    	//TODO: ADD ASSIGNMENT TO DATABASE
    }
    
    public void RemoveAssignment(Assignment a) {
    	for(int i = 0; i < assignments.size(); i += 1) {
    		if(assignments.get(i).equals(a)) {
    			assignments.remove(i);
    		}
    	}
    	//TODO: REMOVE ASSIGNMENT FROM DATABASE
    }
    
    //returns the overall grade for the specified student in this course. When not all assignments have been graded yet, this returns a proportional score based on what has already been graded
    public Double CalcRawOverallGrade(enrolledStudent es) {
    	double totalWeightGraded = 0.0;
    	double totalPercentageScoreGraded = 0.0;
    	for(int i = 0; i < es.grades.size(); i += 1) {
    		totalWeightGraded += es.grades.get(i).assignment.weight;
    		totalPercentageScoreGraded += es.grades.get(i).CalculatePercentageScore(false);
    	}
    	return totalPercentageScoreGraded / totalWeightGraded;
    }
    
    //returns the overall grade for the specified student in this course, taking into account the overall course curve and individual assignment curves
    public Double CalcCurvedOverallGrade(enrolledStudent es) {
    	double totalWeightGraded = 0.0;
    	double totalPercentageScoreGraded = 0.0;
    	for(int i = 0; i < es.grades.size(); i += 1) {
    		totalWeightGraded += es.grades.get(i).assignment.weight;
    		totalPercentageScoreGraded += es.grades.get(i).CalculatePercentageScore(true); //take assignment curve into account
    	}
    	
    	double percentageScoreWithCourseCurve = curve.calculateCurvedScore((totalPercentageScoreGraded / totalWeightGraded) * 100.0, 100.0);
    	
    	return percentageScoreWithCourseCurve;
    }
    
    //ranks the enrolled students from highest to lowest overall course grade
    public enrolledStudent[] RankStudents(){
    	enrolledStudent[] es = (enrolledStudent[])enrolledStudents.toArray();
    	sort(es, 0, es.length -1);
    	return es;
    }
    
    
    //merge sort helper function for ranking students
    void merge(enrolledStudent[] es, int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        // Create temp arrays 
        enrolledStudent[] L = new enrolledStudent [n1]; 
        enrolledStudent[] R = new enrolledStudent [n2]; 
  
        //Copy data to temp arrays
        for (int i=0; i<n1; ++i) 
            L[i] = es[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = es[m + 1+ j]; 
  
  
        // Merge the temp arrays 
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (CalcRawOverallGrade(L[i]) <= CalcRawOverallGrade(R[j])) 
            { 
                es[k] = L[i]; 
                i++; 
            } 
            else
            { 
                es[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        // Copy remaining elements of L[] if any 
        while (i < n1) 
        { 
            es[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        // Copy remaining elements of R[] if any 
        while (j < n2) 
        { 
            es[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    // Main function that sorts arr[l..r] using 
    // merge() 
    void sort(enrolledStudent[] es, int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            sort(es, l, m); 
            sort(es , m+1, r); 
  
            // Merge the sorted halves 
            merge(es, l, m, r); 
        } 
    } 
    */
}
