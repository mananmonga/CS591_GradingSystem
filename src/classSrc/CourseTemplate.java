package classSrc;

//attributes of a course that can be saved and reused for future course configurations
public class CourseTemplate {
	
	public Assignment[] assignments;
	
	public CourseTemplate(Assignment[] as){
		assignments = as;
	}

}
