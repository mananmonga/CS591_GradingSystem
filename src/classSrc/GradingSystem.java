package classSrc;

import java.util.ArrayList;
import database.database;

public class GradingSystem
{	
	public ArrayList<Course> courses = new ArrayList<Course>();
	private database db;
	
	private static class GradingSystemHolder {
		private static GradingSystem instance = new GradingSystem();
	}
	
	public static GradingSystem getInstance() {    //use singleton module to get the GradingSystem instance
		return GradingSystemHolder.instance;
	}
	
	private GradingSystem() {
		//initBank();  //initialize bank with fixed data
		db = new database();
		courses = db.showCourse();
	}
	
	public boolean LoginCheck(String Uid, String password) {
        return true;
    }
}
